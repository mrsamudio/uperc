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
import co.edu.ucundinamarca.uperc.persistencia.entidades.Rol;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
class RolDAOImplTest {

	@Autowired
	RolDAO rolrepo;

	@Autowired
	PerfilUsuarioDAO perfilrepo;

	private Rol rol;
	private List<Rol> roles = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		rol = rolrepo.selectById(2);
		assertNotNull(rol);
	}

	@Test
	void selectAll() {
		roles = rolrepo.selectAll();
		assertFalse(roles.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		PerfilUsuario p = perfilrepo.selectById(2);
		Rol r = new Rol("nuevo Rol", "Descripción del nuevo rol", p);
		boolean test = rolrepo.insert(r);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		PerfilUsuario p = perfilrepo.selectById(3);
		rol = rolrepo.selectById(3);
		Rol r = new Rol(rol.getId(), "nuevo Rol", "Descripción del nuevo rol", p);
		boolean test = rolrepo.update(r);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
	}

	
}
