package co.edu.ucundinamarca.uperc.persistencia.dao;

import java.util.List;

import co.edu.ucundinamarca.uperc.persistencia.entidades.Permiso;

public interface PermisoDAO {
	
	/**
	 * 
	 * @param id
	 */
	public Permiso selectById(long id);

	/**
	 * 
	 * @return
	 */
	public List<Permiso> selectAll();

	/**
	 * 
	 * @param perfilUsuario
	 */
	public boolean insert(Permiso perfilUsuario);

}



//InformeDAOImplTest, PermisoDAOImplTest, RecursoDAOImplTest, RegServicioDAOImplTest, RegistroIEDAOImplTest
