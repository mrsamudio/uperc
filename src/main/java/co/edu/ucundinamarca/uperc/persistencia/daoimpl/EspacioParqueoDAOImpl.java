package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import co.edu.ucundinamarca.uperc.persistencia.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.EspacioParqueo;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Ubicacion;

@Repository
public class EspacioParqueoDAOImpl implements EspacioParqueoDAO {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	@Override
	public EspacioParqueo selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<EspacioParqueo> selectAll() {
		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from espacioparqueo")
				.addScalar("id", new IntegerType())
				
				.addScalar("nombre", new StringType())
//				.addScalar("ubicacion", new Ubicacion())
				.addScalar("ocupado", new BooleanType())
//				.addScalar("reservas", new Set<Reserva>)
				.setResultTransformer(Transformers.aliasToBean(EspacioParqueo.class)).list();
	}

	@Override
	public boolean insert(EspacioParqueo espacioParqueo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(EspacioParqueo espacioParqueo) {
		// TODO Auto-generated method stub
		return false;
	}

}
