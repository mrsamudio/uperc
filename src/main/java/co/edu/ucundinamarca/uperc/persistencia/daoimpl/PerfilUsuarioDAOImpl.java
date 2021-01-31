/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

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
public class PerfilUsuarioDAOImpl implements PerfilUsuarioDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public PerfilUsuario selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional
	public List<PerfilUsuario> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from perfil_usuario")
				.addScalar("id", new IntegerType())
				.addScalar("nombre", new StringType())
				.addScalar("descripcion", new StringType())
//				.addScalar("roles", new IntegerType())
				.setResultTransformer(Transformers.aliasToBean(PerfilUsuario.class)).list();
	}

	@Override
	public boolean insert(PerfilUsuario perfilUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PerfilUsuario perfilUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
