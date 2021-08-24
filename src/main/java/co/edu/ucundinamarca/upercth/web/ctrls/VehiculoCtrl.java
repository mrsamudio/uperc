package co.edu.ucundinamarca.upercth.web.ctrls;

import java.util.HashMap;
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

import co.edu.ucundinamarca.upercth.model.dao.VehiculoDAO;
import co.edu.ucundinamarca.upercth.model.entities.Vehiculo;
import co.edu.ucundinamarca.upercth.util.HelpersUperc;

@Controller
public class VehiculoCtrl {

	@Autowired
	private VehiculoDAO vehiculoRepo;

	private HelpersUperc helpersUperc = new HelpersUperc();

	/**
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping("/modulos/vehiculos.html")
	public String getVehiculos(Model model, Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
			model.addAttribute("vehiculos", vehiculoRepo.selectAll());
			model.addAttribute("nuevoVehiculo", new Vehiculo());

			return "modulos/vehiculos";
		}

		return "";

	}

	/**
	 * 
	 * @param id
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(path = "/comun/edit.html", params = { "editarVehiculo" })
	public String editarVehiculo(@RequestParam(value = "editarVehiculo", required = true) final Long id, Model model,
			Authentication auth) {

		if (auth != null) {
			model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
			model.addAttribute("object", vehiculoRepo.selectById(id));

			return "comun/edit";
		}

		return "";
	}

	/**
	 * 
	 * @param vehiculo
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/comun/edit.html", params = { "guardarVehiculo" })
	public String guardarVehiculo(@ModelAttribute("object") Vehiculo vehiculo, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		vehiculoRepo.update(vehiculo);

		return "redirect:/modulos/vehiculos.html";

	}

	/**
	 * 
	 * @param vehiculo
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/modulos/vehiculos.html", params = { "crearNuevoVehiculo" })
	public String crearVehiculo(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculo, BindingResult result, Model model,
			Authentication auth) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<String, String>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			return "test";
		}

		if (vehiculoRepo.selectByPlaca(vehiculo.getPlaca()) == null) {
			vehiculoRepo.insert(vehiculo);
			return "redirect:/modulos/vehiculos.html";
		} else {
			if (auth != null)
				model.addAttribute("rolusuario", helpersUperc.colorNav(auth));
			model.addAttribute("error", "La placa del vehiculo ya se encuentra registrada!");
			return "test";
		}
	}
}
