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
@Table(name = "SISTEMA_EXTERNO")
public class SistemaExterno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "IP")
	private String ip;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CONTRASENA")
	private String contrasena;


	/**
	 * Constructor por defecto
	 */
	public SistemaExterno() {

	}

	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param ip
	 * @param nombre
	 * @param contrasena
	 */
	public SistemaExterno(String ip, String nombre, String contrasena) {
		
		setIp(ip);
		setNombre(nombre);
		setcontrasena(contrasena);
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
	public String getIp() {
		return this.ip;
	}

	/**
	 * 
	 * @param ip
	 */
	private void setIp(String ip) {
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
	private void setNombre(String nombre) {
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
	private void setcontrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	
}// end SistemaExterno