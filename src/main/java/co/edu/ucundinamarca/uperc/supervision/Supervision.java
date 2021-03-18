package co.edu.ucundinamarca.uperc.supervision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.SupervisionDAO;

@Controller
//@Component
public class Supervision {
	
	@Autowired
	private SupervisionDAO supervisionrepo;


	@GetMapping(path = "/supervision")
	public String getSupervision(Model model) {
		model.addAttribute("supervisiones", supervisionrepo.selectAll());
		return "modulos/supervision.html";
		
	}

}











