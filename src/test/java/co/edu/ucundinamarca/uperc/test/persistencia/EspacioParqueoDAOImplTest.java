/**
 * 
 */
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
import co.edu.ucundinamarca.uperc.persistencia.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UbicacionDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.EspacioParqueo;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Ubicacion;

/**
 * @author mrsamudio
 *
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
class EspacioParqueoDAOImplTest {

	@Autowired
	EspacioParqueoDAO espacioparqueorepo;

	@Autowired
	UbicacionDAO ubicacionrepo;

	private EspacioParqueo espacioParqueo;
	private List<EspacioParqueo> espaciosParqueo = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		espacioParqueo = espacioparqueorepo.selectById(2);
		assertNotNull(espacioParqueo);
	}

	@Test
	void selectAll() {
		espaciosParqueo = espacioparqueorepo.selectAll();
		assertFalse(espaciosParqueo.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		
		Ubicacion u = ubicacionrepo.selectById(2);
		EspacioParqueo e = new EspacioParqueo("zzz-AAA", u, false);
		boolean test = espacioparqueorepo.insert(e);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	/**
	 * 
	 * 
	 */
	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		EspacioParqueo e = espacioparqueorepo.selectById(2);
		EspacioParqueo e2 = new EspacioParqueo(e.getId(), "A-234-B", e.getUbicacion(), true);
		
		boolean test = espacioparqueorepo.update(e2);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");

	}

}
