package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;

/**
 * @author Mario Roberto Samudio Martinez
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
public class Rol {

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
	public Rol(int id, String nombre) {

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
	 * 
	 * @param id
	 */
	public Rol selectById(int id) {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Rol> selectAll() {
		return null;
	}

	/**
	 * 
	 * @param rol
	 */
	public boolean insert(Rol rol) {
		return false;
	}

	/**
	 * 
	 * @param rol
	 */
	public boolean update(Rol rol) {
		return false;
	}
}// end Rol