/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.daoimpl;

import java.io.Serializable;
import java.util.Collections;
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
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.upercth.model.dao.RegServicioDAO;
import co.edu.ucundinamarca.upercth.model.entities.RegServicio;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository
public class RegServicioDAOImpl extends PersistenciaUtil implements RegServicioDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
//	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly = true)
	public RegServicio selectById(long id) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegServicio> cquery = cb.createQuery(RegServicio.class);
			Root<RegServicio> root = cquery.from(RegServicio.class);

			cquery.where(cb.equal(root.get("id"), id));
			Query<RegServicio> q = session.createQuery(cquery);

			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public RegServicio selectByIdSession(String idSession) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegServicio> cquery = cb.createQuery(RegServicio.class);
			Root<RegServicio> root = cquery.from(RegServicio.class);

			cquery.where(cb.equal(root.get("idSession"), idSession));
			Query<RegServicio> q = session.createQuery(cquery);

			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegServicio> selectByFechaSession(Date fechaSession) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegServicio> cquery = cb.createQuery(RegServicio.class);
			Root<RegServicio> root = cquery.from(RegServicio.class);

			cquery.where(cb.lessThanOrEqualTo(root.get("fechaSession"), fechaSession));

			Query<RegServicio> q = session.createQuery(cquery);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegServicio> selectByFechaSession(Date fechaSession, String idSession) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegServicio> cquery = cb.createQuery(RegServicio.class);
			Root<RegServicio> root = cquery.from(RegServicio.class);

			cquery.where(cb.lessThanOrEqualTo(root.get("fechaSession"), fechaSession));
			cquery.where(cb.equal(root.get("idSession"), idSession));

			Query<RegServicio> q = session.createQuery(cquery);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegServicio> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegServicio> cquery = cb.createQuery(RegServicio.class);
			Root<RegServicio> root = cquery.from(RegServicio.class);
			cquery.select(root);

			Query<RegServicio> q = session.createQuery(cquery);

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional
	public boolean insert(RegServicio regServicio) {
		Session session = sessionFactory.getCurrentSession();
//		int res = 0;
		try {

			Serializable resultado = session.save(regServicio);
			resultado.toString();

			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(RegServicio regServicio) {
		Session session = sessionFactory.getCurrentSession();
		int res=0;
		try {
//			
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaUpdate<RegServicio> cquery = cb.createCriteriaUpdate(RegServicio.class);
//			Root<RegServicio> root = cquery.from(RegServicio.class);
//			
//			cquery.set("idSession", regServicio.getIdSession());
//			cquery.set("sistemaExterno", regServicio.getSistemaExterno().getId());
//			cquery.set("fechaSession", regServicio.getFechaSession());
//			
//			
//			cquery.where(cb.equal(root.get("id"), regServicio.getId()));
//			
//			
//			res = session.createQuery(cquery).executeUpdate();
			
			
			res = session
					.createSQLQuery("UPDATE " + "reg_servicio"
							+ " SET"
							+ " idsession = uuid(:idsession)"
							+ ", sistemaexterno = :sistemaexterno"
							+ ", fechasession = :fechasession"
							+ " WHERE id = :idConf"
							+ "")
					
					.setParameter("idsession", regServicio.getIdSession())
					.setParameter("sistemaexterno", regServicio.getSistemaExterno())
					.setParameter("fechasession", regServicio.getFechaSession())
					
					.setParameter("idConf", regServicio.getId())
					
					.executeUpdate();
			
			
			return isResultado(res);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
