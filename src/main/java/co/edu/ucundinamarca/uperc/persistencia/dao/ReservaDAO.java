/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Reserva;
import co.edu.ucundinamarca.uperc.persistencia.utilidades.ConstantesDB;

/**
 * @author ingsamudio
 *
 */
public interface ReservaDAO {

	/**
	 * Seleción de una reserva por su id
	 * 
	 * @param id
	 * @return la reserva identificada con el id
	 */
	public Reserva selectById(long id);

	/**
	 * Consulta de selección de la reserva por fecha 
	 * 
	 * @param fecha a buscar
	 * @param tipo de fecha en entidad {@link Reserva}  <p>
	 * <ol>
	 *  <li> {@link ConstantesDB#SOLICITUD}
	 *  <li> {@link ConstantesDB#RESERVA}
	 *  <li> {@link ConstantesDB#FIN}
	 * </ol>
	 * @return lista de Reservas <= a la fecha a buscar
	 */
	public List<Reserva> selectByFecha(Timestamp fecha, String tipo);


	/**
	 * Consulta por rango de fechas
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param tipo de fecha en entidad {@link Reserva}  <p>
	 * <ol>
	 *  <li> {@link ConstantesDB#SOLICITUD}
	 *  <li> {@link ConstantesDB#RESERVA}
	 *  <li> {@link ConstantesDB#FIN}
	 * </ol>
	 * 
	 * @return lista de reservas que coinciden con el rango de fechas
	 */
	public List<Reserva> selectByRango(Timestamp fechaInicial, Timestamp fechaFinal, String tipo);


	/**
	 * Seleccionar todas las reservas
	 * 
	 * @return lista de todas las reservas
	 */
	public List<Reserva> selectAll();

	/**
	 * Insertar una nueva reserva
	 * 
	 * @param reserva nueva
	 * @return true or false
	 */
	public boolean insert(Reserva reserva);

	/**
	 * Actualizar la reserva
	 * 
	 * @param reserva para actualizar
	 * @return true or false
	 */
	public boolean update(Reserva reserva);

	/**
	 * Finaliza o cancela la reserva
	 * 
	 * @param reserva a finalizar o cancelar
	 * @return true or false
	 */
	public boolean endReserva(Reserva reserva);

}
