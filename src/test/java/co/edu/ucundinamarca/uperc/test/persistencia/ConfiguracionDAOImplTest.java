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
import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

@ActiveProfiles("test")
@ContextConfiguration(classes = ConfigFuenteDatos.class)
@ExtendWith(SpringExtension.class)
public class ConfiguracionDAOImplTest {

	@Autowired
	ConfiguracionDAO configuracionrepo;

	@Autowired
	UsuarioDAO usuariorepo;

	private Configuracion configuracion;
	private List<Configuracion> configuraciones = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		configuracion = configuracionrepo.selectById((long) 2);
		assertNotNull(configuracion);
	}

	@Test
	void selectAll() {
		configuraciones = configuracionrepo.selectAll();
		assertFalse(configuraciones.isEmpty());
	}

	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Usuario u = usuariorepo.selectById((long) 6);
		Configuracion c = new Configuracion(6, 5, 60, 3, Timestamp.from(Instant.now()), u);
		boolean test = configuracionrepo.insert(c);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	/**
	 * 
	 * En esta prueba se evidenció que si crea el objeto usuario y se utiliza dentro
	 * del objeto configuración sin posibilidad de cambiar los valores de los
	 * atributos de la entidad (es recomendable que los setters en
	 * {@link Configuracion} sean públicos), entonces hibernate no permite
	 * actualizar el mismo objeto en una sola sesión.
	 * <p>
	 *   De esta forma se obtiene el error mencionado en este comentario:
	 * <pre>
	 * {@code
	 * 
	 * Usuario u = usuariorepo.selectById((long) 2);
	 * Configuracion c = new Configuracion(u.getConfiguracion().getId(), 100, 365, 5, fechaGuard, u);
	 * boolean test = configuracionrepo.update(u.getConfiguracion());
	 * boolean test = configuracionrepo.update(c);
	 * assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
	 *  }
	 * </pre>
	 * 
	 */
	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1 
		Timestamp fechaGuard = Timestamp.from(Instant.now());
		Configuracion c = new Configuracion(2, 100, 365, 5, fechaGuard);
		boolean test = configuracionrepo.update(c);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");

		// 2
		Usuario u = usuariorepo.selectById((long) 2);
		u.getConfiguracion().setFechaGuardado(fechaGuard);
		u.getConfiguracion().setCaducidadContrasena(90);
		u.getConfiguracion().setIntentosFallidos(200);
		u.getConfiguracion().setMaxAdmin(2);
		test = configuracionrepo.update(u.getConfiguracion());
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");
	}

}
