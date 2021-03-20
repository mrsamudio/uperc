package co.edu.ucundinamarca.uperc.recursos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.RecursoDAO;

@Controller
public class Recursos {

	@Autowired
	private RecursoDAO recursorepo;
	
	@GetMapping(path = "/recursos")
	public String getRecursos(Model model) {
		model.addAttribute("recursos", recursorepo.selectAll());
		return "modulos/recursos.html";
		
	}

}
