/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.PerfilUsuario;

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
