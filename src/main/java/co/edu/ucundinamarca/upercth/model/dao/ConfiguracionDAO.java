/**
 * 
 */
package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Configuracion;

/**
 * Interfaz para el acceso los datos con objetos DAO en la entidad CONFIGURACION
 * 
 * @author mrsamudio
 *
 */
public interface ConfiguracionDAO {

	/**
	 * Consulta de configuraci??n por id del registro en BD
	 * 
	 * @param id
	 */
	public Configuracion selectById(Long id);

	/**
	 * Lista todos los registros en la entidad CONFIGURACION
	 * 
	 * @return
	 */
	public List<Configuracion> selectAll();

	/**
	 * Inserta un registro de configuraci??n
	 * 
	 * @param configuracion
	 */
	public boolean insert(Configuracion configuracion);

	/**
	 * Actualiza un registro de configuraci??n
	 * 
	 * @param configuracion
	 */
	public boolean update(Configuracion configuracion);

}
