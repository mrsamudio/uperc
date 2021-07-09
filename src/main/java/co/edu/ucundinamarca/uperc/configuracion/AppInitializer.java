package co.edu.ucundinamarca.uperc.configuracion;

//import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//	public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  implements WebApplicationInitializer {

	@Override
    protected Class<?>[] getRootConfigClasses() {
		//TODO: configure properly
        return new Class[] { ConfigWebMVC.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return new Class[] { ConfigWebMVC.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
