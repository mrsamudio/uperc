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
import org.hibernate.SessionFactory;
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
//@SuppressWarnings("unchecked")
//@Service

@Repository
//@Repository("ConfiguracionDAO")
public class ConfiguracionDAOImpl implements ConfiguracionDAO {
	

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
	@Transactional
	public Configuracion selectById(Long id) {
		
		return sessionFactory.getCurrentSession().getSession().get(Configuracion.class, id);
//		Usuario u = sessionFactory.getCurrentSession().get(Usuario.class, id);

//		return (Configuracion) sessionFactory.getCurrentSession()
//				.createSQLQuery("select * from configuracion c, usuario u where c.id = :idconf AND c.usuario = u.id")
//				.addEntity(Configuracion.class)
//				.setParameter("idconf", id)
//				.addScalar("id", new IntegerType())
//				.addScalar("intentosFallidos", new IntegerType())
//				.addScalar("caducidadContrasena", new IntegerType())
//				.addScalar("maxAdmin", new IntegerType())
//				.addScalar("fechaGuardado", new TimestampType())
////				.addJoin("c", "c.usuario")
////				.addEntity(Usuario.class)
//////				.addScalar("usuario", new IntegerType())
//				.setResultTransformer(Transformers.aliasToBean(Configuracion.class)).uniqueResult();
//		return sessionFactory.getCurrentSession().find(Configuracion.class, id);
//		return sessionFactory.getCurrentSession().byId(Configuracion.class).getReference(id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional(readOnly = true)
	@Override
	public List<Configuracion> selectAll() {
		

		return sessionFactory.getCurrentSession()

				.createSQLQuery("select * from configuracion")
				.addScalar("id", new IntegerType())
				.addScalar("intentosFallidos", new IntegerType())
				.addScalar("caducidadContrasena", new IntegerType())
				.addScalar("maxAdmin", new IntegerType())
				.addScalar("fechaGuardado", new DateType())
//				.addScalar("usuario", new IntegerType())
				.setResultTransformer(Transformers.aliasToBean(Configuracion.class)).list();
	}

	@Override
	public boolean insert(Configuracion configuracion) {
		try {
//			sessionFactory.getCurrentSession().getTransaction().begin();
//			sessionFactory.getCurrentSession().persist(configuracion);
//			sessionFactory.getCurrentSession().flush();
//			sessionFactory.getCurrentSession().getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}finally {
//			sessionFactory.getCurrentSession().close();
		}
	}

	@Override
	public boolean update(Configuracion configuracion) {
		try {
			sessionFactory.getCurrentSession().getTransaction().begin();
			sessionFactory.getCurrentSession().merge(configuracion);
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}finally {
//			sessionFactory.getCurrentSession().close();
		}
	}

	@Override
	public boolean deactivate(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean activate(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
