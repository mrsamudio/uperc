/**
 * 
 */
package co.edu.ucundinamarca.uperc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Inicio de sesion
 * @since 0.0.1
 * @author mrsamudio
 * @version 0.0.1
 */
@Controller
@Component
public class InicioSession {
	
	
	@RequestMapping
	public String inicioDeSession() {
		return "InicioDeSesion";		
	}
}
