/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Vehiculo;

/**
 * @author mrsamudio
 *
 */
public interface VehiculoDAO {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Vehiculo selectById(long id);

	/**
	 * 
	 * @param placa
	 * @return
	 */
	public Vehiculo selectByPlaca(String placa);

	/**
	 * 
	 * @param marca
	 * @return
	 */
	public List<Vehiculo> selectByMarca(String marca);

	/**
	 * 
	 * @return
	 */
	public List<Vehiculo> selectAll();

	/**
	 * 
	 * @param vehiculo
	 * @return
	 */
	public boolean insert(Vehiculo vehiculo);

	/**
	 * 
	 * @param vehiculo
	 * @return
	 */
	public boolean update(Vehiculo vehiculo);

}
