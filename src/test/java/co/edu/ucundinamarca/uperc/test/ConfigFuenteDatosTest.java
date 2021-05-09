/**
 * 
 */
package co.edu.ucundinamarca.uperc.test;


import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * @author mrsamudio
 *
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConfigFuenteDatosTest {

//	private static Logger logger = LogManager.getLogger(ConfigFuenteDatosTest.class);

//	@Autowired
//	private ApplicationContext contextoApp;

//	@Autowired
//	@Qualifier("ConfiguracionDAO")
//	private ConfiguracionDAO configDao;

//	@Autowired
//	FuenteDatos fuenteDatos;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void test1() {
		assertFalse(false);
	}


	@Test
	@Order(2)
	void selectById() {
		assertFalse(false);
	}

	@Test
	@Order(3)
	void insert() {
		assertFalse(false);
	}
	
	@Test
	@Order(4)
	void update() {
		assertFalse(false);
	}


}
