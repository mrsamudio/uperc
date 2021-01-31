/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.SistemaExternoDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.SupervisionDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Supervision;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */
//@Repository("SupervisionDAO")
@Repository
public class SupervisionDAOImpl implements SupervisionDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Supervision selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional
	public List<Supervision> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from supervision")
				.addScalar("id", new IntegerType())
				.addScalar("mensaje", new StringType())
				.addScalar("estado", new BooleanType())
				.addScalar("fecha", new DateType())
				.addScalar("tipo", new BooleanType())
//				.addScalar("usuario", new Usuario())
				.setResultTransformer(Transformers.aliasToBean(Supervision.class)).list();
	}

	@Override
	public boolean insert(Supervision supervision) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Supervision supervision) {
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
