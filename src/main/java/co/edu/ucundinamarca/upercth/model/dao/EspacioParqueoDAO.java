package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.EspacioParqueo;

public interface EspacioParqueoDAO {
	
	/**
	 * 
	 * @param id
	 */
	public EspacioParqueo selectById(int id);
	
	/**
	 * Consulta los espacios de parqueo por estado
	 * 
	 * @param estado libre(false), ocupado(true)
	 * @return
	 */
	public List<EspacioParqueo> selectByOcupado(Boolean estado);

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
	
	/**
	 * Marca un espacio de parqueo como ocupado
	 * 
	 * @param id
	 * @return
	 */
	public boolean activate(Integer id);
	
	
	
	/**
	 * Desmarca un espacio de parqueo como ocupado
	 * 
	 * @param id
	 * @return
	 */
	public boolean deActivate(Integer id);

}
