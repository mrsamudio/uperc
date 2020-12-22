package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Guarda los datos de acceso del sistema externo al sistema.
 * 
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

//	Listas 
	@OneToMany(mappedBy = "SISTEMA_EXTERNO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<RegServicio> regServicios;

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
	 * @param regServicios
	 */
	public SistemaExterno(String ip, String nombre, String contrasena, List<RegServicio> regServicios) {

		setIp(ip);
		setNombre(nombre);
		setContrasena(contrasena);
		setRegServicios(regServicios);
	}

	/**
	 * Listas de informes generados por sistemas externos?
	 * 
	 * @param informe
	 */
	public void agregarRegServicios(RegServicio regServicios) {
		this.regServicios.add(regServicios);
		regServicios.setSistemaExterno(this);
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
	protected void setId(int id) {
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
	protected void setIp(String ip) {
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
	protected void setNombre(String nombre) {
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
	 * @param contrasena the contrasena to set
	 */
	protected void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return the regServicios
	 */
	public List<RegServicio> getRegServicios() {
		return regServicios;
	}

	/**
	 * @param regServicios the regServicios to set
	 */
	protected void setRegServicios(List<RegServicio> regServicios) {
		this.regServicios = regServicios;
	}

	

	
}// end SistemaExterno