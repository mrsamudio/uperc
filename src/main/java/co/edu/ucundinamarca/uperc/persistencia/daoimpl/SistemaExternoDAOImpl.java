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
import co.edu.ucundinamarca.uperc.persistencia.dao.SistemaExternoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.SistemaExterno;

/**
 * @author mrsamudio
 *
 */
@Repository("SistemaExternoDAO")
public class SistemaExternoDAOImpl implements SistemaExternoDAO {

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
	public SistemaExterno selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<SistemaExterno> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from sistema_externo")
				.addScalar("id", new IntegerType())
				.addScalar("ip", new StringType())
				.addScalar("nombre", new StringType())
				.addScalar("contrasena", new StringType())
//				.addScalar("regServicios", new Set<RegServicio>)
				.setResultTransformer(Transformers.aliasToBean(SistemaExterno.class)).list();
	}

	@Override
	public boolean insert(SistemaExterno sistemaExterno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(SistemaExterno sistemaExterno) {
		// TODO Auto-generated method stub
		return false;
	}

}
