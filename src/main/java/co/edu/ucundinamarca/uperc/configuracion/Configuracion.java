package co.edu.ucundinamarca.uperc.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.ucundinamarca.uperc.persistencia.dao.ConfiguracionDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.EspacioParqueoDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.PerfilUsuarioDAO;
import co.edu.ucundinamarca.uperc.persistencia.dao.RolDAO;

@Controller
public class Configuracion {
	
	@Autowired
	private ConfiguracionDAO configuracionrepo;

	@Autowired
	private PerfilUsuarioDAO perfilrepo;
	
	@Autowired
	private RolDAO rolrepo;
	
	@Autowired
	private EspacioParqueoDAO espaciorepo;
	
	@GetMapping(path = "/configuracion")
	public String getConfiguracion(Model model) {
		model.addAttribute("configuraciones", configuracionrepo.selectAll());
		return "modulos/configuracion.html";
		
	}

	@GetMapping(path = "/roles")
	public String getRoles(Model model) {
		model.addAttribute("roles", rolrepo.selectAll());
		
		return "modulos/configuracion/roles.html";
		
	}
	
	@GetMapping(path = "/perfiles")
	public String getPerfiles(Model model) {
		model.addAttribute("perfiles", perfilrepo.selectAll());
		return "modulos/configuracion/perfiles.html";
		
	}
	
	@GetMapping(path = "/espacios")
	public String getEspacios(Model model) {
		model.addAttribute("espacios", espaciorepo.selectAll());
		return "modulos/configuracion/espacios.html";
		
	}
//	<!-- 					<a class="dropdown-item" href="/permisos">Permisos</a> -->
//	<!-- 						<a class="nav-link disabled" href="#">Ubicaciones</a> -->
}
