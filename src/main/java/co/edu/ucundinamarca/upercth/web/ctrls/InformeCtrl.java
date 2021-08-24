package co.edu.ucundinamarca.upercth.web.ctrls;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ucundinamarca.upercth.model.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.upercth.model.dao.InformeDAO;
import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.dao.ReservaDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.EspacioParqueo;
import co.edu.ucundinamarca.upercth.model.entities.Informe;
import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.util.ConstantesDB;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;

@Controller
public class InformeCtrl {

	@Autowired
	private InformeDAO informerepo;

	@Autowired
	private ReservaDAO reservaRepo;

	@Autowired
	private RegistroIEDAO registroRepo;

	@Autowired
	private EspacioParqueoDAO espacioRepo;

	@Autowired
	private UsuarioDAO usuarioRepo;

	private HelpersUperc helpersUperc = new HelpersUperc();

	@GetMapping(path = "/modulos/informes.html")
	public String getInformes(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		List<Informe> informes = informerepo.selectAll();

		model.addAttribute("informes", informes);
		model.addAttribute("nuevoInforme", new Informe());

		long idUsuario = usuarioRepo.selectByCorreo(auth.getName()).getId();
		model.addAttribute("idUsuario", idUsuario);
		return "modulos/informes";

	}

	@PostMapping(path = "/modulos/informes.html", params = { "generarInforme" })
	public String generarInforme(@RequestParam(value = "generarInforme", required = true) final Long idUsuario,
			Model model, Authentication auth, Informe informe, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		// TODO: preguntar por la fecha de inicio y fin (intervalo) en que se
		// recolectarán los datos en el sistema
		Date fechaInicio = informe.getFechaInicio();
		Date fechaFin = informe.getFechaFin();

		informe.setUsuario(usuarioRepo.selectById(idUsuario));

		int reservasFinalizadasOk = 0;
		int reservascanceladas = 0;

		// las reservas totales en ese rango de fecha
		List<Reserva> reservasTotales = reservaRepo.selectByRango(Timestamp.from(fechaInicio.toInstant()),
				Timestamp.from(fechaFin.toInstant()), ConstantesDB.RESERVA);

		for (Reserva reserva : reservasTotales) {
			if (reserva.isCancelada()) {
				// cuente reservas canceladas
				reservascanceladas++;
			} else if (!reserva.isEstado()) {// finalizada?
				// cuente reservas finalizadas correctamente
				reservasFinalizadasOk++;
			}
		}

		// setear las reservas finalizadas correctamente
		informe.setReservasOk((double) (reservasFinalizadasOk * 100d) / reservasTotales.size());

		// setear las reservas canceladas
		informe.setReservasFail((double) (reservascanceladas * 100d) / reservasTotales.size());

		// setear ingresos totales
		List<RegistroIE> ingresosT = registroRepo.selectByRange(fechaInicio, fechaFin, true);
		informe.setIngresosTotal(ingresosT.size());

		// setear egresos totales
		List<RegistroIE> egresosT = registroRepo.selectByRange(fechaInicio, fechaFin, false);
		informe.setEgresosTotal(egresosT.size());

		// revisar los ingresos exitosos en esas fechas y consultar el total de espacios
		// de parqueo para sacar porcentaje
		List<EspacioParqueo> espacios = espacioRepo.selectAll();

		// contar la cantidad de ingresos por espacio de parqueo en esas fechas
//		Map<Integer, Integer> ingresosporEspacio = new HashMap<>();
//
//		for (EspacioParqueo espacioParqueo : espacios) {
//			int i = 0;
//			for (RegistroIE registroIE : ingresosT) {
//				// de todas las reservas finalizadas correctamente del usuario, ¿ cuantas
//				// pertenecen al mismo espacio de parqueo?
//				List<Reserva> reservasDelUsuario = reservaRepo.selectByUser(registroIE.getUsuarioIngreso().getId());
//				for (Reserva reserva : reservasDelUsuario)
//					if (reserva.getEspacioParqueo().getNombre().equals(espacioParqueo.getNombre()))
//						i++;
//			}
//			ingresosporEspacio.put(espacioParqueo.getId(), i);
//		}
//		

		Map<Integer, Integer> reservasPorEspacio = new HashMap<>();
		// conteo de espaciosReservados
		for (EspacioParqueo espacioParqueo : espacios) {
			int i = 0;
			for (Reserva reserva : reservasTotales) {
				if (reserva.getEspacioParqueo().getId() == espacioParqueo.getId())
					i++;
			}
			if (i != 0)
				reservasPorEspacio.put(espacioParqueo.getId(), i);
		}

		// setear el porcentaje de disponibilidad de espacios de parqueo
		informe.setDisponibilidad(porcEspaciosDisponibles(reservasPorEspacio.size(), espacios.size()));

		// setear la fecha en que se genera el reporte
		informe.setFechaGenerado(Timestamp.from(Instant.now()));

		informerepo.insertU(informe);

		model.addAttribute("espTotal", espacioRepo.selectAll().size());
		model.addAttribute("detalleInforme", informe);

		return "informeGenerado";
	}

	/**
	 * 
	 * @param model
	 * @param auth
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/modulos/informes.html", params = { "verDetalle" })
	public String verDetalleIforme(Model model, Authentication auth,
			@RequestParam(value = "verDetalle", required = true) final Long id) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		Informe informe = informerepo.selectById(id);
		int reservActivas = 0;
		int reservFin = 0;
		int reservFinOk = 0;
		int reservFinCancel = 0;
		int totalReservas = 0;

		// el total de reservas activas e inactivas en el rango de fecha
		List<Reserva> reservas = reservaRepo.selectByRango(informe.getFechaInicio(), informe.getFechaFin(),
				ConstantesDB.RESERVA);
		totalReservas = reservas.size();
		
		for (Reserva reserva : reservas) {
			if (reserva.isEstado()) {
				reservActivas++;
			} else if (!reserva.isEstado()) {
				reservFin++;
				if (reserva.isCancelada()) {
					reservFinCancel++;
				} else {
					reservFinOk++;
				}
			}
		}

		model.addAttribute("totalReservas", totalReservas);
		model.addAttribute("reservFinCancel", reservFinCancel);
		model.addAttribute("reservFinOk", reservFinOk);
		model.addAttribute("reservFin", reservFin);
		
		model.addAttribute("reservActivasnum", reservActivas);
		model.addAttribute("reservActivas", (double) (reservActivas*100d)/totalReservas);
		
		
		model.addAttribute("espTotal", espacioRepo.selectAll().size());
		model.addAttribute("detalleInforme", informe);
		return "informeGenerado";
	}

	/**
	 * Obtiene el porcentaje de espacios disponibles ( (espaciosReservados -
	 * totalespacios) · 100 ) / totalespacios = %espaciosDisponibles
	 * 
	 * @param espaciosReservados
	 * @param totalEspacios
	 * @return %espaciosDisponibles
	 */
	public Double porcEspaciosDisponibles(int espaciosReservados, int totalEspacios) {
		Double resultado;
		resultado = (double) (((totalEspacios - espaciosReservados) * 100d) / totalEspacios);
		return resultado;
	}

}
