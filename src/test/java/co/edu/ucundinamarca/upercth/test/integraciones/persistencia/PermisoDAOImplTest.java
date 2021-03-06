package co.edu.ucundinamarca.upercth.test.integraciones.persistencia;

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

import co.edu.ucundinamarca.upercth.model.DataSourceConfig;
import co.edu.ucundinamarca.upercth.model.dao.PermisoDAO;
import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Permiso;
import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;

@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class PermisoDAOImplTest {
	
	@Autowired
	PermisoDAO permisorepo;

	@Autowired
	UsuarioDAO usuariorepo;
	
	@Autowired
	RegistroIEDAO registroierepo;

	private Permiso permiso;
	private List<Permiso> permisos = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		permiso = permisorepo.selectById((long) 2);
		assertNotNull(permiso);
	}

	@Test
	void selectAll() {
		permisos = permisorepo.selectAll();
		assertFalse(permisos.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Usuario u = usuariorepo.selectById((long) 6);
		RegistroIE rie = registroierepo.selectById(4);
		Permiso p = new Permiso(u, rie);
		boolean test = permisorepo.insert(p);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}
}
