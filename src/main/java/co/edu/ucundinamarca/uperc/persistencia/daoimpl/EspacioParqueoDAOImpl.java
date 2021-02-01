package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.EspacioParqueo;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Ubicacion;

@Repository
public class EspacioParqueoDAOImpl extends PersistenciaUtil implements EspacioParqueoDAO {

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
	public EspacioParqueo selectById(int id) {
		return sessionFactory.getCurrentSession().getSession().get(EspacioParqueo.class, id);
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
							//FIXME: QUITAR LOS ID PARA INSERCIONES 
							+ "(id, nombre, ubicacion, ocupado)"
							+ " VALUES(:idconf, :nombre, :ubicacion, :ocupado)")
					.setParameter("idconf", espacioParqueo.getId())
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
							+ " SET nombre = :nombre, ubicacion = :ubicacion, ocupado = :ocupado"
							+ " WHERE id = :idconf")
					.setParameter("idconf", espacioParqueo.getId())
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
	public boolean activate(EspacioParqueo espacioParqueo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + EspacioParqueo.class.getSimpleName() 
							+ " SET ocupado = :ocupado"
							+ " WHERE id = :idconf"
							+ " AND nombre = :nombre"
							+ " AND ubicacion = :ubicacion")
					.setParameter("idconf", espacioParqueo.getId())
					.setParameter("nombre", espacioParqueo.getNombre())
					.setParameter("ubicacion", espacioParqueo.getUbicacion().getId())
					.setParameter("ocupado", true)
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deActivate(EspacioParqueo espacioParqueo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + EspacioParqueo.class.getSimpleName() 
							+ " SET ocupado = :ocupado"
							+ " WHERE id = :idconf"
							+ " AND nombre = :nombre"
							+ " AND ubicacion = :ubicacion")
					.setParameter("idconf", espacioParqueo.getId())
					.setParameter("nombre", espacioParqueo.getNombre())
					.setParameter("ubicacion", espacioParqueo.getUbicacion().getId())
					.setParameter("ocupado", false)
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
