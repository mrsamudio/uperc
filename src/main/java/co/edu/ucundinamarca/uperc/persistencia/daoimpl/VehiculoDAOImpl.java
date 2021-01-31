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

import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.VehiculoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Vehiculo;

/**
 * @author mrsamudio
 *
 */
@Repository("VehiculoDAO")
public class VehiculoDAOImpl implements VehiculoDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Vehiculo selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Vehiculo selectByPlaca(String placa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehiculo> selectByMarca(String marca) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Vehiculo> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from vehiculo")
				.addScalar("id", new IntegerType())
				
				.addScalar("placa", new StringType())
				.addScalar("marca", new StringType())
				.addScalar("color", new StringType())
				.addScalar("modelo", new StringType())
				.addScalar("clase", new StringType())
				.addScalar("tipoServicio", new StringType())
//				.addScalar("registrosIE", new Set<RegistroIE>)
				.setResultTransformer(Transformers.aliasToBean(Vehiculo.class)).list();
	}

	@Override
	public boolean insert(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

}
