/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Ubicacion;

/**
 * @author mrsamudio
 *
 */
public interface UbicacionDAO {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Ubicacion selectById(int id);

	/**
	 * 
	 * @return
	 */
	public List<Ubicacion> selectAll();

	/**
	 * 
	 * @param ubicacion
	 * @return
	 */
	public boolean insert(Ubicacion ubicacion);

	/**
	 * 
	 * @param ubicacion
	 * @return
	 */
	public boolean update(Ubicacion ubicacion);



}
