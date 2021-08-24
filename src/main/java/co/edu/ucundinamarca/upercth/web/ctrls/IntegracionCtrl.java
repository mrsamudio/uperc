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

import co.edu.ucundinamarca.upercth.model.dao.SistemaExternoDAO;
import co.edu.ucundinamarca.upercth.model.entities.SistemaExterno;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;


@Controller
public class IntegracionCtrl {

	
	@Autowired
	private SistemaExternoDAO sistemarepo;
	
	private HelpersUperc helpersUperc = new HelpersUperc();
	
	/**
	 * Obtiene el módulo de integraciones
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/modulos/integracion.html")
	public String getIntegracion(Model model, Authentication auth) {
		
		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		
		model.addAttribute("nuevoSistemae", new SistemaExterno());
		model.addAttribute("sistemasext", sistemarepo.selectAll());
		return "modulos/integracion";
		
	}
	
	
	/**
	 * Crea un nuevo acceso para un sistema externo
	 * 
	 * @param sistemae
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "modulos/integracion.html", params = { "crearNuevoSistemae" })
	public String crearSistemae(@Validated SistemaExterno sistemae, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
				});
			model.addAttribute("error", errores);
			return "test";
		}
		
		sistemarepo.insert(sistemae);
		
		return "redirect:/modulos/integracion.html";
	}
	
	
	/**
	 * Muestra el formulario de edición para un sistema externo
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = {"editarSistemae"})
	public String editarSistemae(@RequestParam(value = "editarSistemae", required = true) final Integer id, Model model, Authentication auth) {
		
		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		
		model.addAttribute("object", sistemarepo.selectById(id));
		return "/comun/edit";
	}
	
	/**
	 * Guarda un sistema externo editado
	 * 
	 * @param sistemae
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = {"guardarSistemae"})
	public String guardarSistemae(@ModelAttribute("object") SistemaExterno sistemae, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
				});
			model.addAttribute("error", errores);
			return "test";
		}
		
		sistemarepo.update(sistemae);
		return "redirect:/modulos/integracion.html";
		
	}
	
}
