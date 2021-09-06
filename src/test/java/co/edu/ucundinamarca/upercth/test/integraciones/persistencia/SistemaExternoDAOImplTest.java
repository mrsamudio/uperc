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
import co.edu.ucundinamarca.upercth.model.dao.SistemaExternoDAO;
import co.edu.ucundinamarca.upercth.model.entities.SistemaExterno;


@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class SistemaExternoDAOImplTest {

	@Autowired
	SistemaExternoDAO sistemaexternorepo;

	private SistemaExterno sistemaexterno;
	private List<SistemaExterno> sistemasexternos = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		sistemaexterno = sistemaexternorepo.selectById(2);
		assertNotNull(sistemaexterno);
	}

	@Test
	void selectAll() {
		sistemasexternos = sistemaexternorepo.selectAll();
		assertFalse(sistemasexternos.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		SistemaExterno s = new SistemaExterno("198.24.10/24", "nombre de usuario", "contraseña");
		boolean test = sistemaexternorepo.insert(s);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		sistemaexterno = sistemaexternorepo.selectById(1);
		SistemaExterno s = new SistemaExterno(sistemaexterno.getId(), "192.168.3.156", "nombre de usuario", "contraseña");
		boolean test = sistemaexternorepo.update(s);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
	}

}
