package co.edu.ucundinamarca.upercth.test.integraciones.persistencia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import co.edu.ucundinamarca.upercth.model.dao.RecursoDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Recurso;

@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class RecursoDAOImplTest {

	@Autowired
	RecursoDAO recursorepo;

	@Autowired
	UsuarioDAO usuariorepo;

	private Recurso recurso;
	private List<Recurso> recursos = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		recurso = recursorepo.selectById((long) 1);
		assertNotNull(recurso);
	}

	@Test
	void selectAll() {
		recursos = recursorepo.selectAll();
		assertFalse(recursos.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
//		InetAddress ip = null;
//		try {
//			ip = InetAddress.getByName("140.82.112.4");
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Recurso r = new Recurso("recurso tal", "lg", "tipo"
//				,ip.getAddress().toString()
				,"140.82.112.4"
				, 4530, "08:FF:2b:01:02:03", "TCP",
				Timestamp.from(Instant.now()), "http://proveedor.co", false);
		boolean test = recursorepo.insert(r);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1
		Timestamp fechaRegistro = Timestamp.from(Instant.now());
		recurso = recursorepo.selectById((long) 1);
		Recurso r = new Recurso(recurso.getId(), recurso.getNombre(), "huawei", "Camara 360", recurso.getIp(), recurso.getPuerto(), "08:FF:2b:01:F2:E3", "UDP",
				fechaRegistro, "http://proveedor.co", false);
		boolean test = recursorepo.update(r);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
	}
	
	@Rollback(true)
	@Test
	@Transactional
	void activate() {
		recurso = recursorepo.selectById((long) 1);
		boolean test = recursorepo.activate(recurso);
		assertEquals(true, test, "Activación de recurso a bd, no se obtuvo el resultado esperado.");
	}
	
	@Rollback(true)
	@Test
	@Transactional
	void deactivate() {
		recurso = recursorepo.selectById((long) 1);
		boolean test = recursorepo.deactivate(recurso);
		assertEquals(true, test, "Desactivación de recurso a bd, no se obtuvo el resultado esperado.");
	}

}
