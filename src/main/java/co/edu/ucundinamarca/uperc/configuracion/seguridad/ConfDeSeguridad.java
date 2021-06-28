/**
 * 
 */
package co.edu.ucundinamarca.uperc.configuracion.seguridad;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mrsamudio
 *
 */
@Configuration
@EnableWebSecurity
public class ConfDeSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
    DataSource dataSource;
	    

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
		.withUser("user").password(passwordEncoder.encode("user123")).roles("USER")
		.and()
		.withUser("admin").password(passwordEncoder.encode("admin123")).roles("USER", "ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Autowired
	    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	        
	      auth.jdbcAuthentication().dataSource(dataSource)
	        .usersByUsernameQuery(
	            "select username,password, enabled from users where username=?")
	        .authoritiesByUsernameQuery(
	            "select username, role from user_roles where username=?");
	    }	
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	      http.authorizeRequests()
	        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
	        .and()
	          .formLogin().loginPage("/login").failureUrl("/login-error")
	          .usernameParameter("username").passwordParameter("password")
	        .and()
	          .logout().logoutSuccessUrl("/logout")
	        .and()
	          .exceptionHandling().accessDeniedPage("/403")
	        .and()
	          .csrf();
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

}
