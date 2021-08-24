package co.edu.ucundinamarca.upercth.util;

public class PersistenciaUtil {

	public PersistenciaUtil() {
		// TODO Auto-generated constructor stub
//		super();
	}
	
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
