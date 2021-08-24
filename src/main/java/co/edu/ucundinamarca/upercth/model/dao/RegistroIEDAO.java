/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.time.Month;
import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.RegistroIE;
import co.edu.ucundinamarca.upercth.model.entities.Vehiculo;

/**
 * @author ingsamudio
 *
 */
public interface RegistroIEDAO {

	/**
	 * Seleccionar un registro por id
	 * 
	 * @param id
	 * @return un solo registroIE
	 */
	public RegistroIE selectById(long id);

	/**
	 * Selecci??n de registros por fecha de ingreso o fecha de salida
	 * 
	 * @param fecha
	 * @param tipo  true si es fecha de ingreso, false si es fecha egreso (de
	 *              salida)
	 * @return lista de registros que coinciden con el criterio de b??squeda <=
	 *         fecha ingresada
	 */
	public List<RegistroIE> selectByDate(Date fecha, boolean tipo);

	/**
	 * Selecci??n de registros por mes
	 * 
	 * @param mes
	 * @param tipo true si es fecha de ingreso, false si es fecha egreso (de salida)
	 * @return Lista de registros
	 */
	public List<RegistroIE> selectByMonth(Month mes, boolean tipo);

	/**
	 * Selecci??n de registros por un rango de fechas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param tipo         true si es fecha de ingreso, false si es fecha egreso (de
	 *                     salida)
	 * @return Lista de registros
	 */
	public List<RegistroIE> selectByRange(Date fechaInicial, Date fechaFinal, boolean tipo);

	/**
	 * Selecci??n de todos los registrosIE
	 * 
	 * @return Lista de registros
	 */
	public List<RegistroIE> selectAll();

	/**
	 * Insertar un registro de ingreso
	 * 
	 * @param registroI
	 * @return true or false
	 */
	public boolean insertI(RegistroIE registroI);

	/**
	 * Actualizar un registroIE
	 * 
	 * @param registroI
	 * @return true or false
	 */
	public boolean updateIE(RegistroIE registroI);

	/**
	 * Selecciona el registroIE que tenga vacio los datos de egreso por el id del
	 * vehiculo registrado
	 * 
	 * @param vehiculo
	 * @return el regsitro
	 */
	public RegistroIE selectByVehiculo(Vehiculo vehiculo);

	/**
	 * Obtiene los registros que solo tienen ingreso de vehiculos, de los vehiculos
	 * que no han egresado del parqueadero
	 * 
	 * @return
	 */
	public List<RegistroIE> getRegistrosI();
	
	/**
	 * Obtiene el registro activo del usuario que ingresó al parqueadero
	 * @param usuarioIngresoId
	 * @return
	 */
	public RegistroIE getRegistroActivo(Long usuarioIngresoId);
	
	/**
	 * Obtiene los registros que han registrado egreso, de los vehiculos que ya han salido
	 * 
	 * @return
	 */
	public List<RegistroIE> getRegistrosE();
	
	

}