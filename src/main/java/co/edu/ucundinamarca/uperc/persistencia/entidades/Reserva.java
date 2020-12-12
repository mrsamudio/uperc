package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Guarda las reservas que han sido solicitadas por los usuarios. - El campo
 * FECHA_SOLICITUD registra el momento de tiempo en que se solicitó la reserva.
 * - El campo ESTADO registra si la reserva se encuentra activa o inactiva. - El
 * campo NUM_ESTACION registra el número del estacionamiento reservado para el
 * vehiculo. - El campo FECHA_RESERVA registra el momento en el que se espera
 * que el vehiculo ingrese al parqueadero de la universidad para ocupar el
 * espacio reservado. - El campo FECHA_FIN registra el momento en que finaliza
 * la reserva. - El campo CANCELADA registra si la reserva fue cancelada o no
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "RESERVA")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "FECHA_SOLICITUD")
	private Date fechaSolicitud;

	@Column(name = "ESTADO")
	private boolean estado;

	@Column(name = "NUM_ESTACION")
	private int numEstacion;

	@Column(name = "FECHA_RESERVA")
	private Date fechaReserva;

	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	@Column(name = "CANCELADA")
	private boolean cancelada;

	@Column(name = "USUARIO")
	private int usuario;

	/**
	 * Constructor por defecto
	 */
	public Reserva() {

	}

	/**
	 * Constructor que carga todos los atributos
	 * 
	 * @param fechaSolicitud
	 * @param estado
	 * @param numEstacion
	 * @param fechaReserva
	 * @param fechaFin
	 * @param cancelada
	 * @param usuario
	 */
	public Reserva(Date fechaSolicitud, boolean estado, int numEstacion, Date fechaReserva, Date fechaFin,
			boolean cancelada, int usuario) {

		setFechaSolicitud(fechaSolicitud);
		setEstado(estado);
		setNumEstacion(numEstacion);
		setFechaReserva(fechaReserva);
		setFechaFin(fechaFin);
		setCancelada(cancelada);
		setUsuario(usuario);
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
	 * 
	 * @return
	 */
	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	/**
	 * 
	 * @param fechaSolicitud
	 */
	private void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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
	public int getNumEstacion() {
		return this.numEstacion;
	}

	/**
	 * 
	 * @param numEstacion
	 */
	private void setNumEstacion(int numEstacion) {
		this.numEstacion = numEstacion;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaReserva() {
		return this.fechaReserva;
	}

	/**
	 * 
	 * @param fechaReserva
	 */
	private void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaFin() {
		return this.fechaFin;
	}

	/**
	 * 
	 * @param fechaFin
	 */
	private void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isCancelada() {
		return this.cancelada;
	}

	/**
	 * 
	 * @param cancelada
	 */
	private void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	/**
	 * 
	 * @return
	 */
	public int getUsuario() {
		return this.usuario;
	}

	/**
	 * 
	 * @param usuario
	 */
	private void setUsuario(int usuario) {
		this.usuario = usuario;
	}

}// end Reserva