package co.edu.ucundinamarca.uperc.reservas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Reservas {

	@GetMapping(path = "/reservas")
	public String getReservas(Model model) {
		return "modulos/reservas.html";
		
	}


}
