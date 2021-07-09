/**
 * 
 */
package co.edu.ucundinamarca.uperc.configuracion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author mrsamudio
 *
 */
@Configuration
@EnableWebSecurity
public class ConfDeSeguridad extends WebSecurityConfigurerAdapter {

//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	@Autowired
    DataSource dataSource;
	    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder(encoder::encode); 

		auth.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
		.withUser(users.username("mario").password("12345").roles("USER"));
	}
//	
//	@Autowired
//	protected void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		
//		PasswordEncoder encoder = passwordEncoder();
//		UserBuilder users = User.builder().passwordEncoder(encoder::encode); 
//		
//		auth.inMemoryAuthentication()
//		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
//		.withUser(users.username("mario").password("12345").roles("USER"));
//	}

	
//	@Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        
//      auth.jdbcAuthentication().dataSource(dataSource)
//      
//        .usersByUsernameQuery(
//			"SELECT correo, contrasena, estado FROM usuario WHERE correo=?")
//        .authoritiesByUsernameQuery(
//    		"SELECT usuario.correo, rol.nombre FROM rol INNER JOIN usuario ON (rol.id = usuario.rol) WHERE usuario.correo=?")
////        .passwordEncoder(passwordEncoder().encode("pass"))
//        ;
//    }	
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http.authorizeRequests()
//        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//        .and()
//          .formLogin().loginPage("/login").failureUrl("/login-error")
//          .usernameParameter("username").passwordParameter("password")
//        .and()
//          .logout().logoutSuccessUrl("/logout")
//        .and()
//          .exceptionHandling().accessDeniedPage("/403")
//        .and()
//          .csrf();
      
      .antMatchers("/", "/css/**", "/js/**", "/img/**", "/txt/**", "/registro", "/informes").permitAll()
      .antMatchers("/tableroPrincipal").hasAnyRole("USER")
      .antMatchers("/configuracion", "/roles", "/perfiles", "/espacios").hasAnyRole("ADMIN")
//      .antMatchers("/informes").hasAnyRole("ADMIN")
//      .antMatchers("/informes").permitAll()
      .antMatchers("/integracion").hasAnyRole("ADMIN")
      .antMatchers("/recursos").hasAnyRole("ADMIN")
      .antMatchers("/reservas").hasAnyRole("USER")
      .antMatchers("/supervision").hasAnyRole("USER")
      .antMatchers("/usuarios").hasAnyRole("ADMIN")
      .anyRequest().authenticated()
      .and().formLogin();
    }

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/", "/welcome", "/login").permitAll()
//		.antMatchers("/authenticated/**").hasAnyRole("ADMIN", "USER")
//		.and().formLogin()
//		.and().logout().logoutSuccessUrl("/welcome").permitAll()
//		.and().csrf().disable();
//	}
	
//	@Override
//	protected void configure(final HttpSecurity http) throws Exception {
//	    http.formLogin()
//	        .loginPage("/login.html")
//	        .failureUrl("/login-error.html")
//	      .and()
//	        .logout()
//	        .logoutSuccessUrl("/tableroPrincipal.html");
//	}

    
    /* CONFIGURACION INTENTO UNO*/
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }
}
