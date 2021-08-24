package co.edu.ucundinamarca.upercth.web.ctrls;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.util.ConstantesDB;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;
import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.dao.ReservaDAO;
import co.edu.ucundinamarca.upercth.model.dao.SupervisionDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;

@Controller
public class TableroPrincipalCtrl {

	@Autowired
	private ReservaDAO reservarepo;

	@Autowired
	private RegistroIEDAO registrorepo;

	@Autowired
	private UsuarioDAO usuariorepo;

	@Autowired
	private SupervisionDAO supervisionrepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private HelpersUperc helpersUperc = new HelpersUperc();

	@GetMapping("/test.html")
	public String getTest(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("contras1", passwordEncoder.encode("12345"));
		model.addAttribute("contras2", passwordEncoder.encode("12345"));

//		RegistroIECtrl.this.probarOnvif();

		return "test";
	}

	/**
	 * Vista del tablero principal en donde se muestran los graficos resumiendo los
	 * datos actuales del sistema de información
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({ "/tableroPrincipal.html" })
	public String getTableroPrincipal(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("descripcion", "Configuración inicial de Thymeleaf");

		// Gráfico de ingresos y egresos al parqueadero en el día actual
		int ingresos = registrorepo.selectByDate(Timestamp.from(Instant.now()), true).size();
		int egresos = registrorepo.selectByDate(Timestamp.from(Instant.now()), false).size();
		Double registrosIE[] = tantoPorc(ingresos, egresos);

		String etiquetas[] = { "Ingresos " + ingresos, "Egresos " + egresos };
		double puntos[] = { registrosIE[0], registrosIE[1] };

		model.addAttribute("etiqueta", etiquetas);
		model.addAttribute("puntos", puntos);

		// Recurrencia de reservas finalizadas en la semana
		// TODO: crear registros de las fechas de fin deben estar en los cinco días
		// anteriores al día actual de consulta
		Instant ahora = Instant.now();
		List<Reserva> reservas = reservarepo.selectByFecha(Timestamp.from(ahora), ConstantesDB.SOLICITUD);
		int reservados[] = { 0, 0, 0, 0, 0 };

		// poblando los datos de reservas solicitadas
		for (int i = (reservados.length - 1), j = 0; i >= 0 && j <= 96; i--, j += 24) {

			for (Reserva reserva : reservas) {

				Instant fechaSolicitud = reserva.getFechaSolicitud().toInstant();

				if (fechaSolicitud.isBefore(ahora.minus(j, ChronoUnit.HOURS))
						&& fechaSolicitud.isAfter(ahora.minus(j + 24l, ChronoUnit.HOURS))) {
					reservados[i]++;
				}
			}
		}

		String label[] = { "120h", "96h", "72h", "48h", "24h" };
		model.addAttribute("label", label);
		model.addAttribute("point", reservados);

		// Gráfico de histórico de mensaje de alertas y avisos de supervisión
		int alertas = supervisionrepo.selectAlertas().size();
		int avisos = supervisionrepo.selectAvisos().size();
		Double superv[] = tantoPorc(alertas, avisos);

		String etiquetasSup[] = { "Alertas", "Avisos" };
//        int supervisiones[] = {alertas, avisos};
		double supervisiones[] = { superv[0], superv[1] };

		model.addAttribute("etiquetasSup", etiquetasSup);
		model.addAttribute("supervisiones", supervisiones);

		// Usuarios registrados en total
		model.addAttribute("totalUsuarios", usuariorepo.selectAll().size());

		return "tableroPrincipal";
	}

	/**
	 * Actualiza las reservas solicitadas en el gráfico
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping({ "/reservasXsemana.html" })
	public String updateReservasXSemana(ModelMap map) {

		// Recurrencia de reservas finalizadas en la semana
		// TODO: crear registros de las fechas de fin deben estar en los cinco días
		// anteriores al día actual de consulta
		Instant ahora = Instant.now();
		List<Reserva> reservas = reservarepo.selectByFecha(Timestamp.from(ahora), ConstantesDB.SOLICITUD);
		int reservados[] = { 0, 0, 0, 0, 0 };

		// poblando los datos de reservas solicitadas
		for (int i = (reservados.length - 1), j = 0; i >= 0 && j <= 96; i--, j += 24) {

			for (Reserva reserva : reservas) {

				Instant fechaSolicitud = reserva.getFechaSolicitud().toInstant();

				if (fechaSolicitud.isBefore(ahora.minus(j, ChronoUnit.HOURS))
						&& fechaSolicitud.isAfter(ahora.minus(j + 24l, ChronoUnit.HOURS))) {
					reservados[i]++;
				}
			}
		}

		String label[] = { "120h", "96h", "72h", "48h", "24h" };
		map.addAttribute("label", label);
		map.addAttribute("point", reservados);

		return "tableroPrincipal :: #reservasEnLaSemana";
	}

	/**
	 * Actualiza el total de usuarios registrados
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping({ "/usuariosRegistrados.html" })
	public String updateUsuariosRegistrados(ModelMap map) {
		map.addAttribute("totalUsuarios", usuariorepo.selectAll().size());
		return "tableroPrincipal :: #usuariosRegGrafico";
	}

	/**
	 * Actualiza el de registrosIE
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping({ "/losRegistrosIE.html" })
	public String updateRregistrosIE(ModelMap map) {

		// Gráfico de ingresos y egresos al parqueadero en el día actual
		int ingresos = registrorepo.selectByDate(Timestamp.from(Instant.now()), true).size();
		int egresos = registrorepo.selectByDate(Timestamp.from(Instant.now()), false).size();
		Double registrosIE[] = tantoPorc(ingresos, egresos);

		String etiquetas[] = { "Ingresos " + ingresos, "Egresos " + egresos };
		double puntos[] = { registrosIE[0], registrosIE[1] };

		map.addAttribute("etiqueta", etiquetas);
		map.addAttribute("puntos", puntos);

		return "tableroPrincipal :: #graficoRegistrosIE";
	}

	/**
	 * Actualiza el gráfico de supervisiones
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping({ "/lasSupervisiones.html" })
	public String updateSupervisiones(ModelMap map) {

		// Gráfico de histórico de mensaje de alertas y avisos de supervisión
		int alertas = supervisionrepo.selectAlertas().size();
		int avisos = supervisionrepo.selectAvisos().size();
		Double superv[] = tantoPorc(alertas, avisos);

		String etiquetasSup[] = { "Alertas", "Avisos" };
//		        int supervisiones[] = {alertas, avisos};
		double supervisiones[] = { superv[0], superv[1] };

		map.addAttribute("etiquetasSup", etiquetasSup);
		map.addAttribute("supervisiones", supervisiones);

		return "tableroPrincipal :: #graficoDeSupervisiones";
	}

	/**
	 * Array de porcentajes para el gráfico de alertas y avisos
	 * 
	 * @param num1
	 * @param num2
	 * @return array de porcentajes
	 */
	public Double[] tantoPorc(long num1, long num2) {
		double pNum1, pNum2;
		pNum1 = (double) (100 * num1) / (num1 + num2);
		pNum2 = (double) (100 * num2) / (num1 + num2);
		return new Double[] { pNum1, pNum2 };
	}

}
