package co.edu.ucundinamarca.uperc.informes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Informes {

	@GetMapping(path = "/informes")
	public String getInformes(Model model) {
		return "modulos/informes.html";
		
	}



}
