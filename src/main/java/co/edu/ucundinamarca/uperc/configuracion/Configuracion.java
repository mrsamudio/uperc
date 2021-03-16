package co.edu.ucundinamarca.uperc.configuracion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Configuracion {

	@GetMapping(path = "/configuracion")
	public String getUsuarios(Model model) {
		return "modulos/configuracion.html";
		
	}

	@GetMapping(path = "/roles")
	public String getRoles(Model model) {
		return "modulos/configuracion/roles.html";
		
	}
	
	@GetMapping(path = "/perfiles")
	public String getPerfiles(Model model) {
		return "modulos/configuracion/perfiles.html";
		
	}
	
	@GetMapping(path = "/espacios")
	public String getEspacios(Model model) {
		return "modulos/configuracion/espacios.html";
		
	}
//	<!-- 					<a class="dropdown-item" href="/permisos">Permisos</a> -->
//	<!-- 						<a class="nav-link disabled" href="#">Ubicaciones</a> -->
}
