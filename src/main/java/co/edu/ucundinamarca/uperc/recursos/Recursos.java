package co.edu.ucundinamarca.uperc.recursos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Recursos {

	@GetMapping(path = "/recursos")
	public String getUsuarios(Model model) {
		return "modulos/recursos.html";
		
	}

}
