package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Objeto java con anotaciones AJO. La entidad configuración guarda las
 * configuraciones del sistema realizadas por los usuarios
 * autorizados(Administradores)
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */

@Entity
//@Entity(name = "Configuracion")
@Table(name = "configuracion")
//@Table(name = "configuracion", schema = "public")
//TODO: probar con named queries
public class Configuracion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8071463945094328022L;

	
	@Id
//	@SequenceGenerator(name = "configuracion_id_seq", sequenceName = "configuracion_id_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "configuracion_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
//	@Column(name = "id", columnDefinition = "serial")
	private Long id;

	@Column(name = "intentosfallidos")
	private int intentosFallidos;

	@Column(name = "caducidadcontrasena")
	private int caducidadContrasena;

	@Column(name = "maxadmin")
	private int maxAdmin;

	@Column(name = "fechaguardado")
	private Date fechaGuardado;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario", referencedColumnName = "id")
//	@JoinColumn(name = "usuario", referencedColumnName = "id")
//	@JoinColumn(name = "usuarioId", updatable = false, insertable = false)
	private Usuario usuario;

//	/**
//	 * Constructor por defecto
//	 */
//	public Configuracion() {
//
//	}

//	/**
//	 * Constructor que carga todos los atributos
//	 * 
//	 * @param intentosFallidos
//	 * @param caducidadPass
//	 * @param maxAdmin
//	 * @param fechaGuardado
//	 * @param usuario
//	 * 
//	 */
//	public Configuracion(int intentosFallidos, int caducidadPass, int maxAdmin, Date fechaGuardado, Usuario usuario) {
//
//		setIntentosFallidos(intentosFallidos);
//		setCaducidadContrasena(caducidadPass);
//		setMaxAdmin(maxAdmin);
//		setFechaGuardado(fechaGuardado);
//		setUsuario(usuario);
//
//	}

	/**
	 * Obtiene el id
	 * 
	 * @return id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Guarda el id
	 * 
	 * @param id
	 */
	protected void setId(long id) {
		this.id = id;
	}

	/**
	 * Obtiene los intentos fallidos
	 * 
	 * @return intentos fallidos
	 */
	public int getIntentosFallidos() {
		return this.intentosFallidos;
	}

	/**
	 * Guarda los intentos fallidos
	 * 
	 * @param intentosFallidos
	 */
	protected void setIntentosFallidos(int intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	/**
	 * Obtiene el valor máximo de caducidad de la contraseña. El entero representa
	 * días
	 * 
	 * @return caducidad de la contraseña
	 */
	public int getCaducidadContrasena() {
		return this.caducidadContrasena;
	}

	/**
	 * Guarda el valor máximo de caducidad de la contraseña
	 * 
	 * @param caducidadContrasena
	 */
	protected void setCaducidadContrasena(int caducidadContrasena) {
		this.caducidadContrasena = caducidadContrasena;
	}

	/**
	 * Obtiene el número máximo de administradores permitidos en el sistema
	 * 
	 * @return el número máximo de administradores permitidos
	 */
	public int getMaxAdmin() {
		return this.maxAdmin;
	}

	/**
	 * Guarda el número máximo de administradores permitidos en el sistema
	 * 
	 * @param maxAdmin
	 */
	protected void setMaxAdmin(int maxAdmin) {
		this.maxAdmin = maxAdmin;
	}

	/**
	 * Obtiene la fecha en que se guardó la configuración
	 * 
	 * @return fecha de la configuración guardada
	 */
	public Date getFechaGuardado() {
		return this.fechaGuardado;
	}

	/**
	 * Guarda la fecha en que se realizó la configuración
	 * 
	 * @param fechaGuardado
	 */
	protected void setFechaGuardado(Date fechaGuardado) {
		this.fechaGuardado = fechaGuardado;
	}

	/**
	 * Obtiene el usuario
	 * 
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Inicializa el atributo usuario
	 * 
	 * @param usuario the usuario to set
	 */
	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}// end Configuracion