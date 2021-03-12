/**
 * 
 */
package co.edu.ucundinamarca.uperc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;


/**
 * Inicio de sesión
 * @since 0.0.1
 * @author mrsamudio
 * @version 0.0.1
 */
@Controller
@Component
public class InicioSession {
	
	
	@RequestMapping
	public String inicioDeSession() {
//		return "InicioDeSesion";		
		return "redirect:/login";		
	}
	
	
	@Autowired
	private UsuarioDAO usuariorepo;

	/**
	 * Página de inicio de sesión
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/login")
	public String getLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login.html";
	}
	
	/**
	 * Verifica si el usuario y la contraseña ingresados se encuentran en bd
	 * @param model
	 * @param usuario
	 * @param bindingResult
	 * @return página de respuesta con los atributos relacionados 
	 */
//	@PostMapping(value="/doLogin", params={"goLogin"})
	@PostMapping(path ="/login", params={"goLogin"})
    public String doLogin(Model model,@ModelAttribute Usuario usuario, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("errortitulo", "Error");
			model.addAttribute("errormensaje", "Error al realizar la petición");
			model.addAttribute("usuario", new Usuario());
			return "login.html";
		}
		
		if (usuariorepo.selectByCorreo(usuario.getCorreo()).getContrasena().equals(usuario.getContrasena())) {
//			TODO: agregar atributos al modelo para conservar la sessión activa
			return "redirect:/tableroPrincipal";
		}else {
			model.addAttribute("errortitulo", "Ingreso fallido");
			model.addAttribute("errormensaje", "La contraseña no es correcta, por favor intenta de nuevo");
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("mostrar", "$(window).load(function(){$('#mensajeModal').modal('show');});");
			return "login.html";
		}
    }
	
	
}
