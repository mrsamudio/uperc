/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Informe;

/**
 * 
 * @author ingsamudio
 *
 */
public interface InformeDAO {

	/**
	 * 
	 * @param id
	 */
	public Informe selectById(long id);

	/**
	 * 
	 * @return
	 */
	public List<Informe> selectAll();

	/**
	 * 
	 * @param informe
	 */
	public boolean insert(Informe informe);

	/**
	 * 
	 * @param informe
	 */
	public boolean update(Informe informe);

	/**
	 * 
	 * @param informe
	 */
	public boolean delete(Informe informe);

}
