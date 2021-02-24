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

import co.edu.ucundinamarca.uperc.persistencia.dao.UbicacionDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Ubicacion;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository("UbicacionDAO")
public class UbicacionDAOImpl extends PersistenciaUtil implements UbicacionDAO {

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
	public Ubicacion selectById(int id) {
		return sessionFactory.getCurrentSession().getSession().get(Ubicacion.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Ubicacion.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(Ubicacion ubicacion) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Ubicacion.class.getSimpleName() 
							+ "(telefono, coordenadas, direccion, nombre)"
							+ " VALUES("
							+ ":telefono, :coordenadas, :direccion, :nombre"
							+ ")")
					.setParameter("telefono", ubicacion.getTelefono())
					.setParameter("coordenadas", ubicacion.getCoordenadas())
					.setParameter("direccion", ubicacion.getDireccion())
					.setParameter("nombre", ubicacion.getNombre())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Ubicacion ubicacion) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Ubicacion.class.getSimpleName() 
							+ " SET"
							+ "telefono, coordenadas, direccion, nombre"
							+ "telefono = :telefono, coordenadas = :coordenadas, direccion = :direccion, nombre = :nombre"
							+ " WHERE id = :idconf")
					.setParameter("idconf", ubicacion.getId())
					.setParameter("telefono", ubicacion.getTelefono())
					.setParameter("coordenadas", ubicacion.getCoordenadas())
					.setParameter("direccion", ubicacion.getDireccion())
					.setParameter("nombre", ubicacion.getNombre())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
