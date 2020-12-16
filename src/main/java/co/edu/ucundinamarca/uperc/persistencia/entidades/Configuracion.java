package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "CONFIGURACION")
public class Configuracion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "INTENTOS_FALLIDOS")
	private int intentosFallidos;

	@Column(name = "CADUCIDAD_CONTRASENA")
	private int caducidadContrasena;

	@Column(name = "MAX_ADMIN")
	private int maxAdmin;

	@Column(name = "FECHA_GUARDADO")
	private Date fechaGuardado;

	@OneToOne
	@JoinColumn(name = "USUARIO", referencedColumnName = "ID")
	private Usuario usuario;

	/**
	 * Constructor por defecto
	 */
	public Configuracion() {

	}

	/**
	 * Constructor que carga todos los atributos
	 * 
	 * @param intentosFallidos
	 * @param caducidadPass
	 * @param maxAdmin
	 * @param fechaGuardado
	 * @param usuario
	 * 
	 */
	public Configuracion(int intentosFallidos, int caducidadPass, int maxAdmin, Date fechaGuardado, Usuario usuario) {

		setIntentosFallidos(intentosFallidos);
		setCaducidadContrasena(caducidadPass);
		setMaxAdmin(maxAdmin);
		setFechaGuardado(fechaGuardado);
		setUsuario(usuario);

	}

	/**
	 * Obtiene el id
	 * 
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Guarda el id
	 * 
	 * @param id
	 */
	private void setId(int id) {
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
	private void setIntentosFallidos(int intentosFallidos) {
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
	private void setCaducidadContrasena(int caducidadContrasena) {
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
	private void setMaxAdmin(int maxAdmin) {
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
	private void setFechaGuardado(Date fechaGuardado) {
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
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}// end Configuracion