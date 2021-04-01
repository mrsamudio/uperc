package co.edu.ucundinamarca.uperc.usuarios;

import java.sql.Timestamp;
import java.time.Instant;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.RolDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.UsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.entidades.Usuario;

@Controller
@Component
public class UsuariosControlador {

	@Autowired
	private UsuarioDAO usuariorepo;
	
	@Autowired
	private RolDAO rolrepo;

	@GetMapping(path = "/usuarios")
	public String getUsuarios(Model model) {

		model.addAttribute("usuarios", usuariorepo.selectAll());
		model.addAttribute("nuevoUsuario", new Usuario());
		model.addAttribute("roles", rolrepo.selectAll());
		return "modulos/usuarios.html";
	}

	@PostMapping(path ="/usuarios", params={"crearNuevoUsuario"})
//	@Transactional
    public String crearUsuario(Model model,@ModelAttribute Usuario usuario, BindingResult bindingResult) {
		
		boolean insercionOk = false;
		
		if (bindingResult.hasErrors()) {
//			model.addAttribute("errortitulo", "Error");
//			model.addAttribute("errormensaje", "Error al realizar la petición");
//			model.addAttribute("usuario", new Usuario());
			return "";
		}
		
		
		// agregar usuario
		if (usuarioOk(usuario)) {
			usuario.setFechaReg(Timestamp.from(Instant.now()));
			insercionOk = usuariorepo.insert(usuario);
			model.addAttribute("respuestaInsercion", insercionOk);
			return "redirect:/usuarios";
		}else
			return "";
    }

	/**
	 * 
	 * @param usuario
	 * @return true si el usuario tiene los campo completos, false en caso contrario
	 */
	public Boolean usuarioOk(Usuario usuario) {

		if (usuario.getRol() != null
//				&& usuario.isEstado() 
//				&& usuario.getFechaReg() != null 
				&& usuario.getFechaNac() != null 
				&& usuario.getCorreo() != null
				&& usuario.getContrasena() != null 
				&& usuario.getNumId() != null
				&& usuario.getTipoId() != '\0'
				&& usuario.getApellidos() != null
				&& usuario.getNombres() != null

		) {
			return true;
		} else
			return false;
	}

}
