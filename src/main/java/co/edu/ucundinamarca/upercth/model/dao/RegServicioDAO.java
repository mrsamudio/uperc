/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.RegServicio;

/**
 * @author ingsamudio
 *
 */
public interface RegServicioDAO {

	/**
	 * Seleccionar un registro de servicio para sistema externo por id
	 * 
	 * @param id
	 * @return
	 */
	public RegServicio selectById(long id);

	/**
	 * Seleccionar un registro de servicio para sistema externo por id de sesion
	 * 
	 * @param idSession
	 * @return
	 */
	public RegServicio selectByIdSession(String idSession);
	
	/**
	 * Seleccionar un registro de servicio para sistema externo por fecha de sesi??n
	 * 
	 * @param fechaSession
	 * @return
	 */
	public List<RegServicio> selectByFechaSession(Date fechaSession);
	
	/**
	 * Seleccionar un registro de servicio para sistema externo por fecha de sesi??n
	 * 
	 * @param fechaSession
	 * @param idSession
	 * @return lista de registros <= a la fecha de session
	 */
	public List<RegServicio> selectByFechaSession(Date fechaSession, String idSession);

	/**
	 * Seleccionar todos los registros
	 * 
	 * @return lista de todos los registros
	 */
	public List<RegServicio> selectAll();

	/**
	 * Insertar un registro de servicio relacionado a un sistema externo
	 * 
	 * @param regServicio
	 * @return true or false a la inserci??n
	 */
	public boolean insert(RegServicio regServicio);

	/**
	 * Actualizar un registro de servicio relacionado a un sistema externo
	 * 
	 * 
	 * @param regServicio
	 * @return true or false a la actualizaci??n
	 */
	public boolean update(RegServicio regServicio);

}
