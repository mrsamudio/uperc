package co.edu.ucundinamarca.upercth.web.ctrls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ucundinamarca.upercth.model.dao.RecursoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Recurso;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;

@Controller
public class RecursoCtrl {

	@Autowired
	private RecursoDAO recursorepo;
	
	private HelpersUperc helpersUperc = new HelpersUperc();
	
	/**
	 * Obtiene el modulo de recursos
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping("/modulos/recursos.html")
	public String getRecursos(Model model, Authentication auth) {
		
		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		
		model.addAttribute("nuevoRecurso", new Recurso());
		model.addAttribute("recursosl", recursorepo.selectAll());
		return "modulos/recursos";
		
	}
	
	
	@PostMapping(path = "modulos/recursos.html", params = { "crearNuevoRecurso" })
	public String crearRecurso(@Validated Recurso recurso, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
				});
			model.addAttribute("error", errores);
			return "test";
		}
		
		recursorepo.insert(recurso);
		
		return "redirect:/modulos/recursos.html";
	}
	
	
	/**
	 * Muestra el formulario de edición para un recurso
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = {"editarRecurso"})
	public String editarRecurso(@RequestParam(value = "editarRecurso", required = true) final Long id, Model model, Authentication auth) {
		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		
		model.addAttribute("object", recursorepo.selectById(id));
		
		return "/comun/edit";
	}
	
	/**
	 * Guarda un recurso editado
	 * 
	 * @param recurso
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = {"guardarRecurso"})
	public String guardarUsuario(@ModelAttribute("object") Recurso recurso, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
				});
			model.addAttribute("error", errores);
			return "test";
		}
		
		recursorepo.update(recurso);
		
		return "redirect:/modulos/recursos.html";
	}
	
	/**
	 * Cambia el estado de un recurso
	 * 
	 * @param recurso
	 * @param bindingResult
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/modulos/recursos.html", params = { "cambiarEstado" })
	public String cambiarEstado(@RequestParam(value = "cambiarEstado", required = false) Long id) {
		
		if(recursorepo.selectById(id).isEstado())
			recursorepo.deactivate(id);
		else
			recursorepo.activate(id);
		
		return "redirect:/modulos/recursos.html";
	}
	
}
