package co.edu.ucundinamarca.upercth.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Objeto java con anotaciones AJO. La entidad configuraci??n guarda las
 * configuraciones del sistema realizadas por los usuarios
 * autorizados(Administradores)
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */

@Entity
@Table(name = "configuracion")
public class Configuracion implements Serializable {

	private static final long serialVersionUID = -8071463945094328022L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "intentosfallidos")
	private int intentosFallidos;

	@Column(name = "caducidadcontrasena")
	private int caducidadContrasena;

	@Column(name = "maxadmin")
	private int maxAdmin;

	@Column(name = "fechaguardado")
	private Timestamp fechaGuardado;

	@OneToOne(mappedBy = "configuracion")
	private Usuario usuario;

	/**
	 * Constructor por defecto
	 */
	public Configuracion() {

	}

	/**
	 * Carga solamente id
	 * @param id
	 */
	public Configuracion(long id) {
		setId(id);
	}

	/**
	 * todos los atributos sin el atribudo id
	 * 
	 * @param intentosFallidos
	 * @param caducidadPass
	 * @param maxAdmin
	 * @param fechaGuardado
	 * @param usuario
	 * 
	 */
	public Configuracion(int intentosFallidos, int caducidadPass, int maxAdmin, Timestamp fechaGuardado, Usuario usuario) {

		setIntentosFallidos(intentosFallidos);
		setCaducidadContrasena(caducidadPass);
		setMaxAdmin(maxAdmin);
		setFechaGuardado(fechaGuardado);
		setUsuario(usuario);

	}
	
	/**
	 * Constructor que carga todos los atributos de la entidad mapeada en bd
	 * @param id
	 * @param intentosFallidos
	 * @param caducidadPass
	 * @param maxAdmin
	 * @param fechaGuardado
	 * @param usuario
	 */
	public Configuracion(long id, int intentosFallidos, int caducidadPass, int maxAdmin, Timestamp fechaGuardado) {
		
		setId(id);
		setIntentosFallidos(intentosFallidos);
		setCaducidadContrasena(caducidadPass);
		setMaxAdmin(maxAdmin);
		setFechaGuardado(fechaGuardado);
		
	}
	
	/**
	 * Constructor que carga todos los atributos
	 * @param id
	 * @param intentosFallidos
	 * @param caducidadPass
	 * @param maxAdmin
	 * @param fechaGuardado
	 * @param usuario
	 */
	public Configuracion(long id, int intentosFallidos, int caducidadPass, int maxAdmin, Timestamp fechaGuardado, Usuario usuario) {
		
		setId(id);
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
	public long getId() {
		return this.id;
	}

	/**
	 * Guarda el id
	 * 
	 * @param id
	 */
	public void setId(long id) {
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
	public void setIntentosFallidos(int intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	/**
	 * Obtiene el valor m??ximo de caducidad de la contrase??a. El entero representa
	 * d??as
	 * 
	 * @return caducidad de la contrase??a
	 */
	public int getCaducidadContrasena() {
		return this.caducidadContrasena;
	}

	/**
	 * Guarda el valor m??ximo de caducidad de la contrase??a
	 * 
	 * @param caducidadContrasena
	 */
	public void setCaducidadContrasena(int caducidadContrasena) {
		this.caducidadContrasena = caducidadContrasena;
	}

	/**
	 * Obtiene el n??mero m??ximo de administradores permitidos en el sistema
	 * 
	 * @return el n??mero m??ximo de administradores permitidos
	 */
	public int getMaxAdmin() {
		return this.maxAdmin;
	}

	/**
	 * Guarda el n??mero m??ximo de administradores permitidos en el sistema
	 * 
	 * @param maxAdmin
	 */
	public void setMaxAdmin(int maxAdmin) {
		this.maxAdmin = maxAdmin;
	}

	/**
	 * Obtiene la fecha en que se guard?? la configuraci??n
	 * 
	 * @return fecha de la configuraci??n guardada
	 */
	public Timestamp getFechaGuardado() {
		return this.fechaGuardado;
	}

	/**
	 * Guarda la fecha en que se realiz?? la configuraci??n
	 * 
	 * @param fechaGuardado
	 */
	public void setFechaGuardado(Timestamp fechaGuardado) {
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
		usuario.setConfiguracion(this);
	}

}// end Configuracion