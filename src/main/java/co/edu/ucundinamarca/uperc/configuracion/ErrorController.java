/**
 * 
 */
package co.edu.ucundinamarca.uperc.configuracion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * @author mrsamudio
 *
 */
@ControllerAdvice
public class ErrorController {
	private static Logger logger = LogManager.getLogger(ErrorController.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        logger.error("OCURRIÓ UNA EXCEPCIÓN DURANTE LA EJECUCIÓN DEL SISTEMA!", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Error desconocido");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

}
