/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.SistemaExterno;

/**
 * @author ingsamudio
 *
 */
public interface SistemaExternoDAO {
	
	/**
	 * 
	 * @param id
	 */
	public SistemaExterno selectById(int id);
	
	/**
	 * 
	 * @return
	 */
	public List<SistemaExterno> selectAll();

	/**
	 * 
	 * @param sistemaExterno
	 */
	public boolean insert(SistemaExterno sistemaExterno);

	/**
	 * 
	 * @param sistemaExterno
	 */
	public boolean update(SistemaExterno sistemaExterno);

}
