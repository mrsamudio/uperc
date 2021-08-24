/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.daoimpl;

import java.util.Collections;
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

import co.edu.ucundinamarca.upercth.model.dao.VehiculoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Vehiculo;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository("VehiculoDAO")
public class VehiculoDAOImpl extends PersistenciaUtil implements VehiculoDAO {

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
	public Vehiculo selectById(long id) {
		return sessionFactory.getCurrentSession().getSession().get(Vehiculo.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo selectByPlaca(String placa) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Vehiculo> cquery = cb.createQuery(Vehiculo.class);
			Root<Vehiculo> root = cquery.from(Vehiculo.class);

			cquery.where(cb.equal(root.get("placa"), placa));
			Query<Vehiculo> q = session.createQuery(cquery);
			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> selectByMarca(String marca) {
		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Vehiculo> cquery = cb.createQuery(Vehiculo.class);
//			CriteriaQuery<Object[]> cquery = cb.createQuery(Object[].class);
			Root<Vehiculo> root = cquery.from(Vehiculo.class);

//			cquery.multiselect(root.get("marca"), root.get("Name"));
//			cquery.select(root.get("marca"));
			cquery.where(cb.like(root.get("marca"), "%" + marca + "%"));

//			Query<Vehiculo> q = session.createQuery(cquery);

			return session.createQuery(cquery).getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> selectAll() {
		return sessionFactory.getCurrentSession().getSession().createQuery("from " + Vehiculo.class.getSimpleName())
				.list();
	}

	
	@Override
	@Transactional
	public boolean insert(Vehiculo vehiculo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Vehiculo.class.getSimpleName() + "("
							+ "tiposervicio, clase, modelo" + ", color, marca, placa" + ")" + " VALUES("
							+ ":tiposervicio, :clase, :modelo" + ", :color, :marca, :placa" + ")")
					.setParameter("tiposervicio", vehiculo.getTipoServicio()).setParameter("clase", vehiculo.getClase())
					.setParameter("modelo", vehiculo.getModelo()).setParameter("color", vehiculo.getColor())
					.setParameter("marca", vehiculo.getMarca()).setParameter("placa", vehiculo.getPlaca())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Vehiculo vehiculo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("UPDATE " + Vehiculo.class.getSimpleName() + " SET"
							+ " tiposervicio = :tiposervicio, clase = :clase, modelo = :modelo"
							+ ", color = :color, marca = :marca, placa = :placa" + " WHERE id = :idconf" + "")
					.setParameter("idconf", vehiculo.getId()).setParameter("tiposervicio", vehiculo.getTipoServicio())
					.setParameter("clase", vehiculo.getClase()).setParameter("modelo", vehiculo.getModelo())
					.setParameter("color", vehiculo.getColor()).setParameter("marca", vehiculo.getMarca())
					.setParameter("placa", vehiculo.getPlaca())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
