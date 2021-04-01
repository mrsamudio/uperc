package co.edu.ucundinamarca.uperc;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.RegistroIEDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.ReservaDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.SupervisionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Reserva;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.ConstantesDB;

@Controller
@Component
public class TableroPrincipal {

	@Autowired
	private ReservaDAO reservarepo;

	@Autowired
	private RegistroIEDAO registrorepo;

	@Autowired
	private UsuarioDAO usuariorepo;

	@Autowired
	private SupervisionDAO supervisionrepo;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/tableroPrincipal")
	public String getTableroPrincipal(Model model) {
		model.addAttribute("nombreUsuario", "Inicio UPERC");
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

		Timestamp[] dias = { Timestamp.from(Instant.now()), Timestamp.from(ahora.minus(1, ChronoUnit.DAYS)),
				Timestamp.from(ahora.minus(2, ChronoUnit.DAYS)), Timestamp.from(ahora.minus(3, ChronoUnit.DAYS)),
				Timestamp.from(ahora.minus(4, ChronoUnit.DAYS)) };

		// poblando los datos de reservas solicitadas
		for (Reserva reserva : reservas) {

			if (!reserva.getFechaSolicitud().toInstant().isBefore(ahora.minus(120, ChronoUnit.HOURS))) {
				
				if (reserva.getFechaSolicitud().toInstant().until(dias[0].toInstant(), ChronoUnit.DAYS) <= 1)
					++reservados[0];
				
				for (int i = 1; i < dias.length; i++) {
					if (reserva.getFechaSolicitud().toInstant().until(dias[i].toInstant(), ChronoUnit.DAYS) == (i+1))
						++reservados[i];
					
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

		return "tableroPrincipal.html";
	}

	/**
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
