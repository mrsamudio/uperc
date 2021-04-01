/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
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

import co.edu.ucundinamarca.uperc.persistencia.dao.ReservaDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Reserva;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.ConstantesDB;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository
public class ReservaDAOImpl  extends PersistenciaUtil implements ReservaDAO {
	
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
	public Reserva selectById(long id) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reserva> cquery = cb.createQuery(Reserva.class);
			Root<Reserva> root = cquery.from(Reserva.class);

			cquery.where(cb.equal(root.get("id"), id));
			Query<Reserva> q = session.createQuery(cquery);


			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reserva> selectByFecha(Timestamp fecha, String tipo) {
		Session session = sessionFactory.getCurrentSession();
		try {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reserva> cquery = cb.createQuery(Reserva.class);
			Root<Reserva> root = cquery.from(Reserva.class);

			switch (tipo) {
			case ConstantesDB.SOLICITUD:
				cquery.where(cb.lessThanOrEqualTo(root.get("fechaSolicitud"), fecha));
				break;
			case ConstantesDB.RESERVA:
				cquery.where(cb.lessThanOrEqualTo(root.get("fechaReserva"), fecha));
				break;
			case ConstantesDB.FIN:
				cquery.where(cb.lessThanOrEqualTo(root.get("fechaFin"), fecha));
				break;

			default:
				break;
			}
			
			
			Query<Reserva> q = session.createQuery(cquery);
			return q.getResultList();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reserva> selectByRango(Timestamp fechaInicial, Timestamp fechaFinal, String tipo) {
		Session session = sessionFactory.getCurrentSession();
		try {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reserva> cquery = cb.createQuery(Reserva.class);
			Root<Reserva> root = cquery.from(Reserva.class);
			
			switch (tipo) {
			case ConstantesDB.SOLICITUD:
				cquery.where(cb.between(root.get("fechaSolicitud"), fechaInicial, fechaFinal));
				break;
				
			case ConstantesDB.RESERVA:
				cquery.where(cb.between(root.get("fechaReserva"), fechaInicial, fechaFinal));
				break;
				
			case ConstantesDB.FIN:
				cquery.where(cb.between(root.get("fechaFin"), fechaInicial, fechaFinal));
				break;
				
			default:
				break;
			}
			
			
			Query<Reserva> q = session.createQuery(cquery);
			return q.getResultList();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reserva> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Reserva> cquery = cb.createQuery(Reserva.class);
			Root<Reserva> root = cquery.from(Reserva.class);
			cquery.select(root);
			
			Query<Reserva> q = session.createQuery(cquery);
			
			
//			CriteriaBuilder cbs = session.getCriteriaBuilder();
//	        CriteriaQuery<Reserva> cq = cbs.createQuery(Reserva.class);
//	        Root<Reserva> rootEntry = cq.from(Reserva.class);
//	        CriteriaQuery<Reserva> all = cq.select(rootEntry);
//	        TypedQuery<Reserva> allQuery = session.createQuery(all);
//	        return allQuery.getResultList();
			
			
			

			return q.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional
	public boolean insert(Reserva reserva) {
		Session session = sessionFactory.getCurrentSession();
//		int res=0;
		try {

//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<Reserva> cquery = cb.createQuery(Reserva.class);
//			CriteriaQuery<Reserva> cquery = cb.ad(Reserva.class);
//			Query<Reserva> q = session.createQuery(cquery);
			Serializable resultado =
			session.save(reserva);
			
			resultado.toString();
			
			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Reserva reserva) {
		Session session = sessionFactory.getCurrentSession();
		int res=0;
		try {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<Reserva> cquery = cb.createCriteriaUpdate(Reserva.class);
			Root<Reserva> root = cquery.from(Reserva.class);
			
			cquery.set("fechaSolicitud", reserva.getFechaSolicitud());
//			cquery.set("estado", reserva.isEstado());
			cquery.set("espacioParqueo", reserva.getEspacioParqueo().getId());
			cquery.set("fechaReserva", reserva.getFechaReserva());
//			cquery.set("fechafin", reserva.getFechaFin());
//			cquery.set("cancelada", reserva.isCancelada());
			cquery.set("usuario", reserva.getUsuario().getId());
			
			
			cquery.where(cb.equal(root.get("id"), reserva.getId()));
			
			
			res = session.createQuery(cquery).executeUpdate();
			return isResultado(res);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean endReserva(Reserva reserva, boolean isCancelada) {
		Session session = sessionFactory.getCurrentSession();
		int res=0;
		try {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<Reserva> cquery = cb.createCriteriaUpdate(Reserva.class);
			Root<Reserva> root = cquery.from(Reserva.class);
			
//			cquery.set("fechasolicitud", reserva.getFechaSolicitud());
			cquery.set("estado", false); //desactivada la reserva
//			cquery.set("espacioparqueo", reserva.getEspacioParqueo().getId());
//			cquery.set("fechareserva", reserva.getFechaReserva());
			cquery.set("fechaFin", Timestamp.from(Instant.now()));
			cquery.set("cancelada", isCancelada);
//			cquery.set("usuario", reserva.getUsuario().getId());
			
			
			cquery.where(cb.equal(root.get("id"), reserva.getId()));
			
			
			res = session.createQuery(cquery).executeUpdate();
			return isResultado(res);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
