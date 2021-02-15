package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * ReqF-08 Roles El sistema deberá permitir la gestión de roles para asignar los
 * permisos y visualizaciones de funcionalidades dependiendo del tipo de rol.
 * 
 * 
 * ReqF-08.01 Administrativo ReqF-08.02 Docente ReqF-08.03 Graduado ReqF-08.04
 * Estudiante
 * 
 * ReqF-08.05 Visitante ReqF-08.06 Supervisión ReqF-08.07 Administrador
 * 
 * 
 * ReqF-07 Perfiles de usuario ReqF-07.01 Perfil Administrador ReqF-08.07 Rol
 * Administrador
 * 
 * ReqF-07.02 Perfil Supervisor ReqF-08.06 Rol Supervisión
 * 
 * ReqF-07.03 Perfil Usuario general ReqF-08.01 Rol Administrativo ReqF-08.02
 * Rol Docente ReqF-08.03 Rol Graduado ReqF-08.04 Rol Estudiante
 * 
 * ReqF-07.04 Perfil Visitante ReqF-08.05 Rol Visitante
 * 
 * ReqF-08.01 Administrativo ReqF-08.02 Docente ReqF-08.03 Graduado ReqF-08.04
 * Estudiante
 * 
 * 
 * 
 * 
 * 
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "perfilusuario", referencedColumnName = "id")
	@ManyToOne
	@Column(name = "perfil")
//	@JoinColumn(name = "perfil", referencedColumnName = "id")
	private PerfilUsuario perfilUsuario;

//	TODO: verificar en el modelo
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "rol", orphanRemoval = true)//dueño de relacion
	private Set<Usuario> usuarios;

	/**
	 * Constructor por defecto
	 */
	public Rol() {

	}

	/**
	 * Constructor que inicializa atributo nombre
	 * 
	 * @param nombre
	 */
	public Rol(String nombre) {

	}

	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param perfilUsuario
	 * @param usuarios
	 */
	public Rol(String nombre, String descripcion, PerfilUsuario perfilUsuario, Set<Usuario> usuarios) {

		setNombre(nombre);
		setDescripcion(descripcion);
		setPerfilUsuario(perfilUsuario);
		setUsuarios(usuarios);
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	protected void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the perfilUsuario
	 */
	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}

	/**
	 * @param perfilUsuario the perfilUsuario to set
	 */
	protected void setPerfilUsuario(PerfilUsuario perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	/**
	 * @return the usuarios
	 */
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	protected void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	/**
	 * Agregar usuario a la colección de usuarios
	 * @param usuario
	 */
	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		usuario.setRol(this);
		
	}

}// end Rol