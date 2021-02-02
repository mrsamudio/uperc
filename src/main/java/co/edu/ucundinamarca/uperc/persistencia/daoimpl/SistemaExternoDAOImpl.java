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

import co.edu.ucundinamarca.uperc.persistencia.dao.ReservaDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.SistemaExternoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.SistemaExterno;

/**
 * @author mrsamudio
 *
 */
@Repository("SistemaExternoDAO")
public class SistemaExternoDAOImpl extends PersistenciaUtil implements SistemaExternoDAO {

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
	public SistemaExterno selectById(int id) {
		return sessionFactory.getCurrentSession().getSession().get(SistemaExterno.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<SistemaExterno> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + SistemaExterno.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(SistemaExterno sistemaExterno) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + SistemaExterno.class.getSimpleName() 
							+ "( ip, nombre, contrasena)"
							+ " VALUES("
							+ ":ip, :nombre, :contrasena"
							+ ")")
					.setParameter("ip", sistemaExterno.getIp())
					.setParameter("nombre", sistemaExterno.getNombre())
					.setParameter("contrasena", sistemaExterno.getContrasena())
//					.setParameter("idConf", sistemaExterno.getId())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(SistemaExterno sistemaExterno) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + SistemaExterno.class.getSimpleName() 
							+ " SET"
							+ " ip = :ip, nombre = :nombre, contrasena = :contrasena"
							+ " WHERE id = :idconf")
					.setParameter("idConf", sistemaExterno.getId())
					.setParameter("ip", sistemaExterno.getIp())
					.setParameter("nombre", sistemaExterno.getNombre())
					.setParameter("contrasena", sistemaExterno.getContrasena())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
