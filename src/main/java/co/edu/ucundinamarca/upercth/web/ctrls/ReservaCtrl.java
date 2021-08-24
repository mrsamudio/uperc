package co.edu.ucundinamarca.upercth.web.ctrls;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ucundinamarca.upercth.model.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.dao.ReservaDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.EspacioParqueo;
import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.util.ConstantesDB;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;
import net.bytebuddy.implementation.MethodCall.ArgumentLoader.ForField;

@Controller
public class ReservaCtrl {

	@Autowired
	private ReservaDAO reservasrepo;

	@Autowired
	private EspacioParqueoDAO espaciorepo;

	@Autowired
	private UsuarioDAO usuariorepo;

	@Autowired
	private RegistroIEDAO registroierepo;

	private HelpersUperc helpersUperc = new HelpersUperc();

	@GetMapping("/modulos/reservas.html")
	public String getReservas(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));

			Collection<SimpleGrantedAuthority> rolesok = helpersUperc.getRolesok();
//			if (auth.getAuthorities().stream().anyMatch(rolesok::contains)) {

			if (auth.getAuthorities().stream().anyMatch(rolok -> rolesok.contains(rolok))) {
				List<Reserva> reservas = reservasrepo.selectAll();
				reservas.sort(Comparator.comparing(a -> a.isEstado()));

//				Predicate<SimpleGrantedAuthority> isSupervisorPresent = item -> item.getAuthority().equals("Supervisor");
//				rolesok.stream().filter(isSupervisorPresent).forEach(item -> item.getAuthority());
//				rolesok.removeIf(isSupervisorPresent);

//				if( !rolesok.contains(new SimpleGrantedAuthority("Supervisor")) && auth.getAuthorities().stream().anyMatch(rolesok::contains)) {

				// TODO: poner texto en fecha fin "Ocupando el espacio" mientras se encuentre
				// registrado el ingreso(ingreso activo)
				// TODO: es un mapa para los posibles registros activos
				Map<Long, String> ocupandoEspacios = new HashMap<Long, String>();

				for (Reserva reserva : reservas) {
					RegistroIE registroActivo = registroierepo.getRegistroActivo(reserva.getUsuario().getId());

					if (registroActivo != null)
						ocupandoEspacios.put(reserva.getUsuario().getId(), registroActivo.getVehiculo().getPlaca());
				}

				
				model.addAttribute("ocupandoEspacios", ocupandoEspacios);
				model.addAttribute("reservas", reservas);
				model.addAttribute("espacios", espaciorepo.selectAll());
				model.addAttribute("usuarios", usuariorepo.selectAll());
				model.addAttribute("nuevaReserva", new Reserva());

//				}
			} else {
				long userId = usuariorepo.selectByCorreo(auth.getName()).getId();

				if (auth.getAuthorities().stream().anyMatch(item -> item.getAuthority().equals("Supervisor"))) {
					List<Reserva> reservas = reservasrepo.selectAll();
					reservas.sort(Comparator.comparing(a -> a.isEstado()));
					
					
					
					// TODO: poner texto en fecha fin "Ocupando el espacio" mientras se encuentre
					// registrado el ingreso(ingreso activo)
					// TODO: es un mapa para los posibles registros activos
					Map<Long, String> ocupandoEspacios = new HashMap<Long, String>();

					for (Reserva reserva : reservas) {
						RegistroIE registroActivo = registroierepo.getRegistroActivo(reserva.getUsuario().getId());

						if (registroActivo != null)
							ocupandoEspacios.put(reserva.getUsuario().getId(), registroActivo.getVehiculo().getPlaca());
					}

					
					model.addAttribute("ocupandoEspacios", ocupandoEspacios);
					
					
					model.addAttribute("reservas", reservas);
					
					

//					model.addAttribute("reservas", reservasrepo.selectAll());
				} else {
					List<Reserva> reservas = reservasrepo.selectByUser(userId);
					reservas.sort(Comparator.comparing(a -> a.isEstado()));
					
					
					// TODO: poner texto en fecha fin "Ocupando el espacio" mientras se encuentre
					// registrado el ingreso(ingreso activo)
					// TODO: es un mapa para los posibles registros activos
					Map<Long, String> ocupandoEspacios = new HashMap<Long, String>();

					for (Reserva reserva : reservas) {
						RegistroIE registroActivo = registroierepo.getRegistroActivo(reserva.getUsuario().getId());

						if (registroActivo != null)
							ocupandoEspacios.put(reserva.getUsuario().getId(), registroActivo.getVehiculo().getPlaca());
					}

					
					model.addAttribute("ocupandoEspacios", ocupandoEspacios);
					
					
					model.addAttribute("reservas", reservas);

//					model.addAttribute("reservas", reservasrepo.selectByUser(userId));
				}
				
				
				

				// solicitar reserva propia
				model.addAttribute("laReserva", new Reserva());
				model.addAttribute("idUsuario", userId);
			}

		}
		return "modulos/reservas";

	}

	/**
	 * Cancela la reserva
	 * 
	 * @param reserva
	 * @param bindingResult
	 * @param id
	 * @return
	 */
	// TODO: cambiar esto para cancelar la reserva
//	@RequestMapping(value = "/reservas", params = { "cancelarReserva" })
	@GetMapping(value = "/modulos/reservas.html", params = { "cancelarReserva" })
	public String cancelarReserva(final Reserva reserva, final BindingResult bindingResult,
			@RequestParam(value = "cancelarReserva", required = true) Long id) {

		Reserva reserv = reservasrepo.selectById(id);

		if (reserv.isEstado()) {

			boolean res = reservasrepo.endReserva(reserv, true);
			if (res)
				return "redirect:/modulos/reservas.html";

			// consulta la fecha actual
			// si la fecha de reserva es menor a la fecha
		} else
			reservasrepo.activate(id);

		return "redirect:/modulos/reservas.html";
	}

	/**
	 * Muestra el formulario de edición para una reserva
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = { "editarReserva" })
	public String editarReserva(@RequestParam(value = "editarReserva", required = true) final Long id, Model model,
			Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("espacios", espaciorepo.selectAll());
		model.addAttribute("usuarios", usuariorepo.selectAll());
		model.addAttribute("object", reservasrepo.selectById(id));

		return "/comun/edit";
	}

	/**
	 * Guarda una reserva editada
	 * 
	 * @param reserva
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = { "guardarReserva" })
	public String guardarReserva(@ModelAttribute("object") Reserva reserva, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		reservasrepo.update(reserva);

		return "redirect:/modulos/reservas.html";
	}

	@PostMapping(path = "/modulos/reservas.html", params = { "crearReserva" })
	public String crearReserva(@ModelAttribute("nuevaReserva") Reserva reserva, BindingResult result,
//			, @RequestParam(value = "crearReserva", required = false) final Long id
			Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		Reserva activa = reservasrepo.getReservedByUser(reserva.getUsuario().getId());

		if (activa == null) {
			EspacioParqueo espacio = espaciorepo.selectById(reserva.getEspacioParqueo().getId());

			// 2 horas atras de la reserva solicitada
			Timestamp dosHorasAtras = Timestamp.from(reserva.getFechaReserva().toInstant().minus(2, ChronoUnit.HOURS));
			// 2 horas delante de la reserva solicitada
			Timestamp dosHorasDelante = Timestamp.from(reserva.getFechaReserva().toInstant().plus(2, ChronoUnit.HOURS));

			List<Reserva> reservas = reservasrepo.selectByRango(dosHorasAtras, dosHorasDelante, ConstantesDB.RESERVA,
					reserva.getEspacioParqueo().getId());

			// El espacio de parqueo se encuentra reservado para esa fecha y hora concreta?
			if (!reservas.isEmpty()) {
				model.addAttribute("error",
						"Este espacio" + reserva.getEspacioParqueo().getId() + " se encuentra reservado!");
				return "test";
			}
			// Comprobar que el espacio no se encuentre ocupado
			if (espacio.isOcupado()) {
				model.addAttribute("error", "espacio de parqueo ocupado");
				return "test";
			}

			reserva.setFechaSolicitud(Timestamp.from(Instant.now()));
			reserva.setEstado(true);
			reservasrepo.insert(reserva);

			return "redirect:/modulos/reservas.html";

		} else {
			model.addAttribute("error", "El usuario tiene una reserva activa!");
			return "test";
		}

	}

	/**
	 * Solicita un reserva de un espacio de parqueo disponible para una fecha y hora
	 * especifica
	 * 
	 * @param reserva
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/modulos/reservas.html", params = { "solicitaReserva" })
	public String solicitarReserva(@ModelAttribute("laReserva") Reserva reserva, BindingResult result,
			@RequestParam(value = "solicitaReserva", required = true) final Long idUsuario, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		Reserva activa = reservasrepo.getReservedByUser(idUsuario);

		if (activa == null) {

			// 2 horas atras de la reserva solicitada
			Timestamp dosHorasAtras = Timestamp.from(reserva.getFechaReserva().toInstant().minus(2, ChronoUnit.HOURS));
			// 2 horas delante de la reserva solicitada
			Timestamp dosHorasDelante = Timestamp.from(reserva.getFechaReserva().toInstant().plus(2, ChronoUnit.HOURS));

			List<EspacioParqueo> espacios = espaciorepo.selectAll();

			for (EspacioParqueo espacioParqueo : espacios) {

				List<Reserva> reservas = reservasrepo.selectByRango(dosHorasAtras, dosHorasDelante,
						ConstantesDB.RESERVA, espacioParqueo.getId());

				// El espacio de parqueo se encuentra reservado para esa fecha y hora concreta?
				if (reservas.isEmpty()) { // si no hay reserva para ese espacio en ese rango de fecha

					reserva.setFechaSolicitud(Timestamp.from(Instant.now()));
					reserva.setEstado(true);
					reserva.setEspacioParqueo(espacioParqueo);

					reserva.setUsuario(usuariorepo.selectById(idUsuario)); // si mismo
					reservasrepo.insert(reserva);

					return "redirect:/modulos/reservas.html";
				}
			}

			model.addAttribute("error",
					"Lo sentimos, en este momento no hay disponibilidad de espacios para reservar en la hora solicitada");
			return "test";
		} else {
			model.addAttribute("error",
					"Usted tiene una reserva activa! Recuerde que puede cancelarla en el módulo de reservas.");
			return "test";

		}

	}

}
