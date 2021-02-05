/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author ingsamudio
 *
 */
public interface UsuarioDAO {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Usuario selectById(Long id);

	/**
	 * 
	 * @return
	 */
	public List<Usuario> selectAll();

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean insert(Usuario usuario);

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean update(Usuario usuario);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean activate(Usuario usuario);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deactivate(Usuario usuario);

}