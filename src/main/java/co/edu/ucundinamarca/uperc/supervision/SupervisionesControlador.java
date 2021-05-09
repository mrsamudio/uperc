package co.edu.ucundinamarca.uperc.supervision;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ucundinamarca.uperc.persistencia.dao.SupervisionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Supervision;

@Controller
//@Component
public class SupervisionesControlador {

	@Autowired
	private SupervisionDAO supervisionrepo;

	@Autowired
	private UsuarioDAO usuariorepo;

	@GetMapping(path = "/supervision")
	public String getSupervision(Model model) {
		model.addAttribute("supervisiones", supervisionrepo.selectAll());
		model.addAttribute("nuevaSupervision", new Supervision());
		model.addAttribute("usuarios", usuariorepo.selectAll());

		return "modulos/supervision.html";

	}

	@PostMapping(path = "/supervision", params = "crearNuevaSupervision")
	public String nuevaSupervision(Model model, @ModelAttribute Supervision supervision, BindingResult bindingResult) {

		if (!supervision.getMensaje().isEmpty()) {

			supervision.setEstado(true);
			supervision.setFecha(Timestamp.from(Instant.now()));

			supervisionrepo.insert(supervision);
			return "redirect:/supervision";
		} else {
			return "";
		}

	}

//	@RequestMapping(value = "/supervision", params = { "cambiarEstado" })
	@GetMapping(value = "/supervision", params = { "cambiarEstado" })
	public String cambiarEstado(final Supervision supervision, final BindingResult bindingResult,
			@RequestParam(value = "cambiarEstado", required = false) Long id

	) {

		if (supervisionrepo.selectById(id).isEstado())
			supervisionrepo.deactivate(id);
		else
			supervisionrepo.activate(id);

		return "redirect:/supervision";
	}

}