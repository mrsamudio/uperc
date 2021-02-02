/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Reserva;

/**
 * @author ingsamudio
 *
 */
public interface ReservaDAO {

	/**
	 * 
	 * @param id
	 */
	public Reserva selectById(long id);

	/**
	 * 
	 * @param fechaSolicitud
	 */
	public List<Reserva> selectByFechaSol(Date fechaSolicitud);

	/**
	 * 
	 * @param fechaReserva
	 */
	public List<Reserva> selectByFechaRes(Date fechaReserva);

	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 */
	public List<Reserva> selectByRangoFSol(Date fechaInicial, Date fechaFinal);

	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 */
	public List<Reserva> selectByRangoFRes(Date fechaInicial, Date fechaFinal);

	/**
	 * 
	 * @return
	 */
	public List<Reserva> selectAll();
	
	/**
	 * 
	 * @param reserva
	 */
	public boolean insert(Reserva reserva);

	/**
	 * 
	 * @param reserva
	 */
	public boolean update(Reserva reserva);


	/**
	 * 
	 * @param id
	 */
	public boolean activate(long id);

	/**
	 * 
	 * @param id
	 */
	public boolean deactivate(long id);
}
