/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.SistemaExterno;

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
