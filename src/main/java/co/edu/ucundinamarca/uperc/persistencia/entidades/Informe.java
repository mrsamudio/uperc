package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
public class Informe {

	private long id;
	private long usuario;
	private Date fechaGenerado;
	private Date fechaInicio;
	private Date fechaFin;
	/**
	 * Porcentaje de disponibilidad
	 */
	private double disponibilidad;
	/**
	 * Porcentaje de reservas exitosas
	 */
	private double reservasOk;
	/**
	 * Porcentaje de reservas fallidas
	 */
	private double reservasFail;
	/**
	 * Porcentaje de reconocimientos exitosos
	 */
	private double recogOk;
	/**
	 * Porcentaje de reconocimientos fallidos
	 */
	private double recogFail;
	/**
	 * Reconocimientos totales
	 */
	private int recogTotal;
	private int ingresosTotal;
	private int egresosTotal;
	public RegServicio m_RegServicio;
	public Usuario m_Usuario;

	public void finalize() throws Throwable {

	}

	/**
	 * Constructor por defecto
	 */
	public Informe() {

	}

	/**
	 * Constructor que inicializa todos los atributos de la clase con excepci�n del
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

	}

	/**
	 * Constructor que inicializa todos los atributos de la clase
	 * 
	 * @param id
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
	public Informe(long id, long usuario, Date fechaGen, Date fechaIni, Date fechaFin, double disponibilidad,
			double reservasOk, double reservasFail, double recogOk, double recogFail, int recogTotal, int ingresosTotal,
			int egresosTotal) {

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
	public long getUsuario() {
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
	 * @return
	 */
	public Date getFechaGenerado() {
		return this.fechaGenerado;
	}

	/**
	 * 
	 * @param fechaGenerado
	 */
	public void setFechaGenerado(Date fechaGenerado) {
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
	public void setFechaInicio(Date fechaInicio) {
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
	public void setFechaFin(Date fechaFin) {
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

	/**
	 * 
	 * @param id
	 */
	public Informe selectById(long id) {
		return null;
	}

	public List<Informe> selectAll() {
		return null;
	}

	/**
	 * 
	 * @param informe
	 */
	public boolean insert(Informe informe) {
		return false;
	}

	/**
	 * 
	 * @param informe
	 */
	public boolean update(Informe informe) {
		return false;
	}

	/**
	 * 
	 * @param informe
	 */
	public boolean delete(Informe informe) {
		return false;
	}
}// end Informe