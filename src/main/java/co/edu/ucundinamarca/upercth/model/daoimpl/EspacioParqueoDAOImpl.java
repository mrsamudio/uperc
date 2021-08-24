package co.edu.ucundinamarca.upercth.model.daoimpl;

import java.util.Collection;
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
import org.hibernate.persister.walking.spi.CollectionDefinition;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.upercth.model.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.upercth.model.entities.EspacioParqueo;
import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

@Repository
public class EspacioParqueoDAOImpl extends PersistenciaUtil implements EspacioParqueoDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
//	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true)
	public EspacioParqueo selectById(int id) {
		return sessionFactory.getCurrentSession().getSession().get(EspacioParqueo.class, id);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<EspacioParqueo> selectByOcupado(Boolean estado) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<EspacioParqueo> cquery = cb.createQuery(EspacioParqueo.class);
			Root<EspacioParqueo> root = cquery.from(EspacioParqueo.class);
			cquery.select(root);
			cquery.where(cb.equal(root.get("ocupado"), estado));
			
			return session.createQuery(cquery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<EspacioParqueo> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + EspacioParqueo.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(EspacioParqueo espacioParqueo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + EspacioParqueo.class.getSimpleName()
							+ "("
							+ "nombre, ubicacion, ocupado"
							+ ")"
							+ " VALUES("
							+ ":nombre, :ubicacion, :ocupado)")
					.setParameter("nombre", espacioParqueo.getNombre())
					.setParameter("ubicacion", espacioParqueo.getUbicacion().getId())
					.setParameter("ocupado", espacioParqueo.isOcupado())
//					.setParameter("reservas", espacioParqueo.getReservas().iterator())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(EspacioParqueo espacioParqueo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + EspacioParqueo.class.getSimpleName() 
							+ " SET"
							+ " nombre = :nombre"
//							+ ", ubicacion = :ubicacion"
//							+ ", ocupado = :ocupado"
							+ " WHERE id = :idconf"
							+ "")
					.setParameter("idconf", espacioParqueo.getId())
					.setParameter("nombre", espacioParqueo.getNombre())
//					.setParameter("ubicacion", espacioParqueo.getUbicacion().getId())
//					.setParameter("ocupado", espacioParqueo.isOcupado())
//					.setParameter("reservas", espacioParqueo.getReservas().iterator())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean activate(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<EspacioParqueo> cquery = cb.createCriteriaUpdate(EspacioParqueo.class);
			Root<EspacioParqueo> root = cquery.from(EspacioParqueo.class);
			
			cquery.set("ocupado", true);
			cquery.where(cb.equal(root.get("id"), id));
			
			res = session.createQuery(cquery).executeUpdate();
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deActivate(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<EspacioParqueo> cquery = cb.createCriteriaUpdate(EspacioParqueo.class);
			Root<EspacioParqueo> root = cquery.from(EspacioParqueo.class);
			
			cquery.set("ocupado", false);
			cquery.where(cb.equal(root.get("id"), id));
			
			res = session.createQuery(cquery).executeUpdate();
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
