package co.edu.ucundinamarca.upercth.test.integraciones.persistencia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import co.edu.ucundinamarca.upercth.model.dao.VehiculoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Vehiculo;


@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class VehiculoDAOImplTest {

	@Autowired
	VehiculoDAO vehiculorepo;

	private Vehiculo vehiculo;
	private List<Vehiculo> vehiculos = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		vehiculo = vehiculorepo.selectById((long) 2);
		assertNotNull(vehiculo);
	}

	@Test
	void selectAll() {
		vehiculos = vehiculorepo.selectAll();
		assertFalse(vehiculos.isEmpty());
	}
	
	@Test
	void selectByPlaca() {
		vehiculo = vehiculorepo.selectByPlaca("1");
		assertNull(vehiculo);
	}
	
	@Test
	void selectByMarca() {
		vehiculos = vehiculorepo.selectByMarca("a");
		assertFalse(vehiculos.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Vehiculo v = new Vehiculo("BHN-234", "FIAT", "negro", "2019", "automovil", "particular");
		boolean test = vehiculorepo.insert(v);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1
		Vehiculo old = vehiculorepo.selectById(2);
		Vehiculo v = new Vehiculo(old.getId(), old.getPlaca(), old.getMarca(), "blanco", old.getModelo(),
				old.getClase(), "especial");
		boolean test = vehiculorepo.update(v);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");

	}

}
