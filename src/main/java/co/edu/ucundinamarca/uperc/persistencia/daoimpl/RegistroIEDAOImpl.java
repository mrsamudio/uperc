/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.daoimpl;

import java.time.Month;
import java.util.Date;
import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.dao.RegistroIEDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.RegistroIE;

/**
 * @author mrsamudio
 *
 */
public class RegistroIEDAOImpl implements RegistroIEDAO {

	@Override
	public RegistroIE selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistroIE> selectByDate(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistroIE> selectByMonth(Month mes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegistroIE> selectByRange(Date fechaInicial, Date fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(RegistroIE registroIE) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RegistroIE registroIE) {
		// TODO Auto-generated method stub
		return false;
	}

}
