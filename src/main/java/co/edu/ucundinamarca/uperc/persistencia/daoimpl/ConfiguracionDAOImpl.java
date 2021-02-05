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

import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;

/**
 * Implementación de la interfaz para el acceso a datos por objetos de la
 * entidad CONFIGURACION
 * 
 * @author mrsamudio
 *
 */
@Repository
//@Repository("ConfiguracionDAO")
public class ConfiguracionDAOImpl extends PersistenciaUtil implements ConfiguracionDAO {

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
	public Configuracion selectById(Long id) {
		return sessionFactory.getCurrentSession().getSession().get(Configuracion.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	@Override
	public List<Configuracion> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Configuracion.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(Configuracion configuracion) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Configuracion.class.getSimpleName() 
							+ "("
							+ "intentosfallidos, caducidadcontrasena, maxadmin, fechaguardado, usuario"
							+ ")"
							+ " VALUES("
							+ ":falla, :caduca, :maxadmin, :fechaguar, :usuario)"
							+ "")
					.setParameter("falla", configuracion.getIntentosFallidos())
					.setParameter("caduca", configuracion.getCaducidadContrasena())
					.setParameter("maxadmin", configuracion.getMaxAdmin())
					.setParameter("fechaguar", configuracion.getFechaGuardado())
					.setParameter("usuario", configuracion.getUsuario().getId())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Configuracion configuracion) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Configuracion.class.getSimpleName() 
							+ " SET"
							+ " intentosfallidos = :falla, caducidadcontrasena = :caduca,"
							+ " maxadmin = :maxadmin, fechaguardado = :fechaguar, usuario = :usuario"
							+ " WHERE id = :idconf")
					.setParameter("idconf", configuracion.getId())
					.setParameter("falla", configuracion.getIntentosFallidos())
					.setParameter("caduca", configuracion.getCaducidadContrasena())
					.setParameter("maxadmin", configuracion.getMaxAdmin())
					.setParameter("fechaguar", configuracion.getFechaGuardado())
					.setParameter("usuario", configuracion.getUsuario().getId())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}