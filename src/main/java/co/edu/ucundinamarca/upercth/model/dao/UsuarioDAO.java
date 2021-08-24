/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Usuario;

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
	 * Trae a todos los usuarios de la bd
	 * 
	 * @return
	 */
	public List<Usuario> selectAll();

	/**
	 * Trae a todos los usuarios con rol administrador
	 * 
	 * @return
	 */
	public List<Usuario> selectAdmins();

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
	 * Activa un registro de usuario
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean activate(Usuario usuario);

	/**
	 * Activa un registro de usuario por id
	 * 
	 * @param id
	 * @return
	 */
	public boolean activate(long id);

	/**
	 * Desactiva un registro de usuario
	 * 
	 * @param usuario
	 * @return
	 */
	public boolean deactivate(Usuario usuario);

	/**
	 * 
	 * Desactiva un registro de usuario por id
	 * 
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
