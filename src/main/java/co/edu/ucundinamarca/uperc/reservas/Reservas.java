package co.edu.ucundinamarca.uperc.reservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.ReservaDAO;

@Controller
public class Reservas {
	
	@Autowired
	private ReservaDAO reservasrepo;

	@GetMapping(path = "/reservas")
	public String getReservas(Model model) {
		model.addAttribute("reservas", reservasrepo.selectAll());
		return "modulos/reservas.html";
		
	}


}
