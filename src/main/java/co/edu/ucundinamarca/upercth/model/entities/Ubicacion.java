/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.postgresql.geometric.PGpoint;

import co.edu.ucundinamarca.upercth.util.PGPointType;


/**
 * @author mrsamudio
 *
 */
@TypeDef(name = "type", typeClass = PGPointType.class)
@Entity
@Table(name = "ubicacion")
public class Ubicacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "direccion")
	private String direccion;

	@Type(type = "type")
//	@Column(name = "coordenadas", columnDefinition="Point")
	private PGpoint coordenadas;

	@Column(name = "telefono")
	private String telefono;

	@OneToMany(mappedBy = "ubicacion")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ubicacion", orphanRemoval = true)//due??o de relacion
	private Set<EspacioParqueo> espaciosParqueo;

	/**
	 * 
	 */
	public Ubicacion() {

	}
	
	/**
	 * 
	 */
	public Ubicacion(int id) {
		setId(id);
	}
	
	/**
	 * Constructor carga atributos para update
	 * 
	 * @param id
	 * @param nombre
	 * @param direccion
	 * @param coordenadas
	 * @param telefono
	 */
	public Ubicacion(int id, String nombre, String direccion, PGpoint coordenadas, String telefono) {
		
		setId(id);
		setNombre(nombre);
		setDireccion(direccion);
		setCoordenadas(coordenadas);
		setTelefono(telefono);
		setEspaciosParqueo(espaciosParqueo);
	}

	/**
	 * Constructor carga todos los atributos de la clase
	 * 
	 * @param id
	 * @param nombre
	 * @param direccion
	 * @param coordenadas
	 * @param telefono
	 * @param espaciosParqueo
	 */
	public Ubicacion(int id, String nombre, String direccion, PGpoint coordenadas, String telefono, Set<EspacioParqueo> espaciosParqueo) {
		
		setId(id);
		setNombre(nombre);
		setDireccion(direccion);
		setCoordenadas(coordenadas);
		setTelefono(telefono);
		setEspaciosParqueo(espaciosParqueo);
	}
	
	/**
	 * @param nombre
	 * @param direccion
	 * @param coordenadas
	 * @param telefono
	 * @param espaciosParqueo
	 */
	public Ubicacion(String nombre, String direccion, PGpoint coordenadas, String telefono, Set<EspacioParqueo> espaciosParqueo) {

		setNombre(nombre);
		setDireccion(direccion);
		setCoordenadas(coordenadas);
		setTelefono(telefono);
		setEspaciosParqueo(espaciosParqueo);
	}
	
	/**
	 * Constructor que carga atributos para inserci??n a bd
	 * 
	 * @param nombre
	 * @param direccion
	 * @param coordenadas
	 * @param telefono
	 */
	public Ubicacion(String nombre, String direccion, PGpoint coordenadas, String telefono) {
		
		setNombre(nombre);
		setDireccion(direccion);
		setCoordenadas(coordenadas);
		setTelefono(telefono);
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
	public PGpoint getCoordenadas() {
		return coordenadas;
	}

	/**
	 * @param coordenadas the coordenadas to set
	 */
	public void setCoordenadas(PGpoint coordenadas) {
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
	 * @param reserva
	 */
	public void agregarEspaciosParqueo(EspacioParqueo espacioParqueo) {
		this.espaciosParqueo.add(espacioParqueo);
		espacioParqueo.setUbicacion(this);
	}

}
