package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
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
 * Guarda la sesión del sistema externo y la fecha en la que se creó la sessión.
 * notes Guarda los dispositivos que capturan o muestran la información del
 * ingreso o egreso de los usuarios con sus vehículos. - El campo ID_SESSION
 * registra un número que identifica la sesión en que el sistema externo
 * realizará consultas a la base de datos - El campo FECHA_SESSION registra el
 * momento en el que el número de sesión fué creado.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "REG_SERVICIO")
public class RegServicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "ID_SESSION")
	private String idSession;

	@ManyToOne
	@JoinColumn(name = "SISTEMA_EXTERNO", referencedColumnName = "ID")
//	@Column(name = "SISTEMA_EXTERNO")
	private SistemaExterno sistemaExterno;

	@Column(name = "FECHA_SESSION")
	private Date fechaSession;

//	Listas 
	@OneToMany(mappedBy = "REG_SERVICIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<Informe> informes;

	/**
	 * Constructor por defecto
	 */
	public RegServicio() {

	}

	/**
	 * Constructor que carga todos los atributos
	 * 
	 * @param idSession
	 * @param sistemaExterno
	 * @param fechaSession
	 */
	public RegServicio(String idSession, SistemaExterno sistemaExterno, Date fechaSession, List<Informe> informes) {

		setIdSession(idSession);
		setSistemaExterno(sistemaExterno);
		setFechaSession(fechaSession);
		setInformes(informes);
	}

	/**
	 * Listas de informes generados por sistemas externos?
	 * 
	 * @param informe
	 */
	public void agregarInformes(Informe informe) {
		this.informes.add(informe);
		informe.setRegServicio(this);
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
	public String getIdSession() {
		return this.idSession;
	}

	/**
	 * 
	 * @param idSession
	 */
	protected void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	/**
	 * 
	 * @return
	 */
	public SistemaExterno getSistemaExterno() {
		return this.sistemaExterno;
	}

	/**
	 * 
	 * @param sistemaExterno
	 */
	protected void setSistemaExterno(SistemaExterno sistemaExterno) {
		this.sistemaExterno = sistemaExterno;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaSession() {
		return this.fechaSession;
	}

	/**
	 * 
	 * @param fechaSession
	 */
	protected void setFechaSession(Date fechaSession) {
		this.fechaSession = fechaSession;
	}

	/**
	 * @return the informes
	 */
	public List<Informe> getInformes() {
		return informes;
	}

	/**
	 * @param informes the informes to set
	 */
	protected void setInformes(List<Informe> informes) {
		this.informes = informes;
	}
	
	

}// end RegServicio