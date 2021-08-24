package co.edu.ucundinamarca.upercth;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import co.edu.ucundinamarca.upercth.model.DataSourceConfig;
import co.edu.ucundinamarca.upercth.web.WebConfig;

@Order(1) // Filters declared at the Dispatcher initializer should be registered first
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String CHARACTER_ENCODING = "UTF-8";


    public WebAppInitializer() {
        super();
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
    	return new Class<?>[] { SecurityConfig.class };
//    	return new Class<?>[] { SecurityConfig.class, WebConfig.class,  DataSourceConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class,  DataSourceConfig.class};
//        return new Class<?>[] { };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);
        return new Filter[] { encodingFilter };
    }
    
    

}
