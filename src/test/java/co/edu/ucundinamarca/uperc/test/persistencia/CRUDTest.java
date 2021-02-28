/**
 * 
 */
package co.edu.ucundinamarca.uperc.test.persistencia;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.ucundinamarca.uperc.configuracion.ConfigFuenteDatos;
import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RolDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.PerfilUsuario;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Rol;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */
@ActiveProfiles("prueba")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CRUDTest {

	@Autowired
	ConfiguracionDAO configuracionrepo;

	@Autowired
	UsuarioDAO usuariorepo;

	@Autowired
	RolDAO rolrepo;

	private static PerfilUsuario perfilUsuario = new PerfilUsuario(1, "Administrador", "Descripcíon breve");
	private static Rol rol = new Rol(5, "Administrador", "Descripcíon breve", perfilUsuario);

	private static Usuario usuarioInsert = new Usuario("nombre", "apellidos", 'E', "77225302292021", "mi contraseña",
			"info43@ucundinamarca.edu.co", Date.valueOf("1999-10-04"), Timestamp.from(Instant.now()), false, rol);

	private static Usuario usuarioUpdate = new Usuario((long) 2, "Ad", "Ministro", 'C', "00000001", "minijtro",
			"minijtro@localhost", Date.valueOf("2021-02-16"), Timestamp.valueOf("2021-02-16 11:14:55.808771"), true,
			rol);

	private boolean prueba;
	private Usuario usuario;
	private Usuario usuarioEsperado;
	private List<Usuario> usuarios = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Order(1)
	void selectById() {
		usuario = usuariorepo.selectById((long) 2);
		assertAll("usuario seleccionado por id en " + CRUDTest.class.getSimpleName(),
				() -> assertEquals((long) 2, usuario.getId(), "El id del usuario no corresponde con el esperado"),
				() -> assertEquals("Ad", usuario.getNombres(), "El nombre de usuario no corresponde con el esperado"),
				() -> assertEquals("Ministro", usuario.getApellidos(),
						"Los apellidos del usuario no corresponde con el esperado"),
				() -> assertEquals('C', usuario.getTipoId(), "El tipoid del usuario no corresponde con el esperado"),
				() -> assertEquals("00000001", usuario.getNumId(),
						"El numid del usuario no corresponde con el esperado"),
				() -> assertEquals("minijtro", usuario.getContrasena(),
						"La contraseña del usuario no corresponde con la esperada"),
				() -> assertEquals("minijtro@localhost", usuario.getCorreo(),
						"El correo del usuario no corresponde con el esperado"),
				() -> assertEquals(Date.valueOf("2021-02-16"), usuario.getFechaNac(),
						"La fecha de nacimiento del usuario no corresponde con la esperada"),
				() -> assertEquals(Timestamp.valueOf("2021-02-16 11:14:55.808771"), usuario.getFechaReg(),
						"La fecha de registro del usuario no corresponde con la esperada"),
				() -> assertEquals(true, usuario.isEstado(), "El estado del usuario no corresponde con el esperado"),
				() -> assertEquals(5, usuario.getRol().getId(),
						"El id del rol de usuario no corresponde con el esperado"));

	}

	@Test
	@Order(2)
	void selectAll() {
		usuarios = usuariorepo.selectAll();
		usuarioEsperado = usuariorepo.selectById((long) 2);
		assertEquals(usuarioEsperado.getFechaReg(), usuarios.get(0).getFechaReg(),
				"no corresponde el primer usuario de la lista de usuarios en " + CRUDTest.class.getSimpleName());

		usuarios.clear();
	}

	@Test
	@Transactional
	@Order(3)
	void insert() {
		boolean test = usuariorepo.insert(getUsuarioInsert());
		assertEquals(true, test,
				"Inserción a bd, no se obtuvo el resultado esperado en " + CRUDTest.class.getSimpleName());
	}

	@Test
	@Transactional
	@Order(4)
	void deleteInsert() {
		prueba = usuariorepo.delete(getUsuarioInsert());
		assertEquals(true, prueba,
				"insert rollback a bd, no se obtuvo el resultado esperado en " + CRUDTest.class.getSimpleName());
	}

	@Test
	@Transactional
	@Order(5)
	void update() {

		Timestamp fechaReg = Timestamp.from(Instant.now());

		Usuario u = new Usuario(usuarioUpdate.getId(), "nombre", "apellidos", 'E', "1925377335", "mi contraseña update",
				"info@ucundinamarca.edu.co", Date.valueOf("1999-10-04"), fechaReg, false, rolrepo.selectById(5));

		prueba = usuariorepo.update(u);
		usuarioEsperado = usuariorepo.selectById((long) 2);

		assertAll("En actualización del usuario.", () -> assertNotNull(getUsuarioUpdate()),
				() -> assertNotNull(usuarioEsperado),
				() -> assertEquals(true, prueba, "Consulta a bd, no se obtuvo el resultado esperado."),
				() -> assertNotEquals("Los objetos son iguales, nada cambió.", usuarioEsperado, getUsuarioUpdate()),
				() -> assertEquals("nombre", usuarioEsperado.getNombres(),
						"El nombre de usuario no corresponde con el esperado"),
				() -> assertEquals("apellidos", usuarioEsperado.getApellidos(),
						"Los apellidos del usuario no corresponde con el esperado"),
				() -> assertEquals('E', usuarioEsperado.getTipoId(),
						"El tipo de identificación del usuario no corresponde con el esperado"),
				() -> assertEquals("1925377335", usuarioEsperado.getNumId(),
						"El número de identificación de usuario no corresponde con el esperado"),
				() -> assertEquals("mi contraseña update", usuarioEsperado.getContrasena(),
						"La contraseña de usuario no corresponde con el esperado"),
				() -> assertEquals("info@ucundinamarca.edu.co", usuarioEsperado.getCorreo(),
						"El correo electrónico de usuario no corresponde con el esperado"),
				() -> assertEquals(Date.valueOf("1999-10-04"), usuarioEsperado.getFechaNac(),
						"La fecha de nacimiento del usuario no corresponde con la esperada"),
				() -> assertEquals(false, usuarioEsperado.isEstado(),
						"El estado de usuario no corresponde con el esperado"),
				() -> assertEquals(fechaReg, usuarioEsperado.getFechaReg(),
						"La fecha de registro del usuario no corresponde con la esperada"),
				() -> assertEquals((long) 5, usuarioEsperado.getRol().getId(),
						"El rol de usuario no corresponde con el esperado"));

		prueba = false;

	}

	@Test
	@Transactional
	@Order(5)
	void rollbackLastUpdate() {
		prueba = usuariorepo.update(getUsuarioUpdate());
		assertEquals(true, prueba, "update rolback a bd, no se obtuvo el resultado esperado.");
	}

	@Test
	@Transactional
	@Order(6)
	void deactivate() {
		usuario = usuariorepo.selectById((long) 2);
		prueba = usuariorepo.deactivate(usuario);
		usuarioEsperado = usuariorepo.selectById((long) 2);

		assertAll("En desactivación del usuario", () -> assertNotNull(getUsuarioUpdate()),
				() -> assertNotNull(usuarioEsperado),
				() -> assertEquals(true, prueba, "Consulta a bd, no se obtuvo el resultado esperado."),
				() -> assertEquals(usuarioEsperado.isEstado(), getUsuarioUpdate().isEstado(),
						"No se realizó la desactivacion del usuario"));

		prueba = usuariorepo.activate(usuario);
		assertEquals(true, prueba, "deactivate rollback a bd, no se obtuvo el resultado esperado.");

		prueba = false;

	}

	@Test
	@Transactional
	@Order(7)
	void activate() {
		usuario = usuariorepo.selectById((long) 2);
		prueba = usuariorepo.activate(usuario);
		usuarioEsperado = usuariorepo.selectById((long) 2);

		assertAll("En activación del usuario. ", () -> assertNotNull(usuario), () -> assertNotNull(usuarioEsperado),
				() -> assertEquals(true, prueba, "Consulta a bd, no se obtuvo el resultado esperado."),
				() -> assertEquals(usuarioEsperado.isEstado(), getUsuarioUpdate().isEstado(),
						"No se realizó la activacion del usuario"));

		prueba = usuariorepo.deactivate(usuario);
		assertEquals(true, prueba, "activate rollback a bd, no se obtuvo el resultado esperado.");

		prueba = false;
	}

	/**
	 * @return the usuarioInsert
	 */
	private Usuario getUsuarioInsert() {
		return usuarioInsert;
	}

	/**
	 * @return the usuarioUpdate
	 */
	private Usuario getUsuarioUpdate() {
		return usuarioUpdate;
	}
}
