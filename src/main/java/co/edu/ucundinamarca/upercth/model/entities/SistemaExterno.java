package co.edu.ucundinamarca.upercth.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

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
	@ColumnTransformer(read="CAST(ip AS varchar)", write="CAST(? AS inet)")
	private String ip;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "contrasena")
	private String contrasena;

//	@OneToMany()// relaci??n unidireccional
	@OneToMany(mappedBy = "sistemaExterno")//due??o de relacion bidireccional
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)//due??o de relacion
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
	 * Constructor que inicializa todos los atributos de la entidad para inserci??n a bd
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
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
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