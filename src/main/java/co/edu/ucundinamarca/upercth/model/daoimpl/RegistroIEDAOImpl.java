/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.daoimpl;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.upercth.model.dao.RegistroIEDAO;
import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Vehiculo;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository
public class RegistroIEDAOImpl extends PersistenciaUtil implements RegistroIEDAO {

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
	public RegistroIE selectById(long id) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			cquery.where(cb.equal(root.get("id"), id));
			Query<RegistroIE> q = session.createQuery(cquery);

			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegistroIE> selectByDate(Date fecha, boolean tipo) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			if (tipo) {
				cquery.where(cb.lessThanOrEqualTo(root.get("fechaIngreso"), fecha));
			} else {
				cquery.where(cb.lessThanOrEqualTo(root.get("fechaEgreso"), fecha));
			}

			Query<RegistroIE> q = session.createQuery(cquery);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegistroIE> selectByMonth(Month mes, boolean tipo) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

//			TODO: realizar consulta de mes
			if (tipo) {
				cquery.where(cb.lessThanOrEqualTo(root.get("fechaIngreso"), mes));
			} else {
				cquery.where(cb.lessThanOrEqualTo(root.get("fechaEgreso"), mes));
			}

			Query<RegistroIE> q = session.createQuery(cquery);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegistroIE> selectByRange(Date fechaInicial, Date fechaFinal, boolean tipo) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			if (tipo) {
				cquery.where(cb.between(root.get("fechaIngreso"), fechaInicial, fechaFinal));
			} else {
				cquery.where(cb.between(root.get("fechaEgreso"), fechaInicial, fechaFinal));
			}

			Query<RegistroIE> q = session.createQuery(cquery);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegistroIE> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);
			cquery.select(root);

			Query<RegistroIE> q = session.createQuery(cquery);

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional
	public boolean insertI(RegistroIE registroI) {

		Session session = sessionFactory.getCurrentSession();
//		int res = 0;
		try {

//			Serializable resultado = 
			session.save(registroI);
			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	@Transactional
	public boolean updateIE(RegistroIE registroI) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<RegistroIE> cquery = cb.createCriteriaUpdate(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			cquery.set("recursoEgreso", registroI.getRecursoEgreso());
			cquery.set("usuarioEgreso", registroI.getUsuarioEgreso().getId());
			cquery.set("fechaEgreso", registroI.getFechaEgreso());
//			cquery.set("", registroI);

			cquery.where(cb.equal(root.get("id"), registroI.getId()));

			res = session.createQuery(cquery).executeUpdate();
			return isResultado(res);

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public RegistroIE selectByVehiculo(Vehiculo vehiculo) {
		Session session = sessionFactory.getCurrentSession();

		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(cb.equal(root.get("vehiculo"), vehiculo.getId()));

			predicates.add(cb.isNull(root.get("fechaEgreso")));
			predicates.add(cb.isNull(root.get("recursoEgreso")));
			predicates.add(cb.isNull(root.get("usuarioEgreso")));

			cquery.where(predicates.toArray(new Predicate[] {}));

			Query<RegistroIE> q = session.createQuery(cquery);
			return q.getSingleResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegistroIE> getRegistrosI() {
		Session session = sessionFactory.getCurrentSession();

		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			List<Predicate> predicates = new ArrayList<Predicate>();


			predicates.add(cb.isNull(root.get("fechaEgreso")));
			predicates.add(cb.isNull(root.get("recursoEgreso")));
			predicates.add(cb.isNull(root.get("usuarioEgreso")));

			cquery.where(predicates.toArray(new Predicate[] {}));

			Query<RegistroIE> q = session.createQuery(cquery);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RegistroIE> getRegistrosE(){
		Session session = sessionFactory.getCurrentSession();

		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			List<Predicate> predicates = new ArrayList<Predicate>();


			predicates.add(cb.isNotNull(root.get("fechaEgreso")));
			predicates.add(cb.isNotNull(root.get("recursoEgreso")));
			predicates.add(cb.isNotNull(root.get("usuarioEgreso")));

			cquery.where(predicates.toArray(new Predicate[] {}));

			Query<RegistroIE> q = session.createQuery(cquery);
			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public RegistroIE getRegistroActivo(Long usuarioIngresoId) {
		Session session = sessionFactory.getCurrentSession();

		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<RegistroIE> cquery = cb.createQuery(RegistroIE.class);
			Root<RegistroIE> root = cquery.from(RegistroIE.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(cb.equal(root.get("usuarioIngreso"), usuarioIngresoId));
			
			predicates.add(cb.isNull(root.get("fechaEgreso")));
			predicates.add(cb.isNull(root.get("recursoEgreso")));
			predicates.add(cb.isNull(root.get("usuarioEgreso")));

			cquery.where(predicates.toArray(new Predicate[] {}));

			Query<RegistroIE> q = session.createQuery(cquery);
			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

}
