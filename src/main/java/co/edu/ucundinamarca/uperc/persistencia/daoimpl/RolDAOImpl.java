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

import co.edu.ucundinamarca.uperc.persistencia.dao.ReservaDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RolDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.PerfilUsuario;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Rol;

/**
 * @author mrsamudio
 *
 */
//@Repository("RolDAO")
@Repository
public class RolDAOImpl implements RolDAO {
	

		private SessionFactory sessionFactory;

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		@Resource(name = "factoriaSesion")
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

	/**
	 * 
	 */
	public RolDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rol selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional
	public List<Rol> selectAll() {
		
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from rol")
				.addScalar("id", new IntegerType())
				.addScalar("nombre", new StringType())
				.addScalar("descripcion", new StringType())
//				.addScalar("perfilUsuario", new PerfilUsuario())
//				.addScalar("usuarios", new Set<Usuario>)
				.setResultTransformer(Transformers.aliasToBean(Rol.class)).list();
	}

	@Override
	public boolean insert(Rol rol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Rol rol) {
		// TODO Auto-generated method stub
		return false;
	}

}
