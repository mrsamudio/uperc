package co.edu.ucundinamarca.upercth.model.entities;

import java.io.Serializable;
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
 * ReqF-08 Roles El sistema deber?? permitir la gesti??n de roles para asignar los
 * permisos y visualizaciones de funcionalidades dependiendo del tipo de rol.
 * 
 * 
 * ReqF-08.01 Administrativo ReqF-08.02 Docente ReqF-08.03 Graduado ReqF-08.04
 * Estudiante
 * 
 * ReqF-08.05 Visitante ReqF-08.06 Supervisi??n ReqF-08.07 Administrador
 * 
 * 
 * ReqF-07 Perfiles de usuario ReqF-07.01 Perfil Administrador ReqF-08.07 Rol
 * Administrador
 * 
 * ReqF-07.02 Perfil Supervisor ReqF-08.06 Rol Supervisi??n
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
public class Rol implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
//	@Column(name = "perfil")
//	@JoinColumn(name = "perfil", referencedColumnName = "id")
	@JoinColumn(name = "perfil")
	private PerfilUsuario perfil;

	@OneToMany(mappedBy = "rol")//due??o de relacion
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "rol", orphanRemoval = true)//due??o de relacion
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "rol")
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
	 * Constructor que inicializa todos los atributos sin id
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param perfil
	 * @param usuarios
	 */
	public Rol(String nombre, String descripcion, PerfilUsuario perfilUsuario, Set<Usuario> usuarios) {

		setNombre(nombre);
		setDescripcion(descripcion);
		setPerfil(perfilUsuario);
		setUsuarios(usuarios);
	}
	
	/**
	 * Constructor que inicializa todos los atributos sin set usuarios para inserci??n en bd
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param perfil
	 */
	public Rol(String nombre, String descripcion, PerfilUsuario perfilUsuario) {
		
		setNombre(nombre);
		setDescripcion(descripcion);
		setPerfil(perfilUsuario);
	}
	
	/**
	 * Constructor que inicializa todos los atributos sin set usuarios para update en bd
	 * 
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param perfil
	 */
	public Rol(int id, String nombre, String descripcion, PerfilUsuario perfilUsuario) {
		
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
		setPerfil(perfilUsuario);
	}
	
	/**
	 * Constructor que inicializa todos los atributos 
	 * 
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param perfil
	 * @param usuarios
	 */
	public Rol(int id, String nombre, String descripcion, PerfilUsuario perfilUsuario, Set<Usuario> usuarios) {
		
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
		setPerfil(perfilUsuario);
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
	public void setId(int id) {
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
	public void setNombre(String nombre) {
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
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the perfil
	 */
	public PerfilUsuario getPerfil() {
		return perfil;
	}

	/**
	 * @param perfil the perfil to set
	 */
	public void setPerfil(PerfilUsuario perfilUsuario) {
		this.perfil = perfilUsuario;
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
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	/**
	 * Agregar usuario a la colecci??n de usuarios
	 * @param usuario
	 */
	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
		usuario.setRol(this);
		
	}

}// end Rol