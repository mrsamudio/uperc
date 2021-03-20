package co.edu.ucundinamarca.uperc.integracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.SistemaExternoDAO;

@Controller
public class Integracion {

	@Autowired
	private SistemaExternoDAO sistemarepo;
	
	
	@GetMapping(path = "/integracion")
	public String getIntegracion(Model model) {
		model.addAttribute("sistemasext", sistemarepo.selectAll());
		return "modulos/integracion.html";
		
	}

}
