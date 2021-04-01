/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
//		Session session = sessionFactory.getCurrentSession();
//		
//		Supervision s = session.getSession().get(Supervision.class, id);
//		
//		System.out.println(s.getId());

		return sessionFactory.getCurrentSession().getSession().get(Supervision.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<Supervision> selectAll() {
		return sessionFactory.getCurrentSession().getSession().createQuery("from " + Supervision.class.getSimpleName())
				.list();
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Supervision> selectAlertas(){
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Supervision> cquery = cb.createQuery(Supervision.class);
			Root<Supervision> root = cquery.from(Supervision.class);

			cquery.where(cb.equal(root.get("tipo"), true));
			Query<Supervision> q = session.createQuery(cquery);

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Supervision> selectAvisos(){
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Supervision> cquery = cb.createQuery(Supervision.class);
			Root<Supervision> root = cquery.from(Supervision.class);

			cquery.where(cb.equal(root.get("tipo"), false));
			Query<Supervision> q = session.createQuery(cquery);

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Supervision> selectByDate(Date fecha) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Supervision> cquery = cb.createQuery(Supervision.class);
			Root<Supervision> root = cquery.from(Supervision.class);

			cquery.where(cb.lessThanOrEqualTo(root.get("fecha"), fecha));
			Query<Supervision> q = session.createQuery(cquery);

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean insert(Supervision supervision) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Supervision.class.getSimpleName()
							+ "(usuario, tipo, fecha, estado, mensaje)" + " VALUES("
							+ ":usuario, :tipo, :fecha, :estado, :mensaje" + ")")
					.setParameter("usuario", supervision.getUsuario().getId())
					.setParameter("tipo", supervision.isTipo()).setParameter("fecha", supervision.getFecha())
					.setParameter("estado", supervision.isEstado()).setParameter("mensaje", supervision.getMensaje())
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
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() + " SET"
							+ " usuario = :usuario, tipo = :tipo, fecha = :fecha, estado = :estado, mensaje = :mensaje"
							+ " WHERE id = :idconf")
					.setParameter("idconf", supervision.getId())
					.setParameter("usuario", supervision.getUsuario().getId())
					.setParameter("tipo", supervision.isTipo()).setParameter("fecha", supervision.getFecha())
					.setParameter("estado", supervision.isEstado()).setParameter("mensaje", supervision.getMensaje())
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
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() + " SET estado = :estado"
							+ " WHERE id = :idconf" + "")
					.setParameter("idconf", supervision.getId()).setParameter("estado", true).executeUpdate();

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
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() + " SET estado = :estado"
							+ " WHERE id = :idconf" + "")
					.setParameter("idconf", supervision.getId()).setParameter("estado", false).executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Override
	@Transactional
	public boolean activate(long id) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() + " SET estado = :estado"
							+ " WHERE id = :idconf" + "")
					.setParameter("idconf", id)
					.setParameter("estado", true).executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	@Transactional
	public boolean deactivate(long id) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Supervision.class.getSimpleName() + " SET estado = :estado"
							+ " WHERE id = :idconf" + "")
					.setParameter("idconf", id)
					.setParameter("estado", false).executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
