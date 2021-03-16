package co.edu.ucundinamarca.uperc.usuarios;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Usuario {

	@GetMapping(path = "/usuarios")
	public String getUsuarios(Model model) {
		return "modulos/usuarios.html";
		
	}

	
}
