package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;

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
@Table(name = "PERFIL_USUARIO")
public class PerfilUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
//	TODO: verificar en el modelo
	@OneToMany(mappedBy = "PERFIL_USUARIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Usuario> usuarios;



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
	public PerfilUsuario(String nombre, String descripcion, List<Usuario> usuarios) {

		setNombre(nombre);
		setDescripcion(descripcion);
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
	private void setId(int id) {
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
	private void setNombre(String nombre) {
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
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}// end PerfilUsuario