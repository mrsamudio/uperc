package co.edu.ucundinamarca.uperc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Component
public class TableroPrincipal {

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/tableroPrincipal")
//	TODO: agregar parametros datos que se muestran en el tablero principal
	public String getTableroPrincipal(Model model) {
		model.addAttribute("nombreUsuario", "Inicio UPERC");
		model.addAttribute("descripcion", "Configuración inicial de Thymeleaf");
//		TODO: crear vista de tablero principal
		return "tableroPrincipal.html";
	}


}
