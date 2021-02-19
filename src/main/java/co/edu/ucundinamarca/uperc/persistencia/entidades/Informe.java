package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
 * Guarda los datos procesados de los reportes consultados a la base de datos
 * para ser consultados posteriormente por usuarios o sistemas externos
 * autorizados. Que contiene un (informe/reporte)? - Espacios disponibles/mes -
 * % planificación de reservas/dias/semanas/mes - Frecuencia de entrada y salida
 * de vehículos/mes - % reservas exitosas/mes - % reservas fallidas/mes - %
 * reconocimientos exitoso/mes (ingresos) - % reconocimiento fallido/mes -
 * Cantidad de placas reconocidas/mes - Cantidad de ingresos /día/semana/mes -
 * Cantidad de salidas /día/semana/mes
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
@Entity
@Table(name = "informe")
public class Informe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6120282131434078221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
//	@Column(name = "usuario")
	private Usuario usuario;

	@ManyToOne
//	@Column(name = "regservicio")
	@JoinColumn(name = "regservicio", referencedColumnName = "id")
	private RegServicio regServicio;

	@Column(name = "fechagenerado")
	private Timestamp fechaGenerado;

	@Column(name = "fechainicio")
	private Date fechaInicio;

	@Column(name = "fechafin")
	private Date fechaFin;

	/**
	 * Porcentaje de disponibilidad
	 */
	@Column(name = "disponibilidad")
	private double disponibilidad;

	/**
	 * Porcentaje de reservas exitosas
	 */
	@Column(name = "reservasok")
	private double reservasOk;

	/**
	 * Porcentaje de reservas fallidas
	 */
	@Column(name = "reservasfail")
	private double reservasFail;

	/**
	 * Porcentaje de reconocimientos exitosos
	 */
	@Column(name = "recogok")
	private double recogOk;

	/**
	 * Porcentaje de reconocimientos fallidos
	 */
	@Column(name = "recogfail")
	private double recogFail;

	/**
	 * Reconocimientos totales
	 */
	@Column(name = "recogtotal")
	private int recogTotal;

	@Column(name = "ingresostotal")
	private int ingresosTotal;

	@Column(name = "egresostotal")
	private int egresosTotal;

	/**
	 * Constructor por defecto
	 */
	public Informe() {

	}

	/**
	 * carga de solo id
	 */
	public Informe(long id) {
		setId(id);
	}

	/**
	 * @param usuario
	 * @param regServicio
	 * @param fechaGenerado
	 * @param fechaInicio
	 * @param fechaFin
	 * @param disponibilidad
	 * @param reservasOk
	 * @param reservasFail
	 * @param recogOk
	 * @param recogFail
	 * @param recogTotal
	 * @param ingresosTotal
	 * @param egresosTotal
	 */
	public Informe(Usuario usuario, RegServicio regServicio, Timestamp fechaGenerado, Date fechaInicio, Date fechaFin,
			double disponibilidad, double reservasOk, double reservasFail, double recogOk, double recogFail,
			int recogTotal, int ingresosTotal, int egresosTotal) {

		setUsuario(usuario);
		setRegServicio(regServicio);
		setFechaGenerado(fechaGenerado);
		setFechaInicio(fechaInicio);
		setFechaFin(fechaFin);
		setDisponibilidad(disponibilidad);
		setReservasOk(reservasOk);
		setReservasFail(reservasFail);
		setRecogOk(recogOk);
		setRecogFail(recogFail);
		setRecogTotal(recogTotal);
		setIngresosTotal(ingresosTotal);
		setEgresosTotal(egresosTotal);
	}
	
	/**
	 * carga de todos los atributos
	 * @param id
	 * @param usuario
	 * @param regServicio
	 * @param fechaGenerado
	 * @param fechaInicio
	 * @param fechaFin
	 * @param disponibilidad
	 * @param reservasOk
	 * @param reservasFail
	 * @param recogOk
	 * @param recogFail
	 * @param recogTotal
	 * @param ingresosTotal
	 * @param egresosTotal
	 */
	public Informe(long id, Usuario usuario, RegServicio regServicio, Timestamp fechaGenerado, Date fechaInicio, Date fechaFin,
			double disponibilidad, double reservasOk, double reservasFail, double recogOk, double recogFail,
			int recogTotal, int ingresosTotal, int egresosTotal) {
		setId(id);
		setUsuario(usuario);
		setRegServicio(regServicio);
		setFechaGenerado(fechaGenerado);
		setFechaInicio(fechaInicio);
		setFechaFin(fechaFin);
		setDisponibilidad(disponibilidad);
		setReservasOk(reservasOk);
		setReservasFail(reservasFail);
		setRecogOk(recogOk);
		setRecogFail(recogFail);
		setRecogTotal(recogTotal);
		setIngresosTotal(ingresosTotal);
		setEgresosTotal(egresosTotal);
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

//	
	/**
	 * @return the regServicio
	 */
	public RegServicio getRegServicio() {
		return this.regServicio;
	}

	/**
	 * @param regServicio the regServicio to set
	 */
	protected void setRegServicio(RegServicio regServicio) {
		this.regServicio = regServicio;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getFechaGenerado() {
		return this.fechaGenerado;
	}

	/**
	 * 
	 * @param fechaGenerado
	 */
	protected void setFechaGenerado(Timestamp fechaGenerado) {
		this.fechaGenerado = fechaGenerado;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * 
	 * @param fechaInicio
	 */
	protected void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
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
	protected void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * 
	 * @return
	 */
	public double getDisponibilidad() {
		return this.disponibilidad;
	}

	/**
	 * 
	 * @param disponibilidad
	 */
	protected void setDisponibilidad(double disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	/**
	 * 
	 * @return
	 */
	public double getReservasOk() {
		return this.reservasOk;
	}

	/**
	 * 
	 * @param reservasOk
	 */
	protected void setReservasOk(double reservasOk) {
		this.reservasOk = reservasOk;
	}

	/**
	 * 
	 * @return
	 */
	public double getReservasFail() {
		return this.reservasFail;
	}

	/**
	 * 
	 * @param reservasFail
	 */
	protected void setReservasFail(double reservasFail) {
		this.reservasFail = reservasFail;
	}

	/**
	 * 
	 * @return
	 */
	public double getRecogOk() {
		return this.recogOk;
	}

	/**
	 * 
	 * @param recogOk
	 */
	protected void setRecogOk(double recogOk) {
		this.recogOk = recogOk;
	}

	/**
	 * 
	 * @return
	 */
	public double getRecogFail() {
		return this.recogFail;
	}

	/**
	 * 
	 * @param recogFail
	 */
	protected void setRecogFail(double recogFail) {
		this.recogFail = recogFail;
	}

	/**
	 * 
	 * @return
	 */
	public int getRecogTotal() {
		return this.recogTotal;
	}

	/**
	 * 
	 * @param recogTotal
	 */
	protected void setRecogTotal(int recogTotal) {
		this.recogTotal = recogTotal;
	}

	/**
	 * 
	 * @return
	 */
	public int getIngresosTotal() {
		return this.ingresosTotal;
	}

	/**
	 * 
	 * @param ingresosTotal
	 */
	protected void setIngresosTotal(int ingresosTotal) {
		this.ingresosTotal = ingresosTotal;
	}

	/**
	 * 
	 * @return
	 */
	public int getEgresosTotal() {
		return this.egresosTotal;
	}

	/**
	 * 
	 * @param egresosTotal
	 */
	protected void setEgresosTotal(int egresosTotal) {
		this.egresosTotal = egresosTotal;
	}

}// end Informe