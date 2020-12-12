/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Rol;

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
