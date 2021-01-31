/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.CharacterType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.SupervisionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Rol;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */
@Repository
//@Repository("UsuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO {

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
	@Transactional
	public Usuario selectById(Long id) {
		return sessionFactory.getCurrentSession().getSession().get(Usuario.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional
	public List<Usuario> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from usuario")
				.addScalar("id", new IntegerType())
				
				.addScalar("nombres", new StringType())
				.addScalar("apellidos", new StringType())
				.addScalar("tipoId", new CharacterType())
				.addScalar("numId", new StringType())
				.addScalar("contrasena", new StringType())
				.addScalar("correo", new StringType())
				.addScalar("fechaNac", new DateType())
				.addScalar("fechaReg", new DateType())
				.addScalar("estado", new BooleanType())
//				.addScalar("rol", new Rol())
//				.addScalar("configuracion", new Configuracion())
//				.addScalar("supervisiones", new Set<Supervision>)
//				.addScalar("reservas", new Set<Reserva>)
//				.addScalar("informes", new Set<Informe>)
//				.addScalar("registrosIE", new Set<RegistroIE>)
//				.addScalar("permisos", new Set<Permiso>)
				.setResultTransformer(Transformers.aliasToBean(Usuario.class)).list();
	}

	@Override
	public boolean insert(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean activate(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deactivate(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
