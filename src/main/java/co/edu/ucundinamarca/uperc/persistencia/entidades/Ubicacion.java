/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.awt.geom.Point2D;
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
 * @author mrsamudio
 *
 */
@Entity
@Table(name = "UBICACION")
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DIRECCION")
	private String direccion;

	@Column(name = "COORDENADAS")
	private Point2D coordenadas;

	@Column(name = "TELEFONO")
	private String telefono;

//	Listas
	@OneToMany(mappedBy = "UBICACION", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<EspacioParqueo> espaciosParqueo;

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
	public Ubicacion(String nombre, String direccion, Point2D coordenadas, String telefono, List<EspacioParqueo> espaciosParqueo) {

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
	public Point2D getCoordenadas() {
		return coordenadas;
	}

	/**
	 * @param coordenadas the coordenadas to set
	 */
	public void setCoordenadas(Point2D coordenadas) {
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
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the espaciosParqueo
	 */
	public List<EspacioParqueo> getEspaciosParqueo() {
		return espaciosParqueo;
	}

	/**
	 * @param espaciosParqueo the espaciosParqueo to set
	 */
	protected void setEspaciosParqueo(List<EspacioParqueo> espaciosParqueo) {
		this.espaciosParqueo = espaciosParqueo;
	}

}
