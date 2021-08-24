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

import co.edu.ucundinamarca.upercth.model.dao.RolDAO;
import co.edu.ucundinamarca.upercth.model.entities.Rol;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
//@Repository("RolDAO")
@Repository
public class RolDAOImpl extends PersistenciaUtil implements RolDAO {
	

		private SessionFactory sessionFactory;

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		@Resource(name = "factoriaSesion")
//		@Autowired
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}


	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true)
	public Rol selectById(int id) {
		return sessionFactory.getCurrentSession().getSession().get(Rol.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<Rol> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Rol.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(Rol rol) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Rol.class.getSimpleName() 
							+ "(nombre, descripcion, perfil)"
							+ " VALUES("
							+ ":nombre, :descripcion, :perfil"
							+ ")")
					.setParameter("nombre", rol.getNombre())
					.setParameter("descripcion", rol.getDescripcion())
					.setParameter("perfil", rol.getPerfil().getId())
//					.setParameter("usuario", rol.getUsuarios())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Rol rol) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Rol.class.getSimpleName() 
							+ " SET"
//							+ " nombre = :nombre"
//							+ ", "
							+ " descripcion = :descripcion"
//							+ ", perfil = :perfil"
							+ " WHERE id = :idConf")
					.setParameter("idConf", rol.getId())
//					.setParameter("nombre", rol.getNombre())
					.setParameter("descripcion", rol.getDescripcion())
//					.setParameter("perfil", rol.getPerfil().getId())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
