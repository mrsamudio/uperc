package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.time.Month;
import java.util.Date;
import java.util.List;

/**
 * clase que representa la tabla de registros de entrada y salida de vehiculos y
 * los usuarios asociados.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
public class RegistroIE {

	private long id;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private int recurso;
	private int vehiculo;
	private int usuarioIngreso;
	private int usuarioEgreso;
	public Vehiculo m_Vehiculo;
	public Usuario m_Usuario;
	public Recurso m_Recurso;

	public void finalize() throws Throwable {

	}

	/**
	 * Constructor por defecto
	 */
	public RegistroIE() {

	}

	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 * @param fechaIngreso
	 * @param fechaEgreso
	 * @param recurso
	 * @param vehiculo
	 * @param usuarioIngreso
	 * @param usuarioEgreso
	 */
	public RegistroIE(long id, Date fechaIngreso, Date fechaEgreso, int recurso, int vehiculo, int usuarioIngreso,
			int usuarioEgreso) {

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
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	/**
	 * 
	 * @param fechaIngreso
	 */
	public void setFechaIngreso(Date fechaIngreso) {
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
	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	/**
	 * 
	 * @return
	 */
	public int getRecurso() {
		return this.recurso;
	}

	/**
	 * 
	 * @param recurso
	 */
	public void setRecurso(int recurso) {
		this.recurso = recurso;
	}

	/**
	 * 
	 * @return
	 */
	public int getVehiculo() {
		return this.vehiculo;
	}

	/**
	 * 
	 * @param vehiculo
	 */
	public void setVehiculo(int vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * 
	 * @return
	 */
	public int getUsuarioIngreso() {
		return this.usuarioIngreso;
	}

	/**
	 * 
	 * @param usuarioIngreso
	 */
	public void setUsuarioIngreso(int usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}

	/**
	 * 
	 * @return
	 */
	public int getUsuarioEgreso() {
		return this.usuarioEgreso;
	}

	/**
	 * 
	 * @param usuarioEgreso
	 */
	public void setUsuarioEgreso(int usuarioEgreso) {
		this.usuarioEgreso = usuarioEgreso;
	}

	/**
	 * 
	 * @param id
	 */
	public RegistroIE selectById(long id) {
		return null;
	}

	/**
	 * 
	 * @param fecha
	 */
	public List<RegistroIE> selectByDate(Date fecha) {
		return null;
	}

	/**
	 * 
	 * @param mes
	 */
	public List<RegistroIE> selectByMonth(Month mes) {
		return null;
	}

	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 */
	public List<RegistroIE> selectByRange(Date fechaInicial, Date fechaFinal) {
		return null;
	}

	/**
	 * 
	 * @param registroIE
	 */
	public boolean insert(RegistroIE registroIE) {
		return false;
	}

	/**
	 * 
	 * @param registroIE
	 */
	public boolean update(RegistroIE registroIE) {
		return false;
	}
}// end RegistroIE