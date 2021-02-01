/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.model.source.spi.HibernateTypeSource;
import org.hibernate.boot.spi.MetadataBuildingContext;
import org.hibernate.mapping.OneToOne;
import org.hibernate.mapping.Table;
import org.hibernate.transform.Transformers;
import org.hibernate.type.AnyType;
import org.hibernate.type.ClassType;
import org.hibernate.type.CustomType;
import org.hibernate.type.DateType;
import org.hibernate.type.ForeignKeyDirection;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.OneToOneType;
import org.hibernate.type.SerializableType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.hibernate.type.TypeFactory.TypeScope;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * Implementación de la interfaz para el acceso a datos por objetos de la
 * entidad CONFIGURACION
 * 
 * @author mrsamudio
 *
 */
@Repository
//@Repository("ConfiguracionDAO")
public class ConfiguracionDAOImpl extends PersistenciaUtil implements ConfiguracionDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "factoriaSesion")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @implNote asdlfkj
	 * @since 1.0
	 */
	@SuppressWarnings("deprecation")
	@Override
	@Transactional(readOnly = true)
	public Configuracion selectById(Long id) {
		return sessionFactory.getCurrentSession().getSession().get(Configuracion.class, id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	@Override
	public List<Configuracion> selectAll() {
		return sessionFactory.getCurrentSession().getSession()
				.createQuery("from " + Configuracion.class.getSimpleName()).list();
	}

	@Override
	@Transactional
	public boolean insert(Configuracion configuracion) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {
			res = session
					.createSQLQuery("INSERT INTO configuracion"
							+ "(intentosfallidos, caducidadcontrasena, maxadmin, fechaguardado, usuario)"
							+ " VALUES(:falla, :caduca, :maxadmin, :fechaguar, :usuario)")
					.setParameter("falla", configuracion.getIntentosFallidos())
					.setParameter("caduca", configuracion.getCaducidadContrasena())
					.setParameter("maxadmin", configuracion.getMaxAdmin())
					.setParameter("fechaguar", configuracion.getFechaGuardado())
					.setParameter("usuario", configuracion.getUsuario().getId()).executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean update(Configuracion configuracion) {
		Session session = sessionFactory.getCurrentSession();
		int res = 0;
		try {

			res = session
					.createSQLQuery("UPDATE configuracion" + " SET"
							+ " intentosfallidos = :falla, caducidadcontrasena = :caduca,"
							+ " maxadmin = :maxadmin, fechaguardado = :fechaguar, usuario = :usuario"
							+ " WHERE id = :idconf")
					.setParameter("idconf", configuracion.getId())
					.setParameter("falla", configuracion.getIntentosFallidos())
					.setParameter("caduca", configuracion.getCaducidadContrasena())
					.setParameter("maxadmin", configuracion.getMaxAdmin())
					.setParameter("fechaguar", configuracion.getFechaGuardado())
					.setParameter("usuario", configuracion.getUsuario().getId()).executeUpdate();

			return isResultado(res);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
