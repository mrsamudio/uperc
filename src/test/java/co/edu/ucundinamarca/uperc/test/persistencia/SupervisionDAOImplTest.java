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
import co.edu.ucundinamarca.uperc.persistencia.dao.SupervisionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Supervision;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
class SupervisionDAOImplTest {

	@Autowired
	SupervisionDAO supervisionrepo;

	@Autowired
	UsuarioDAO usuariorepo;

	private Supervision supervision;
	private List<Supervision> supervisiones = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		supervision = supervisionrepo.selectById((long) 3);
		assertNotNull(supervision);
	}

	@Test
	void selectAll() {
		supervisiones = supervisionrepo.selectAll();
		assertFalse(supervisiones.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Usuario u = usuariorepo.selectById((long) 8);
		Supervision s = new Supervision("Un mensaje de alerta", true, Timestamp.from(Instant.now()), true, u);
		boolean test = supervisionrepo.insert(s);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1
		supervision = supervisionrepo.selectById((long) 1);
		Supervision s = new Supervision(supervision.getId(), "Un mensaje de alerta", true,
				Timestamp.from(Instant.now()), true, supervision.getUsuario());
		
		boolean test = supervisionrepo.update(s);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
	}
	
	@Rollback(true)
	@Test
	@Transactional
	void activate() {
		supervision = supervisionrepo.selectById((long) 3);
		boolean test = supervisionrepo.activate(supervision);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
		
	}
	
	@Rollback(true)
	@Test
	@Transactional
	void deactivate() {
		supervision = supervisionrepo.selectById((long) 1);
		boolean test = supervisionrepo.deactivate(supervision);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
		
	}

}
