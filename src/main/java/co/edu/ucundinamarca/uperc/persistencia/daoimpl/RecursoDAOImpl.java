/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.RecursoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Recurso;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository
public class RecursoDAOImpl extends PersistenciaUtil implements RecursoDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly = true)
	public Recurso selectById(long id) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Recurso> cquery = cb.createQuery(Recurso.class);
			Root<Recurso> root = cquery.from(Recurso.class);

			cquery.where(cb.equal(root.get("id"), id));
			Query<Recurso> q = session.createQuery(cquery);

			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recurso> selectByMarca(String marca) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Recurso> cquery = cb.createQuery(Recurso.class);
			Root<Recurso> root = cquery.from(Recurso.class);

			cquery.where(cb.like(root.get("marca"), "%" + marca + "%"));
			Query<Recurso> q = session.createQuery(cquery);

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recurso> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Recurso> cquery = cb.createQuery(Recurso.class);
			Root<Recurso> root = cquery.from(Recurso.class);
			cquery.select(root);

			Query<Recurso> q = session.createQuery(cquery);

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional
	public boolean insert(Recurso recurso) {
		Session session = sessionFactory.getCurrentSession();
		try {

//			Serializable resultado = 
			session.save(recurso);

			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Recurso recurso) {
		Session session = sessionFactory.getCurrentSession();
		int res=0;
		try {
			
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaUpdate<Recurso> cquery = cb.createCriteriaUpdate(Recurso.class);
//			Root<Recurso> root = cquery.from(Recurso.class);
//			
//			
////			cquery.set("estado", recurso.isEstado());
//			cquery.set("urlFabricante", recurso.getUrlFabricante());
//			cquery.set("fechaRegistro", recurso.getFechaRegistro());
//			cquery.set("protocolo", recurso.getProtocolo());
//			
//			cquery.set(root.get("mac"), "macaddr(" + recurso.getMac() + ")");
//
////			cquery.set(cb.function("macaddr",, recurso.getMac()));
//
//			
//			cquery.set("puerto", recurso.getPuerto());
//			cquery.set("ip", "inet(" + recurso.getIp().toString() + ")");
//			cquery.set("tipo", recurso.getTipo());
//			cquery.set("marca", recurso.getMarca());
//			cquery.set("nombre", recurso.getNombre());
//			
//			cquery.where(cb.equal(root.get("id"), recurso.getId()));
//			
//			res = session.createQuery(cquery).executeUpdate();
			
			res = session
					.createSQLQuery("UPDATE " + Recurso.class.getSimpleName() 
							+ " SET"
							+ " puerto = :puerto, ip = inet(:ip)"
							+ ", tipo = :tipo, marca = :marca, nombre = :nombre"
							+ ", mac = macaddr(:mac), protocolo = :protocolo"
							+ ", fecharegistro = :fecharegistro"
							+ ", urlfabricante = :urlfabricante"
							+ " WHERE id = :idConf"
							+ "")
					
					.setParameter("puerto", recurso.getPuerto())
					.setParameter("ip", recurso.getIp())
					.setParameter("tipo", recurso.getTipo())
					.setParameter("marca", recurso.getMarca())
					.setParameter("nombre", recurso.getNombre())
					
					.setParameter("mac", recurso.getMac())
					.setParameter("protocolo", recurso.getProtocolo())
					.setParameter("fecharegistro", recurso.getFechaRegistro())
					.setParameter("urlfabricante", recurso.getUrlFabricante())
					
					.setParameter("idConf", recurso.getId())
					
					.executeUpdate();

			return isResultado(res);

			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean activate(Recurso recurso) {
		Session session = sessionFactory.getCurrentSession();
		int res=0;
		try {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<Recurso> cquery = cb.createCriteriaUpdate(Recurso.class);
			Root<Recurso> root = cquery.from(Recurso.class);
			
			cquery.set("estado", true);
			
			cquery.where(cb.equal(root.get("id"), recurso.getId()));
			
			res = session.createQuery(cquery).executeUpdate();
			
			return isResultado(res);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deactivate(Recurso recurso) {
		Session session = sessionFactory.getCurrentSession();
		int res=0;
		try {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<Recurso> cquery = cb.createCriteriaUpdate(Recurso.class);
			Root<Recurso> root = cquery.from(Recurso.class);
			
			cquery.set("estado", false);
			
			cquery.where(cb.equal(root.get("id"), recurso.getId()));
			
			res = session.createQuery(cquery).executeUpdate();
			
			return isResultado(res);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}