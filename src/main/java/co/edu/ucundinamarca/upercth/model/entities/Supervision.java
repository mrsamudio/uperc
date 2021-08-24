package co.edu.ucundinamarca.upercth.model.entities;

import java.sql.Timestamp;

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
 * Guarda los mensajes que env??an los usuarios que posean el rol de supervisi??n.
 * - El campo ESTADO indica si el mensaje se encuentra activo o inactivo. - el
 * campo FECHA indica el momento de tiempo en que el mensaje fu?? creado o
 * modificado. - El campo TIPO indica si el mensaje es una alerta o un aviso.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "supervision")
public class Supervision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "mensaje")
	private String mensaje;

	@Column(name = "estado")
	private boolean estado;

	@Column(name = "fecha")
	private Timestamp fecha;

	/**
	 * <ul>
	 * <li>ALERTA - TRUE</li>
	 * <li>AVISOS - FALSE</li>
	 * </ul>
	 */
	@Column(name = "tipo")
	private boolean tipo;

	@ManyToOne
//	@Column(name = "usuario")
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	/**
	 * Constructor por defecto
	 */
	public Supervision() {

	}

	/**
	 * Constructor que inicializa todos los atributos para inserci??n
	 * 
	 * @param mensaje
	 * @param estado
	 * @param fecha
	 * @param tipo {@link #tipo}
	 * @param usuario
	 */
	public Supervision(String mensaje, boolean estado, Timestamp fecha, boolean tipo, Usuario usuario) {
		
		setMensaje(mensaje);
		setEstado(estado);
		setFecha(fecha);
		setTipo(tipo);
		setUsuario(usuario);
	}
	
	/**
	 * Constructor que inicializa todos los atributos para inserci??n
	 * 
	 * @param id
	 * @param mensaje
	 * @param estado
	 * @param fecha
	 * @param tipo {@link #tipo}
	 * @param usuario
	 */
	public Supervision(long id, String mensaje, boolean estado, Timestamp fecha, boolean tipo, Usuario usuario) {
		
		setId(id);
		setMensaje(mensaje);
		setEstado(estado);
		setFecha(fecha);
		setTipo(tipo);
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
	public String getMensaje() {
		return this.mensaje;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	public Timestamp getFecha() {
		return this.fecha;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTipo() {
		return this.tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
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

	
}// end Supervision