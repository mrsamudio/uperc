/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.InformeDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Informe;

/**
 * @author mrsamudio
 *
 */
@Repository
//@Repository("InformeDAO")
public class InformeDAOImpl implements InformeDAO {
	
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
	public Informe selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	@Override
	public List<Informe> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from informe")
				.addScalar("id", new IntegerType())
//				.addScalar("usuario", new IntegerType())
//				.addScalar("regServicio", new IntegerType())
				.addScalar("fechaGenerado", new DateType())
				.addScalar("fechaInicio", new DateType())
				.addScalar("fechaFin", new DateType())
				.addScalar("disponibilidad", new DoubleType())
				.addScalar("reservasOk", new DoubleType())
				.addScalar("reservasFail", new DoubleType())
				.addScalar("recogOk", new DoubleType())
				.addScalar("recogFail", new DoubleType())
				.addScalar("recogTotal", new IntegerType())
				.addScalar("ingresosTotal", new IntegerType())
				.addScalar("egresosTotal", new IntegerType())
				.setResultTransformer(Transformers.aliasToBean(Informe.class)).list();
	}

	@Override
	public boolean insert(Informe informe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Informe informe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Informe informe) {
		// TODO Auto-generated method stub
		return false;
	}

}
