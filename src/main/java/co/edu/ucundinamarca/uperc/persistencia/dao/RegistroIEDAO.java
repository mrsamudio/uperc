/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.time.Month;
import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Informe;
import co.edu.ucundinamarca.uperc.persistencia.entidades.RegistroIE;

/**
 * @author ingsamudio
 *
 */
public interface RegistroIEDAO {

	/**
	 * 
	 * @param id
	 */
	public RegistroIE selectById(long id);

	/**
	 * 
	 * @param fecha
	 */
	public List<RegistroIE> selectByDate(Date fecha);

	/**
	 * 
	 * @param mes
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
