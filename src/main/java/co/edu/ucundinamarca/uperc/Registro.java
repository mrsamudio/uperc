/**
 * 
 */
package co.edu.ucundinamarca.uperc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;

/**
 * 
 * @author mrsamudio
 *
 */
@Controller
@Component
public class Registro {
	
	@Autowired
	private UsuarioDAO usuariorepo;
	
	
	@GetMapping(path = "/registro")
	public String registro() {
		//TODO: crear vista de registro de usuario
		return "registro.html";
	}

}
