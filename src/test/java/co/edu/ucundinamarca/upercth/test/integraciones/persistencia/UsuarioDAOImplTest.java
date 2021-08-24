package co.edu.ucundinamarca.upercth.test.integraciones.persistencia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
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
import co.edu.ucundinamarca.upercth.model.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.upercth.model.dao.RolDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;


@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class UsuarioDAOImplTest {

	@Autowired
	ConfiguracionDAO configuracionrepo;

	@Autowired
	UsuarioDAO usuariorepo;

	@Autowired
	RolDAO rolrepo;

	private Usuario usuario = null;
	private List<Usuario> usuarios = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void selectById() {
		usuario = usuariorepo.selectById((long) 2);
		assertNotNull(usuario);
		usuario = null;

	}

	@Test
	void selectAll() {
		usuarios = usuariorepo.selectAll();
		assertFalse(usuarios.isEmpty());
		usuarios.clear();
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Usuario u = new Usuario("nombre", "apellidos", 'E', "1925000000377335", "mi contraseña", "info654@uuucundinamarca.edu.co",
				Date.valueOf("1999-10-04"), Timestamp.from(Instant.now()), false, rolrepo.selectById(5));
		boolean test = usuariorepo.insert(u);

		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {

		Timestamp fechaReg = Timestamp.from(Instant.now());
		usuario = usuariorepo.selectById((long) 2);
		Usuario u = new Usuario(usuario.getId(), "nombreusuarioDAOimttest", "apellidos", 'E', "192515905377335", "mi  asd6f54contraseña",
				"info@ucundinamarca.edu.cos", Date.valueOf("1999-10-04"), fechaReg, false, rolrepo.selectById(5));

		boolean test = usuariorepo.update(u);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
		usuario = null;
	}

	@Rollback(true)
	@Test
	@Transactional
	void deactivate() {
		usuario = usuariorepo.selectById((long) 2);
		boolean test = usuariorepo.deactivate(usuario);
		assertEquals(true, test, "No se desactivó del usuario.");
		usuario = null;
	}

	@Rollback(true)
	@Test
	@Transactional
	void activate() {
		usuario = usuariorepo.selectById((long) 2);
		boolean test = usuariorepo.activate(usuario);
		assertEquals(true, test, "No se activó del usuario.");
		usuario = null;
	}
}
