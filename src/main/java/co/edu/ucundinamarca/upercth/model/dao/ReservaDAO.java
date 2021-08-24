/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.sql.Timestamp;
import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Reserva;
import co.edu.ucundinamarca.upercth.model.entities.Supervision;
import co.edu.ucundinamarca.upercth.util.ConstantesDB;

/**
 * @author ingsamudio
 *
 */
public interface ReservaDAO {

	/**
	 * Selección de una reserva por su id
	 * 
	 * @param id
	 * @return la reserva identificada con el id
	 */
	public Reserva selectById(long id);
	
	
	/**
	 * Consulta las reservas por usuario
	 * 
	 * @param id
	 * @return
	 */
	public List<Reserva> selectByUser(long id);
	
	/**
	 * Consulta las reservas por usuario y su estado
	 * 
	 * @param id
	 * @param estado 
	 * @return
	 */
	public List<Reserva> selectByUser(long id, Boolean estado);
	
	/**
	 * Obtiene la reserva activa por el usuario que reservó el espacio
	 * @param id userid
	 * @return
	 */
	public Reserva getReservedByUser(long id);

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
	 * @return lista de ReservaCtrl <= a la fecha a buscar
	 */
	public List<Reserva> selectByFecha(Timestamp fecha, String tipo);
	
	/**
	 * Consulta de selección de la reserva por fecha menores o iguales 
	 * 
	 * @param fecha a buscar
	 * @param tipo de fecha en entidad {@link Reserva}  <p>
	 * <ol>
	 *  <li> {@link ConstantesDB#SOLICITUD}
	 *  <li> {@link ConstantesDB#RESERVA}
	 *  <li> {@link ConstantesDB#FIN}
	 * </ol>
	 * @param estado true(activa), false(finalizada)
	 * @return lista de ReservaCtrl <= a la fecha a buscar
	 */
	public List<Reserva> selectByFecha(Timestamp fecha, String tipo, Boolean estado);


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
	 * Consulta seleccionar reservas activas por rango y con el espacio de parqueo especifico
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param tipo
	 * @param espacioId
	 * @return
	 */
	public List<Reserva> selectByRango(Timestamp fechaInicial, Timestamp fechaFinal, String tipo, int espacioId);

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
	 * @param true para cancelada, false para no cancelada
	 * @return true or false
	 */
	public boolean endReserva(Reserva reserva, boolean isCancelada);
	

	/**
	 * Activa un registro de reserva
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean activate(Reserva reserva);

	/**
	 * Desactiva un registro de reserva
	 * 
	 * @param supervision
	 * @return
	 */
	public boolean deactivate(Reserva reserva);

	/**
	 * Activa un registro de reserva por id
	 * 
	 * @param id
	 * @return
	 */
	public boolean activate(long id);

	/**
	 * Desactiva un registro de reserva por id
	 * 
	 * @param id
	 * @return
	 */
	public boolean deactivate(long id);
	

}
