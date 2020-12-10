package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;

/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
public class SistemaExterno {

	private int id;
	private String ip;
	private String nombre;
	private String contrasena;

	/**
	 * 
	 */
	public void finalize() throws Throwable {

	}

	/**
	 * Constructor por defecto
	 */
	public SistemaExterno() {

	}

	/**
	 * Constructor que inicializa todos los atributos menos el id
	 * 
	 * @param ip
	 * @param nombre
	 * @param contrasena
	 */
	public SistemaExterno(String ip, String nombre, String contrasena) {

	}

	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param id
	 * @param ip
	 * @param nombre
	 * @param contrasena
	 */
	public SistemaExterno(int id, String ip, String nombre, String contrasena) {

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
	public String getIp() {
		return this.ip;
	}

	/**
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
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
	 * @return
	 */
	public String getContrasena() {
		return this.contrasena;
	}

	/**
	 * 
	 * @param contrasena
	 */
	public void setcontrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * 
	 * @param id
	 */
	public SistemaExterno selectById(int id) {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<SistemaExterno> selectAll() {
		return null;
	}

	/**
	 * 
	 * @param sistemaExterno
	 */
	public boolean insert(SistemaExterno sistemaExterno) {
		return false;
	}

	/**
	 * 
	 * @param sistemaExterno
	 */
	public boolean update(SistemaExterno sistemaExterno) {
		return false;
	}
}// end SistemaExterno