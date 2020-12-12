package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
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

	@Column(name = "SISTEMA_EXTERNO")
	private int sistemaExterno;

	@Column(name = "FECHA_SESSION")
	private Date fechaSession;

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
	public RegServicio(String idSession, int sistemaExterno, Date fechaSession) {

		setIdSession(idSession);
		setSistemaExterno(sistemaExterno);
		setFechaSession(fechaSession);
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
	public String getIdSession() {
		return this.idSession;
	}

	/**
	 * 
	 * @param idSession
	 */
	private void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	/**
	 * 
	 * @return
	 */
	public int getSistemaExterno() {
		return this.sistemaExterno;
	}

	/**
	 * 
	 * @param sistemaExterno
	 */
	private void setSistemaExterno(int sistemaExterno) {
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
	private void setFechaSession(Date fechaSession) {
		this.fechaSession = fechaSession;
	}

	
}// end RegServicio