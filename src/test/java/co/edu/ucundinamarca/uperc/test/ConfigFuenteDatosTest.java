/**
 * 
 */
package co.edu.ucundinamarca.uperc.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;


//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.FuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.FuenteDatosDev;
import co.edu.ucundinamarca.uperc.persistencia.FuenteDatosTest;
import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.daoimpl.ConfiguracionDAOImpl;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * @author mrsamudio
 *
 */
//@RunWith(Springr)
//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
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
//        configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
//        configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
    }
	
	@Test
	public void test1() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigFuenteDatos.class);
		System.out.println(" ");
		System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()) );
		System.out.println(" ");
//		SessionFactory sessionFact = (SessionFactory) ctx.getBean("factoriaSesion");
//		sessionFact.openSession();
		
		ConfiguracionDAO configDao = ctx.getBean(ConfiguracionDAO.class);
//		ConfiguracionDAO configDao = (ConfiguracionDAO) ctx.getBean("configuracionDao");
//		ConfiguracionDAO configDao = ctx.getb
//		configDao.delete(Configuracion);
		List<Configuracion> res = new ArrayList<Configuracion>();
				res = configDao.selectAll();
				
		System.out.println("Tamaño " + res.size() + "\n");
//		System.out.println(res.get(0));
//		
//		for (int i = 0; i < res.size(); i++) {
//			System.out.println(res.get(i).toString());
////			Configuracion c = res.get(i);
//			System.out.println(res.get(i).getCaducidadContrasena()); 
//		    System.out.println(res.get(i).getId()); 
//		    System.out.println(res.get(i).getIntentosFallidos()); 
//		    System.out.println(res.get(i).getMaxAdmin()); 
//		    System.out.println(res.get(i).getFechaGuardado()); 
//		}
		for (Configuracion configuracion : res) {
//			Configuracion c = configuracion.getClass();
			System.out.println("getId: " + configuracion.getId()); 
			System.out.println("getIntentosFallidos: " + configuracion.getIntentosFallidos()); 
			System.out.println("getCaducidadContrasena: " + configuracion.getCaducidadContrasena()); 
		    System.out.println("getMaxAdmin: " + configuracion.getMaxAdmin()); 
		    System.out.println("getFechaGuardado: " + configuracion.getFechaGuardado().toString());
		    System.out.println("Usuario: " + configuracion.getUsuario());
		    System.out.println("");
		    System.out.println("");

		}
		
//		listSingers(configDao.selectAll());
//		ctx.close();
	}

	private static void listSingers(List<Configuracion> singers) {
		    logger.info(" ---- Listing singers:");
		    for (Configuracion singer : singers) {
		    logger.info(singer.toString());
		    System.out.println(singer.getCaducidadContrasena()); 
		    System.out.println(singer.getId()); 
		    System.out.println(singer.getIntentosFallidos()); 
		    System.out.println(singer.getMaxAdmin()); 
		    System.out.println(singer.getFechaGuardado()); 
		    logger.info(singer.getUsuario());
		    }
	}
	
	@Test
	public void selectById() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigFuenteDatos.class);
		System.out.println(" ");
		System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()) );
		System.out.println(" ");
		
		ConfiguracionDAO configDao = ctx.getBean(ConfiguracionDAO.class);

		Configuracion c = configDao.selectById( Long.parseLong("1"));
		
		System.out.println("prueba de selectbyid");
		System.out.println("get123Id: " + c.getId()); 
		System.out.println("getIntentosFallidos123: " + c.getIntentosFallidos()); 
		System.out.println("getCaducidadContrasena: " + c.getCaducidadContrasena()); 
	    System.out.println("getMaxAdmin: " + c.getMaxAdmin()); 
	    System.out.println("getFechaGuardado: " + c.getFechaGuardado().toString());
	    System.out.println("Usuario: " + c.getUsuario());
	}
	
	
//	@Test
//    public void testTwo() throws SQLException {
//        GenericApplicationContext ctx =
//            new AnnotationConfigApplicationContext(ConfigFuenteDatos.class);
//        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
//        assertNotNull(dataSource);
//        testDataSource(dataSource);
//        ctx.close();
//    }
//    private void testDataSource(DataSource dataSource) throws SQLException{
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//            PreparedStatement statement =
//                connection.prepareStatement("SELECT 1");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next())  {
//                int mockVal = resultSet.getInt("1");
//                assertTrue(mockVal== 1);
//            }
//            statement.close();
//        } catch (Exception e) {
//            logger.debug("Something  unexpected happened.",  e);
//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }

}
