/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.daoimpl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.upercth.model.dao.SistemaExternoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.model.entities.SistemaExterno;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository("SistemaExternoDAO")
public class SistemaExternoDAOImpl extends PersistenciaUtil implements SistemaExternoDAO {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
//			res = session
//					.createSQLQuery("INSERT INTO " + "sistema_externo"
//							+ " ( inet(:ip), nombre, contrasena)"
//							+ " VALUES("
//							+ " :ip, :nombre, :contrasena"
//							+ " )")
//					.setParameter("ip", sistemaExterno.getIp())
//					.setParameter("nombre", sistemaExterno.getNombre())
//					.setParameter("contrasena", sistemaExterno.getContrasena())
//					.executeUpdate();
			
			String encodedPass = passwordEncoder.encode(sistemaExterno.getContrasena()); 
			sistemaExterno.setContrasena(encodedPass);
			
			 session.save(sistemaExterno);

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
					.createSQLQuery("UPDATE " + "sistema_externo"
							+ " SET"
							+ " ip = inet(:ip), nombre = :nombre, contrasena = :contrasena"
							+ " WHERE id = :idConf")
					.setParameter("idConf", sistemaExterno.getId())
					.setParameter("ip", sistemaExterno.getIp())
					.setParameter("nombre", sistemaExterno.getNombre())
					.setParameter("contrasena", passwordEncoder.encode(sistemaExterno.getContrasena()))
					.executeUpdate();
//			
//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaUpdate<SistemaExterno> cquery = cb.createCriteriaUpdate(SistemaExterno.class);
//			Root<SistemaExterno> root = cquery.from(SistemaExterno.class);
//			
//			cquery.set("ip", "inet(".concat(sistemaExterno.getIp()).concat(")"));
//			cquery.set("nombre", sistemaExterno.getNombre());
//			cquery.set("contrasena", sistemaExterno.getContrasena());
//			
//			cquery.where(cb.equal(root.get("id"), sistemaExterno.getId()));
//			
//			res = session.createQuery(cquery).executeUpdate();
			
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
