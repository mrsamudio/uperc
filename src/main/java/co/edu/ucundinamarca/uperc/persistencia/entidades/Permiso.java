/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.edu.ucundinamarca.uperc.persistencia.dao.RegistroIEDAO;

/**
 * @author mrsamudio
 *
 */
@Entity
@Table(name = "PERMISO")
public class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "USUARIO", referencedColumnName = "ID")
	private Usuario usuario;

	@OneToOne(mappedBy = "PERMISO")
	private RegistroIE registroE;

	/**
	 * 
	 */
	public Permiso() {

	}

	/**
	 * @param usuario
	 * @param registroE
	 */
	public Permiso(Usuario usuario, RegistroIE registroE) {
		setUsuario(usuario);
		setRegistroE(registroE);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	protected void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	protected void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the registroE
	 */
	public RegistroIE getRegistroE() {
		return registroE;
	}

	/**
	 * @param registroE the registroE to set
	 */
	protected void setRegistroE(RegistroIE registroE) {
		this.registroE = registroE;
	}
	
	

}
