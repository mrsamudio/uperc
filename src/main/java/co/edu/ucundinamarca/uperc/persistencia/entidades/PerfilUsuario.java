package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;



/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
public class PerfilUsuario {

	private int id;
	private String nombre;

	/**
	 * 
	 */
	public void finalize() throws Throwable {

	}

	/**
	 * Constructor por defecto
	 */
	public PerfilUsuario() {

	}

	/**
	 * Constructor por defecto que inicializa atributo nombre
	 * 
	 * @param nombre
	 */
	public PerfilUsuario(String nombre) {

	}

	/**
	 * Constructor por defecto que inicializa todos los atributos
	 * 
	 * @param id
	 * @param nombre
	 */
	public PerfilUsuario(int id, String nombre) {

	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @param id
	 */
	public PerfilUsuario selectById(int id) {
		return null;
	}

	public List<PerfilUsuario> selectAll() {
		return null;
	}

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean insert(PerfilUsuario perfilUsuario) {
		return false;
	}

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean update(PerfilUsuario perfilUsuario) {
		return false;
	}
}// end PerfilUsuario