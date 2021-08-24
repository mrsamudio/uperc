package co.edu.ucundinamarca.upercth.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * Guarda los datos de los veh??culos que han ingresado al parqueadero.
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
	
	@OneToMany()//due??o de la relacion
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vehiculo", orphanRemoval = false)//due??o de la relacion
	private Set<RegistroIE> registrosIE;

	/**
	 * Constructor por defecto
	 */
	public Vehiculo() {

	}
	
	/**
	 * Constructor por defecto
	 * @param id
	 */
	public Vehiculo(long id) {
		setId(id);
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
	 * Constructor que inicializa los atributos para inserción a base de datos
	 * 
	 * @param placa
	 * @param marca
	 * @param color
	 * @param modelo
	 * @param clase
	 * @param tipoServicio
	 */
	public Vehiculo(String placa, String marca, String color, String modelo, String clase,
			String tipoServicio) {
		
		setPlaca(placa);
		setMarca(marca);
		setColor(color);
		setModelo(modelo);
		setClase(clase);
		setTipoServicio(tipoServicio);
	}
	
	/**
	 * Constructor que inicializa los atributos para actualizar registro en base de datos
	 * 
	 * @param id
	 * @param placa
	 * @param marca
	 * @param color
	 * @param modelo
	 * @param clase
	 * @param tipoServicio
	 */
	public Vehiculo(long id, String placa, String marca, String color, String modelo, String clase,
			String tipoServicio) {
		
		setId(id);
		setPlaca(placa);
		setMarca(marca);
		setColor(color);
		setModelo(modelo);
		setClase(clase);
		setTipoServicio(tipoServicio);
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
	public void setId(long id) {
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
	public void setPlaca(String placa) {
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
	public void setMarca(String marca) {
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
	public void setColor(String color) {
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
	public void setModelo(String modelo) {
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
	public void setClase(String clase) {
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
	public void setTipoServicio(String tipoServicio) {
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
	public void setRegistrosIE(Set<RegistroIE> registrosIE) {
		this.registrosIE = registrosIE;
	}

	/**
	 * Listas de registros de ingreso y egreso desde veh??culos
	 * @param registrosIE
	 */
	public void agregarRegitrosIE(RegistroIE registrosIE) {
		this.registrosIE.add(registrosIE);
		registrosIE.setVehiculo(this);
	}
	
	

	
}// end Vehiculo