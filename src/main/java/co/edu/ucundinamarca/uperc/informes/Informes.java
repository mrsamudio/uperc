package co.edu.ucundinamarca.uperc.informes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.InformeDAO;

@Controller
public class Informes {
	
	@Autowired
	private InformeDAO informerepo;

	@GetMapping(path = "/informes")
	public String getInformes(Model model) {
		model.addAttribute("informes", informerepo.selectAll());
		return "modulos/informes.html";
		
	}



}
