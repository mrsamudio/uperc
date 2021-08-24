/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Ubicacion;

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
