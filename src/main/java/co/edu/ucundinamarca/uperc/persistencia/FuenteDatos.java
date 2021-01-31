/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Interfaz para la configuracion de la fuente de datos persistentes
 * 
 * @author mrsamudio
 *
 */
public interface FuenteDatos {

	/**
	 * Obtiene el tipo de fuente de datos dependiendo del perfil en ejecución
	 * @return
	 */
	public DataSource fuenteDatos();
	
	/**
	 * Obtiene las propiedades de hibernate dependiendo del perfil en ejecución
	 * @return
	 */
	public Properties hibernateProperties();
	
	/**
	 * 
	 * @return
	 */
	public SessionFactory factoriaSesion()  throws IOException;
	
	/**
	 * 
	 * @return
	 */
	public PlatformTransactionManager transactionManager()  throws IOException;
}
