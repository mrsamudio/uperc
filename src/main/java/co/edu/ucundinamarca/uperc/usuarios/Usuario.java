package co.edu.ucundinamarca.uperc.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;

@Controller
@Component
public class Usuario {

	@Autowired
	private UsuarioDAO usuariorepo;

	@GetMapping(path = "/usuarios")
	public String getUsuarios(Model model) {
		
//		model.addAllAttributes("usuarios", usuariorepo.selectAll());
		model.addAttribute("usuarios", usuariorepo.selectAll());
		return "modulos/usuarios.html";
		
	}

	
}
