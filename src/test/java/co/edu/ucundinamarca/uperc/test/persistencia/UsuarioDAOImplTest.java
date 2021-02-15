package co.edu.ucundinamarca.uperc.test.persistencia;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RolDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.daoimpl.ConfiguracionDAOImpl;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
public class UsuarioDAOImplTest {

	// TODO: insertar info en Log AOP
	private static Logger logger = LogManager.getLogger(UsuarioDAOImplTest.class);

	
	@Autowired
	ConfiguracionDAO configuracionrepo;
	
	@Autowired
	UsuarioDAO usuariorepo;
	
	@Autowired
	RolDAO rolrepo;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<>();
	
	
	
	public UsuarioDAOImplTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}
	
	@Test
	public void selectById() {
		
		usuario = usuariorepo.selectById((long) 2);
		assertEquals((long) 2, usuario.getId());
		assertAll("usuario",
				() -> assertEquals((long) 2, usuario.getId(), "El id del usuario no corresponde con el esperado"),
				() -> assertEquals("Ad", usuario.getNombres(), "El nombre de usuario no corresponde con el esperado"),
				() -> assertEquals("Ministro", usuario.getApellidos(), "Los apellidos del usuario no corresponde con el esperado"),
				() -> assertEquals('C', usuario.getTipoId(), "El tipoid del usuario no corresponde con el esperado"),
				() -> assertEquals("00000001", usuario.getNumId(), "El numid del usuario no corresponde con el esperado"),
				() -> assertEquals("minijtro", usuario.getContrasena(), "La contraseña del usuario no corresponde con la esperada"),
				() -> assertEquals("minijtro@localhost", usuario.getCorreo(), "El correo del usuario no corresponde con el esperado"),
				() -> assertEquals(Date.valueOf("1900-01-01"), usuario.getFechaNac(), "La fecha de nacimiento del usuario no corresponde con la esperada"),
				() -> assertEquals(Timestamp.valueOf("2021-01-18 05:25:59.0"), usuario.getFechaReg(), "La fecha de registro del usuario no corresponde con la esperada"),
				() -> assertEquals(true, usuario.isEstado(), "El estado del usuario no corresponde con el esperado"),
				() -> assertEquals(5, usuario.getRol().getId(), "El id del rol de usuario no corresponde con el esperado")
				);
				
	}
	
	@Test
	public void selectAll(){
		usuarios = usuariorepo.selectAll();
		assertEquals(usuariorepo.selectById((long) 2), usuarios.get(0), "no corresponde el primer usuario de la lista de usuarios");
//		System.out.println(configuracionrepo.selectAll());
	}
	
	@Rollback(true)
	@Test
	@Transactional
	public void insert() {
		Usuario u = new Usuario("nombre","apellidos", 'E', "1925377335", "mi contraseña", "info@ucundinamarca.edu.co", Date.valueOf("1999-10-04"),Timestamp.from(Instant.now()), false, rolrepo.selectById(5));
		boolean test = usuariorepo.insert(u);
		assertEquals(true, test, "no corresponde el primer usuario de la lista de usuarios");
//		System.out.println(test);
	}
	
	@Rollback(true)
	@Test
	@Transactional
	public void update() {
		//TODO: realizar update

//		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigFuenteDatos.class);
//
//		UsuarioDAO uDao = ctx.getBean(UsuarioDAO.class);
//		ConfiguracionDAO configDao = ctx.getBean(ConfiguracionDAO.class);

//		Usuario u = uDao.selectById((long) 1);
//
//		Date d = java.sql.Date.valueOf(LocalDate.now());
//
//		Configuracion c = new Configuracion(1, 47, 80, 7, d, u);

//		System.out.println(configDao.update(c));
		// configDao.insert(c);
//		System.out.println(configuracionrepo.update(c));
//		System.out.println("hecho");

	}
	
	@Rollback(true)
	@Test
	@Transactional
	public void activate(){
		//TODO: realizar activacion 
	}
	
	@Rollback(true)
	@Test
	@Transactional
	public void deactivate(){
		//TODO: realizar desactivacion
	}

	


}
