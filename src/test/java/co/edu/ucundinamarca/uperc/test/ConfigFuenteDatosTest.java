/**
 * 
 */
package co.edu.ucundinamarca.uperc.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */
//@RunWith(Springr)
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@Component
//@SpringJUnitConfig
class ConfigFuenteDatosTest {

	private static Logger logger = LogManager.getLogger(ConfigFuenteDatosTest.class);

//	@Autowired
//	private ApplicationContext contextoApp;

//	@Autowired
//	@Qualifier("ConfiguracionDAO")
//	private ConfiguracionDAO configDao;

//	@Autowired
//	FuenteDatos fuenteDatos;

	@BeforeEach
	public void setUp() throws Exception {
	}

//	@Test
	public void test1() {
	}


	@Test
	public void selectById() {
	}

//	@Test
	public void insert() {
	}
	
//	@Test
	public void update() {
		
	}


}
