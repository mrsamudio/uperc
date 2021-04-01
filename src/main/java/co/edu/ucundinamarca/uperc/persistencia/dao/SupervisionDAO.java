/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Supervision;

/**
 * @author ingsamudio
 *
 */
public interface SupervisionDAO {
	
	/**
	 * Selecciona por id de supervisión
	 * @param id
	 * @return
	 */
	public Supervision selectById(long id);

	/**
	 * Selecciona los registros de supervisión por tipo alerta
	 * @return lista de registros
	 */
	public List<Supervision> selectAlertas();
	
	/**
	 * Selecciona los registros de supervisión por tipo avisos
	 * @return lista de registros
	 */
	public List<Supervision> selectAvisos();
	
	/**
	 * Selecciona los registros de supervisión por fecha
	 * @param fecha
	 * @return lista de registros de supervisión <= a la fecha dada
	 */
	public List<Supervision> selectByDate(Date fecha);
	
	/**
	 * Selecciona todos los registros de supervisión
	 * @return
	 */
	public List<Supervision> selectAll();

	/**
	 * Inserta un registro de supervisión
	 * @param supervision
	 * @return
	 */
	public boolean insert(Supervision supervision);

	/**
	 * Actualiza un registro de supervisión
	 * @param supervision
	 * @return
	 */
	public boolean update(Supervision supervision);

	/**
	 * Activa un registro de supervisión
	 * @param id
	 * @return
	 */
	public boolean activate(Supervision supervision);
	

	/**
	 * Desactiva un registro de supervisión
	 * @param id
	 * @return
	 */
	public boolean deactivate(Supervision supervision);
	
	public boolean activate(long id);
	public boolean deactivate(long id);

}
