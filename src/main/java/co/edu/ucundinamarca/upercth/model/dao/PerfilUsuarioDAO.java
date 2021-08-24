/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.PerfilUsuario;

/**
 * @author ingsamudio
 *
 */
public interface PerfilUsuarioDAO {
	

	/**
	 * 
	 * @param id
	 */
	public PerfilUsuario selectById(int id);

	/**
	 * 
	 * @return
	 */
	public List<PerfilUsuario> selectAll();

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean insert(PerfilUsuario perfilUsuario);

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean update(PerfilUsuario perfilUsuario);
}
