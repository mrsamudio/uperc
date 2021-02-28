package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "sistema_externo")
public class SistemaExterno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "ip")
	private String ip;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "contrasena")
	private String contrasena;

//	@OneToMany()// relación unidireccional
	@OneToMany(mappedBy = "sistemaExterno")//dueño de relacion bidireccional
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)//dueño de relacion
	private Set<RegServicio> regServicios;

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
	public SistemaExterno(String ip, String nombre, String contrasena, Set<RegServicio> regServicios) {

		setIp(ip);
		setNombre(nombre);
		setContrasena(contrasena);
		setRegServicios(regServicios);
	}
	
	/**
	 * Constructor que inicializa todos los atributos de la entidad para inserción a bd
	 * 
	 * @param ip
	 * @param nombre
	 * @param contrasena
	 */
	public SistemaExterno(String ip, String nombre, String contrasena) {
		
		setIp(ip);
		setNombre(nombre);
		setContrasena(contrasena);
	}
	
	/**
	 * Constructor que inicializa todos los atributos de la entidad para actualizacion en bd
	 * 
	 * @param ip
	 * @param nombre
	 * @param contrasena
	 */
	public SistemaExterno(int id, String ip, String nombre, String contrasena) {
		
		setId(id);
		setIp(ip);
		setNombre(nombre);
		setContrasena(contrasena);
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
	public Set<RegServicio> getRegServicios() {
		return regServicios;
	}

	/**
	 * @param regServicios the regServicios to set
	 */
	protected void setRegServicios(Set<RegServicio> regServicios) {
		this.regServicios = regServicios;
	}

	/**
	 * 
	 * @param regServicio
	 */
	public void agregarRegServicio(RegServicio regServicio) {
		this.regServicios.add(regServicio);
		regServicio.setSistemaExterno(this);
	}

	
}// end SistemaExterno