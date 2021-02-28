package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/**
	 * Los nombres del usuario
	 */
	@Column(name = "nombres")
	private String nombres;

	/**
	 * Los apellidos del usuario
	 */
	@Column(name = "apellidos")
	private String apellidos;

	/**
	 * Tipo de identificación. "C" cédula de ciudadanía, "I" Tarjeta de identidad,
	 * "E" cédula de extranjería
	 */
	@Column(name = "tipoid")
	private char tipoId;

	/**
	 * Número de identificación, no se requieren operaciones con esta variable
	 */
	@Column(name = "numid")
	private String numId;

	@Column(name = "contrasena")
	private String contrasena;

	@Column(name = "correo")
	private String correo;

	@Column(name = "fechanac")
	@Temporal(TemporalType.DATE)
	private Date fechaNac;

	@Column(name = "fechareg")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaReg;

	@Column(name = "estado")
	private boolean estado;

	@ManyToOne // Foreign key
//	@Column(name = "rol")
//	@ManyToOne(targetEntity = Rol.class, fetch = FetchType.LAZY)
//	@ManyToOne(targetEntity = Rol.class)
	@JoinColumn(name = "rol")
	private Rol rol;

	@OneToOne(cascade = { CascadeType.ALL }) // Dueño de relacion
	@PrimaryKeyJoinColumn //
	private Configuracion configuracion = new Configuracion();

//	@OneToMany
	@OneToMany(mappedBy = "usuario")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario", orphanRemoval = true) // dueño
	private Set<Supervision> supervisiones;

	@OneToMany
//	@OneToMany(mappedBy = "usuario")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario", orphanRemoval = true) // dueño
	private Set<Reserva> reservas;

	@OneToMany()// relación unidireccional
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario", orphanRemoval = true) // dueño
//	@OneToMany(mappedBy = "usuario")
	private Set<Informe> informes;

	@OneToMany(mappedBy = "usuarioIngreso")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuarioIngreso", orphanRemoval = true) // dueño
	private Set<RegistroIE> registrosI;

	@OneToMany(mappedBy = "usuarioEgreso")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuarioEgreso", orphanRemoval = true) // dueño
	private Set<RegistroIE> registrosE;

	@OneToMany
//	@OneToMany(mappedBy = "usuario")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario", orphanRemoval = true) // dueño
	private Set<Permiso> permisos;

	/**
	 * Constructor por defecto
	 */
	public Usuario() {

	}

	/**
	 * 
	 * @param id
	 */
	public Usuario(long id) {
		this.setId(id);
	}

	/**
	 * Constructor que carga todos los atributos de la entidad con id
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
	 * @param rol
	 */
	public Usuario(long id, String nombres, String apellidos, char tipoId, String numid, String contrasena, String correo,
			Date fechaNac, Date fechaReg, boolean estado, Rol rol) {
		
		
		setId(id);
		setNombres(nombres);
		setApellidos(apellidos);
		setTipoId(tipoId);
		setNumId(numid);
		setContrasena(contrasena);
		setCorreo(correo);
		setFechaNac(fechaNac);
		setFechaReg(fechaReg);
		setEstado(estado);
		setRol(rol);
	}
	
	/**
	 * Constructor que carga todos los atributos de la entidad
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
	 * @param rol
	 */
	public Usuario(String nombres, String apellidos, char tipoId, String numid, String contrasena, String correo,
			Date fechaNac, Date fechaReg, boolean estado, Rol rol) {
		
		
		
		setNombres(nombres);
		setApellidos(apellidos);
		setTipoId(tipoId);
		setNumId(numid);
		setContrasena(contrasena);
		setCorreo(correo);
		setFechaNac(fechaNac);
		setFechaReg(fechaReg);
		setEstado(estado);
		setRol(rol);
	}
	
	/**
	 * Constructor que carga todos los atributos y listas
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
	 * @param rol
	 * @param configuracion
	 * @param supervisiones
	 * @param reservas
	 * @param informes
	 * @param registrosI
	 * @param registrosE
	 * @param permisos
	 */
	public Usuario(String nombres, String apellidos, char tipoId, String numid, String contrasena, String correo,
			Date fechaNac, Date fechaReg, boolean estado, Rol rol, Configuracion configuracion,
			Set<Supervision> supervisiones, Set<Reserva> reservas, Set<Informe> informes, Set<RegistroIE> registrosI,
			Set<RegistroIE> registrosE, Set<Permiso> permisos) {
		
		

		setNombres(nombres);
		setApellidos(apellidos);
		setTipoId(tipoId);
		setNumId(numid);
		setContrasena(contrasena);
		setCorreo(correo);
		setFechaNac(fechaNac);
		setFechaReg(fechaReg);
		setEstado(estado);
		setRol(rol);

		setConfiguracion(configuracion);
		setSupervisiones(supervisiones);
		setReservas(reservas);
		setInformes(informes);
		setRegistrosI(registrosI);
		setRegistrosE(registrosE);
		setPermisos(permisos);

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
	protected void setId(long id) {
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
	 * Obtiene el tipo de identificación del usuario {@link #tipoId}.
	 * 
	 * @return "C" cédula de ciudadanía, "I" Tarjeta de identidad, "E" cédula de
	 *         extranjería
	 */
	public char getTipoId() {
		return this.tipoId;
	}

	/**
	 * define el atributo tipo de identificación {@link #tipoId}.
	 * 
	 * @param tipoId "C" cédula de ciudadanía, "I" Tarjeta de identidad,
	 * "E" cédula de extranjería
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
	 * @return Date
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
	 * @return Date
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
	public Rol getRol() {
		return this.rol;
	}

	/**
	 * 
	 * @param rol
	 */
	protected void setRol(Rol rol) {
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

	/**
	 * @return the supervisiones
	 */
	public Set<Supervision> getSupervisiones() {
		return supervisiones;
	}

	/**
	 * @param supervisiones the supervisiones to set
	 */
	public void setSupervisiones(Set<Supervision> supervisiones) {
		this.supervisiones = supervisiones;
	}

	/**
	 * @return the reservas
	 */
	public Set<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	/**
	 * @return the informes
	 */
	public Set<Informe> getInformes() {
		return informes;
	}

	/**
	 * @param informes the informes to set
	 */
	public void setInformes(Set<Informe> informes) {
		this.informes = informes;
	}

	/**
	 * 
	 * @return
	 */
	public Set<RegistroIE> getRegistrosI() {
		return registrosI;
	}

	/**
	 * 
	 * @param registrosI
	 */
	public void setRegistrosI(Set<RegistroIE> registrosI) {
		this.registrosI = registrosI;
	}

	/**
	 * 
	 * @return
	 */
	public Set<RegistroIE> getRegistrosE() {
		return registrosE;
	}

	/**
	 * 
	 * @param registrosE
	 */
	public void setRegistrosE(Set<RegistroIE> registrosE) {
		this.registrosE = registrosE;
	}

	/**
	 * @return the permisos
	 */
	public Set<Permiso> getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}

//	/**
//	 * Agregar una configuración al usuario
//	 * 
//	 * @param configuracion
//	 */
//	public void agregarConfig(Configuracion configuracion) {
//		this.configuracion.setUsuario(this);
//	}

	/**
	 * 
	 * @param supervision
	 */
	public void agregarSupervision(Supervision supervision) {
		this.supervisiones.add(supervision);
		// Vinculación efectiva de la entidad supervision
		supervision.setUsuario(this);
	}

	/**
	 * 
	 * @param reserva
	 */
	public void agregarReservas(Reserva reserva) {
		this.reservas.add(reserva);
		reserva.setUsuario(this);
	}

	/**
	 * Listas de informes generados por usuarios o sistemas externos?
	 * 
	 * @param informe
	 */
	public void agregarInformes(Informe informe) {
		this.informes.add(informe);
		informe.setUsuario(this);
	}

	/**
	 * lista de usuarios que ingresan del parqueadero
	 * 
	 * @param registro
	 */
	public void agregarRegistrosI(RegistroIE registro) {
		this.registrosI.add(registro);
		registro.setUsuarioIngreso(this);
	}

	/**
	 * lista de usuarios que salen del parqueadero
	 * 
	 * @param registro
	 */
	public void agregarRegistrosE(RegistroIE registro) {
		this.registrosE.add(registro);
		registro.setUsuarioEgreso(this);
	}

	/**
	 * Lista de permisos dados por los usuarios
	 * 
	 * @param permiso
	 */
	public void agregarPermisos(Permiso permiso) {
		this.permisos.add(permiso);
		permiso.setUsuario(this);
	}

}// end Usuario