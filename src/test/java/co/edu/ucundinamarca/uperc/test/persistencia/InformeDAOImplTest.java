/**
 * 
 */
package co.edu.ucundinamarca.uperc.test.persistencia;

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

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.InformeDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RegServicioDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Informe;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
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

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
//		TODO: null pointer en el objeto RegServicio
		Informe informe = informerepo.selectById((long) 1);
		Timestamp fechaGen = Timestamp.from(Instant.now());
		Informe informe2 = new Informe(informe.getUsuario(), informe.getRegServicio(), fechaGen, Date.valueOf("1999-10-04"), Date.valueOf("1999-10-04"), 0.40, 0.67, 0.33, 0.90, 0.1, 100, 30, 25);
		boolean test = informerepo.insert(informe2);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		Informe informe = informerepo.selectById((long) 1);
		Timestamp fechaGen = Timestamp.from(Instant.now());
		Informe informe2 = new Informe(informe.getId(), informe.getUsuario(), informe.getRegServicio(), fechaGen, Date.valueOf("1999-10-04"), Date.valueOf("1999-10-04"), 0.40, 0.67, 0.33, 0.90, 0.1, 100, 30, 25);
		boolean test = informerepo.update(informe2);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
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
