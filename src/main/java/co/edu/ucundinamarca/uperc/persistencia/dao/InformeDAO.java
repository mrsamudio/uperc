/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Informe;
import co.edu.ucundinamarca.uperc.persistencia.entidades.RegServicio;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

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
	 * Insertar un informe generado por soicitud del {@link Usuario}
	 * 
	 * @param informe
	 */
	public boolean insertU(Informe informe);

	/**
	 * Insertar un informe generado por solicitud del registro de servicio
	 * {@link RegServicio}
	 * 
	 * @param informe
	 */
	public boolean insertR(Informe informe);

	/**
	 * Actualizar un informe generado por soicitud del {@link Usuario}
	 * 
	 * @param informe
	 */
	public boolean updateU(Informe informe);
	
	/**
	 * Actulizar un informe generado por solicitud del registro de servicio
	 * {@link RegServicio}
	 * @param informe
	 */
	public boolean updateR(Informe informe);

	/**
	 * 
	 * @param informe
	 */
	public boolean delete(Informe informe);

}
