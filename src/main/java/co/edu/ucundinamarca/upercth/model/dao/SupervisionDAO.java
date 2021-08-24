/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Supervision;

/**
 * @author ingsamudio
 *
 */
public interface SupervisionDAO {

	/**
	 * Selecciona por id de supervisi??n
	 * 
	 * @param id
	 * @return
	 */
	public Supervision selectById(long id);

	/**
	 * Selecciona los registros de supervisi??n por tipo alerta
	 * 
	 * @return lista de registros
	 */
	public List<Supervision> selectAlertas();

	/**
	 * Selecciona los registros de supervisi??n por tipo avisos
	 * 
	 * @return lista de registros
	 */
	public List<Supervision> selectAvisos();

	/**
	 * Selecciona los registros de supervisi??n por fecha
	 * 
	 * @param fecha
	 * @return lista de registros de supervisi??n <= a la fecha dada
	 */
	public List<Supervision> selectByDate(Date fecha);

	/**
	 * Selecciona todos los registros de supervisi??n
	 * 
	 * @return
	 */
	public List<Supervision> selectAll();

	/**
	 * Inserta un registro de supervisi??n
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean insert(Supervision supervision);

	/**
	 * Actualiza un registro de supervisi??n
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean update(Supervision supervision);

	/**
	 * Activa un registro de supervisi??n
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean activate(Supervision supervision);

	/**
	 * Desactiva un registro de supervisi??n
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean deactivate(Supervision supervision);

	/**
	 * Activa un registro de supervisi??n por id
	 * 
	 * @param id
	 * @return
	 */
	public boolean activate(long id);

	/**
	 * Desactiva un registro de supervisi??n por id
	 * 
	 * @param id
	 * @return
	 */
	public boolean deactivate(long id);

}
