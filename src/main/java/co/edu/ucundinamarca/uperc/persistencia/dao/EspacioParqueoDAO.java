package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.EspacioParqueo;

public interface EspacioParqueoDAO {
	
	/**
	 * 
	 * @param id
	 */
	public EspacioParqueo selectById(int id);

	/**
	 * 
	 * @return
	 */
	public List<EspacioParqueo> selectAll();

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean insert(EspacioParqueo espacioParqueo);

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean update(EspacioParqueo espacioParqueo);

}
