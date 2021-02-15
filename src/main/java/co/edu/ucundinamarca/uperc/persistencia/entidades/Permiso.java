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
@Table(name = "permiso")
public class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
	@Column(name = "usuario")
	private Usuario usuario;

	@OneToOne(mappedBy = "permiso")
	private RegistroIE registroIE;

	/**
	 * 
	 */
	public Permiso() {

	}

	/**
	 * @param usuario
	 * @param registroE
	 */
	public Permiso(Usuario usuario, RegistroIE registroIE) {
		setUsuario(usuario);
		setRegistroIE(registroIE);
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
	public RegistroIE getRegistroIE() {
		return registroIE;
	}

	/**
	 * @param registroE the registroE to set
	 */
	protected void setRegistroIE(RegistroIE registroIE) {
		this.registroIE = registroIE;
//		registroIE.setPermiso(this);
	}
	
	

}
