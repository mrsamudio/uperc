package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

/**
 * @author Mario Roberto Samudio Martinez
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
public class Reserva {

	private long id;
	private Date fechaSolicitud;
	private boolean estado;
	private int numEstacion;
	private Date fechaReserva;
	private Date fechaFin;
	private boolean cancelada;
	private int usuario;
	public Usuario m_Usuario;

	/**
	 * 
	 */
	public void finalize() throws Throwable {

	}

	/**
	 * Constructor por defecto
	 */
	public Reserva() {

	}

	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 * @param fechaSolicitud
	 * @param estado
	 * @param numEstacion
	 * @param fechaReserva
	 * @param fechaFin
	 * @param cancelada
	 * @param usuario
	 */
	public Reserva(long id, Date fechaSolicitud, boolean estado, int numEstacion, Date fechaReserva, Date fechaFin,
			boolean cancelada, int usuario) {

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
	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	/**
	 * 
	 * @param fechaSolicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
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
	public void setEstado(boolean estado) {
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
	public void setNumEstacion(int numEstacion) {
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
	public void setFechaReserva(Date fechaReserva) {
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
	public void setFechaFin(Date fechaFin) {
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
	public void setCancelada(boolean cancelada) {
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
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	/**
	 * 
	 * @param id
	 */
	public Reserva selectById(long id) {
		return null;
	}

	/**
	 * 
	 * @param fechaSolicitud
	 */
	public List<Reserva> selectByFechaSol(Date fechaSolicitud) {
		return null;
	}

	/**
	 * 
	 * @param fechaReserva
	 */
	public List<Reserva> selectByFechaRes(Date fechaReserva) {
		return null;
	}

	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 */
	public List<Reserva> selectByRangoFSol(Date fechaInicial, Date fechaFinal) {
		return null;
	}

	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 */
	public List<Reserva> selectByRangoFRes(Date fechaInicial, Date fechaFinal) {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Reserva> selectAll() {
		return null;
	}

	/**
	 * 
	 * @param reserva
	 */
	public boolean update(Reserva reserva) {
		return false;
	}

	/**
	 * 
	 * @param reserva
	 */
	public boolean insert(Reserva reserva) {
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean activate(long id) {
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean deactivate(long id) {
		return false;
	}

//	/**
//	 * 
//	 * @param fecha
//	 */
//	public List<EspaciosParqueo> consultaDisponibilidad(Date fecha) {
//		return null;
//	}
}// end Reserva