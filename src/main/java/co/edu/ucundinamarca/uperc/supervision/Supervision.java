package co.edu.ucundinamarca.uperc.supervision;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Supervision {

	@GetMapping(path = "/supervision")
	public String getSupervision(Model model) {
		return "modulos/supervision.html";
		
	}

}
