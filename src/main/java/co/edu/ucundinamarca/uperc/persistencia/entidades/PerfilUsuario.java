package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Guarda los perfiles de usuario definidos en los requisitos. Es una tabla
 * catálogo.
 * 
 * ReqF-07 Perfiles de usuario 
 * ReqF-07.01 Administrador 
 * ReqF-07.02 Supervisor
 * ReqF-07.03 Usuario general
 * ReqF-07.04 Visitante 
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
@Entity
//@Table(name = "perfil_usuario")
//@Table(name = "perfilusuario")
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "perfil")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)//dueño de relacion
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "perfilusuario", orphanRemoval = true)//dueño de relacion
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "perfilUsuario", orphanRemoval = true)//dueño de relacion
	private Set<Rol> roles;



	/**
	 * Constructor por defecto
	 */
	public PerfilUsuario() {

	}

	/**
	 * Constructor que inicializa atributo nombre
	 * 
	 * @param nombre
	 */
	public PerfilUsuario(String nombre) {
		setNombre(nombre);
	}
	
	/**
	 * Constructor que inicializa atributo id
	 * 
	 * @param id
	 */
	public PerfilUsuario(int id) {

		setId(id);
	}
	
	
	/**
	 * Constructor que inicializa todos los atributos sin id
	 * 
	 * @param nombre
	 */
	public PerfilUsuario(String nombre, String descripcion, Set<Rol> roles) {
		
		setNombre(nombre);
		setDescripcion(descripcion);
		setRoles(roles);
	}

	/**
	 * Constructor que inicializa los atributos para inserción
	 * 
	 * @param nombre
	 */
	public PerfilUsuario(String nombre, String descripcion) {

		setNombre(nombre);
		setDescripcion(descripcion);
	}
	
	/**
	 * Constructor que inicializa todos los atributos sin set roles
	 * 
	 * @param id
	 * @param nombre
	 * @param descripcion
	 */
	public PerfilUsuario(int id, String nombre, String descripcion) {
		
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
	}
	
	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param roles
	 */
	public PerfilUsuario(int id, String nombre, String descripcion, Set<Rol> roles) {

		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
		setRoles(roles);
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
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return la descripción
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * @param descripcion the descripción to set
	 */
	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the roles
	 */
	public Set<Rol> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	protected void setRoles(Set<Rol> usuarios) {
		this.roles = usuarios;
	}

	/**
	 * Agregar rol a la colección de roles
	 * @param rol
	 */
	public void agregarRol(Rol rol) {
		this.roles.add(rol);
		rol.setPerfil(this);
	}
	
	
}// end PerfilUsuario