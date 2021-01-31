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
@Table(name = "perfilusuario")
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;
	
//	TODO: verificar en el modelo
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfilUsuario")
//	@OneToMany(mappedBy = "perfilusuario",  targetEntity = Rol.class)
//	@OneToMany(mappedBy = "perfilusuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
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
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param nombre
	 */
	public PerfilUsuario(String nombre, String descripcion, Set<Rol> roles) {

		setNombre(nombre);
		setDescripcion(descripcion);
		setRoles(roles);
	}
	
	
	/**
	 * 
	 * @param rol
	 */
	public void agregarRoles(Rol rol) {
		this.roles.add(rol);
		rol.setPerfilUsuario(this);
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
	
	
}// end PerfilUsuario