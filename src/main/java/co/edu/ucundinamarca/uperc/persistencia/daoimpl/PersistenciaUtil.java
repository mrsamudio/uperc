package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

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

	/**
	 * Comprobacion resultado
	 * 
	 * @param res
	 * @return
	 */
	protected boolean isResultado(int res) {
		if (res != 0)
			return true;
		else
			return false;
	}

}