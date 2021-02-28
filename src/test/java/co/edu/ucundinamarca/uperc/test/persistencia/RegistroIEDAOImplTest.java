package co.edu.ucundinamarca.uperc.test.persistencia;

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

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.PermisoDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RecursoDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RegistroIEDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.VehiculoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Recurso;
import co.edu.ucundinamarca.uperc.persistencia.entidades.RegistroIE;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Vehiculo;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
class RegistroIEDAOImplTest {


	@Autowired
	RegistroIEDAO registroierepo;

	@Autowired
	UsuarioDAO usuariorepo;

	@Autowired
	VehiculoDAO vehiculorepo;

	@Autowired
	PermisoDAO permisorepo;

	@Autowired
	RecursoDAO recursorepo;

	private RegistroIE registroie;
	private List<RegistroIE> registrosie = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		registroie = registroierepo.selectById((long) 2);
		assertNotNull(registroie);
	}

	@Test
	void selectAll() {
		registrosie = registroierepo.selectAll();
		assertFalse(registrosie.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Timestamp fechaIngreso = Timestamp.from(Instant.now());
		
		Recurso r = recursorepo.selectById((long) 1);
		Vehiculo v = vehiculorepo.selectById((long) 2);
		Usuario uing = usuariorepo.selectById((long) 6);
		String ticketId = "a0ccbc99-9F0b-47f8-bb6d-6bb9bd380aDD";
		
		RegistroIE rie = new RegistroIE(fechaIngreso, r, v, uing, ticketId);
		boolean test = registroierepo.insertI(rie);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1
		Timestamp fechaEgreso = Timestamp.from(Instant.now());
		Usuario uEgre = usuariorepo.selectById((long) 4);
		Recurso rEgreso = recursorepo.selectById((long) 1);
		
		registroie = registroierepo.selectById((long) 5);
		RegistroIE rie = new RegistroIE(registroie.getId(), fechaEgreso, uEgre, rEgreso);
		
		boolean test = registroierepo.updateIE(rie);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");

	}

}
