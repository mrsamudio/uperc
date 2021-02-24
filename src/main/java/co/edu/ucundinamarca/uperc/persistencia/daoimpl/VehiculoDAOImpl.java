/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.ucundinamarca.uperc.persistencia.dao.VehiculoDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Vehiculo;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.PersistenciaUtil;

/**
 * @author mrsamudio
 *
 */
@Repository("VehiculoDAO")
public class VehiculoDAOImpl extends PersistenciaUtil implements VehiculoDAO {

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
	@Transactional(readOnly = true)
	public Vehiculo selectById(long id) {
		return sessionFactory.getCurrentSession().getSession().get(Vehiculo.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo selectByPlaca(String placa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> selectByMarca(String marca) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Vehiculo.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(Vehiculo vehiculo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO " + Vehiculo.class.getSimpleName()
							+ "("
							+ "tiposervicio, clase, modelo"
							+ ", color, marca, placa"
							+ ")"
							+ " VALUES("
							+ ":tiposervicio, :clase, :modelo"
							+ ", :color, :marca, :placa"
							+ ")")
					.setParameter("tiposervicio", vehiculo.getTipoServicio())
					.setParameter("clase", vehiculo.getClase())
					.setParameter("modelo", vehiculo.getModelo())
					.setParameter("color", vehiculo.getColor())
					.setParameter("marca", vehiculo.getMarca())
					.setParameter("placa", vehiculo.getPlaca())
					.executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Vehiculo vehiculo) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("UPDATE " + Vehiculo.class.getSimpleName() 
							+ " SET"
							+ "tiposervicio = :tiposervicio, clase = :clase, modelo = :modelo"
							+ ", color = :color, marca = :marca, placa = :placa"
							+ " WHERE id = :idconf"
							+ "")
					.setParameter("idconf", vehiculo.getId())
					.setParameter("tiposervicio", vehiculo.getTipoServicio())
					.setParameter("clase", vehiculo.getClase())
					.setParameter("modelo", vehiculo.getModelo())
					.setParameter("color", vehiculo.getColor())
					.setParameter("marca", vehiculo.getMarca())
					.setParameter("placa", vehiculo.getPlaca())
					.executeUpdate();
			
			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
