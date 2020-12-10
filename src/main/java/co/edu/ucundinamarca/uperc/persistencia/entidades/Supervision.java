package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

/**
 * @author Mario Roberto Samudio Martinez
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
public class Supervision {

	private long id;
	private String mensaje;
	private boolean estado;
	private Date fecha;
	/**
	 * <ul>
	 * 	<li>ALERTA - TRUE</li>
	 * 	<li>AVISOS - FLASE</li>
	 * </ul>
	 */
	private boolean tipo;
	private long usuario;
	public Usuario m_Usuario;



	/**
	 * 
	 */
	public void finalize() throws Throwable {

	}
	
	/**
	 * Constructor por defecto
	 */
	public Supervision(){

	}

	/**
	 * Constructor que inicializa todos loa atributos
	 * 
	 * @param id
	 * @param mensaje
	 * @param estado
	 * @param fecha
	 * @param tipo
	 * @param usuario
	 */
	public Supervision(long id, String mensaje, boolean estado, Date fecha, boolean tipo, long usuario){

	}

	/**
	 * 
	 * @return
	 */
	public long getId(){
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id){
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getMensaje(){
		return this.mensaje;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public void setMensaje(String mensaje){
		this.mensaje = mensaje;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEstado(){
		return this.estado;
	}

	/**
	 * 
	 * @param estado
	 */
	public void setEstado(boolean estado){
		this.estado = estado;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFecha(){
		return this.fecha;
	}

	/**
	 * 
	 * @param fecha
	 */
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTipo(){
		return this.tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(boolean tipo){
		this.tipo = tipo;
	}

	/**
	 * 
	 * @return
	 */
	public long getUsuario(){
		return this.usuario;
	}

	/**
	 * 
	 * @param usuario
	 */
	public void setUsuario(long usuario){
		this.usuario = usuario;
	}

	/**
	 * 
	 * @param id
	 */
	public Supervision selectById(long id){
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Supervision> selectAll(){
		return null;
	}

	/**
	 * 
	 * @param supervision
	 */
	public boolean insert(Supervision supervision){
		return false;
	}

	/**
	 * 
	 * @param supervision
	 */
	public boolean update(Supervision supervision){
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean activate(long id){
		return false;
	}

	/**
	 * 
	 * @param id
	 */
	public boolean deactivate(long id){
		return false;
	}
}//end Supervision