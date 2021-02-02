/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.InformeDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.PerfilUsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Informe;
import co.edu.ucundinamarca.uperc.persistencia.entidades.PerfilUsuario;

/**
 * @author mrsamudio
 *
 */
@Repository
//@Repository("PerfilUsuarioDAO")
public class PerfilUsuarioDAOImpl extends PersistenciaUtil implements PerfilUsuarioDAO {

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
	public PerfilUsuario selectById(int id) {
		return sessionFactory.getCurrentSession().getSession().get(PerfilUsuario.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<PerfilUsuario> selectAll() {
		return sessionFactory.getCurrentSession().getSession().createQuery("from " + PerfilUsuario.class.getSimpleName())
				.list();
	}

	@Override
	@Transactional
	public boolean insert(PerfilUsuario perfilUsuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + PerfilUsuario.class.getSimpleName()
							+ "(nombre, descripcion)"
							+ " VALUES("
							+ " nombre = :nombre, descripcion = :descripcion"
							+ ")")

							.setParameter("nombre", perfilUsuario.getNombre() )
							.setParameter("descripcion", perfilUsuario.getDescripcion() )
							.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean update(PerfilUsuario perfilUsuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + PerfilUsuario.class.getSimpleName() 
							+ " SET"
							+ " nombre = :nombre, descripcion = :descripcion"
							+ " WHERE id = :idconf")
					
					.setParameter("idconf", perfilUsuario.getId())
					.setParameter("nombre", perfilUsuario.getNombre() )
					.setParameter("descripcion", perfilUsuario.getDescripcion() )
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
