package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.sql.Timestamp;
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
@Table(name = "recurso")
public class Recurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "marca")
	private String marca;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "ip")
	private String ip;

	@Column(name = "puerto")
	private int puerto;

	@Column(name = "mac")
	private String mac;

	@Column(name = "protocolo")
	private String protocolo;

	@Column(name = "fecharegistro")
	private Timestamp fechaRegistro;

	@Column(name = "urlfabricante")
	private String urlFabricante;

	@Column(name = "estado")
	private boolean estado;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recurso", orphanRemoval = false)
	private Set<RegistroIE> registrosIE;

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
	 * @param registrosIE
	 */
	public Recurso(String nombre, String marca, String tipo, String ip, int puerto, String mac, String protocolo,
			Timestamp fechaRegistro, String urlFabricante, boolean estado, Set<RegistroIE> registrosIE) {

		setNombre(nombre);
		setMarca(marca);
		setTipo(tipo);
		setIp(ip);
		setPuerto(puerto);
		setMac(mac);
		setProtocolo(protocolo);
		setFechaRegistro(fechaRegistro);
		setUrlFabricante(urlFabricante);
		setEstado(estado);
		setRegistrosIE(registrosIE);
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
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	protected void setNombre(String nombre) {
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
	protected void setMarca(String marca) {
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
	protected void setTipo(String tipo) {
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
	protected void setIp(String ip) {
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
	protected void setPuerto(int puerto) {
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
	protected void setMac(String mac) {
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
	protected void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	/**
	 * 
	 * @param fechaRegistro
	 */
	protected void setFechaRegistro(Timestamp fechaRegistro) {
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
	protected void setUrlFabricante(String urlFabricante) {
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
	protected void setEstado(boolean estado) {
		this.estado = estado;
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

	/**
	 * Listas de registros de ingreso y egreso desde vehículos
	 * 
	 * @param registrosIE
	 */
	public void agregarRegitrosIE(RegistroIE registrosIE) {
		this.registrosIE.add(registrosIE);
		registrosIE.setRecurso(this);
	}

}// end Recurso