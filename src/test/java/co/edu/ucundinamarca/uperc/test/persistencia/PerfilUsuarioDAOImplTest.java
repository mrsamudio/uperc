package co.edu.ucundinamarca.uperc.test.persistencia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import co.edu.ucundinamarca.uperc.persistencia.dao.PerfilUsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RolDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.PerfilUsuario;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
class PerfilUsuarioDAOImplTest {

	@Autowired
	PerfilUsuarioDAO perfilusuariorepo;

	@Autowired
	RolDAO rolrepo;

	private PerfilUsuario perfilUsuario;
	private List<PerfilUsuario> perfilesUsuario = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		perfilUsuario = perfilusuariorepo.selectById(2);
		assertNotNull(perfilUsuario);
	}

	@Test
	void selectAll() {
		perfilesUsuario = perfilusuariorepo.selectAll();
		assertFalse(perfilesUsuario.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		PerfilUsuario p = new PerfilUsuario("Mi perfil de usuario", "Descripción del perfil de usuario");
		boolean test = perfilusuariorepo.insert(p);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		perfilUsuario = perfilusuariorepo.selectById(3);
		PerfilUsuario p = new PerfilUsuario(perfilUsuario.getId(), "Mi perfil", perfilUsuario.getDescripcion());
		boolean test = perfilusuariorepo.update(p);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
	}

}
