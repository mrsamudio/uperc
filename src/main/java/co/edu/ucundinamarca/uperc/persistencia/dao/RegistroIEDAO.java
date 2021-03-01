/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.time.Month;
import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.RegistroIE;

/**
 * @author ingsamudio
 *
 */
public interface RegistroIEDAO {

	/**
	 * 
	 * @param id
	 * @return
	 */
	public RegistroIE selectById(long id);

	/**
	 * Selección de registro por fecha de ingreso o fecha de salida
	 * 
	 * @param fecha
	 * @param tipo true si es fecha de ingreso, false si es fecha de salida
	 * @return lista de registros que coinciden con el criterio de busqueda
	 */
	public List<RegistroIE> selectByDate(Date fecha, boolean tipo);

	/**
	 * 
	 * @param mes
	 * @return
	 */
	public List<RegistroIE> selectByMonth(Month mes);

	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 */
	public List<RegistroIE> selectByRange(Date fechaInicial, Date fechaFinal);
	
	/**
	 * 
	 * @return
	 */
	public List<RegistroIE> selectAll();
	
	/**
	 * 
	 * @param registroI
	 * @return
	 */
	public boolean insertI(RegistroIE registroI);
	

	/**
	 * 
	 * @param registroI
	 * @return
	 */
	public boolean updateIE(RegistroIE registroI);
	
}
