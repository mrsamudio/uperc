/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.upercth.model.dao.PermisoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Permiso;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

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
//	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true)
	public Permiso selectById(long id) {
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
	public boolean insert(Permiso permiso) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Permiso.class.getSimpleName()
							+ "(id, usuario)"
							+ " VALUES("
							+ " :registroie, :usuario"
							+ ")")

					.setParameter("registroie", permiso.getRegistroIE().getId() )
					.setParameter("usuario", permiso.getUsuario().getId() )
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}
}
