/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.RegServicio;

/**
 * @author ingsamudio
 *
 */
public interface RegServicioDAO {
	
	/**
	 * 
	 * @param id
	 */
	public RegServicio selectById(long id);

	/**
	 * 
	 * @param idSession
	 */
	public RegServicio selectByIdSession(String idSession);
	
	/**
	 * 
	 * @return
	 */
	public List<RegServicio> selectAll();

	/**
	 * 
	 * @param regServicio
	 */
	public boolean insert(RegServicio regServicio);

	/**
	 * 
	 * @param regServicio
	 */
	public boolean update(RegServicio regServicio);

}
