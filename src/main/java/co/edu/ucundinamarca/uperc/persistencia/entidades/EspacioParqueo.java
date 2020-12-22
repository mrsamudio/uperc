/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;

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
 * @author mrsamudio
 *
 */
@Entity
@Table(name = "ESPACIOPARQUEO")
public class EspacioParqueo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "")
	private long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "UBICACION", referencedColumnName = "ID")
	private Ubicacion ubicacion;
	
	@Column(name = "OCUPADO")
	private boolean ocupado;
	
//	Listas
	@OneToMany(mappedBy = "ESPACIOPARQUEO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Reserva> reservas;
	
	/**
	 * 
	 */
	public EspacioParqueo() {
		
	}

	/**
	 * @param nombre
	 * @param ubicacion
	 * @param ocupado
	 * @param reservas
	 */
	public EspacioParqueo(String nombre, Ubicacion ubicacion, boolean ocupado, List<Reserva> reservas) {
//		super();
		
		setNombre(nombre);
		setUbicacion(ubicacion);
		setOcupado(ocupado);
		setReservas(reservas);
	}
	
	/**
	 * 
	 * @param reserva
	 */
	public void agregaReservas(Reserva reserva) {
		this.reservas.add(reserva);
		reserva.setEspacioParqueo(this);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	protected void setId(long id) {
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
	protected void setNombre(String nombre) {
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
	public List<Reserva> getReservas() {
		return reservas;
	}

	/**
	 * @param reservas the reservas to set
	 */
	protected void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	
	
	

}
