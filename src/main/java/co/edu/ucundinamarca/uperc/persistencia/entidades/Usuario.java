package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * Guarda los usuarios registrados en el sistema. - El campo NOMBRES registra
 * los nombres del usuario - El campo APELLIDOS registra los apellidos del
 * usuario - El campo TIPOID registra el tipo de documento del usuario. - El
 * campo NUMID registra el número de documento del usuario. - El campo FECHAREG
 * guarda la fecha en que se registró el usuario. - El campo ESTADO registra si
 * el usuario se encuentra activo o inactivo. - El campo PERFIL registra el tipo
 * de perfil de usuario asignado al usuario. - El campo ROL registra el rol que
 * tiene asignado el usuario
 * 
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "")
	private long id;

	/**
	 * Los nombres del usuario
	 */
	@Column(name = "NOMBRES")
	private String nombres;

	/**
	 * Los apellidos del usuario
	 */
	@Column(name = "APELLIDOS")
	private String apellidos;

	/**
	 * Tipo de identificación. "C" cédula de ciudadanía, "I" Tarjeta de identidad,
	 * "E" cédula de extranjería
	 */
	@Column(name = "TIPOID")
	private char tipoId;

	/**
	 * Número de identificación, no se requieren operaciones con esta variable
	 */
	@Column(name = "NUMID")
	private String numId;

	@Column(name = "CONTRASENA")
	private String contrasena;

	@Column(name = "CORREO")
	private String correo;

	@Column(name = "FECHANAC")
	private Date fechaNac;

	@Column(name = "FECHAREG")
	private Date fechaReg;

	@Column(name = "ESTADO")
	private boolean estado;

	
	@ManyToOne
	@JoinColumn(name = "PERFIL", referencedColumnName = "ID")
//	@Column(name = "PERFIL") Nota. no es posiBle agregar perfiles o roles desde usuario, son tablas catalogo
	private PerfilUsuario perfil;

	@ManyToOne
	@JoinColumn(name = "ROL", referencedColumnName = "ID")
//	@Column(name = "ROL")
	private Rol rol;

	
//	TODO: modificar relacion de uno a uno en modelo bd
	@OneToOne(mappedBy = "USUARIO")
	private Configuracion configuracion;

//	Listas de muchos a uno
//	TODO: verificar en el modelo
	@OneToMany(mappedBy = "USUARIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Supervision> supervisiones;
	
	
	/**
	 * Constructor por defecto
	 */
	public Usuario() {

	}

	/**
	 * Constructor que carga todos los atributos
	 * 
	 * @param nombres
	 * @param apellidos
	 * @param tipoId
	 * @param numid
	 * @param contrasena
	 * @param correo
	 * @param fechaNac
	 * @param fechaReg
	 * @param estado
	 * @param perfil
	 * @param rol
	 * @param configuracion
	 */
	public Usuario(String nombres, String apellidos, char tipoId, String numid, String contrasena, String correo,
			Date fechaNac, Date fechaReg, boolean estado, PerfilUsuario perfil, Rol rol, Configuracion configuracion) {

		setNombres(nombres);
		setApellidos(apellidos);
		setTipoId(tipoId);
		setNumId(numid);
		setContrasena(contrasena);
		setCorreo(correo);
		setFechaNac(fechaNac);
		setFechaReg(fechaReg);
		setEstado(estado);
		setPerfil(perfil);
		setRol(rol);
		setConfiguracion(configuracion);
	}

	/**
	 * Agregar una configuración al usuario
	 * 
	 * @param configuracion
	 */
	public void agregarConfig(Configuracion configuracion) {
		this.configuracion.setUsuario(this);
	}
	
	/**
	 * 
	 * @param supervision
	 */
	public void agregarMensajesSuper(Supervision supervision) {
		this.supervisiones.add(supervision);
		//Vinculacion efectiva de la entidad supervision
		supervision.setUsuario(this);
	}

	/**
	 * 
	 * @return
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	private void setId(long id) {
		this.id = id;
	}

	/**
	 * Los nombres del usuario
	 * 
	 * @return
	 */
	public String getNombres() {
		return this.nombres;
	}

	/**
	 * Los nombres del usuario
	 * 
	 * @param nombres
	 */
	private void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Los apellidos del usuario
	 */
	public String getApellidos() {
		return this.apellidos;
	}

	/**
	 * Los apellidos del usuario
	 * 
	 * @param apellidos
	 */
	private void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Tipo de identificación. "C" cédula de ciudadanía, "I" Tarjeta de identidad,
	 * "E" cédula de extranjería
	 */
	public char getTipoId() {
		return this.tipoId;
	}

	/**
	 * Tipo de identificación. "C" cédula de ciudadanía, "I" Tarjeta de identidad,
	 * "E" cédula de extranjería
	 * 
	 * @param tipoId
	 */
	private void setTipoId(char tipoId) {
		this.tipoId = tipoId;
	}

	/**
	 * Número de identificación, no se requieren operaciones con esta variable
	 */
	public String getNumId() {
		return this.numId;
	}

	/**
	 * Número de identificación, no se requieren operaciones con esta variable
	 * 
	 * @param numId
	 */
	private void setNumId(String numId) {
		this.numId = numId;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	/**
	 * 
	 * @param contrasena
	 */
	private void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * 
	 * @return
	 */
	public String getCorreo() {
		return this.correo;
	}

	/**
	 * 
	 * @param correo
	 */
	private void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaNac() {
		return this.fechaNac;
	}

	/**
	 * 
	 * @param fechaNac
	 */
	private void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaReg() {
		return this.fechaReg;
	}

	/**
	 * 
	 * @param fechaReg
	 */
	private void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEstado() {
		return this.estado;
	}

	/**
	 * 
	 * @param estado
	 */
	private void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @return
	 */
	public PerfilUsuario getPerfil() {
		return this.perfil;
	}

	/**
	 * 
	 * @param perfil
	 */
	private void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	/**
	 * 
	 * @return
	 */
	public Rol getRol() {
		return this.rol;
	}

	/**
	 * 
	 * @param rol
	 */
	private void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * @return the configuracion
	 */
	public Configuracion getConfiguracion() {
		return configuracion;
	}

	/**
	 * @param configuracion la configuracion to set
	 */
	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

}// end Usuario