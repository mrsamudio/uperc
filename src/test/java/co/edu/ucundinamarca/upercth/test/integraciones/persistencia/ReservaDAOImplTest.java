package co.edu.ucundinamarca.upercth.test.integraciones.persistencia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.ucundinamarca.upercth.model.DataSourceConfig;
import co.edu.ucundinamarca.upercth.model.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.upercth.model.dao.ReservaDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.EspacioParqueo;
import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;
import co.edu.ucundinamarca.upercth.util.ConstantesDB;

@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class ReservaDAOImplTest {


	@Autowired
	ReservaDAO reservarepo;

	@Autowired
	UsuarioDAO usuariorepo;
	
	@Autowired
	EspacioParqueoDAO  espacioparqueorepo;

	private Reserva reserva;
	private List<Reserva> reservas = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		reserva = reservarepo.selectById((long) 2);
		assertNotNull(reserva);
	}
	
	@Test
	void selectByFecha() {
//		TODO: continuar
		Timestamp fechaReserva = Timestamp.valueOf("2021-03-15 09:00:00");
		reservas = reservarepo.selectByFecha(fechaReserva, ConstantesDB.SOLICITUD);
		
		assertFalse("Lista vacia, ¿Existen datos para realizar la pruba?", reservas.isEmpty());

		reservas = reservarepo.selectByFecha(fechaReserva, ConstantesDB.RESERVA);
		assertFalse("Lista vacia, ¿Existen datos para realizar la pruba?", reservas.isEmpty());

//		reservas = reservarepo.selectByFecha(fechaReserva, ConstantesDB.FIN);
//		assertFalse("Lista vacia, ¿Existen datos para realizar la pruba?", reservas.isEmpty());
	}

	@Test
	void selectAll() {
		reservas = reservarepo.selectAll();
		assertFalse(reservas.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Timestamp fechaSolicitud = Timestamp.from(Instant.now());
		Timestamp fechaReserva = Timestamp.valueOf("2021-03-15 09:00:00");

		Usuario u = usuariorepo.selectById((long) 6);
		EspacioParqueo ep = espacioparqueorepo.selectById(27);
		
		Reserva r = new Reserva(fechaSolicitud, true, ep, fechaReserva, false, u);
		boolean test = reservarepo.insert(r);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		Timestamp fechaSolicitud = Timestamp.from(Instant.now());
		Timestamp fechaReserva = Timestamp.valueOf("2021-03-15 09:00:00");
		Timestamp fechaFin = Timestamp.valueOf("2021-03-15 09:00:00");

		Usuario u = usuariorepo.selectById((long) 7);
		EspacioParqueo ep = espacioparqueorepo.selectById(23);
		reserva = reservarepo.selectById(2);
		
		
		Reserva r = new Reserva(reserva.getId(), fechaSolicitud, false, ep, fechaReserva, fechaFin, false, u);
		boolean test = reservarepo.update(r);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
		
		
	}
	
	@Rollback(true)
	@Test
	@Transactional
	void endReserva() {
		Timestamp fechaFin = Timestamp.valueOf("2021-03-15 09:00:00");
		reserva = reservarepo.selectById(2);
		
		Reserva re = new Reserva(reserva.getId(), true, fechaFin, true);
		boolean test = reservarepo.endReserva(re, false);// no se canceló, solo terminó
		assertEquals(true, test, "Finalización de reserva en bd, no se obtuvo el resultado esperado.");
	}

}
