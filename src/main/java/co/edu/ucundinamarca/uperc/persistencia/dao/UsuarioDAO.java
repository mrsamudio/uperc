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
	 * @param correo
	 * @return
	 */
	public Usuario selectByCorreo(String correo);

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
	 * @param usuario
	 * @return
	 */
	public boolean activate(Usuario usuario);

	/**
	 * Desactiva un registro de supervisión por id
	 * @param id
	 * @return
	 */
	public boolean activate(long id);

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean deactivate(Usuario usuario);

	/**
	 * 
	 * Activa un registro de supervisión por id
	 * @param id
	 * @return
	 */
	public boolean deactivate(long id);

	/**
	 * Metodo utilizado solamente para pruebas
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean delete(Usuario usuario);

}
