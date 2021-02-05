package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * clase que representa la tabla de registros de entrada y salida de vehículos y
 * los usuarios asociados.
 * 
 * Guarda el momento en que los recursos(dispositivos de captura) registran el
 * ingreso o egreso al parqueadero de los usuarios y vehículos.
 * 
 * TODO: req-f El usuario que egresa debe tener permiso para salir con un
 * Por medio de ticket_id
 * vehículo diferente
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "registroie")
public class RegistroIE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "fechaingreso")
	private Date fechaIngreso;

	@Column(name = "fechaegreso")
	private Date fechaEgreso;

	@ManyToOne
	@JoinColumn(name = "recurso", referencedColumnName = "id")
//	@Column(name = "RECURSO")
	private Recurso recurso;

	@ManyToOne
	@JoinColumn(name = "vehiculo", referencedColumnName = "id")
//	@Column(name = "VEHICULO")
	private Vehiculo vehiculo;

	@ManyToOne
//	@JoinColumn(name = "usuarioIngreso")
	@JoinColumn(name = "usuarioingreso", referencedColumnName = "id")
//	@Column(name = "USUARIO_INGRESO")
	private Usuario usuarioIngreso;

	@ManyToOne
//	@JoinColumn(name = "usuarioEgreso")
	@JoinColumn(name = "usuarioegreso", referencedColumnName = "id")
//	@Column(name = "USUARIO_EGRESO")
	private Usuario usuarioEgreso;
	
	@OneToOne
	@JoinColumn(name = "permiso", referencedColumnName = "id")
	private Permiso permiso;
	
	@Column(name = "ticketid")//tipo uuid postgresql
	private String ticketId;

	/**
	 * Constructor por defecto
	 */
	public RegistroIE() {

	}

	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param fechaIngreso
	 * @param fechaEgreso
	 * @param recurso
	 * @param vehiculo
	 * @param usuarioIngreso
	 * @param usuarioEgreso
	 * @param permiso
	 * @param ticketId
	 */
	public RegistroIE(Date fechaIngreso, Date fechaEgreso, Recurso recurso, Vehiculo vehiculo, Usuario usuarioIngreso,
			Usuario usuarioEgreso, Permiso permiso, String ticketId) {

		setFechaIngreso(fechaIngreso);
		setFechaEgreso(fechaEgreso);
		setRecurso(recurso);
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
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	/**
	 * 
	 * @param fechaIngreso
	 */
	protected void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaEgreso() {
		return this.fechaEgreso;
	}

	/**
	 * 
	 * @param fechaEgreso
	 */
	protected void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	/**
	 * 
	 * @return
	 */
	public Recurso getRecurso() {
		return this.recurso;
	}

	/**
	 * 
	 * @param recurso
	 */
	protected void setRecurso(Recurso recurso) {
		this.recurso = recurso;
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