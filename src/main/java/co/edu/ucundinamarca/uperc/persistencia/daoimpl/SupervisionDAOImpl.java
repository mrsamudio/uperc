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

import co.edu.ucundinamarca.uperc.persistencia.dao.SupervisionDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Supervision;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
//@Repository("SupervisionDAO")
@Repository
public class SupervisionDAOImpl extends PersistenciaUtil implements SupervisionDAO {

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
	public Supervision selectById(long id) {
		return sessionFactory.getCurrentSession().getSession().get(Supervision.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<Supervision> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Supervision.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(Supervision supervision) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Supervision.class.getSimpleName() 
							+ "(usuario, tipo, fecha, estado, mensaje)"
							+ " VALUES("
							+ ":usuario, :tipo, :fecha, :estado, :mensaje"
							+ ")")
					.setParameter("usuario", supervision.getUsuario().getId())
					.setParameter("tipo", supervision.isTipo())
					.setParameter("fecha", supervision.getFecha())
					.setParameter("estado", supervision.isEstado())
					.setParameter("mensaje", supervision.getMensaje())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Supervision supervision) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() 
							+ " SET"
							+ " usuario = :usuario, tipo = :tipo, fecha = :fecha, estado = :estado, mensaje = :mensaje"
							+ " WHERE id = :idconf")
					.setParameter("idconf", supervision.getId())
					.setParameter("usuario", supervision.getUsuario().getId())
					.setParameter("tipo", supervision.isTipo())
					.setParameter("fecha", supervision.getFecha())
					.setParameter("estado", supervision.isEstado())
					.setParameter("mensaje", supervision.getMensaje())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean activate(Supervision supervision) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() 
							+ " SET estado = :estado"
							+ " WHERE id = :idconf"
							+ "")
					.setParameter("idconf", supervision.getId())
					.setParameter("estado", true)
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deactivate(Supervision supervision) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() 
							+ " SET estado = :estado"
							+ " WHERE id = :idconf"
							+ "")
					.setParameter("idconf", supervision.getId())
					.setParameter("estado", false)
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			//TODO: verificar resultado
			return false;
		}
	}

}
