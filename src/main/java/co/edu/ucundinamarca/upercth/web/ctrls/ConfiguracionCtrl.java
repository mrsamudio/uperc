package co.edu.ucundinamarca.upercth.web.ctrls;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

import co.edu.ucundinamarca.upercth.model.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.upercth.model.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.upercth.model.dao.PerfilUsuarioDAO;
import co.edu.ucundinamarca.upercth.model.dao.RolDAO;
import co.edu.ucundinamarca.upercth.model.dao.UbicacionDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Configuracion;
import co.edu.ucundinamarca.upercth.model.entities.EspacioParqueo;
import co.edu.ucundinamarca.upercth.model.entities.PerfilUsuario;
import co.edu.ucundinamarca.upercth.model.entities.Rol;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;

@Controller
public class ConfiguracionCtrl {

	@Autowired
	private ConfiguracionDAO configuracionrepo;

	@Autowired
	private PerfilUsuarioDAO perfilrepo;

	@Autowired
	private RolDAO rolrepo;

	@Autowired
	private EspacioParqueoDAO espaciorepo;

	@Autowired
	private UsuarioDAO usuariorepo;

	@Autowired
	private UbicacionDAO ubicacionrepo;

	private HelpersUperc helpersUperc = new HelpersUperc();

	/**
	 * Obtiene el módulo de configuración
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping("/modulos/configuracion.html")
	public String getConfiguracion(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
			model.addAttribute("miUsuario", usuariorepo.selectByCorreo(auth.getName()));
		}

		model.addAttribute("configuraciones", configuracionrepo.selectAll());
		return "modulos/configuracion";
	}

	/**
	 * Muestra el formulario de edición para una configuración
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = { "editarConfiguracion" })
	public String editarConfiguracion(@RequestParam(value = "editarConfiguracion", required = true) final Long id,
			Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

//		model.addAttribute("usuarios", usuariorepo.selectAll());
		model.addAttribute("object", configuracionrepo.selectById(id));

		return "/comun/edit";
	}

	/**
	 * Guarda una configuración editada
	 * 
	 * @param configuracion
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = { "guardarConfiguracion" })
	public String guardarConfiguracion(@ModelAttribute("object") Configuracion configuracion, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		if (usuariorepo.selectAdmins().size() > configuracion.getMaxAdmin()) {
			
			model.addAttribute("errormensaje", "El número de administradores asociados es mayor, debe escojer otro número válido.");
			model.addAttribute("mostrar", "$('#mensajeModal').modal('show');");

			model.addAttribute("object", configuracion);
			return "/comun/edit";
		}

		configuracionrepo.update(configuracion);
		return "redirect:/modulos/configuracion.html";
	}

	/**
	 * Obtiene el módulo de roles
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping("/modulos/configuracion/roles.html")
	public String getRoles(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
			model.addAttribute("roles", rolrepo.selectAll());
		}


		return "modulos/configuracion/roles";
	}

	/**
	 * Muestra el formulario de edición para un rol
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = { "editarRol" })
	public String editarRol(@RequestParam(value = "editarRol", required = true) final Integer id, Model model,
			Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("perfiles", perfilrepo.selectAll());
		model.addAttribute("object", rolrepo.selectById(id));

		return "/comun/edit";
	}

	/**
	 * Guarda un rol editado
	 * 
	 * @param perfilu
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = { "guardarRol" })
	public String guardarRol(@ModelAttribute("object") Rol rol, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		rolrepo.update(rol);

		return "redirect:/modulos/configuracion/roles.html";
	}

	/**
	 * Obtiene el módulo de perfiles de usuarios
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping("/modulos/configuracion/perfiles.html")
	public String getPerfiles(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("perfiles", perfilrepo.selectAll());
		return "modulos/configuracion/perfiles";

	}

	/**
	 * Muestra el formulario de edición para un perfil de usuario
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = { "editarPerfilu" })
	public String editarPerfilu(@RequestParam(value = "editarPerfilu", required = true) final Integer id, Model model,
			Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("object", perfilrepo.selectById(id));

		return "/comun/edit";
	}

	/**
	 * Guarda un perfil de usuario editado
	 * 
	 * @param perfilu
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = { "guardarPerfilu" })
	public String guardarPerfilu(@ModelAttribute("object") PerfilUsuario perfilu, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		perfilrepo.update(perfilu);

		return "redirect:/modulos/configuracion/perfiles.html";
	}

	/**
	 * Obtiene el módulo de espacios de parqueo
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping("/modulos/configuracion/espacios.html")
	public String getEspacios(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}
		
		List<EspacioParqueo> espacios =  espaciorepo.selectAll();
		espacios.sort(Comparator.comparing(a -> a.isOcupado()));

		model.addAttribute("espacios", espacios);
		return "modulos/configuracion/espacios";

	}

	/**
	 * Muestra el formulario de edición para un espacio de parqueo
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = { "editarEspaciop" })
	public String editarEspaciop(@RequestParam(value = "editarEspaciop", required = true) final Integer id, Model model,
			Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("ubicaciones", ubicacionrepo.selectAll());
		model.addAttribute("object", espaciorepo.selectById(id));

		return "/comun/edit";
	}

	/**
	 * Guarda un espacio de parquero editado
	 * 
	 * @param espacio
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = { "guardarEspaciop" })
	public String guardarEspaciop(@ModelAttribute("object") EspacioParqueo espacio, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		espaciorepo.update(espacio);
		return "redirect:/modulos/configuracion/espacios.html";
	}

}
