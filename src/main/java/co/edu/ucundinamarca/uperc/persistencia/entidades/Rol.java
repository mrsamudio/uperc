package co.edu.ucundinamarca.uperc.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "ROL")
public class Rol {

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
	public Rol() {

	}

	/**
	 * Constructor que inicializa atributo nombre
	 * 
	 * @param nombre
	 */
	public Rol(String nombre) {

	}

	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param id
	 * @param nombre
	 */
	public Rol(String nombre, String descripcion) {
		
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
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}// end Rol