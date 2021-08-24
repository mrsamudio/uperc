package co.edu.ucundinamarca.upercth.web.ctrls;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import co.edu.ucundinamarca.upercth.model.dao.RecursoDAO;
import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.dao.ReservaDAO;
import co.edu.ucundinamarca.upercth.model.entities.Recurso;
import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.util.ConstantesDB;

@EnableAsync
@Component
public class IEReservasCtrl {

	@Autowired
	private ReservaDAO reservaRepo;

	@Autowired
	private RegistroIEDAO registroRepo;
	
	@Autowired
	private RecursoDAO recursoRepo;

	private static Logger applicationsfile = LogManager.getLogger("applicationsScheduledMS");

	/**
	 * Cancela las reservas que no han tenido ingreso despues de 15 minutos de la
	 * fecha de la reserva. Si la reserva tiene un registro de ingreso activo,
	 * entonces no se cancelará la reserva.
	 * 
	 * @throws InterruptedException
	 */
	@Async
	@Scheduled(cron = "0 0/15 * * * *")
	public void cancelarReservasExpiradas() throws InterruptedException {

		// todas las reservas activas hasta la fecha
		List<Reserva> reservas = reservaRepo.selectByFecha(Timestamp.from(Instant.now().minus(15, ChronoUnit.MINUTES)),
				ConstantesDB.RESERVA, true);

		for (Reserva reserva : reservas) {
			RegistroIE registroActivo = registroRepo.getRegistroActivo(reserva.getUsuario().getId());

			if (registroActivo == null) {
				if (reservaRepo.endReserva(reserva, true)) {
					applicationsfile.log(Level.INFO,
							"La reserva del usuario {} programada para el {}, ha expirado! la reserva {} ha sido cancelada.",
							reserva.getUsuario().getCorreo(), reserva.getFechaReserva(), reserva.getId());
				} else {
					applicationsfile.log(Level.ERROR,
							"La reserva {} del usuario {} programada para el {} NO HA SIDO CANCELADA!", reserva.getId(),
							reserva.getUsuario().getCorreo(), reserva.getFechaReserva());
				}
			}
		}

	}
	
	
	public void simuCamIp() {
		
		Recurso camIpIngresos = recursoRepo.selectById(1);
		Recurso camIpEgresos = recursoRepo.selectById(62);
		
		
		
		
	}
	

}
