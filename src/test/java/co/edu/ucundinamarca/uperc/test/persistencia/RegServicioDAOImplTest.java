package co.edu.ucundinamarca.uperc.test.persistencia;

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

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.dao.RegServicioDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.SistemaExternoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.RegServicio;
import co.edu.ucundinamarca.uperc.persistencia.entidades.SistemaExterno;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
class RegServicioDAOImplTest {
	
	@Autowired
	RegServicioDAO regserviciorepo;

	@Autowired
	SistemaExternoDAO sistemaexternorepo;

	private RegServicio regServicio;
	private List<RegServicio> regServicios = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		regServicio = regserviciorepo.selectById((long) 2);
		assertNotNull(regServicio);
	}
	
	@Test
	void selectByIdSession() {
		regServicio = regserviciorepo.selectByIdSession("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
		assertNotNull("Objeto nulo, error en BD", regServicio);
		
		assertEquals("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11", regServicio.getIdSession(), "No coincide el id de sessión");
		
	}

	@Test
	void selectAll() {
		regServicios = regserviciorepo.selectAll();
		
//		for (RegServicio regServicio : regServicios) {
//			System.out.println(regServicio.getIdSession());
//			System.out.println(regServicio.getFechaSession());
//			System.out.println(regServicio.getId());
//			
//		}
		assertFalse(regServicios.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Timestamp fechaSession = Timestamp.from(Instant.now());
		SistemaExterno sis = sistemaexternorepo.selectById(1);
		RegServicio reg = new RegServicio("a0befc79-9c0b-4ef8-bb6d-6bb9bd380a10", sis, fechaSession);

		boolean test = regserviciorepo.insert(reg);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		Timestamp fechaSession = Timestamp.from(Instant.now());
		SistemaExterno sis = sistemaexternorepo.selectById(1);
		RegServicio regOld = regserviciorepo.selectById((long) 1);
		RegServicio regNew = new RegServicio(regOld.getId(), "a0befc79-9c0b-4ef8-bb6d-6bb9bd380a10", sis, fechaSession);
		
		boolean test = regserviciorepo.update(regNew);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");

	}

}
