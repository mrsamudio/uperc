package co.edu.ucundinamarca.upercth.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnTransformer;

/**
 * Guarda la sesi??n del sistema externo y la fecha en la que se cre?? la sessi??n.
 * notes Guarda los dispositivos que capturan o muestran la informaci??n del
 * ingreso o egreso de los usuarios con sus veh??culos. - El campo ID_SESSION
 * registra un n??mero que identifica la sesi??n en que el sistema externo
 * realizar?? consultas a la base de datos - El campo FECHA_SESSION registra el
 * momento en el que el n??mero de sesi??n fu?? creado.
 * 
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
@Entity
@Table(name = "reg_servicio")
public class RegServicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "idsession")
	@ColumnTransformer(read="CAST(idsession AS varchar)", write="CAST(? AS uuid)")
	private String idSession;

	@ManyToOne
	@JoinColumn(name = "sistemaexterno", referencedColumnName = "ID")
//	@Column(name = "SISTEMA_EXTERNO")
	private SistemaExterno sistemaExterno;

	@Column(name = "fechasession")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSession;

//	Listas 
	@OneToMany()
//	@OneToMany(mappedBy = "REG_SERVICIO", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
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
	public RegServicio(String idSession, SistemaExterno sistemaExterno, Date fechaSession) {

		setIdSession(idSession);
		setSistemaExterno(sistemaExterno);
		setFechaSession(fechaSession);
	}

	/**
	 * Constructor para update
	 * 
	 * @param idSession
	 * @param sistemaExterno
	 * @param fechaSession
	 */
	public RegServicio(long id, String idSession, SistemaExterno sistemaExterno, Date fechaSession) {
		
		setId(id);
		setIdSession(idSession);
		setSistemaExterno(sistemaExterno);
		setFechaSession(fechaSession);
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