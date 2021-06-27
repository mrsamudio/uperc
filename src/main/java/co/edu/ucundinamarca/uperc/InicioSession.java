/**
 * 
 */
package co.edu.ucundinamarca.uperc;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

/**
 * Inicio de sesión
 * 
 * @since 0.0.1
 * @author mrsamudio
 * @version 0.0.1
 */
@Controller
@Component
//@EnableAspectJAutoProxy
public class InicioSession {

	@Autowired
	private UsuarioDAO usuariorepo;

	/**
	 * 
	 * Redirección de raiz /uperc/ a login, anteriormente raiz de pagina jsp
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public String inicioDeSession() {
		return "redirect:/login";
	}

	/**
	 * Página de inicio de sesión
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/login")
	public String getLogin(Model model) {
		model.addAttribute(Usuario.class.getSimpleName().toLowerCase(), new Usuario());
		return "login.html";
	}

	/**
	 * Verifica si el usuario y la contraseña ingresados se encuentran en bd
	 * 
	 * @param model
	 * @param usuario
	 * @param bindingResult
	 * @return página de respuesta con los atributos relacionados
	 */
	@PostMapping(value = "/doLogin", params = { "goLogin" })
//	@PostMapping(path ="/login", params={"goLogin"})
	public String doLogin(Model model, @ModelAttribute Usuario usuario, BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errortitulo", "Error");
			model.addAttribute("errormensaje", "Error al realizar la petición");
			model.addAttribute(Usuario.class.getName().toLowerCase(), new Usuario());
			return "login.html";
		}

		Usuario u = null;

		try {
			u = usuariorepo.selectByCorreo(usuario.getCorreo());

//			testeo();

			if (u != null) {
				if (u.getContrasena().equals(usuario.getContrasena())) {
					// TODO: agregar atributos al modelo para conservar la sessión activa
					return "redirect:/tableroPrincipal";
				} else {
					return errorDeIngreso(model, "Ingreso fallido",
							"La contraseña no es correcta, por favor intenta de nuevo");
				}
			} else {
				return errorDeIngreso(model, "Usuario no encontrado", "El usuario no se encuentra registrado!");
			}

		} catch (Exception e) {
			e.getMessage();
			return errorDeIngreso(model, "Error desconocido", "Por favor contacte al administrador!");
		}

	}

	/**
	 * Desencadena un mensaje modal indicando el error relacionado
	 * 
	 * @param model
	 * @param titulo
	 * @param mensaje
	 * @return
	 */
	private String errorDeIngreso(Model model, String titulo, String mensaje) {
		model.addAttribute("errortitulo", titulo);
		model.addAttribute("errormensaje", mensaje);
		model.addAttribute(Usuario.class.getName().toLowerCase(), new Usuario());
		model.addAttribute("mostrar", "$(window).load(function(){$('#mensajeModal').modal('show');});");
		return "login.html";
	}

	public void testeo() throws Exception {
		throw new Exception("Simulate an error");
	}

}
