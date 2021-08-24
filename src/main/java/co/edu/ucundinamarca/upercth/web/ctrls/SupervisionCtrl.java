package co.edu.ucundinamarca.upercth.web.ctrls;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ucundinamarca.upercth.model.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.upercth.model.dao.SupervisionDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Supervision;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;



@Controller
public class SupervisionCtrl {

	@Autowired
	private SupervisionDAO supervisionrepo;

	@Autowired
	private UsuarioDAO usuariorepo;
	
	private HelpersUperc helpersUperc = new HelpersUperc();

	
	@GetMapping(path = "/modulos/supervision.html")
	public String getSupervision(Model model, Authentication auth) {
		
		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		
		model.addAttribute("supervisiones", supervisionrepo.selectAll());
		model.addAttribute("nuevaSupervision", new Supervision());
		model.addAttribute("usuarios", usuariorepo.selectAll());

		return "modulos/supervision";

	}

	@PostMapping(path = "/modulos/supervision.html", params = "crearNuevaSupervision")
	public String nuevaSupervision(@ModelAttribute Supervision supervision, BindingResult bindingResult) {

//		if (!supervision.getMensaje().isEmpty()) {

			supervision.setEstado(true);
			supervision.setFecha(Timestamp.from(Instant.now()));

			supervisionrepo.insert(supervision);
			return "redirect:/modulos/supervision.html";
//		} else {
//			return "";
//		}

	}

//	@RequestMapping(value = "/supervision", params = { "cambiarEstado" })
	@GetMapping(value = "/modulos/supervision.html", params = { "cambiarEstado" })
	public String cambiarEstado(final Supervision supervision, final BindingResult bindingResult,
			@RequestParam(value = "cambiarEstado", required = false) Long id
	) {

		if (supervisionrepo.selectById(id).isEstado())
			supervisionrepo.deactivate(id);
		else
			supervisionrepo.activate(id);

		return "redirect:/modulos/supervision.html";
	}
	
	/**
	 * Muestra el formulario de edición para un mensaje de supervisión
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = {"editarSupervision"})
	public String editarSupervision(@RequestParam(value = "editarSupervision", required = true) final Long id, Model model, Authentication auth) {
		
		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		
		model.addAttribute("usuarios", usuariorepo.selectAll());
		model.addAttribute("object", supervisionrepo.selectById(id));
		
		return "/comun/edit.html";
	}
	
	/**
	 * Guarda el mensaje de supervision editado
	 * 
	 * @param supervision
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = {"guardarSupervision"})
	public String guardarSupervision(@ModelAttribute("object") Supervision supervision, BindingResult result, Model model ) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
				});
			model.addAttribute("error", errores);
			return "test";
		}
		
		supervisionrepo.update(supervision);
		
		return "redirect:/modulos/supervision.html";
	}
	

}
