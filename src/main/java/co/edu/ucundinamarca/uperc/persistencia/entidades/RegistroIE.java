package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * clase que representa la tabla de registros de entrada y salida de vehículos y
 * los usuarios asociados.
 * 
 * Guarda el momento en que los recursos(dispositivos de captura) registran el
 * ingreso o egreso al parqueadero de los usuarios y vehículos.
 * 
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "registro_ie")
public class RegistroIE implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "fechaingreso")
	private Timestamp fechaIngreso;

	@Column(name = "fechaegreso")
	private Timestamp fechaEgreso;

	@ManyToOne
//	@Column(name = "recurso")
	@JoinColumn(name = "recursoingreso")
	private Recurso recursoIngreso;

	@ManyToOne
//	@Column(name = "recurso")
	@JoinColumn(name = "recursoegreso")
	private Recurso recursoEgreso;

	@ManyToOne
//	@Column(name = "vehiculo")
	@JoinColumn(name = "vehiculo")
	private Vehiculo vehiculo;

	@ManyToOne
	@JoinColumn(name = "usuarioingreso")
//	@Column(name = "usuarioingreso")
	private Usuario usuarioIngreso;

	@ManyToOne
	@JoinColumn(name = "usuarioegreso")
//	@Column(name = "usuarioegreso")
	private Usuario usuarioEgreso;

	@Column(name = "ticketid") // tipo uuid postgresql
	private String ticketId;

	@OneToOne(cascade = { CascadeType.ALL }) // Dueño de relacion
	@PrimaryKeyJoinColumn
	private Permiso permiso;

	/**
	 * Constructor por defecto
	 */
	public RegistroIE() {

	}

	/**
	 *  Constructor que inicializa todos los atributos
	 * 
	 * @param fechaIngreso
	 * @param fechaEgreso
	 * @param recursoI
	 * @param recursoE
	 * @param vehiculo
	 * @param usuarioIngreso
	 * @param usuarioEgreso
	 * @param permiso
	 * @param ticketId
	 */
	public RegistroIE(Timestamp fechaIngreso, Timestamp fechaEgreso, Recurso recursoI, Recurso recursoE,
			Vehiculo vehiculo, Usuario usuarioIngreso, Usuario usuarioEgreso, Permiso permiso, String ticketId) {

		setFechaIngreso(fechaIngreso);
		setFechaEgreso(fechaEgreso);

		setRecursoI(recursoI);
		setRecursoE(recursoE);

		setVehiculo(vehiculo);
		setUsuarioIngreso(usuarioIngreso);
		setUsuarioEgreso(usuarioEgreso);
		setPermiso(permiso);
		setTicketId(ticketId);
	}

	/**
	 * Constructor para update del registro de salida en bd
	 * 
	 * @param id
	 * @param fechaEgreso
	 * @param usuarioEgreso
	 * @param recursoE
	 */
	public RegistroIE(long id, Timestamp fechaEgreso, Usuario usuarioEgreso, Recurso recursoE) {

		setId(id);
		setFechaEgreso(fechaEgreso);
		setUsuarioEgreso(usuarioEgreso);
		setRecursoE(recursoE);
//		setPermiso(permiso);
	}

	/**
	 * Constructor que inicializa los atributos para inserción
	 * 
	 * @param fechaIngreso
	 * @param recurso
	 * @param vehiculo
	 * @param usuarioIngreso
	 * @param ticketId
	 */
	public RegistroIE(Timestamp fechaIngreso, Recurso recursoI, Vehiculo vehiculo, Usuario usuarioIngreso,
			String ticketId) {

		setFechaIngreso(fechaIngreso);
		setFechaEgreso(fechaEgreso);
		setRecursoI(recursoI);
		setVehiculo(vehiculo);
		setUsuarioIngreso(usuarioIngreso);
		setUsuarioEgreso(usuarioEgreso);
		setPermiso(permiso);
		setTicketId(ticketId);
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
	 * 
	 * @return
	 */
	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	/**
	 * 
	 * @param fechaIngreso
	 */
	protected void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getFechaEgreso() {
		return this.fechaEgreso;
	}

	/**
	 * 
	 * @param fechaEgreso
	 */
	protected void setFechaEgreso(Timestamp fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

//	/**
//	 * 
//	 * @return
//	 */
//	public Recurso getRecurso() {
//		return this.recurso;
//	}
//
//	/**
//	 * 
//	 * @param recurso
//	 */
//	protected void setRecurso(Recurso recurso) {
//		this.recurso = recurso;
//	}

	/**
	 * 
	 * @return
	 */
	public Recurso getRecursoI() {
		return this.recursoIngreso;
	}

	/**
	 * 
	 * @param recurso
	 */
	protected void setRecursoI(Recurso recursoIngreso) {
		this.recursoIngreso = recursoIngreso;
	}

	/**
	 * 
	 * @return
	 */
	public Recurso getRecursoE() {
		return this.recursoEgreso;
	}

	/**
	 * 
	 * @param recurso
	 */
	protected void setRecursoE(Recurso recursoEgreso) {
		this.recursoEgreso = recursoEgreso;
	}

	/**
	 * 
	 * @return
	 */
	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	/**
	 * 
	 * @param vehiculo
	 */
	protected void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * 
	 * @return
	 */
	public Usuario getUsuarioIngreso() {
		return this.usuarioIngreso;
	}

	/**
	 * 
	 * @param usuarioIngreso
	 */
	protected void setUsuarioIngreso(Usuario usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}

	/**
	 * 
	 * @return
	 */
	public Usuario getUsuarioEgreso() {
		return this.usuarioEgreso;
	}

	/**
	 * 
	 * @param usuarioEgreso
	 */
	protected void setUsuarioEgreso(Usuario usuarioEgreso) {
		this.usuarioEgreso = usuarioEgreso;
	}

	/**
	 * @return the permiso
	 */
	public Permiso getPermiso() {
		return permiso;
	}

	/**
	 * @param permiso the permiso to set
	 */
	protected void setPermiso(Permiso permiso) {
		this.permiso = permiso;
//		permiso.setRegistroIE(this);
	}

	/**
	 * @return the ticketId
	 */
	public String getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	protected void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

}// end RegistroIE