/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.awt.Point;
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
 * @author mrsamudio
 *
 */
@Entity
@Table(name = "ubicacion")
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "coordenadas", columnDefinition="Point")
	private Point coordenadas;

	@Column(name = "telefono")
	private String telefono;

//	@OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ubicacion", orphanRemoval = true)//dueño de relacion
	private Set<EspacioParqueo> espaciosParqueo;

	/**
	 * 
	 */
	public Ubicacion() {

	}

	/**
	 * @param nombre
	 * @param direccion
	 * @param coordenadas
	 * @param telefono
	 * @param espaciosParqueo
	 */
	public Ubicacion(String nombre, String direccion, Point coordenadas, String telefono, Set<EspacioParqueo> espaciosParqueo) {

		setNombre(nombre);
		setDireccion(direccion);
		setCoordenadas(coordenadas);
		setTelefono(telefono);
		setEspaciosParqueo(espaciosParqueo);
	}

	/**
	 * 
	 * @param reserva
	 */
	public void agregarEspaciosParqueo(EspacioParqueo espacioParqueo) {
		this.espaciosParqueo.add(espacioParqueo);
		espacioParqueo.setUbicacion(this);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the coordenadas
	 */
	public Point getCoordenadas() {
		return coordenadas;
	}

	/**
	 * @param coordenadas the coordenadas to set
	 */
	public void setCoordenadas(Point coordenadas) {
		this.coordenadas = coordenadas;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	protected void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the espaciosParqueo
	 */
	public Set<EspacioParqueo> getEspaciosParqueo() {
		return espaciosParqueo;
	}

	/**
	 * @param espaciosParqueo the espaciosParqueo to set
	 */
	protected void setEspaciosParqueo(Set<EspacioParqueo> espaciosParqueo) {
		this.espaciosParqueo = espaciosParqueo;
	}
	
	/**
	 * 
	 * @param espacioParqueo
	 */
	public void agregarEspacioParqueo(EspacioParqueo espacioParqueo) {
		this.espaciosParqueo.add(espacioParqueo);
		espacioParqueo.setUbicacion(this);
	}

}
