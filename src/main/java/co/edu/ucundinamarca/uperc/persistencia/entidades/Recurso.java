package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
public class Recurso {

	private long id;
	private String nombre;
	private String marca;
	private String tipo;
	private String ip;
	private int puerto;
	private String mac;
	private String protocolo;
	private Date fechaRegistro;
	private String urlFabricante;
	private boolean estado;

	/**
	 * 
	 */
	public void finalize() throws Throwable {

	}

	/**
	 * Constructor por defecto
	 */
	public Recurso() {

	}

	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 * @param nombre
	 * @param marca
	 * @param tipo
	 * @param ip
	 * @param puerto
	 * @param mac
	 * @param protocolo
	 * @param fechaRegistro
	 * @param urlFabricante
	 * @param estado
	 */
	public Recurso(long id, String nombre, String marca, String tipo, String ip, String puerto, String mac,
			String protocolo, Date fechaRegistro, String urlFabricante, boolean estado) {

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
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 * @return
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @return
	 */
	public int getPuerto() {
		return this.puerto;
	}

	/**
	 * 
	 * @param puerto
	 */
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	/**
	 * 
	 * @return
	 */
	public String getMac() {
		return this.mac;
	}

	/**
	 * 
	 * @param mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * 
	 */
	public String getProtocolo() {
		return this.protocolo;
	}

	/**
	 * 
	 * @param protocolo
	 */
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	/**
	 * 
	 * @param fechaRegistro
	 */
	public void setfechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * 
	 * @return
	 */
	public String getUrlFabricante() {
		return this.urlFabricante;
	}

	/**
	 * 
	 * @param urlFabricante
	 */
	public void setUrlFabricante(String urlFabricante) {
		this.urlFabricante = urlFabricante;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEstado() {
		return this.estado;
	}

	/**
	 * 
	 * @param estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	/**
	 * 
	 * @param id
	 */
	public Recurso selectById(long id) {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Recurso> selectAll() {
		return null;
	}

	/**
	 * 
	 * @param recurso
	 */
	public boolean insert(Recurso recurso) {
		return false;
	}

	/**
	 * 
	 * @param recurso
	 */
	public boolean update(Recurso recurso) {
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean activate(long id) {
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean deactivate(long id) {
		return false;
	}
}// end Recurso