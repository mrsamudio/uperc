/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Supervision;

/**
 * @author ingsamudio
 *
 */
public interface SupervisionDAO {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Supervision selectById(long id);

	/**
	 * 
	 * @return
	 */
	public List<Supervision> selectAll();

	/**
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean insert(Supervision supervision);

	/**
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean update(Supervision supervision);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean activate(Supervision supervision);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deactivate(Supervision supervision);

}
