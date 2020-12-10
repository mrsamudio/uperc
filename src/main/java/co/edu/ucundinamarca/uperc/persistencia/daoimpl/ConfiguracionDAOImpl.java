/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;

/**
 * Implementación de la interfaz para el acceso a datos por objetos de la
 * entidad CONFIGURACION
 * 
 * @author mrsamudio
 *
 */
public class ConfiguracionDAOImpl implements ConfiguracionDAO {

	/**
	 * @implNote asdlfkj
	 * @since 1.0
	 */
	@Override
	public Configuracion selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Configuracion> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Configuracion configuracion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Configuracion configuracion) {
		// TODO Auto-generated method stub
		return false;
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
