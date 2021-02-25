/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.InformeDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Informe;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository
//@Repository("InformeDAO")
public class InformeDAOImpl extends PersistenciaUtil implements InformeDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true)
	public Informe selectById(long id) {
		return sessionFactory.getCurrentSession().getSession().get(Informe.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	@Override
	public List<Informe> selectAll() {
		return sessionFactory.getCurrentSession().getSession().createQuery("from " + Informe.class.getSimpleName())
				.list();
	}

	@Override
	@Transactional
	public boolean insertU(Informe informe) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Informe.class.getSimpleName()
							+ "(usuario, fechaGenerado, fechaInicio, fechaFin,"
							+ " disponibilidad, reservasOk, reservasFail, recogOk, recogFail,"
							+ " recogTotal, ingresosTotal, egresosTotal)"
							+ " VALUES("
							+ " :usuario, :fechaGenerado,"
							+ " :fechaInicio, :fechaFin,"
							+ " :disponibilidad, :reservasOk,"
							+ " :reservasFail, :recogOk,"
							+ " :recogFail, :recogTotal,"
							+ " :ingresosTotal, :egresosTotal"
							+ ")")

							.setParameter("usuario", informe.getUsuario().getId() )
							.setParameter("fechaGenerado", informe.getFechaGenerado() )
							.setParameter("fechaInicio", informe.getFechaInicio() )
							.setParameter("fechaFin", informe.getFechaFin() )
							.setParameter("disponibilidad", informe.getDisponibilidad() )
							.setParameter("reservasOk", informe.getReservasOk() )
							.setParameter("reservasFail", informe.getReservasFail() )
							.setParameter("recogOk", informe.getRecogOk() )
							.setParameter("recogFail", informe.getRecogFail() )
							.setParameter("recogTotal", informe.getRecogTotal() )
							.setParameter("ingresosTotal", informe.getIngresosTotal() )
							.setParameter("egresosTotal", informe.getEgresosTotal() )
							.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	@Transactional
	public boolean insertR(Informe informe) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Informe.class.getSimpleName()
							+ "(regServicio, fechaGenerado, fechaInicio, fechaFin,"
							+ " disponibilidad, reservasOk, reservasFail, recogOk, recogFail,"
							+ " recogTotal, ingresosTotal, egresosTotal)"
							+ " VALUES("
							+ " :regServicio,"
							+ " :fechaGenerado, :fechaInicio,"
							+ " :fechaFin, :disponibilidad,"
							+ " :reservasOk, :reservasFail,"
							+ " :recogOk, :recogFail, :recogTotal,"
							+ " :ingresosTotal, :egresosTotal"
							+ ")")
					
					.setParameter("regServicio", informe.getRegServicio().getId() )
					.setParameter("fechaGenerado", informe.getFechaGenerado() )
					.setParameter("fechaInicio", informe.getFechaInicio() )
					.setParameter("fechaFin", informe.getFechaFin() )
					.setParameter("disponibilidad", informe.getDisponibilidad() )
					.setParameter("reservasOk", informe.getReservasOk() )
					.setParameter("reservasFail", informe.getReservasFail() )
					.setParameter("recogOk", informe.getRecogOk() )
					.setParameter("recogFail", informe.getRecogFail() )
					.setParameter("recogTotal", informe.getRecogTotal() )
					.setParameter("ingresosTotal", informe.getIngresosTotal() )
					.setParameter("egresosTotal", informe.getEgresosTotal() )
					.executeUpdate();
			
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean updateU(Informe informe) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Informe.class.getSimpleName() 
							+ " SET"
							+ " usuario = :usuario,"
							+ " fechaGenerado = :fechaGenerado, fechaInicio = :fechaInicio,"
							+ " fechaFin = :fechaFin, disponibilidad = :disponibilidad,"
							+ " reservasOk = :reservasOk, reservasFail = :reservasFail,"
							+ " recogOk = :recogOk, recogFail = :recogFail, recogTotal = :recogTotal,"
							+ " ingresosTotal = :ingresosTotal, egresosTotal = :egresosTotal"
							+ " WHERE id = :idconf")
					
					.setParameter("idconf", informe.getId())
					.setParameter("usuario", informe.getUsuario().getId() )
					.setParameter("fechaGenerado", informe.getFechaGenerado() )
					.setParameter("fechaInicio", informe.getFechaInicio() )
					.setParameter("fechaFin", informe.getFechaFin() )
					.setParameter("disponibilidad", informe.getDisponibilidad() )
					.setParameter("reservasOk", informe.getReservasOk() )
					.setParameter("reservasFail", informe.getReservasFail() )
					.setParameter("recogOk", informe.getRecogOk() )
					.setParameter("recogFail", informe.getRecogFail() )
					.setParameter("recogTotal", informe.getRecogTotal() )
					.setParameter("ingresosTotal", informe.getIngresosTotal() )
					.setParameter("egresosTotal", informe.getEgresosTotal() )
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean updateR(Informe informe) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			
			res = session
					.createSQLQuery("UPDATE " + Informe.class.getSimpleName() 
							+ " SET"
							+ " regServicio = :regServicio,"
							+ " fechaGenerado = :fechaGenerado, fechaInicio = :fechaInicio,"
							+ " fechaFin = :fechaFin, disponibilidad = :disponibilidad,"
							+ " reservasOk = :reservasOk, reservasFail = :reservasFail,"
							+ " recogOk = :recogOk, recogFail = :recogFail, recogTotal = :recogTotal,"
							+ " ingresosTotal = :ingresosTotal, egresosTotal = :egresosTotal"
							+ " WHERE id = :idconf")
					
					.setParameter("idconf", informe.getId())
					.setParameter("regServicio", informe.getRegServicio().getId() )
					.setParameter("fechaGenerado", informe.getFechaGenerado() )
					.setParameter("fechaInicio", informe.getFechaInicio() )
					.setParameter("fechaFin", informe.getFechaFin() )
					.setParameter("disponibilidad", informe.getDisponibilidad() )
					.setParameter("reservasOk", informe.getReservasOk() )
					.setParameter("reservasFail", informe.getReservasFail() )
					.setParameter("recogOk", informe.getRecogOk() )
					.setParameter("recogFail", informe.getRecogFail() )
					.setParameter("recogTotal", informe.getRecogTotal() )
					.setParameter("ingresosTotal", informe.getIngresosTotal() )
					.setParameter("egresosTotal", informe.getEgresosTotal() )
					.executeUpdate();
			
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(Informe informe) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("DELETE FROM " + Informe.class.getSimpleName() 
							+ " WHERE id = :idconf")
					.setParameter("idconf", informe.getId())
					
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
