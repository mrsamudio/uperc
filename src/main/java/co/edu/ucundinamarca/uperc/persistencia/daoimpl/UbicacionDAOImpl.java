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

import co.edu.ucundinamarca.uperc.persistencia.dao.UbicacionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Ubicacion;

/**
 * @author mrsamudio
 *
 */
@Repository("UbicacionDAO")
public class UbicacionDAOImpl implements UbicacionDAO {

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
	public UbicacionDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public Ubicacion selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Ubicacion> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from ubicacion")
				.addScalar("id", new IntegerType())
				.addScalar("nombre", new StringType())
				.addScalar("direccion", new StringType())
//				.addScalar("coordenadas", new Point())
				.addScalar("telefono", new StringType())
//				.addScalar("espaciosParqueo", new Set<EspacioParqueo>)
				.setResultTransformer(Transformers.aliasToBean(Ubicacion.class)).list();
	}

	@Override
	public boolean insert(Ubicacion ubicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Ubicacion ubicacion) {
		// TODO Auto-generated method stub
		return false;
	}

}
