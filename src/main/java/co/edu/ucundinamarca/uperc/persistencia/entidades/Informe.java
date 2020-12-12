package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "INFORME")
public class Informe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "USUARIO")
	private long usuario;

	@Column(name = "FECHA_GENERADO")
	private Date fechaGenerado;

	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	/**
	 * Porcentaje de disponibilidad
	 */
	@Column(name = "DISPONIBILIDAD")
	private double disponibilidad;

	/**
	 * Porcentaje de reservas exitosas
	 */
	@Column(name = "RESERVAS_OK")
	private double reservasOk;

	/**
	 * Porcentaje de reservas fallidas
	 */
	@Column(name = "RESERVAS_FAIL")
	private double reservasFail;

	/**
	 * Porcentaje de reconocimientos exitosos
	 */
	@Column(name = "RECOG_OK")
	private double recogOk;

	/**
	 * Porcentaje de reconocimientos fallidos
	 */
	@Column(name = "RECOG_FAIL")
	private double recogFail;

	/**
	 * Reconocimientos totales
	 */
	@Column(name = "RECOG_TOTAL")
	private int recogTotal;

	@Column(name = "INGRESOS_TOTAL")
	private int ingresosTotal;

	@Column(name = "EGRESOS_TOTAL")
	private int egresosTotal;

	/**
	 * Constructor por defecto
	 */
	public Informe() {

	}

	/**
	 * Constructor que inicializa todos los atributos de la clase con excepción del
	 * id
	 * 
	 * @param usuario
	 * @param fechaGen
	 * @param fechaIni
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
	public Informe(long usuario, Date fechaGen, Date fechaIni, Date fechaFin, double disponibilidad, double reservasOk,
			double reservasFail, double recogOk, double recogFail, int recogTotal, int ingresosTotal,
			int egresosTotal) {

		setUsuario(usuario);
		setFechaGenerado(fechaGen);
		setFechaInicio(fechaIni);
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
	private void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public long getUsuario() {
		return this.usuario;
	}

	/**
	 * 
	 * @param usuario
	 */
	private void setUsuario(long usuario) {
		this.usuario = usuario;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaGenerado() {
		return this.fechaGenerado;
	}

	/**
	 * 
	 * @param fechaGenerado
	 */
	private void setFechaGenerado(Date fechaGenerado) {
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
	private void setFechaInicio(Date fechaInicio) {
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
	private void setFechaFin(Date fechaFin) {
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
	private void setDisponibilidad(double disponibilidad) {
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
	private void setReservasOk(double reservasOk) {
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
	private void setReservasFail(double reservasFail) {
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
	private void setRecogOk(double recogOk) {
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
	private void setRecogFail(double recogFail) {
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
	private void setRecogTotal(int recogTotal) {
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
	private void setIngresosTotal(int ingresosTotal) {
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
	private void setEgresosTotal(int egresosTotal) {
		this.egresosTotal = egresosTotal;
	}

}// end Informe