/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.daoimpl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;
import co.edu.ucundinamarca.upercth.util.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository
//@Repository("UsuarioDAO")
public class UsuarioDAOImpl extends PersistenciaUtil implements UsuarioDAO {
	
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
	public Usuario selectById(Long id) {
		return sessionFactory.getCurrentSession().getSession().get(Usuario.class, id);
	}
	
	@Transactional(readOnly = true)
	public Usuario selectByCorreo(String correo) {

		Session session = sessionFactory.getCurrentSession();
		try {

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Usuario> cquery = cb.createQuery(Usuario.class);
			Root<Usuario> root = cquery.from(Usuario.class);

			cquery.where(cb.equal(root.get("correo"), correo));

			Query<Usuario> q = session.createQuery(cquery);

			return q.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Usuario.class.getSimpleName())
				.list();
	}
	
	
	@Transactional
	public List<Usuario> selectAdmins(){
		Session session = sessionFactory.getCurrentSession();
		try {
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Usuario> cquery = cb.createQuery(Usuario.class);
			Root<Usuario> root = cquery.from(Usuario.class);
			
			cquery.select(root);
			cquery.where(cb.equal(root.get("rol"), 5));
			
			return session.createQuery(cquery).list();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean insert(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			
			res = session
					.createSQLQuery("INSERT INTO " + Usuario.class.getSimpleName() 
							+ "("
							+ "rol, estado, fechareg, fechanac, correo"
							+ ", contrasena, numid, tipoid, apellidos"
							+ ", nombres"
							+ ")"
							+ " VALUES("
							+ ":rol, :estado, :fechareg, :fechanac, :correo"
							+ ", :contrasena, :numid, :tipoid, :apellidos"
							+ ", :nombres"
							+ ")")
					.setParameter("rol", usuario.getRol().getId())
					.setParameter("estado", usuario.isEstado())
					.setParameter("fechareg", usuario.getFechaReg())
					.setParameter("fechanac", usuario.getFechaNac())
					.setParameter("correo", usuario.getCorreo())
					
					.setParameter("contrasena", passwordEncoder.encode(usuario.getContrasena()))
					
					.setParameter("numid", usuario.getNumId())
					.setParameter("tipoid", usuario.getTipoId())
					.setParameter("apellidos", usuario.getApellidos())
					.setParameter("nombres", usuario.getNombres())
					
					.executeUpdate();
			
//			session.getTransaction().commit();
			
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

//			res = session
//					.createSQLQuery("UPDATE " + Usuario.class.getSimpleName() 
//							+ " SET"
//							+ " rol = :rol, estado = :estado"
////							+ ", fechareg = :fechareg"
//							+ ", fechanac = :fechanac, correo = :correo"
////							+ ", contrasena = :contrasena"
//							+ ", numid = :numid"
//							+ ", tipoid = :tipoid, apellidos = :apellidos"
//							+ ", nombres = :nombres"
//							+ " WHERE id = :idconf")
//					.setParameter("idconf", usuario.getId())
//					.setParameter("rol", usuario.getRol().getId())
//					.setParameter("estado", usuario.isEstado())
////					.setParameter("fechareg", usuario.getFechaReg())
//					.setParameter("fechanac", usuario.getFechaNac())
//					.setParameter("correo", usuario.getCorreo())
//					
////					.setParameter("contrasena", passwordEncoder.encode(usuario.getContrasena()))
//					
//					.setParameter("numid", usuario.getNumId())
//					.setParameter("tipoid", usuario.getTipoId())
//					.setParameter("apellidos", usuario.getApellidos())
//					.setParameter("nombres", usuario.getNombres())
//					.executeUpdate();
			
			
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<Usuario> cquery = cb.createCriteriaUpdate(Usuario.class);
			Root<Usuario> root = cquery.from(Usuario.class);
			
			cquery.set("rol", usuario.getRol());
			cquery.set("estado", usuario.isEstado());
//			cquery.set("fechaReg", usuario.getFechaReg());
			cquery.set("fechaNac", usuario.getFechaNac());
			cquery.set("correo", usuario.getCorreo());
			
			if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
				cquery.set("contrasena", passwordEncoder.encode(usuario.getContrasena()));
			}
			
			cquery.set("numId", usuario.getNumId());
			cquery.set("tipoId", usuario.getTipoId());
			cquery.set("apellidos", usuario.getApellidos());
			cquery.set("nombres", usuario.getNombres());
			
			cquery.where(cb.equal(root.get("id"), usuario.getId()));
			
			res = session.createQuery(cquery).executeUpdate();
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean activate(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Usuario.class.getSimpleName() 
							+ " SET estado = :estado"
							+ " WHERE id = :idconf"
							+ "")
					.setParameter("idconf", usuario.getId())
					.setParameter("estado", true)
					
					.executeUpdate();
			
//			session.getTransaction().commit();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deactivate(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Usuario.class.getSimpleName() 
							+ " SET estado = :estado"
							+ " WHERE id = :idconf"
							+ "")
					.setParameter("idconf", usuario.getId())
					.setParameter("estado", false)
					
					.executeUpdate();
			
//			session.getTransaction().commit();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean activate(long id) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Usuario.class.getSimpleName() + " SET estado = :estado"
							+ " WHERE id = :idconf" + "")
					.setParameter("idconf", id)
					.setParameter("estado", true).executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	@Transactional
	public boolean deactivate(long id) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE " + Usuario.class.getSimpleName() + " SET estado = :estado"
							+ " WHERE id = :idconf" + "")
					.setParameter("idconf", id)
					.setParameter("estado", false).executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	

	@Override
	@Transactional
	public boolean delete(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
//		int res = 0;
		try {
//			res = session
//			.createSQLQuery("DELETE FROM " + Usuario.class.getSimpleName()
//					+ " WHERE"
//					+ " rol = :rol, estado = :estado, fechareg = :fechareg"
//					+ ", fechanac = :fechanac, correo = :correo"
//					+ ", contrasena = :contrasena, numid = :numid"
//					+ ", tipoid = :tipoid, apellidos = :apellidos"
//					+ ", nombres = :nombres"
//					+ "")
//			.setParameter("rol", usuario.getRol().getId())
//			.setParameter("estado", usuario.isEstado())
//			.setParameter("fechareg", usuario.getFechaReg())
//			.setParameter("fechanac", usuario.getFechaNac())
//			.setParameter("correo", usuario.getCorreo())
//			.setParameter("contrasena", usuario.getContrasena())
//			.setParameter("numid", usuario.getNumId())
//			.setParameter("tipoid", usuario.getTipoId())
//			.setParameter("apellidos", usuario.getApellidos())
//			.setParameter("nombres", usuario.getNombres())
//			.executeUpdate();
			session.delete(usuario);
//			session.fl
//			session.getTransaction().commit();
			return true;
//			return  isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
