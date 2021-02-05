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

import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */
@Repository
//@Repository("UsuarioDAO")
public class UsuarioDAOImpl extends PersistenciaUtil implements UsuarioDAO {

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
	public Usuario selectById(Long id) {
		return sessionFactory.getCurrentSession().getSession().get(Usuario.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Usuario.class.getSimpleName()).list();
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
					.setParameter("contrasena", usuario.getContrasena())
					.setParameter("numid", usuario.getNumId())
					.setParameter("tipoid", usuario.getTipoId())
					.setParameter("apellidos", usuario.getApellidos())
					.setParameter("nombres", usuario.getNombres())
					
					.executeUpdate();

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

			res = session
					.createSQLQuery("UPDATE " + Usuario.class.getSimpleName() 
							+ " SET"
							+ " rol = :rol, estado = :estado, fechareg = :fechareg"
							+ ", fechanac = :fechanac, correo = :correo"
							+ ", contrasena = :contrasena, numid = :numid"
							+ ", tipoid = :tipoid, apellidos = :apellidos"
							+ ", nombres = :nombres"
							+ " WHERE id = :idconf")
					.setParameter("idconf", usuario.getId())
					.setParameter("rol", usuario.getRol().getId())
					.setParameter("estado", usuario.isEstado())
					.setParameter("fechareg", usuario.getFechaReg())
					.setParameter("fechanac", usuario.getFechaNac())
					.setParameter("correo", usuario.getCorreo())
					.setParameter("contrasena", usuario.getContrasena())
					.setParameter("numid", usuario.getNumId())
					.setParameter("tipoid", usuario.getTipoId())
					.setParameter("apellidos", usuario.getApellidos())
					.setParameter("nombres", usuario.getNombres())
					.executeUpdate();

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

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			//TODO: verificar resultado
			return false;
		}
	}

}