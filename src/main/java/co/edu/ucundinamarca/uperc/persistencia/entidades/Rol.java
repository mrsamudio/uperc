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
 * 
 * ReqF-08 Roles El sistema deberá permitir la gestión de roles para asignar los
 * permisos y visualizaciones de funcionalidades dependiendo del tipo de rol.
 * 
 * 
 * ReqF-08.01 Administrativo 
 * ReqF-08.02 Docente 
 * ReqF-08.03 Graduado 
 * ReqF-08.04 Estudiante 
 * ReqF-08.05 Visitante 
 * ReqF-08.06 Supervisión 
 * ReqF-08.07 Administrador
 * 
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "ROL")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
//	TODO: verificar en el modelo
	@OneToMany(mappedBy = "ROL", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Usuario> usuarios;

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
	 * @param id
	 * @param nombre
	 */
	public Rol(String nombre, String descripcion, List<Usuario> usuarios) {

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

}// end Rol