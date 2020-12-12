package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Guarda los dispositivos que capturan o muestran la información del ingreso o
 * egreso de los usuarios con sus vehículos. - El campo ID_SESSION registra un
 * número que identifica la sesión en que el sistema externo realizará consultas
 * a la base de datos - El campo FECHA_SESSION registra el momento en el que el
 * número de sesión fué creado. - El campo TIPO registra el tipo de
 * recurso(dispositivo), puede ser un televisor o monitor o cámara. - El campo
 * IP registra la dirección asignada dentro de la red. - El campo PUERTO
 * registra el puerto por el que el dispositivo se está comunicando. - El campo
 * MAC registra la mac única del dispositivo. - El campo PROTOCOLO registra el
 * protocolo por el cuál el recurso se comunica (TCP - UDP A FUTURO PUEDEN SER
 * MÁS PROTOCOLOS). - El campo FECHA_REGISTRO registra el momento de tiempo en
 * el que se agregó el recurso al sistema. - El campo URL_FABRICANTE registra la
 * dirección web del fabricante del recurso. - El campo ESTADO registra si el
 * recurso se encuentra activo o inactivo.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
@Entity
@Table(name = "RECURSO")
public class Recurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "MARCA")
	private String marca;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "IP")
	private String ip;

	@Column(name = "PUERTO")
	private int puerto;

	@Column(name = "MAC")
	private String mac;

	@Column(name = "PROTOCOLO")
	private String protocolo;

	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;

	@Column(name = "URL_FABRICANTE")
	private String urlFabricante;

	@Column(name = "ESTADO")
	private boolean estado;

	/**
	 * Constructor por defecto
	 */
	public Recurso() {

	}

	/**
	 * Constructor por defecto
	 * 
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
	public Recurso(String nombre, String marca, String tipo, String ip, int puerto, String mac,
			String protocolo, Date fechaRegistro, String urlFabricante, boolean estado) {

		setNombre(nombre);
		setMarca(marca);
		setTipo(tipo);
		setIp(ip);
		setPuerto(puerto);
		setMac(mac);
		setProtocolo(protocolo);
		setfechaRegistro(fechaRegistro);
		setUrlFabricante(urlFabricante);
		setEstado(estado);
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
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	private void setNombre(String nombre) {
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
	private void setMarca(String marca) {
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
	private void setTipo(String tipo) {
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
	private void setIp(String ip) {
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
	private void setPuerto(int puerto) {
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
	private void setMac(String mac) {
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
	private void setProtocolo(String protocolo) {
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
	private void setfechaRegistro(Date fechaRegistro) {
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
	private void setUrlFabricante(String urlFabricante) {
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
	private void setEstado(boolean estado) {
		this.estado = estado;
	}

}// end Recurso