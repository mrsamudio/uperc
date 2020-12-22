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
	@Column(name = "ID")
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
	@JoinColumn(name = "ROL", referencedColumnName = "ID")
//	@Column(name = "ROL")
	private Rol rol;

	@OneToOne(mappedBy = "USUARIO")
	private Configuracion configuracion;

//	Listas de muchos a uno
	@OneToMany(mappedBy = "USUARIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Supervision> supervisiones;

	@OneToMany(mappedBy = "USUARIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Reserva> reservas;

	@OneToMany(mappedBy = "USUARIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Informe> informes;

	@OneToMany(mappedBy = "USUARIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<RegistroIE> registrosIE;

	@OneToMany(mappedBy = "USUARIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Permiso> permisos;

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
	 * @param rol
	 * @param configuracion
	 * @param supervisiones
	 * @param reservas
	 * @param informes
	 * @param registrosIE
	 * @param permisos
	 */
	public Usuario(String nombres, String apellidos, char tipoId, String numid, String contrasena, String correo,
			Date fechaNac, Date fechaReg, boolean estado, Rol rol, Configuracion configuracion,
			List<Supervision> supervisiones, List<Reserva> reservas, List<Informe> informes,
			List<RegistroIE> registrosIE, List<Permiso> permisos) {

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
		setRegistrosIE(registrosIE);
		setPermisos(permisos);

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
		this.registrosIE.add(registro);
		registro.setUsuarioIngreso(this);
	}

	/**
	 * lista de usuarios que salen del parqueadero
	 * 
	 * @param registro
	 */
	public void agregarRegistrosE(RegistroIE registro) {
		this.registrosIE.add(registro);
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

	/**
	 * @return the supervisiones
	 */
	public List<Supervision> getSupervisiones() {
		return supervisiones;
	}

	/**
	 * @param supervisiones the supervisiones to set
	 */
	public void setSupervisiones(List<Supervision> supervisiones) {
		this.supervisiones = supervisiones;
	}

	/**
	 * @return the reservas
	 */
	public List<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	/**
	 * @return the informes
	 */
	public List<Informe> getInformes() {
		return informes;
	}

	/**
	 * @param informes the informes to set
	 */
	public void setInformes(List<Informe> informes) {
		this.informes = informes;
	}

	/**
	 * @return the registrosIE
	 */
	public List<RegistroIE> getRegistrosIE() {
		return registrosIE;
	}

	/**
	 * @param registrosIE the registrosIE to set
	 */
	public void setRegistrosIE(List<RegistroIE> registrosIE) {
		this.registrosIE = registrosIE;
	}

	/**
	 * @return the permisos
	 */
	public List<Permiso> getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

}// end Usuario