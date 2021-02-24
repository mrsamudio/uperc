/**
 * 
 */
package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Configuracion;

/**
 * Interfaz para el acceso los datos con objetos DAO en la entidad CONFIGURACION
 * 
 * @author mrsamudio
 *
 */
public interface ConfiguracionDAO {

	/**
	 * Consulta de configuración por id del registro en BD
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
	 * Inserta un registro de configuración
	 * 
	 * @param configuracion
	 */
	public boolean insert(Configuracion configuracion);

	/**
	 * Actualiza un registro de configuración
	 * 
	 * @param configuracion
	 */
	public boolean update(Configuracion configuracion);

}
