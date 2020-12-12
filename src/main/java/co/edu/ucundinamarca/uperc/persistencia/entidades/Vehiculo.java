package co.edu.ucundinamarca.uperc.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "VEHICULO")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "PLACA")
	private String placa;

	@Column(name = "MARCA")
	private String marca;

	@Column(name = "COLOR")
	private String color;

	@Column(name = "MODELO")
	private String modelo;

	@Column(name = "CLASE")
	private String clase;

	@Column(name = "TIPO_SERVICIO")
	private String tipoServicio;

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
	private void setId(long id) {
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
	private void setPlaca(String placa) {
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
	private void setMarca(String marca) {
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
	private void setColor(String color) {
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
	private void setModelo(String modelo) {
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
	private void setClase(String clase) {
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
	private void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	
}// end Vehiculo