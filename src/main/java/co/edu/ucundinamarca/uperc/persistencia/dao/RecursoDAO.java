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
	 * @return true si se registró correctamente, false si no
	 */
	public boolean insert(Recurso recurso);

	/**
	 * Actualiza un recurso
	 * 
	 * @param recurso
	 * @return true si se actualizó correctamente, false si no
	 */
	public boolean update(Recurso recurso);

	/**
	 * Activar un recurso
	 * 
	 * @param recurso
	 * @return true si se activó correctamente, false si no
	 */
	public boolean activate(Recurso recurso);

	/**
	 * Desactivar un recurso
	 * 
	 * @param recurso
	 * @return true si se desactivó correctamente, false si no
	 */
	public boolean deactivate(Recurso recurso);

}
