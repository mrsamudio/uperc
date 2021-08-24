package co.edu.ucundinamarca.upercth.model.dao;

//import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

import co.edu.ucundinamarca.upercth.model.entities.Usuario;

//@Component
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{

//	@Query("SELECT u.correo, u.contrasena, u.estado FROM usuario u WHERE u.correo=?")
//	@Bean
	@Query("SELECT u FROM usuario u WHERE u.correo=?")
	
	public Usuario findByUsername(String username);
}
