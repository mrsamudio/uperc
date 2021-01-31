package co.edu.ucundinamarca.uperc.persistencia.entidades;

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
 * 
 * Guarda los datos de los vehículos que han ingresado al parqueadero.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:29
 */
@Entity
@Table(name = "vehiculo")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "placa")
	private String placa;

	@Column(name = "marca")
	private String marca;

	@Column(name = "color")
	private String color;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "clase")
	private String clase;

	@Column(name = "tiposervicio")
	private String tipoServicio;
	
//	Listas
//	@OneToMany(mappedBy = "VEHICULO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private Set<RegistroIE> registrosIE;

	/**
	 * Constructor por defecto
	 */
	public Vehiculo() {

	}

	/**
	 * Constructor que inicializa todos los atributos
	 * 
	 * @param placa
	 * @param marca
	 * @param color
	 * @param modelo
	 * @param clase
	 * @param tipoServicio
	 * @param registrosIE
	 */
	public Vehiculo(String placa, String marca, String color, String modelo, String clase,
			String tipoServicio, Set<RegistroIE> registrosIE) {

		setPlaca(placa);
		setMarca(marca);
		setColor(color);
		setModelo(modelo);
		setClase(clase);
		setTipoServicio(tipoServicio);
		setRegistrosIE(registrosIE);
	}
	
	/**
	 * Listas de registros de ingreso y egreso desde vehículos
	 * @param registrosIE
	 */
	public void agregarRegitrosIE(RegistroIE registrosIE) {
		this.registrosIE.add(registrosIE);
		registrosIE.setVehiculo(this);
	}

	/**
	 * 
	 * @return
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	protected void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getPlaca() {
		return this.placa;
	}

	/**
	 * 
	 * @param placa
	 */
	protected void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * 
	 * @return
	 */
	public String getMarca() {
		return this.marca;
	}

	/**
	 * 
	 * @param marca
	 */
	protected void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * 
	 * @return
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * 
	 * @param color
	 */
	protected void setColor(String color) {
		this.color = color;
	}

	/**
	 * 
	 * @return
	 */
	public String getModelo() {
		return this.modelo;
	}

	/**
	 * 
	 * @param modelo
	 */
	protected void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * 
	 * @return
	 */
	public String getClase() {
		return this.clase;
	}

	/**
	 * 
	 * @param clase
	 */
	protected void setClase(String clase) {
		this.clase = clase;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipoServicio() {
		return this.tipoServicio;
	}

	/**
	 * 
	 * @param tipoServicio
	 */
	protected void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	/**
	 * @return the registrosIE
	 */
	public Set<RegistroIE> getRegistrosIE() {
		return registrosIE;
	}

	/**
	 * @param registrosIE the registrosIE to set
	 */
	protected void setRegistrosIE(Set<RegistroIE> registrosIE) {
		this.registrosIE = registrosIE;
	}
	
	

	
}// end Vehiculo