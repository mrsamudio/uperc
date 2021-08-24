package co.edu.ucundinamarca.upercth.model.dao;

import java.util.List;

import co.edu.ucundinamarca.upercth.model.entities.Permiso;

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
