package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * clase que representa la tabla de registros de entrada y salida de vehículos y
 * los usuarios asociados.
 * 
 * Guarda el momento en que los recursos(dispositivos de captura) registran el
 * ingreso o egreso al parqueadero de los usuarios y vehículos.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "REGISTRO_IE")
public class RegistroIE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "FECHA_INGRESO")
	private Date fechaIngreso;

	@Column(name = "FECHA_EGRESO")
	private Date fechaEgreso;

	@Column(name = "RECURSO")
	private int recurso;

	@Column(name = "VEHICULO")
	private int vehiculo;

	@Column(name = "USUARIO_INGRESO")
	private int usuarioIngreso;

	@Column(name = "USUARIO_EGRESO")
	private int usuarioEgreso;

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
	 * @param recurso
	 * @param vehiculo
	 * @param usuarioIngreso
	 * @param usuarioEgreso
	 */
	public RegistroIE(Date fechaIngreso, Date fechaEgreso, int recurso, int vehiculo, int usuarioIngreso,
			int usuarioEgreso) {

		setFechaIngreso(fechaIngreso);
		setFechaEgreso(fechaEgreso);
		setRecurso(recurso);
		setVehiculo(vehiculo);
		setUsuarioIngreso(usuarioIngreso);
		setUsuarioEgreso(usuarioEgreso);

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
	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	/**
	 * 
	 * @param fechaIngreso
	 */
	private void setFechaIngreso(Date fechaIngreso) {
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
	private void setFechaEgreso(Date fechaEgreso) {
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
	private void setRecurso(int recurso) {
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
	private void setVehiculo(int vehiculo) {
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
	private void setUsuarioIngreso(int usuarioIngreso) {
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
	private void setUsuarioEgreso(int usuarioEgreso) {
		this.usuarioEgreso = usuarioEgreso;
	}

}// end RegistroIE