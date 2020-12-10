package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
public class RegServicio {

	private long id;
	private String idSession;
	private int sistemaExterno;
	private Date fechaSession;
	public SistemaExterno m_SistemaExterno;

	public void finalize() throws Throwable {

	}

	/**
	 * Constructor por defecto
	 */
	public RegServicio() {

	}

	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 * @param idSession
	 * @param sistemaExterno
	 * @param fechaSession
	 */
	public RegServicio(long id, String idSession, int sistemaExterno, Date fechaSession) {

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
	public String getIdSession() {
		return this.idSession;
	}

	/**
	 * 
	 * @param idSession
	 */
	public void setIdSession(String idSession) {
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
	public void setSistemaExterno(int sistemaExterno) {
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
	public void setFechaSession(Date fechaSession) {
		this.fechaSession = fechaSession;
	}

	/**
	 * 
	 * @param id
	 */
	public RegServicio selectById(long id) {
		return null;
	}

	/**
	 * 
	 * @param idSession
	 */
	public RegServicio selectByIdSession(String idSession) {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<RegServicio> selectAll() {
		return null;
	}

	/**
	 * 
	 * @param regServicio
	 */
	public boolean insert(RegServicio regServicio) {
		return false;
	}

	/**
	 * 
	 * @param regServicio
	 */
	public boolean update(RegServicio regServicio) {
		return false;
	}
}// end RegServicio