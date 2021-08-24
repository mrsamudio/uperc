package co.edu.ucundinamarca.upercth.model.entities;

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

import org.hibernate.annotations.ColumnTransformer;

/**
 * clase que representa la tabla de registros de entrada y salida de veh??culos
 * y los usuarios asociados.
 * 
 * Guarda el momento en que los recursos(dispositivos de captura) registran el
 * ingreso o egreso al parqueadero de los usuarios y veh??culos.
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
	@ColumnTransformer(read = "CAST(ticketid AS varchar)", write = "CAST(? AS uuid)")
	private String ticketId;

	@OneToOne(cascade = { CascadeType.ALL }) // Due??o de relacion
	@PrimaryKeyJoinColumn
	private Permiso permiso;

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

		setRecursoIngreso(recursoI);
		setRecursoEgreso(recursoE);

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
		setRecursoEgreso(recursoE);
//		setPermiso(permiso);
	}

	/**
	 * Constructor que inicializa los atributos para inserción o para el registro de
	 * ingreso
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
		setRecursoIngreso(recursoI);
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
	public void setId(long id) {
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
	public void setFechaIngreso(Timestamp fechaIngreso) {
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
	public void setFechaEgreso(Timestamp fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	/**
	 * 
	 * @return
	 */
	public Recurso getRecursoIngreso() {
		return this.recursoIngreso;
	}

	/**
	 * 
	 * @param recurso
	 */
	public void setRecursoIngreso(Recurso recursoIngreso) {
		this.recursoIngreso = recursoIngreso;
	}

	/**
	 * 
	 * @return
	 */
	public Recurso getRecursoEgreso() {
		return this.recursoEgreso;
	}

	/**
	 * 
	 * @param recurso
	 */
	public void setRecursoEgreso(Recurso recursoEgreso) {
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
	public void setVehiculo(Vehiculo vehiculo) {
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
	public void setUsuarioIngreso(Usuario usuarioIngreso) {
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
	public void setUsuarioEgreso(Usuario usuarioEgreso) {
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
	public void setPermiso(Permiso permiso) {
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
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

}// end RegistroIE