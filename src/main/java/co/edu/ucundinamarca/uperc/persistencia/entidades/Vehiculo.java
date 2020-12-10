package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.List;

/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:29
 */
public class Vehiculo {

	private long id;
	private String placa;
	private String marca;
	private String color;
	private String modelo;
	private String clase;
	private String tipoServicio;



	/**
	 * 
	 */
	public void finalize() throws Throwable {

	}
	
	/**
	 * Constructor por defecto
	 */
	public Vehiculo(){

	}

	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 * @param placa
	 * @param marca
	 * @param color
	 * @param modelo
	 * @param clase
	 * @param tipoServicio
	 */
	public Vehiculo(long id, String placa, String marca, String color, String modelo, String clase, String tipoServicio){

	}

	/**
	 * 
	 * @return
	 */
	public long getId(){
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id){
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getPlaca(){
		return this.placa;
	}

	/**
	 * 
	 * @param placa
	 */
	public void setPlaca(String placa){
		this.placa = placa;
	}

	/**
	 * 
	 * @return
	 */
	public String getMarca(){
		return this.marca;
	}

	/**
	 * 
	 * @param marca
	 */
	public void setMarca(String marca){
		this.marca = marca;
	}

	/**
	 * 
	 * @return
	 */
	public String getColor(){
		return this.color;
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(String color){
		this.color = color;
	}

	/**
	 * 
	 * @return
	 */
	public String getModelo(){
		return this.modelo;
	}

	/**
	 * 
	 * @param modelo
	 */
	public void setModelo(String modelo){
		this.modelo = modelo;
	}

	/**
	 * 
	 * @return
	 */
	public String getClase(){
		return this.clase;
	}

	/**
	 * 
	 * @param clase
	 */
	public void setClase(String clase){
		this.clase = clase;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipoServicio(){
		return this.tipoServicio;
	}

	/**
	 * 
	 * @param tipoServicio
	 */
	public void settipoServicio(String tipoServicio){
		this.tipoServicio = tipoServicio;
	}

	/**
	 * 
	 * @param id
	 */
	public Vehiculo selectById(long id){
		return null;
	}

	/**
	 * 
	 * @param placa
	 */
	public Vehiculo selectByPlaca(String placa){
		return null;
	}

	/**
	 * 
	 * @param marca
	 */
	public List<Vehiculo> selectByMarca(String marca){
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Vehiculo> selectAll(){
		return null;
	}

	/**
	 * 
	 * @param vehiculo
	 */
	public boolean insert(Vehiculo vehiculo){
		return false;
	}

	/**
	 * 
	 * @param vehiculo
	 */
	public boolean update(Vehiculo vehiculo){
		return false;
	}
}//end Vehiculo