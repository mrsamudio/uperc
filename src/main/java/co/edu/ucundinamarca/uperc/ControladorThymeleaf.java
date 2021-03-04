/**
 * 
 */
package co.edu.ucundinamarca.uperc;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author mrsamudio
 *
 */
@Controller
public class ControladorThymeleaf {

	 @RequestMapping(value = "/html", method = RequestMethod.GET)
	    public String getExampleHTML(Model model) {
	        model.addAttribute("nombreUsuario", "Inicio UPERC");
	        model.addAttribute("descripcion", "Configuración inicial de Thymeleaf");
	        return "inicio.html";
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
