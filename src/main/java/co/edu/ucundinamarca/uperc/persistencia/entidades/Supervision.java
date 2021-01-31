package co.edu.ucundinamarca.uperc.persistencia.entidades;

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
 * Guarda los mensajes que envían los usuarios que posean el rol de supervisión.
 * - El campo ESTADO indica si el mensaje se encuentra activo o inactivo. - el
 * campo FECHA indica el momento de tiempo en que el mensaje fué creado o
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
	private Date fecha;

	/**
	 * <ul>
	 * <li>ALERTA - TRUE</li>
	 * <li>AVISOS - FLASE</li>
	 * </ul>
	 */
	@Column(name = "tipo")
	private boolean tipo;

	@ManyToOne
	@JoinColumn(name = "usuario", referencedColumnName = "id")
//	@Column(name = "USUARIO")
	private Usuario usuario;

	/**
	 * Constructor por defecto
	 */
	public Supervision() {

	}

	/**
	 * Constructor que inicializa todos loa atributos
	 * 
	 * @param mensaje
	 * @param estado
	 * @param fecha
	 * @param tipo
	 * @param usuario
	 */
	public Supervision(String mensaje, boolean estado, Date fecha, boolean tipo, Usuario usuario) {
		
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
	protected void setId(long id) {
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
	protected void setMensaje(String mensaje) {
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
	protected void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFecha() {
		return this.fecha;
	}

	/**
	 * 
	 * @param fecha
	 */
	protected void setFecha(Date fecha) {
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
	protected void setTipo(boolean tipo) {
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
	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}// end Supervision