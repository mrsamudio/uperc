/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Recurso;

/**
 * @author ingsamudio
 *
 */
public interface RecursoDAO {

	/**
	 * selecciona el registro por id
	 * 
	 * @param id
	 * @return el recurso
	 */
	public Recurso selectById(long id);

	/**
	 * busca registros por marca
	 * 
	 * 
	 * @param marca
	 * @return lista de registros
	 */
	public List<Recurso> selectByMarca(String marca);

	/**
	 * Busca todos los registros
	 * 
	 * @return lista de registros
	 */
	public List<Recurso> selectAll();

	/**
	 * Inserta un recurso
	 * 
	 * @param recurso
	 * @return true si se registr?? correctamente, false si no
	 */
	public boolean insert(Recurso recurso);

	/**
	 * Actualiza un recurso
	 * 
	 * @param recurso
	 * @return true si se actualiz?? correctamente, false si no
	 */
	public boolean update(Recurso recurso);

	/**
	 * Activar un recurso
	 * 
	 * @param recurso
	 * @return true si se activ?? correctamente, false si no
	 */
	public boolean activate(Long recurso);

	/**
	 * Desactivar un recurso
	 * 
	 * @param recurso
	 * @return true si se desactiv?? correctamente, false si no
	 */
	public boolean deactivate(Long recurso);

}
