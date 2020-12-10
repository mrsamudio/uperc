package co.edu.ucundinamarca.uperc.persistencia.entidades;

import java.util.Date;
import java.util.List;

/**
 * @author mrsamudio
 * @version 1.0
 * @created 05-nov.-2020 5:20:28
 */
public class Usuario {

	private long id;
	/**
	 * Los nombres del usuario
	 */
	private String nombre;
	/**
	 * Los apellidos del usuario
	 */
	private String apellido;
	/**
	 * Tipo de identificación. "C" cédula de ciudadanía, "I" Tarjeta de identidad, "E"
	 * cédula de extranjería
	 */
	private char tipoId;
	/**
	 * Número de identificación, no se requieren operaciones con esta variable
	 */
	private String numId;
	private String contrasena;
	private String correo;
	private Date fechaNac;
	private Date fechaReg;
	private boolean estado;
	private int perfil;
	private int rol;
	public Rol m_Rol;
	public PerfilUsuario m_PerfilUsuario;



	/**
	 * 
	 */
	public void finalize() throws Throwable {

	}
	
	/**
	 * Constructor por defecto
	 */
	public Usuario(){

	}

	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param tipoId
	 * @param numid
	 * @param contrasena
	 * @param correo
	 * @param fechaNac
	 * @param fechaReg
	 * @param estado
	 * @param perfil
	 * @param rol
	 */
	public Usuario(long id, String nombre, String apellido, char tipoId, String numid, String contrasena, String correo, Date fechaNac, Date fechaReg, boolean estado, int perfil, int rol){

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
	 * Los nombres del usuario
	 */
	public String getNombre(){
		return this.nombre;
	}

	/**
	 * Los nombres del usuario
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	/**
	 * Los apellidos del usuario
	 */
	public String getApellido(){
		return this.apellido;
	}

	/**
	 * Los apellidos del usuario
	 * 
	 * @param apellido
	 */
	public void setApellido(String apellido){
		this.apellido = apellido;
	}

	/**
	 * Tipo de identificación. "C" cédula de ciudadanía, "I" Tarjeta de identidad, "E"
	 * cédula de extranjería
	 */
	public char getTipoId(){
		return this.tipoId;
	}

	/**
	 * Tipo de identificación. "C" cédula de ciudadanía, "I" Tarjeta de identidad, "E"
	 * cédula de extranjería
	 * 
	 * @param tipoId
	 */
	public void setTipoId(char tipoId){
		this.tipoId = tipoId;
	}

	/**
	 * Número de identificación, no se requieren operaciones con esta variable
	 */
	public String getNumId(){
		return this.numId;
	}

	/**
	 * Número de identificación, no se requieren operaciones con esta variable
	 * 
	 * @param numId
	 */
	public void setNumId(String numId){
		this.numId = numId;
	}

	public String getContrasena(){
		return this.contrasena;
	}

	/**
	 * 
	 * @param contrasena
	 */
	public void setContrasena(String contrasena){
		this.contrasena = contrasena;
	}

	/**
	 * 
	 * @return
	 */
	public String getCorreo(){
		return this.correo;
	}

	/**
	 * 
	 * @param correo
	 */
	public void setCorreo(String correo){
		this.correo = correo;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaNac(){
		return this.fechaNac;
	}

	/**
	 * 
	 * @param fechaNac
	 */
	public void setFechaNac(Date fechaNac){
		this.fechaNac = fechaNac;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFechaReg(){
		return this.fechaReg;
	}

	/**
	 * 
	 * @param fechaReg
	 */
	public void setFechaReg(Date fechaReg){
		this.fechaReg = fechaReg;
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
	public int getPerfil(){
		return this.perfil;
	}

	/**
	 * 
	 * @param perfil
	 */
	public void setPerfil(int perfil){
		this.perfil = perfil;
	}

	/**
	 * 
	 * @return
	 */
	public int getRol(){
		return this.rol;
	}

	/**
	 * 
	 * @param rol
	 */
	public void setRol(int rol){
		this.rol = rol;
	}

	/**
	 * 
	 * @param id
	 */
	public Usuario selectById(long id){
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public List<Usuario> selectAll(){
		return null;
	}

	/**
	 * 
	 * @param usuario
	 */
	public boolean insert(Usuario usuario){
		return false;
	}

	/**
	 * 
	 * @param usuario
	 */
	public boolean update(Usuario usuario){
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
}//end Usuario