package co.edu.ucundinamarca.upercth;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2) // Filters declared at the Dispatcher initializer should be registered first
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebAppInitializer() {
		super();
	}

}
