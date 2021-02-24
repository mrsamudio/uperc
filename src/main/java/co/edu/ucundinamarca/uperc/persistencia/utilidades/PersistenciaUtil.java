package co.edu.ucundinamarca.uperc.persistencia.utilidades;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

/**
 * Utilidades relacionadas con persistencia
 * 
 * @author mrsamudio
 *
 */
public class PersistenciaUtil {

	public PersistenciaUtil() {
		super();
	}
	
	
//	private SessionFactory sessionFactory;
//
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	@Resource(name = "factoriaSesion")
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	

	/**
	 * Comprobacion resultado de la consulta a base de datos
	 * 
	 * @param res
	 * @return true or false
	 */
	protected boolean isResultado(int res) {
		if (res != 0)
			return true;
		else
			return false;
	}

}