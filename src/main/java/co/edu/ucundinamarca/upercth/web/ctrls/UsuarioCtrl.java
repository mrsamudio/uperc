package co.edu.ucundinamarca.upercth.web.ctrls;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.ucundinamarca.upercth.model.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.upercth.model.dao.RolDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Rol;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;

@EnableAspectJAutoProxy
@Controller
public class UsuarioCtrl {
	
	private Logger logger = LogManager.getLogger(UsuarioCtrl.class); // LoggerFactory.getLogger(getClass());
	private static Logger applicationsfile = LogManager.getLogger("applicationsfile");

	@Autowired
	private ConfiguracionDAO configuracionrepo;

	@Autowired
	private UsuarioDAO usuariorepo;

	@Autowired
	private RolDAO rolrepo;

	private HelpersUperc helpersUperc = new HelpersUperc();

	/**
	 * Obtiene el módulo de usuarios
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("modulos/usuarios.html")
	public String getUsuarios(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		model.addAttribute("usuarios", usuariorepo.selectAll());
		model.addAttribute("nuevoUsuario", new Usuario());
		
		List<Rol> roles = rolrepo.selectAll();
		if (usuariorepo.selectAdmins().size() == configuracionrepo.selectById(Long.valueOf(2)).getMaxAdmin()) {
		
			Predicate<Rol> isAdminsFull = item -> item.getNombre().equals("Administración");
			roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
			roles.removeIf(isAdminsFull);
			
			model.addAttribute("roles", roles);
		}else
			model.addAttribute("roles", roles);
		
//		logger.info("Proximo a renderizar el módulo!");
//		applicationsfile.info("Proximo a renderizar el módulo!");
		return "modulos/usuarios";
	}

	/**
	 * Crea un nuevo usuario
	 * 
	 * @param model
	 * @param usuario
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(path = "modulos/usuarios.html", params = { "crearNuevoUsuario" })
	public String crearUsuario(@Validated Usuario usuario, BindingResult bindingResult, Model model) {
		boolean insercionOk = false;

		if (bindingResult.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			bindingResult.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		// agregar usuario
		if (helpersUperc.usuarioOk(usuario)) {
			usuario.setFechaReg(Timestamp.from(Instant.now()));
			insercionOk = usuariorepo.insert(usuario);
			model.addAttribute("respuestaInsercion", insercionOk);
			return "redirect:/modulos/usuarios.html";
		} else
			return "";

	}

	/**
	 * Cambia el estado de un usuario
	 * 
	 * @param usuario
	 * @param bindingResult
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/modulos/usuarios.html", params = { "cambiarEstado" })
	public String cambiarEstado(final Usuario usuario, final BindingResult bindingResult,
			@RequestParam(value = "cambiarEstado", required = false) Long id) {

		if (usuariorepo.selectById(id).isEstado())
			usuariorepo.deactivate(id);
		else
			usuariorepo.activate(id);

		return "redirect:/modulos/usuarios.html";
	}

	/**
	 * Muestra el formulario de edición para un usuario
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = { "editarUsuario", "deConfiguracion" })
	public String editarUsuario(@RequestParam(value = "editarUsuario", required = true) final Long id,
			@RequestParam(value = "deConfiguracion", required = true) final Boolean deconfiguracion, Model model,
			Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
		}

		if (deconfiguracion)
			model.addAttribute("deConfiguracion", "deConfiguracion");

		List<Rol> roles = rolrepo.selectAll();
		if (usuariorepo.selectAdmins().size() == configuracionrepo.selectById(Long.valueOf(2)).getMaxAdmin()) {
		
			Predicate<Rol> isAdminsFull = item -> item.getNombre().equals("Administración");
			roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
			roles.removeIf(isAdminsFull);
			
			model.addAttribute("roles", roles);
		}else
			model.addAttribute("roles", roles);
		
		model.addAttribute("object", usuariorepo.selectById(id));

		return "/comun/edit";
	}

	/**
	 * Guarda un usuario editado
	 * 
	 * @param usuario
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = { "guardarUsuario" })
	public String guardarUsuario(@ModelAttribute("object") Usuario usuario, BindingResult result,
			@RequestParam(value = "guardarUsuario", required = true) final String deconfiguracion, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		usuariorepo.update(usuario);

		if (deconfiguracion.equals("deConfiguracion"))
			return "redirect:/modulos/configuracion.html";
		else
			return "redirect:/modulos/usuarios.html";

	}

}
