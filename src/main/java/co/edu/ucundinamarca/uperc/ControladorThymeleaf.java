/**
 * 
 */
package co.edu.ucundinamarca.uperc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * @author mrsamudio
 *
 */

@Controller

public class ControladorThymeleaf {
	
	@Autowired
	private UsuarioDAO usuariorepo;

	@RequestMapping(value = "/html", method = RequestMethod.GET)
	public String getExampleHTML(Model model) {
		model.addAttribute("nombreUsuario", "Inicio UPERC");
		model.addAttribute("descripcion", "Configuración inicial de Thymeleaf");
		return "inicio.html";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login.html";
	}
	
	/**
	 * Verifica si el usuario y la contraseña ingresados se encuentran en bd
	 * @param model
	 * @param usuario
	 * @param bindingResult
	 * @return pagina de respuesta con los atributos relacionados 
	 */
	@PostMapping(value="/doLogin", params={"goLogin"})
    public String doLogin(Model model,@ModelAttribute Usuario usuario, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorlogin", "Error al realizar la petición");
			model.addAttribute("usuario", new Usuario());
			return "login.html";
		}
		
		if (usuariorepo.selectByCorreo(usuario.getCorreo()).getContrasena().equals(usuario.getContrasena())) {
			return "inicio.html";
		}else {
//			TODO: agregar los atributos a la vista
			model.addAttribute("errorlogin", "La contraseña no es correcta, por favor intenta de nuevo");
			model.addAttribute("usuario", new Usuario());
			return "login.html";
		}
    }

	@RequestMapping(value = "/js", method = RequestMethod.GET)
	public String getExampleJS(Model model) {
		List<String> variablesConf = Arrays.asList("conf1", "conf2", "conf3", "conf4", "conf5");
		model.addAttribute("configuracion", variablesConf);
		return "inicio.js";
	}

	@RequestMapping(value = "/plano", method = RequestMethod.GET)
	public String getExamplePlain(Model model) {
		List<String> espaciosLibres = Arrays.asList("b1", "b2", "c10", "s30", "zz0");
		model.addAttribute("usuario", "Mario Roberto Samudio");
		model.addAttribute("disponibles", espaciosLibres);
		return "inicio.txt";
	}
}
