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
import org.postgresql.geometric.PGpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.ucundinamarca.upercth.model.DataSourceConfig;
import co.edu.ucundinamarca.upercth.model.dao.UbicacionDAO;
import co.edu.ucundinamarca.upercth.model.entities.Ubicacion;


@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class UbicacionDAOImplTest {

	@Autowired
	UbicacionDAO ubicacionrepo;

	private Ubicacion ubicacion;
	private List<Ubicacion> ubicaciones = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		ubicacion = ubicacionrepo.selectById(2);
		assertNotNull(ubicacion);
	}

	@Test
	void selectAll() {
		ubicaciones = ubicacionrepo.selectAll();
		assertFalse(ubicaciones.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
//		ubicacion = ubicacionrepo.selectById(6);
		PGpoint p = new PGpoint(4.3777777,-74.3777777);
		Ubicacion u = new Ubicacion("Ubicacion 1", "Calle 1 # 16-15", p, "12748347");
		boolean test = ubicacionrepo.insert(u);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		ubicacion = ubicacionrepo.selectById(6);
		PGpoint p = new PGpoint(4.3777777,-74.3777777);
		Ubicacion u = new Ubicacion(ubicacion.getId(), ubicacion.getNombre(), "Calle 64 # 40-20", p, "13450540");

		boolean test = ubicacionrepo.update(u);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");

	}

}
