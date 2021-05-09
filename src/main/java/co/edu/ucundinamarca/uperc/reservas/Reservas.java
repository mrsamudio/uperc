package co.edu.ucundinamarca.uperc.reservas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ucundinamarca.uperc.persistencia.dao.ReservaDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Reserva;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Supervision;

@Controller
public class Reservas {
	
	@Autowired
	private ReservaDAO reservasrepo;

	@GetMapping(path = "/reservas")
	public String getReservas(Model model) {
		model.addAttribute("reservas", reservasrepo.selectAll());
		return "modulos/reservas.html";
		
	}
	
	
	// TODO: cambiar esto para cancelar la reserva
//	@RequestMapping(value = "/reservas", params = { "cancelarReserva" })
	@GetMapping(value = "/reservas", params = { "cancelarReserva" })
	public String cancelarReserva(final Reserva reserva, final BindingResult bindingResult,
			@RequestParam(value = "cancelarReserva", required = false) Long id

	) {
		
		Reserva reserv = reservasrepo.selectById(id);

		if (reserv.isEstado()) {
			
//			reservasrepo.deactivate(id);
			boolean res = reservasrepo.endReserva(reserv, true);
			if (res)
				return "redirect:/reservas";
				
			
			// consulta la fecha actual 
			// si la fecha de reserva es menor a la fecha 
		}
		
		else
			reservasrepo.activate(id);

		return "";
	}


}
