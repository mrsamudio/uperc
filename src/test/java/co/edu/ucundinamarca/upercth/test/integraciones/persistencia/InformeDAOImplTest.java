/**
 * 
 */
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
import co.edu.ucundinamarca.upercth.model.dao.InformeDAO;
import co.edu.ucundinamarca.upercth.model.dao.RegServicioDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Informe;
import co.edu.ucundinamarca.upercth.model.entities.RegServicio;


/**
 * @author mrsamudio
 *
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class InformeDAOImplTest {

	@Autowired
	InformeDAO informerepo;

	@Autowired
	UsuarioDAO usuariorepo;

	@Autowired
	RegServicioDAO regserviciorepo;

	private Informe informe;
	private List<Informe> informes = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		informe = informerepo.selectById((long) 2);
		assertNotNull(informe);
	}

	@Test
	void selectAll() {
		informes = informerepo.selectAll();
		assertFalse(informes.isEmpty());
	}

	/**
	 * La prueba evidenció que son necesarios dos tipos de inserción diferentes, una
	 * para regservicio y otra para usuario. esto debido a que el el usuario y el
	 * sistema externo pueden generar informes cada uno
	 */
	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Informe informe = informerepo.selectById((long) 2);
		Timestamp fechaGen = Timestamp.from(Instant.now());
		Date fechaIni = Date.valueOf("1999-10-04");
		Date fechaFin = Date.valueOf("1999-10-04");

		Informe informeU = new Informe(informe.getUsuario(), fechaGen, fechaIni, fechaFin, 0.40, 0.67, 0.33, 0.90, 0.1,
				100, 30, 25);
		
		RegServicio reg = regserviciorepo.selectById(1);

		Informe informeR = new Informe(reg, fechaGen, fechaIni, fechaFin, 0.40, 0.67, 0.33, 0.90,
				0.1, 100, 30, 25);

		// Inserción generada por solicitud del usuario
		boolean test = informerepo.insertU(informeU);
		assertEquals(true, test, "Inserción a bd solicitada por el usuario, no se obtuvo el resultado esperado.");

		// Inserción generada por solicitud del registro de servicio
		test = informerepo.insertR(informeR);
		assertEquals(true, test,
				"Inserción a bd solicitada por el registro de servicio, no se obtuvo el resultado esperado.");
	}

	/**
	 * 
	 */
	@Rollback(true)
	@Test
	@Transactional
	void update() {
		Informe informe = informerepo.selectById((long) 2);
		Timestamp fechaGen = Timestamp.from(Instant.now());
		Date fechaIni = Date.valueOf("1999-10-04");
		Date fechaFin = Date.valueOf("1999-10-04");

		Informe informeU = new Informe(informe.getId(), informe.getUsuario(), null, fechaGen, fechaIni, fechaFin, 0.40, 0.67, 0.33, 0.90, 0.1,
				100, 30, 25);

		RegServicio reg = regserviciorepo.selectById(1);
		Informe informeR = new Informe(informe.getId(), null, reg, fechaGen, fechaIni, fechaFin, 0.40, 0.67, 0.33, 0.90,
				0.1, 100, 30, 25);

		// Inserción generada por solicitud del usuario
		boolean test = informerepo.updateU(informeU);
		assertEquals(true, test, "Inserción a bd solicitada por el usuario, no se obtuvo el resultado esperado.");

		// Inserción generada por solicitud del registro de servicio
		test = informerepo.updateR(informeR);
		assertEquals(true, test,
				"Inserción a bd solicitada por el registro de servicio, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void delete() {
		Informe informe = informerepo.selectById((long) 2);
		boolean test = informerepo.delete(informe);
		assertEquals(true, test, "borrado a bd, no se obtuvo el resultado esperado.");
	}

}
