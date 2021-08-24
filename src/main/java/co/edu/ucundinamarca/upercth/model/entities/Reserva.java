package co.edu.ucundinamarca.upercth.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * Guarda las reservas que han sido solicitadas por los usuarios. - El campo
 * FECHA_SOLICITUD registra el momento de tiempo en que se solicit?? la reserva.
 * - El campo ESTADO registra si la reserva se encuentra activa o inactiva. - El
 * campo NUM_ESTACION registra el n??mero del estacionamiento reservado para el
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
@Table(name = "reserva")
public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2004993349333387121L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "fechasolicitud")
	private Timestamp fechaSolicitud;

	@Column(name = "estado")
	private boolean estado;

	@ManyToOne
	@JoinColumn(name = "espacioparqueo")
//	@JoinColumn(name = "espacioparqueo", referencedColumnName = "id")
	private EspacioParqueo espacioParqueo;

	@Column(name = "fechareserva")
	private Timestamp fechaReserva;

	@Column(name = "fechafin")
	private Timestamp fechaFin;

	@Column(name = "cancelada")
	private boolean cancelada;

	@ManyToOne
//	@Column(name = "usuario")
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	/**
	 * Constructor por defecto
	 */
	public Reserva() {

	}

	/**
	 * Constructor para inserci??n en bd
	 * 
	 * @param fechaSolicitud
	 * @param estado
	 * @param espacioParqueo
	 * @param fechaReserva
	 * @param cancelada
	 * @param usuario
	 */
	public Reserva(Timestamp fechaSolicitud, boolean estado, EspacioParqueo espacioParqueo, Timestamp fechaReserva,
			boolean cancelada, Usuario usuario) {

		setFechaSolicitud(fechaSolicitud);
		setEstado(estado);
		setEspacioParqueo(espacioParqueo);
		setFechaReserva(fechaReserva);
		setCancelada(cancelada);
		setUsuario(usuario);
	}

	/**
	 * Constructor que carga todos los atributos con fecha de fin
	 * 
	 * @param fechaSolicitud
	 * @param estado
	 * @param espacioParqueo
	 * @param fechaReserva
	 * @param fechaFin
	 * @param cancelada
	 * @param usuario
	 */
	public Reserva(Timestamp fechaSolicitud, boolean estado, EspacioParqueo espacioParqueo, Timestamp fechaReserva,
			Timestamp fechaFin, boolean cancelada, Usuario usuario) {

		setFechaSolicitud(fechaSolicitud);
		setEstado(estado);
		setEspacioParqueo(espacioParqueo);
		setFechaReserva(fechaReserva);
		setFechaFin(fechaFin);
		setCancelada(cancelada);
		setUsuario(usuario);
	}

	/**
	 * Constructor que carga todos los atributos para actualizaci??n en bd
	 * 
	 * @param id
	 * @param fechaSolicitud
	 * @param estado
	 * @param espacioParqueo
	 * @param fechaReserva
	 * @param fechaFin
	 * @param cancelada
	 * @param usuario
	 */
	public Reserva(long id, Timestamp fechaSolicitud, boolean estado, EspacioParqueo espacioParqueo,
			Timestamp fechaReserva, Timestamp fechaFin, boolean cancelada, Usuario usuario) {

		setId(id);
		setFechaSolicitud(fechaSolicitud);
		setEstado(estado);
		setEspacioParqueo(espacioParqueo);
		setFechaReserva(fechaReserva);
		setFechaFin(fechaFin);
		setCancelada(cancelada);
		setUsuario(usuario);
	}

	/**
	 * Constructor que carga para actualizaci??n de la fecha de finalizaci??n o
	 * cancelaci??n de la reserva en bd
	 * 
	 * @param id
	 * @param estado
	 * @param fechaFin
	 * @param cancelada
	 */
	public Reserva(long id, boolean estado, Timestamp fechaFin, boolean cancelada) {

		setId(id);
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
	public void setId(long id) {
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
	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Obtiene el estado de la reserva
	 * @return true activa, false inactiva
	 */
	public boolean isEstado() {
		return this.estado;
	}

	/**
	 * 
	 * @param estado
	 */
	public void setEstado(boolean estado) {
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
	public void setEspacioParqueo(EspacioParqueo espacioParqueo) {
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
	public void setFechaReserva(Timestamp fechaReserva) {
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
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Obtiene el estado de cancelación de la reserva finalizada
	 * 
	 * @return true se canceĺó, false no se canceló
	 */
	public boolean isCancelada() {
		return this.cancelada;
	}

	/**
	 * 
	 * @param cancelada
	 */
	public void setCancelada(boolean cancelada) {
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
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}// end Reserva