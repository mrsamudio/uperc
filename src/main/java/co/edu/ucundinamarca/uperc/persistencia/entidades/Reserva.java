package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Nota. solo se permite una reserva activa por usuario(restriccion. el usuario
 * no puede tener varias reservas activas)
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
	@Column(name = "id")
	private long id;

	@Column(name = "fechasolicitud")
	private Timestamp fechaSolicitud;

	@Column(name = "estado")
	private boolean estado;

	@ManyToOne
	@Column(name = "espacioparqueo")
	private EspacioParqueo espacioParqueo;

	@Column(name = "fechareserva")
	private Timestamp fechaReserva;

	@Column(name = "fechafin")
	private Timestamp fechaFin;

	@Column(name = "cancelada")
	private boolean cancelada;

	@ManyToOne
	@Column(name = "usuario")
	private Usuario usuario;

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
	 * @param espacioParqueo
	 * @param fechaReserva
	 * @param fechaFin
	 * @param cancelada
	 * @param usuario
	 */
	public Reserva(Timestamp fechaSolicitud, boolean estado, EspacioParqueo espacioParqueo, Timestamp fechaReserva, Timestamp fechaFin,
			boolean cancelada, Usuario usuario) {

		setFechaSolicitud(fechaSolicitud);
		setEstado(estado);
		setEspacioParqueo(espacioParqueo);
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
	protected void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	/**
	 * 
	 * @param fechaSolicitud
	 */
	protected void setFechaSolicitud(Timestamp fechaSolicitud) {
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
	protected void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @return
	 */
	public EspacioParqueo getEspacioParqueo() {
		return this.espacioParqueo;
	}

	/**
	 * 
	 * @param espacioParqueo
	 */
	protected void setEspacioParqueo(EspacioParqueo espacioParqueo) {
		this.espacioParqueo = espacioParqueo;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getFechaReserva() {
		return this.fechaReserva;
	}

	/**
	 * 
	 * @param fechaReserva
	 */
	protected void setFechaReserva(Timestamp fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getFechaFin() {
		return this.fechaFin;
	}

	/**
	 * 
	 * @param fechaFin
	 */
	protected void setFechaFin(Timestamp fechaFin) {
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
	protected void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	/**
	 * 
	 * @return
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}

	/**
	 * 
	 * @param usuario
	 */
	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}// end Reserva