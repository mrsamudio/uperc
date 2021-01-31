package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Permiso;

public interface PermisoDAO {
	
	/**
	 * 
	 * @param id
	 */
	public Permiso selectById(int id);

	/**
	 * 
	 * @return
	 */
	public List<Permiso> selectAll();

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean insert(Permiso perfilUsuario);

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean update(Permiso perfilUsuario);

}
