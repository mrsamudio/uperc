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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad de espacios de parqueo
 * 
 * @author mrsamudio
 *
 */
@Entity
@Table(name = "espacioparqueo")
public class EspacioParqueo implements Serializable {

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

	@ManyToOne
//	@Column(name = "ubicacion")
	@JoinColumn(name = "ubicacion")
//	@JoinColumn(name = "ubicacion", referencedColumnName = "id")
	
	private Ubicacion ubicacion;
//	private Ubicacion ubicacion = new Ubicacion();

	@Column(name = "ocupado")
	private boolean ocupado;

	@OneToMany
//	@OneToMany(mappedBy = "espacioParqueo")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private Set<Reserva> reservas;

	/**
	 * 
	 */
	public EspacioParqueo() {

	}

	/**
	 * 
	 * @param id
	 */
	public EspacioParqueo(int id) {
		setId(id);
	}

	/**
	 * @param nombre
	 * @param ubicacion
	 * @param ocupado
	 * @param reservas
	 */
	public EspacioParqueo(String nombre, Ubicacion ubicacion, boolean ocupado, Set<Reserva> reservas) {

		setNombre(nombre);
		setUbicacion(ubicacion);
		setOcupado(ocupado);
		setReservas(reservas);
	}

	/**
	 * Constructor para uso de inserciones
	 * 
	 * @param nombre
	 * @param ubicacion
	 * @param ocupado
	 */
	public EspacioParqueo(String nombre, Ubicacion ubicacion, boolean ocupado) {

		setNombre(nombre);
		setUbicacion(ubicacion);
		setOcupado(ocupado);
	}

	/**
	 * Carga de todos los atributos de la entidad mapeada (con excepci??n del
	 * atributo set de reservas)
	 * 
	 * @param id
	 * @param nombre
	 * @param ubicacion
	 * @param ocupado
	 */
	public EspacioParqueo(int id, String nombre, Ubicacion ubicacion, boolean ocupado) {

		setId(id);
		setNombre(nombre);
		setUbicacion(ubicacion);
		setOcupado(ocupado);
	}

	/**
	 * Carga de todos los atributos y el set de reservas
	 * 
	 * @param id
	 * @param nombre
	 * @param ubicacion
	 * @param ocupado
	 * @param reservas
	 */
	public EspacioParqueo(int id, String nombre, Ubicacion ubicacion, boolean ocupado, Set<Reserva> reservas) {

		setId(id);
		setNombre(nombre);
		setUbicacion(ubicacion);
		setOcupado(ocupado);
		setReservas(reservas);
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
	 * @return the ubicacion
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	protected void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the ocupado
	 */
	public boolean isOcupado() {
		return ocupado;
	}

	/**
	 * @param ocupado the ocupado to set
	 */
	protected void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	/**
	 * @return the reservas
	 */
	public Set<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	protected void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	/**
	 * 
	 * @param reserva
	 */
	public void agregarReserva(Reserva reserva) {
		this.reservas.add(reserva);
		reserva.setEspacioParqueo(this);
	}
}
