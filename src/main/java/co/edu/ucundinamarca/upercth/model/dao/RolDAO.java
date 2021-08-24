/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Rol;

/**
 * @author ingsamudio
 *
 */
public interface RolDAO {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Rol selectById(int id);

	/**
	 * 
	 * @return
	 */
	public List<Rol> selectAll();

	/**
	 * 
	 * @param rol
	 * @return
	 */
	public boolean insert(Rol rol);

	/**
	 * 
	 * @param rol
	 * @return
	 */
	public boolean update(Rol rol);
}
