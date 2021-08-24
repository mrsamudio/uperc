package co.edu.ucundinamarca.upercth.web.ctrls;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.ucundinamarca.upercth.model.dao.RolDAO;
import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Rol;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;
import co.edu.ucundinamarca.upercth.model.services.EmailService;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;

@Controller
public class MainCtrl {

	@Autowired
	private UsuarioDAO usuariorepo;

	@Autowired
	private RolDAO rolrepo;

	@Autowired
	private EmailService mailserv;

	private HelpersUperc helpersUperc = new HelpersUperc();

	/**
	 * Redirección de raiz /uperc/ a tableroPrincipal
	 * 
	 * @return
	 */
	@GetMapping({ "/", "" })
	public String inicioDeSession(Locale locale) {
		return "redirect:/tableroPrincipal.html";
	}

	/**
	 * Página de inicio de sesión
	 * 
	 * @param error
	 * @param logout
	 * @param model
	 * @param principal
	 * @param flash
	 * @return
	 */
	@GetMapping("/login.html")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (principal != null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
//			TODO: enviar texto a modal en thymeleaf
			return "redirect:/";
		}

		if (error != null) {
			model.addAttribute("error",
					"La contraseña o el correo electrónico no es correcto, por favor intenta de nuevo");
			model.addAttribute("errormensaje",
					"La contraseña o el correo electrónico no es correcto, por favor intenta de nuevo");
			model.addAttribute("mostrar", "$(window).load(function(){$('#mensajeModal').modal('show');});");
		}

		if (logout != null) {
			model.addAttribute("success", "ha cerrado sesión con éxito");
//			model.addAttribute("errormensaje", "La contraseña o el correo electrónico no es correcto, por favor intenta de nuevo");
//			model.addAttribute("mostrar", "$(window).load(function(){$('#mensajeModal').modal('show');});");
		}
		
		if(error != null) {
			model.addAttribute("error", "contraseña invalida le quedan x intentos");
			
		}

		return "login";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/logout.html")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login.html";
	}

	/**
	 * Obtiene la página de registro de usuario
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/registro.html")
	public String getRegistro(Model model) {

		List<Rol> roles = rolrepo.selectAll();

		Predicate<Rol> isAdminsFull = item -> item.getNombre().equals("Administración");
		roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
		roles.removeIf(isAdminsFull);

		isAdminsFull = item -> item.getNombre().equals("Administrativo");
		roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
		roles.removeIf(isAdminsFull);

		isAdminsFull = item -> item.getNombre().equals("Supervisión");
		roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
		roles.removeIf(isAdminsFull);

		model.addAttribute("roles", roles);

		model.addAttribute("object", new Usuario());
		return "registro";
	}

	/**
	 * 
	 * @param usuario
	 * @param result
	 * @param deconfiguracion
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/registro.html", params = { "solicitarRegistro" })
	public String solicitarRegistro(@ModelAttribute("object") Usuario usuario, BindingResult result,
			@RequestParam(value = "solicitarRegistro", required = true) final String deconfiguracion, Model model) {

		if (usuariorepo.selectByCorreo(usuario.getCorreo()) == null && (helpersUperc.usuarioOk(usuario))
				&& !result.hasErrors()) {

			String encoded = Base64.getEncoder().encodeToString(
					usuario.getCorreo().concat(usuario.getNombres()).concat(usuario.getNumId()).getBytes());
			String correo = Base64.getEncoder().encodeToString(usuario.getCorreo().getBytes());

			usuario.setFechaReg(Timestamp.from(Instant.now()));
			usuario.setEstado(false);
			Boolean insercionOk = usuariorepo.insert(usuario);
			model.addAttribute("respuestaInsercion", insercionOk);

			InetAddress ip;
			String address = "http://";
			try {
				ip = InetAddress.getLocalHost();
				address += ip.getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				address += "localhost";
			}
			
			address += ":8080/upercth/".concat("completarReg/");
			
			// TODO: guardar enlace en bd
			String enlace = address.concat(encoded).concat("/").concat(correo);

			mailserv.sendMail(usuario.getCorreo().toString(), "Solicitud de registro de Usuario",
					"Para completar el registro ingrese al siguiente enlace: " + enlace);

			return "redirect:/login.html";
		} else {
			model.addAttribute("correoYaRegistrado",
					"Este correo ya se encuentra registrado! o debe corregir los datos");

			List<Rol> roles = rolrepo.selectAll();

			Predicate<Rol> isAdminsFull = item -> item.getNombre().equals("Administración");
			roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
			roles.removeIf(isAdminsFull);

			isAdminsFull = item -> item.getNombre().equals("Administrativo");
			roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
			roles.removeIf(isAdminsFull);

			isAdminsFull = item -> item.getNombre().equals("Supervisión");
			roles.stream().filter(isAdminsFull).forEach(item -> item.getNombre());
			roles.removeIf(isAdminsFull);

			model.addAttribute("roles", roles);

			model.addAttribute("object", new Usuario());
			return "registro";
		}
	}

	/**
	 * Activar al usuario
	 * 
	 * @param activador
	 * @param correo
	 * @return
	 */
	@GetMapping("/completarReg/{activador}/{correo}")
	public String guardarRegistro(@PathVariable String activador, @PathVariable String correo) {

		byte[] decodedBytes = Base64.getDecoder().decode(activador);
		String decoded = new String(decodedBytes);
		
		decodedBytes = Base64.getDecoder().decode(correo);
		Usuario usuario = usuariorepo.selectByCorreo(new String(decodedBytes));

		String comparar = usuario.getCorreo().concat(usuario.getNombres()).concat(usuario.getNumId());
		
		if (usuario != null && decoded.equals(comparar) && !usuario.isEstado()) {
			usuariorepo.activate(usuario.getId());
			return "redirect:/login.html";
		} else {
			return "";
		}
	}

}
