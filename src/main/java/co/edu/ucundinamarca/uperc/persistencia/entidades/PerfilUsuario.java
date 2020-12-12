package co.edu.ucundinamarca.uperc.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Guarda los perfiles de usuario definidos en los requisitos. Es una tabla
 * catálogo.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
@Entity
@Table(name = "PERFIL_USUARIO")
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	/**
	 * Constructor por defecto
	 */
	public PerfilUsuario() {

	}

	/**
	 * Constructor que inicializa atributo nombre
	 * 
	 * @param nombre
	 */
	public PerfilUsuario(String nombre) {
		setNombre(nombre);
	}

	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param nombre
	 */
	public PerfilUsuario(String nombre, String descripcion) {

		setNombre(nombre);
		setDescripcion(descripcion);
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
	private void setId(int id) {
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
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return la descripción
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @param descripcion the descripción to set
	 */
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}// end PerfilUsuario