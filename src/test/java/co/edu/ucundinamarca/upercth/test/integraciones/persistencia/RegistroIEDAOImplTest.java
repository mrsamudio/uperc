package co.edu.ucundinamarca.upercth.test.integraciones.persistencia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import co.edu.ucundinamarca.upercth.model.dao.PermisoDAO;
import co.edu.ucundinamarca.upercth.model.dao.RecursoDAO;
import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.dao.VehiculoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Recurso;
import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;
import co.edu.ucundinamarca.upercth.model.entities.Vehiculo;


@ActiveProfiles("test")
@ContextConfiguration(classes = DataSourceConfig.class)
@ExtendWith(SpringExtension.class)
class RegistroIEDAOImplTest {


	@Autowired
	RegistroIEDAO registroierepo;

	@Autowired
	UsuarioDAO usuariorepo;

	@Autowired
	VehiculoDAO vehiculorepo;

	@Autowired
	PermisoDAO permisorepo;

	@Autowired
	RecursoDAO recursorepo;

	private RegistroIE registroie;
	private List<RegistroIE> registrosie = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		// configDao = (ConfiguracionDAO) context.getBean("ConfiguracionDAO");
		// configDao = (ConfiguracionDAO) contextoApp.getBean("ConfiguracionDAO");
	}

	@Test
	void selectById() {
		registroie = registroierepo.selectById((long) 2);
		assertNotNull(registroie);
	}

	@Test
	void selectAll() {
		registrosie = registroierepo.selectAll();
		assertFalse(registrosie.isEmpty());
		
	}

	@Test
	void selectByDate() {
		Timestamp fecha = Timestamp.valueOf("2021-02-27 00:00:00");
		
		// true, se realiza busqueda por la fecha de ingreso
		List<RegistroIE> registros = registroierepo.selectByDate(fecha, true);
		
		assertNotNull("La consulta retorna nula, error en bd", registros);
		assertFalse("La consulta no encontró registros segun el criterio de busqueda", registros.isEmpty());
		
		// false, se realiza busqueda por la fecha de egreso
		registros = registroierepo.selectByDate(fecha, false);
		
		assertFalse("La consulta no encontró registros segun el criterio de busqueda", registros.isEmpty());
		assertNotNull("La consulta retorna nula, error en bd", registros);
		
	}
	
	
	@Test
	void selectByMonth() {
//		TODO: 1.2 scompletar prueba selectByMonth
		
//		Tabla de registros_ie  consulta de seleccionar registros por mes
//
//		SELECT 	registro_ie.id, 
//			registro_ie.fechaingreso, 
//			registro_ie.fechaegreso, 
//			recurso.nombre||' '|| recurso.marca||' '|| recurso.tipo as camara,
//			vehiculo.placa||' '|| vehiculo.marca||' '|| vehiculo.color||' '|| vehiculo.clase||' '|| vehiculo.tiposervicio AS automovil,
//			usuing.nombres||' '|| usuing.apellidos AS usuario_ing,
//			usuegr.nombres||' '|| usuegr.apellidos AS usuario_egr,
//			ticketid
//
//		FROM registro_ie 
//			INNER JOIN recurso ON ( registro_ie.recursoingreso = recurso.id)
//			INNER JOIN vehiculo ON ( registro_ie.vehiculo= vehiculo.id)
//			INNER JOIN usuario AS usuing ON ( registro_ie.usuarioingreso = usuing.id)
//			INNER JOIN usuario AS usuegr ON ( registro_ie.usuarioegreso = usuegr.id)
//
//		WHERE extract(year from fechaingreso) = 2021 AND extract(month from fechaingreso)= 2;
		
		assertFalse(false);
		
	}
	
	@Test
	void selectByRange() {
//		TODO: 2.2 completar prueba selectByRange
		
//		Tabla de registros_ie  consulta consulta de seleccionar registros por un rango de fechas, inicial y final (la función BETWEEN no incluye las fechas que se escriben, por eso toca poner la fecha enterior)
//
//		SELECT 	registro_ie.id, 
//			registro_ie.fechaingreso, 
//			registro_ie.fechaegreso, 
//			recurso.nombre||' '|| recurso.marca||' '|| recurso.tipo as camara,
//			vehiculo.placa||' '|| vehiculo.marca||' '|| vehiculo.color||' '|| vehiculo.clase||' '|| vehiculo.tiposervicio AS automovil,
//			usuing.nombres||' '|| usuing.apellidos AS usuario_ing,
//			usuegr.nombres||' '|| usuegr.apellidos AS usuario_egr,
//			ticketid
//			
//		FROM registro_ie 
//			INNER JOIN recurso ON ( registro_ie.recursoingreso = recurso.id)
//			INNER JOIN vehiculo ON ( registro_ie.vehiculo= vehiculo.id)
//			INNER JOIN usuario AS usuing ON ( registro_ie.usuarioingreso = usuing.id)
//			INNER JOIN usuario AS usuegr ON ( registro_ie.usuarioegreso = usuegr.id)
//			
//		WHERE fechaingreso BETWEEN '2021-01-31'::DATE AND '2021-03-01'::DATE;
//
//
//		Tabla de registros_ie  consulta consulta de seleccionar registros por un rango de fechas, inicial y final 
//
//		SELECT 	registro_ie.id, 
//			registro_ie.fechaingreso, 
//			registro_ie.fechaegreso, 
//			recurso.nombre||' '|| recurso.marca||' '|| recurso.tipo as camara,
//			vehiculo.placa||' '|| vehiculo.marca||' '|| vehiculo.color||' '|| vehiculo.clase||' '|| vehiculo.tiposervicio AS automovil,
//			usuing.nombres||' '|| usuing.apellidos AS usuario_ing,
//			usuegr.nombres||' '|| usuegr.apellidos AS usuario_egr,
//			ticketid
//			
//		FROM registro_ie 
//			INNER JOIN recurso ON ( registro_ie.recursoingreso = recurso.id)
//			INNER JOIN vehiculo ON ( registro_ie.vehiculo= vehiculo.id)
//			INNER JOIN usuario AS usuing ON ( registro_ie.usuarioingreso = usuing.id)
//			INNER JOIN usuario AS usuegr ON ( registro_ie.usuarioegreso = usuegr.id)
//			
//		WHERE fechaingreso >= '2021-01-31'::DATE AND fechaingreso >= '2021-03-01'::DATE;
		
		assertTrue(true);
	}
	
	
	
	
	@Rollback(true)
	@Test
	@Transactional
	void insert() {
		Timestamp fechaIngreso = Timestamp.from(Instant.now());
		
		Recurso r = recursorepo.selectById((long) 1);
		Vehiculo v = vehiculorepo.selectById((long) 2);
		Usuario uing = usuariorepo.selectById((long) 6);
		String ticketId = "a0ccbc99-9F0b-47f8-bb6d-6bb9bd380aDD";
		
		RegistroIE rie = new RegistroIE(fechaIngreso, r, v, uing, ticketId);
		boolean test = registroierepo.insertI(rie);
		assertEquals(true, test, "Inserción a bd, no se obtuvo el resultado esperado.");
	}

	@Rollback(true)
	@Test
	@Transactional
	void update() {
		// 1
		Timestamp fechaEgreso = Timestamp.from(Instant.now());
		Usuario uEgre = usuariorepo.selectById((long) 4);
		Recurso rEgreso = recursorepo.selectById((long) 1);
		
		registroie = registroierepo.selectById((long) 5);
		RegistroIE rie = new RegistroIE(registroie.getId(), fechaEgreso, uEgre, rEgreso);
		
		boolean test = registroierepo.updateIE(rie);
		assertEquals(true, test, "Actualización a bd, no se obtuvo el resultado esperado.");

	}

}
