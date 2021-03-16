package co.edu.ucundinamarca.uperc.integracion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Integracion {

	@GetMapping(path = "/integracion")
	public String getUsuarios(Model model) {
		return "modulos/intergracion.html";
		
	}

}
