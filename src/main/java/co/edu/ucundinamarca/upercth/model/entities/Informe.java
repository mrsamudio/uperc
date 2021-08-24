package co.edu.ucundinamarca.upercth.model.entities;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Guarda los datos procesados de los reportes consultados a la base de datos
 * para ser consultados posteriormente por usuarios o sistemas externos
 * autorizados. Que contiene un (informe/reporte)? - Espacios disponibles/mes -
 * % planificaci??n de reservas/dias/semanas/mes - Frecuencia de entrada y salida
 * de veh??culos/mes - % reservas exitosas/mes - % reservas fallidas/mes - %
 * reconocimientos exitoso/mes (ingresos) - % reconocimiento fallido/mes -
 * Cantidad de placas reconocidas/mes - Cantidad de ingresos /d??a/semana/mes -
 * Cantidad de salidas /d??a/semana/mes
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
	private static final long serialVersionUID = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
//	@Column(name = "usuario")
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@ManyToOne
//	@Column(name = "regservicio")
	@JoinColumn(name = "regservicio")
//	@JoinColumn(name = "regservicio", referencedColumnName = "id")
	private RegServicio regServicio;

	@Column(name = "fechagenerado")
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fechaGenerado;

	@Column(name = "fechainicio")
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fechaInicio;

	@Column(name = "fechafin")
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fechaFin;

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
	 * Carga los atributos para una inserci??n para el usuario
	 * 
	 * @param usuario
	 * @param regServicio
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
	public Informe(Usuario usuario, Timestamp fechaGenerado, Timestamp fechaInicio, Timestamp fechaFin,
			double disponibilidad, double reservasOk, double reservasFail, double recogOk, double recogFail,
			int recogTotal, int ingresosTotal, int egresosTotal) {
		
		setUsuario(usuario);
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
	 * Carga los atributos para una inserci??n para el registro de servicio
	 * 
	 * @param usuario
	 * @param regServicio
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
	public Informe(RegServicio regServicio, Timestamp fechaGenerado, Timestamp fechaInicio, Timestamp fechaFin,
			double disponibilidad, double reservasOk, double reservasFail, double recogOk, double recogFail,
			int recogTotal, int ingresosTotal, int egresosTotal) {
		
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
	 * Carga los atributos para una inserción completa {@link Usuario} y {@link RegServicio}
	 * 
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
	public Informe(Usuario usuario, RegServicio regServicio, Timestamp fechaGenerado, Timestamp fechaInicio, Timestamp fechaFin,
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
	 * carga de todos los atributos incluyendo id
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
	public Informe(long id, Usuario usuario, RegServicio regServicio, Timestamp fechaGenerado, Timestamp fechaInicio, Timestamp fechaFin,
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
	public void setId(long id) {
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
	public void setUsuario(Usuario usuario) {
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
	public void setRegServicio(RegServicio regServicio) {
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
	public void setFechaGenerado(Timestamp fechaGenerado) {
		this.fechaGenerado = fechaGenerado;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * 
	 * @param fechaInicio
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
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
	public void setDisponibilidad(double disponibilidad) {
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
	public void setReservasOk(double reservasOk) {
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
	public void setReservasFail(double reservasFail) {
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
	public void setRecogOk(double recogOk) {
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
	public void setRecogFail(double recogFail) {
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
	public void setRecogTotal(int recogTotal) {
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
	public void setIngresosTotal(int ingresosTotal) {
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
	public void setEgresosTotal(int egresosTotal) {
		this.egresosTotal = egresosTotal;
	}

}// end Informe