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
	 * Seleccionar un registro por id
	 * @param id
	 * @return un solo registroIE
	 */
	public RegistroIE selectById(long id);

	/**
	 * Selección de registros por fecha de ingreso o fecha de salida
	 * 
	 * @param fecha
	 * @param tipo true si es fecha de ingreso, false si es fecha egreso (de salida)
	 * @return lista de registros que coinciden con el criterio de búsqueda <= fecha ingresada
	 */
	public List<RegistroIE> selectByDate(Date fecha, boolean tipo);

	/**
	 * Selección de registros por mes
	 * @param mes
	 * @param tipo true si es fecha de ingreso, false si es fecha egreso (de salida)
	 * @return Lista de registros
	 */
	public List<RegistroIE> selectByMonth(Month mes, boolean tipo);

	/**
	 * Selección de registros por un rango de fechas
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param tipo true si es fecha de ingreso, false si es fecha egreso (de salida)
	 * @return Lista de registros
	 */
	public List<RegistroIE> selectByRange(Date fechaInicial, Date fechaFinal, boolean tipo);
	
	/**
	 * Selección de todos los registrosIE
	 * @return Lista de registros
	 */
	public List<RegistroIE> selectAll();
	
	/**
	 * Insertar un registro de ingreso
	 * @param registroI
	 * @return true or false
	 */
	public boolean insertI(RegistroIE registroI);
	

	/**
	 * Actualizar un registroIE 
	 * @param registroI
	 * @return true or false
	 */
	public boolean updateIE(RegistroIE registroI);
	
}