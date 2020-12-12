/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.dao.ReservaDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Reserva;

/**
 * @author mrsamudio
 *
 */
public class ReservaDAOImpl implements ReservaDAO {

	@Override
	public Reserva selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> selectByFechaSol(Date fechaSolicitud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> selectByFechaRes(Date fechaReserva) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> selectByRangoFSol(Date fechaInicial, Date fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> selectByRangoFRes(Date fechaInicial, Date fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Reserva reserva) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(Reserva reserva) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean activate(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deactivate(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
