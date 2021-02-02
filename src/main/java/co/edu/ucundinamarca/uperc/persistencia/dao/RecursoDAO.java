/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Recurso;

/**
 * @author ingsamudio
 *
 */
public interface RecursoDAO {
	
	/**
	 * 
	 * @param id
	 */
	public Recurso selectById(long id);

	/**
	 * 
	 * @return
	 */
	public List<Recurso> selectAll();

	/**
	 * 
	 * @param recurso
	 */
	public boolean insert(Recurso recurso);

	/**
	 * 
	 * @param recurso
	 */
	public boolean update(Recurso recurso);

	/**
	 * 
	 * @param id
	 */
	public boolean activate(Recurso recurso);

	/**
	 * 
	 * @param id
	 */
	public boolean deactivate(Recurso recurso);

}
