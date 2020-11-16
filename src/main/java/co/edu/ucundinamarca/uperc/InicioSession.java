/**
 * 
 */
package co.edu.ucundinamarca.uperc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Inicio de sesion
 * @since 0.0.1
 * @author mrsamudio
 * @version 0.0.1
 */
@Controller
public class InicioSession {
	
	@RequestMapping
	public String inicioDeSession() {
		
		return "InicioDeSesion";		
	}

}
