/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import co.edu.ucundinamarca.uperc.persistencia.dao.PermisoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Permiso;
import co.edu.ucundinamarca.uperc.persistencia.entidades.RegistroIE;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */
@Repository
public class PermisoDAOImpl implements PermisoDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 
	 */
	public PermisoDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Permiso selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Permiso> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from permiso")
				.addScalar("id", new IntegerType())
				
//				.addScalar("usuario", new Usuario())
//				.addScalar("registroIE", new RegistroIE())
				.setResultTransformer(Transformers.aliasToBean(Permiso.class)).list();
	}

	@Override
	public boolean insert(Permiso perfilUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Permiso perfilUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
