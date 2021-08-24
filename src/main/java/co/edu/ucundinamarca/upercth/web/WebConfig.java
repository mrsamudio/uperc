package co.edu.ucundinamarca.upercth.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration

// scheduling
@EnableScheduling

@EnableWebMvc
//@ComponentScan
//@ComponentScan("co.edu.ucundinamarca.upercth")
@EnableAspectJAutoProxy()
@ComponentScan(
		{
			"co.edu.ucundinamarca.upercth.web"
//			"co.edu.ucundinamarca.upercth"
//, "co.edu.ucundinamarca.upercth.aspect"
////, "co.edu.ucundinamarca.upercth.model.dao"
////, "co.edu.ucundinamarca.upercth.model.services"

		})
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
//public class WebConfig implements ApplicationContextAware {

	private ApplicationContext applicationContext;

//	public WebConfig() {
//		super();
//	}
	

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


//    @Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

    /**
     *  Message externalization/internationalization
     */
//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setBasename("Messages");
//        return resourceBundleMessageSource;
//    }
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("WEB-INF/i18n/messages");
//        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setDefaultEncoding(UTF8_ENCODING);
//        messageSource.setFallbackToSystemLocale(false);
//        messageSource.setCacheSeconds((int)TimeUnit.HOURS.toSeconds(1));
//        return messageSource;
//    }

    /* **************************************************************** */
    /*  THYMELEAF-SPECIFIC ARTIFACTS                                    */
    /*  TemplateResolver <- TemplateEngine <- ViewResolver              */
    /* **************************************************************** */

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true); // Compiled SpringEL should speed up executions
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    /* ******************************************************************* */
    /*  Defines callback methods to customize the Java-based configuration */
    /*  for Spring MVC enabled via {@code @EnableWebMvc}                   */
    /* ******************************************************************* */

    /**
     *  Dispatcher configuration for serving static resources
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

}
