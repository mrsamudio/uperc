package co.edu.ucundinamarca.upercth.model.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

/**
 * Guarda los dispositivos que capturan o muestran la informaci??n del ingreso o
 * egreso de los usuarios con sus veh??culos. - El campo ID_SESSION registra un
 * n??mero que identifica la sesi??n en que el sistema externo realizar?? consultas
 * a la base de datos - El campo FECHA_SESSION registra el momento en el que el
 * n??mero de sesi??n fu?? creado. - El campo TIPO registra el tipo de
 * recurso(dispositivo), puede ser un televisor o monitor o c??mara. - El campo
 * IP registra la direcci??n asignada dentro de la red. - El campo PUERTO
 * registra el puerto por el que el dispositivo se est?? comunicando. - El campo
 * MAC registra la mac ??nica del dispositivo. - El campo PROTOCOLO registra el
 * protocolo por el cu??l el recurso se comunica (TCP - UDP A FUTURO PUEDEN SER
 * M??S PROTOCOLOS). - El campo FECHA_REGISTRO registra el momento de tiempo en
 * el que se agreg?? el recurso al sistema. - El campo URL_FABRICANTE registra la
 * direcci??n web del fabricante del recurso. - El campo ESTADO registra si el
 * recurso se encuentra activo o inactivo.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:27
 */
@Entity
@Table(name = "recurso")
public class Recurso implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4234899276192731773L;

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
	@ColumnTransformer(read="CAST(ip AS varchar)", write="CAST(? AS inet)")
	private String ip;
//	private InetAddress ip;

	@Column(name = "puerto")
	private int puerto;

	@Column(name = "mac")
	@ColumnTransformer(read = "CAST(mac AS varchar)", write = "CAST(? AS macaddr)")
	private String mac;

	@Column(name = "protocolo")
	private String protocolo;

	@Column(name = "fecharegistro")
	private Timestamp fechaRegistro;

	@Column(name = "urlfabricante")
	private String urlFabricante;

	@Column(name = "estado")
	private boolean estado;

	@OneToMany(mappedBy = "recursoIngreso")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recurso", orphanRemoval = false)
	private Set<RegistroIE> registrosI;

	@OneToMany(mappedBy = "recursoEgreso")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recurso", orphanRemoval = false)
	private Set<RegistroIE> registrosE;

	/**
	 * Constructor por defecto
	 */
	public Recurso() {

	}

	/**
	 * Constructor para inserci??n de registro en bd
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
	 * @param registrosIE
	 */
//	public Recurso(String nombre, String marca, String tipo, InetAddress ip, int puerto, String mac, String protocolo,
	public Recurso(String nombre, String marca, String tipo, String ip, int puerto, String mac, String protocolo,
			Timestamp fechaRegistro, String urlFabricante, boolean estado) {

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
	}

	/**
	 * Constructor para update de registro en bd
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
	 * @param registrosIE
	 */
//	public Recurso(long id, String nombre, String marca, String tipo, InetAddress ip, int puerto, String mac,
	public Recurso(long id, String nombre, String marca, String tipo, String ip, int puerto, String mac,
			String protocolo, Timestamp fechaRegistro, String urlFabricante, boolean estado) {

		setId(id);
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
	}

	/**
	 * Constructor que inializa todos los atributos y listas
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
//	public Recurso(String nombre, String marca, String tipo, InetAddress ip, int puerto, String mac, String protocolo,
	public Recurso(String nombre, String marca, String tipo, String ip, int puerto, String mac, String protocolo,
			Timestamp fechaRegistro, String urlFabricante, boolean estado, Set<RegistroIE> registrosI,
			Set<RegistroIE> registrosE) {

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
		setRegistrosI(registrosI);
		setRegistrosE(registrosE);
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
//	public InetAddress getIp() {
	public String getIp() {
		return this.ip;
	}

	/**
	 * 
	 * @param ip
	 */
//	protected void setIp(InetAddress ip) {
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
	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	/**
	 * 
	 * @param fechaRegistro
	 */
	public void setFechaRegistro(Timestamp fechaRegistro) {
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
	 * @return the registrosI
	 */
	public Set<RegistroIE> getRegistrosI() {
		return registrosI;
	}

	/**
	 * @param registrosIE the registrosI to set
	 */
	public void setRegistrosI(Set<RegistroIE> registrosI) {
		this.registrosI = registrosI;
	}

	/**
	 * @return the registrosE
	 */
	public Set<RegistroIE> getRegistrosE() {
		return registrosE;
	}

	/**
	 * @param registrosIE the registrosE to set
	 */
	public void setRegistrosE(Set<RegistroIE> registrosE) {
		this.registrosE = registrosE;
	}

	/**
	 * Listas de registros de ingreso
	 * 
	 * @param registrosIE
	 */
	public void agregarRegitrosI(RegistroIE registrosI) {
		this.registrosI.add(registrosI);
		registrosI.setRecursoIngreso(this);
	}

	/**
	 * Listas de registros de egreso
	 * 
	 * @param registrosIE
	 */
	public void agregarRegitrosE(RegistroIE registrosE) {
		this.registrosE.add(registrosE);
		registrosE.setRecursoEgreso(this);
	}

}// end Recurso