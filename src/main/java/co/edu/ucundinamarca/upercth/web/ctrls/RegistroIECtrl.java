package co.edu.ucundinamarca.upercth.web.ctrls;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.ucundinamarca.upercth.model.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.upercth.model.dao.PermisoDAO;
import co.edu.ucundinamarca.upercth.model.dao.RecursoDAO;
import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.dao.ReservaDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.dao.VehiculoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Recurso;
import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;
import co.edu.ucundinamarca.upercth.model.entities.Vehiculo;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;
import co.edu.ucundinamarca.upercth.util.Reconocimiento;

@Controller
public class RegistroIECtrl {

	@Autowired
	private RegistroIEDAO registroierepo;

	@Autowired
	private RecursoDAO recursorepo;

	@Autowired
	private VehiculoDAO vehiculorepo;

	@Autowired
	private UsuarioDAO usuariorepo;

	@Autowired
	private PermisoDAO permisorepo;

	@Autowired
	private ReservaDAO reservasrepo;

	@Autowired
	private EspacioParqueoDAO espaciorepo;

	private HelpersUperc helpersUperc = new HelpersUperc();

	@GetMapping("modulos/registrosie.html")
	public String getRegistroIE(Model model, Authentication auth) {
		
		

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		Map<Long, String> reservasActivas = new HashMap<Long, String>();
		List<RegistroIE> registrosactivos = registroierepo.getRegistrosI();
		List<Vehiculo> vehiculosDentro = new ArrayList<Vehiculo>();
		List<Vehiculo> vehiculosFuera = vehiculorepo.selectAll();

		for (RegistroIE registroIE : registrosactivos) {
			vehiculosDentro.add(registroIE.getVehiculo());

			Reserva activa = reservasrepo.getReservedByUser(registroIE.getUsuarioIngreso().getId());
			if (activa != null)
				reservasActivas.put(registroIE.getUsuarioIngreso().getId(), activa.getEspacioParqueo().getNombre());
		}

		for (Vehiculo vehiculo : vehiculosDentro) {
			Predicate<Vehiculo> conVehDentro = item -> item.getPlaca().equals(vehiculo.getPlaca());
			vehiculosFuera.stream().filter(conVehDentro).forEach(item -> item.getPlaca());
			vehiculosFuera.removeIf(conVehDentro);
		}


		model.addAttribute("reservasActivas", reservasActivas);

		model.addAttribute("registrosIngresos", registroierepo.getRegistrosI());
		model.addAttribute("registrosEgresos", registroierepo.getRegistrosE());

		model.addAttribute("nuevoRegistroI", new RegistroIE());
		model.addAttribute("registroEgreso", new RegistroIE());
		model.addAttribute("recurssos", recursorepo.selectAll());

		model.addAttribute("vehiculosDentro", vehiculosDentro);
		model.addAttribute("vehiculosFuera", vehiculosFuera);

		model.addAttribute("usuarios", usuariorepo.selectAll());

		return "modulos/registrosie";
	}

	/**
	 * 
	 * @param registroIE
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "modulos/registrosie.html", params = { "crearNuevoRegistro" })
	public String registrarIngreso(RegistroIE registroIE, BindingResult result, Model model) {

		// verificar reserva del usuario
		Reserva reserva = reservasrepo.getReservedByUser(registroIE.getUsuarioIngreso().getId());

		if (reserva != null) {
			registroIE.setFechaIngreso(Timestamp.from(Instant.now()));
			registroIE.setTicketId(UUID.randomUUID().toString());
			registroierepo.insertI(registroIE);
			espaciorepo.activate(reserva.getEspacioParqueo().getId());

			// TODO: mensaje de ok
			return "redirect:/modulos/registrosie.html";
		} else {
			model.addAttribute("error", "No tiene una reserva, no se le permitirá el ingreso hasta que solicite una!");
			return "test";
		}

	}
	
	/**
	 * 
	 * @return
	 */
	public String registrarIngresoRecog() {
		Reconocimiento recono = new Reconocimiento();
		recono.setImagen("carrofront5.png");
		String placarec = recono.ejecutar("mrsamudiobucket");
		
		
		
		return "";
	}

	/**
	 * Registra la salida del vehiculo, finaliza la reserva y libera el espacio de
	 * parqueo
	 * 
	 * @param registroIE
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "modulos/registrosie.html", params = { "registrarEgresoBD" })
	public String registrarEgreso(@ModelAttribute("registroEgreso") RegistroIE registroIE, BindingResult result,
			Model model) {

		RegistroIE registroUp = registroierepo.selectByVehiculo(registroIE.getVehiculo());

		registroUp.setFechaEgreso(Timestamp.from(Instant.now()));
		registroUp.setUsuarioEgreso(registroIE.getUsuarioEgreso());
		registroUp.setRecursoEgreso(registroIE.getRecursoEgreso());

		registroierepo.updateIE(registroUp);

		// seleccionar la reserva activa del usuario que ingresó con el vehiculo
		Reserva reserva = reservasrepo.getReservedByUser(registroUp.getUsuarioIngreso().getId());

		// liberar el espacio de parqueo
		espaciorepo.deActivate(reserva.getEspacioParqueo().getId());

		// terminar la reserva
		reservasrepo.endReserva(reserva, false);

		return "redirect:/modulos/registrosie.html";
	}

	/**
	 * Registra el ingreso de un usuario con un vehiculo con un recurso especifico
	 * 
	 * @param placa
	 * @param idUsuario
	 * @param idRecurso
	 * @return
	 */
	@GetMapping("/nuevoIngreso/{placa}/{idUsuario}/{idRecurso}")
	@ResponseBody
	public String registrarIngreso(@PathVariable String placa, @PathVariable Long idUsuario,
			@PathVariable Long idRecurso) {

		// Recurso que toma la imagen
		Recurso recurso = recursorepo.selectById(idRecurso);

		// reconocer la placa(Vehiculo reconocido
		// TODO: do plate recognition
		Vehiculo vehiculo = vehiculorepo.selectByPlaca(placa);

		// Verificar si el vehículo se encuentra en bd y si no registrarlo
		if (vehiculo == null) {
			vehiculo = new Vehiculo(placa, "marca", "color", "modelo", "clase", "tipoServicio");
			vehiculorepo.insert(vehiculo);
		}

		// reconocer al usuario del vehiculo (usuario reconocido)
		// TODO: do recognition face to user
		Usuario usuario = usuariorepo.selectById(idUsuario);

		// Si el usuario no está registrado se da aviso al supervisor y no se permite el
		// ingreso
		if (usuario == null) {
			// sendAlert To supervisor;
			return "{\"response\":false}";
		} else { // el usuario está registrado

			UUID uuid = UUID.randomUUID();

			// Verificar si el usuario tiene reservación de un espacio de parqueo
			List<Reserva> reservas = reservasrepo.selectByUser(usuario.getId(), true);
			if (!reservas.isEmpty()) {

				// tiene reserva, registre el ingreso y ocupe el espaciop
				RegistroIE registroIngreso = new RegistroIE(Timestamp.from(Instant.now()), recurso, vehiculo, usuario,
						uuid.toString());
				registroierepo.insertI(registroIngreso);
				espaciorepo.activate(reservas.get(0).getEspacioParqueo().getId());

				return "{\"response\":true}";

			} else {

//				// Si el usuario no tiene reservación solicitar permiso al supervisión
//				if ("Supervisor".equals("aprueba")) {
//
//					// Si el supervisor aprueba entonces se registra el ingreso y
//					RegistroIE registroIngreso = new RegistroIE(Timestamp.from(Instant.now()), recurso, vehiculo,
//							usuario, uuid.toString());
//					registroierepo.insertI(registroIngreso);
//
//					//	el sistema escoge un espacio libre al azar
//					List<EspacioParqueo> espacios = espaciorepo.selectByOcupado(false);
//					espaciorepo.activate(espacios.get(new Random().nextInt(espacios.size())).getId());
//
//					return true;
//				} else {
//					// Si supervisor no aprueba ingreso no se permite el ingreso
//					return false;
//				}

				return "{\"response\":false}";

			}
		}
	}

	/**
	 * Registra el egreso del vehiculo con un usuario con un recurso especifico
	 * 
	 * @param placa     reconocida
	 * @param idUsuario del usuario reconocido
	 * @param idRecurso
	 * @return
	 */
	@GetMapping("/nuevoEgreso/{placa}/{idUsuario}/{idRecurso}")
	public Boolean registrarEgreso(@PathVariable String placa, @PathVariable Long idUsuario,
			@PathVariable Long idRecurso) {

		// Recurso que toma la imagen
		Recurso recurso = recursorepo.selectById(idRecurso);

		Vehiculo vehiculo = vehiculorepo.selectByPlaca(placa);
		Usuario usuario = usuariorepo.selectById(idUsuario);

		// actualizar registro y terminar reserva
		// TODO: crear consulta para encontrar el registro de ingreso para actualizarlo
		RegistroIE registroEgreso = new RegistroIE();
//		registroEgreso = registroierepo.selectByVehiculoXusuario(vehiculo.getId(), usuario.getId());

		if (registroEgreso == null) {
			return false;
		} else {

			registroEgreso.setFechaEgreso(Timestamp.from(Instant.now()));
			registroEgreso.setUsuarioEgreso(usuario);
			registroEgreso.setRecursoEgreso(recurso);
			registroierepo.updateIE(registroEgreso);

			return true;
		}
	}

	@GetMapping(path = "/testeo.html")
	public void probarOnvif() {

//		OnvifDevice cam;
//		
//		try {
//			OnvifDevice cam2 = new OnvifDevice("192.168.0.14:10000", "admin", "admin");
//			cam = new OnvifDevice("192.168.122.59:10000", "admin", "admin");
//			List<Profile> profilescam = cam.getDevices().getProfiles();
//			
//			String profileToken = "";
//			for (Profile profileCam : profilescam) {
//				profileToken = profileCam.getToken();
//			}
//			String screenShotUri = cam.getMedia().getSceenshotUri(profileToken);
//			
//			System.out.println(screenShotUri);
//			
//			
//
//			cam.getImaging();
//		} catch ( ConnectException | SOAPException  e) {
//			e.getMessage();
//		}

	}

}
