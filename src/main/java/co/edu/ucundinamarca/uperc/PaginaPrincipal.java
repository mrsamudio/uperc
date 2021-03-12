package co.edu.ucundinamarca.uperc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Component
public class PaginaPrincipal {

	@GetMapping(path = "/tableroPrincipal")
	public String getExampleHTML(Model model) {
		model.addAttribute("nombreUsuario", "Inicio UPERC");
		model.addAttribute("descripcion", "Configuración inicial de Thymeleaf");
//		TODO: crear vista de tablero principal
		return "inicio.html";
	}


}
