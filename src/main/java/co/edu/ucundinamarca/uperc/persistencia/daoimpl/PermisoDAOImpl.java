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

import co.edu.ucundinamarca.uperc.persistencia.dao.PermisoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Permiso;

/**
 * @author mrsamudio
 *
 */
@Repository
public class PermisoDAOImpl extends PersistenciaUtil implements PermisoDAO {

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
	public Permiso selectById(int id) {
		return sessionFactory.getCurrentSession().getSession().get(Permiso.class, id);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public List<Permiso> selectAll() {
		return sessionFactory.getCurrentSession().getSession().createQuery("from " + Permiso.class.getSimpleName())
				.list();
	}

	@Override
	@Transactional
	public boolean insert(Permiso perfilUsuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Permiso.class.getSimpleName()
							+ "(usuario, registroie)"
							+ " VALUES("
							+ " usuario = :usuario, registroie = :registroie"
							+ ")")

					.setParameter("usuario", perfilUsuario.getUsuario().getId() )
					.setParameter("regServicio", perfilUsuario.getRegistroIE().getId() )
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	@Transactional
	public boolean update(Permiso perfilUsuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Permiso.class.getSimpleName() 
							+ " SET"
							+ " usuario = :usuario, registroie = :registroie"
							+ " WHERE id = :idconf")
					
					.setParameter("idconf", perfilUsuario.getId())
					.setParameter("usuario", perfilUsuario.getUsuario().getId() )
					.setParameter("regServicio", perfilUsuario.getRegistroIE().getId() )
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
