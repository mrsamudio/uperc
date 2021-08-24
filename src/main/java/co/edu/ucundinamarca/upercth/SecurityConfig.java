package co.edu.ucundinamarca.upercth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.ucundinamarca.upercth.model.services.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan
//@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
		super();
	}
	
	@Autowired
	private JpaUserDetailsService userDetailsService; 
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
       

        http.authorizeRequests()
        
        .antMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
        .antMatchers("/registro.html").permitAll()
        .antMatchers("/completarReg/**").permitAll()
        .antMatchers("/login.html").permitAll()
        .antMatchers("/test.html").permitAll()
        
//    	TODO: pendiente reemplazar por llamado de roles y perfiles de usuario directamente a la base de datos
//        Todos los roles de usuario
//        .antMatchers("/test.html").hasAnyRole("Docente", "Graduado", "Estudiante", "Administrativo", "Administrador", "Supervisión", "Visitante")
        
//        perfiles de usuario
//        .antMatchers("/test.html").hasAnyRole("Administrador", "Supervisor", "Usuario general", "Visitante")
        
        
        .antMatchers("/roles.html", "/perfiles.html")	.hasAnyRole("Administrador")
        
        .antMatchers("/integracion.html")				.hasAnyRole("Administrador")
        .antMatchers("/nuevoIngreso/**")				.hasAnyAuthority("Administrador", "Administración")
        
        .antMatchers("/supervision.html")				.hasAnyRole("Administrador","Supervisor")
        .antMatchers("/recursos.html")					.hasAnyRole("Administrador","Supervisor")
        .antMatchers("/registrosie.html")					.hasAnyRole("Administrador","Supervisor")
        .antMatchers("/vehiculos.html")					.hasAnyRole("Administrador","Supervisor")
        
        .antMatchers("/informes.html")					.hasAnyRole("Administrador", "Administrativo")
        
        //Actualizaciones de graficos en tablero principal
        .antMatchers("/reservasXsemana.html")					.hasAnyRole("Administrador", "Administrativo")
        .antMatchers("/usuariosRegistrados.html")					.hasAnyRole("Administrador", "Administrativo")
        .antMatchers("/losRegistrosIE.html")					.hasAnyRole("Administrador", "Administrativo", "Supervisor")
        .antMatchers("/lasSupervisiones.html")					.hasAnyRole("Administrador","Supervisor")
        
        
        .antMatchers("/espacios.html")					.hasAnyRole("Administrador", "Administrativo", "Supervisor")
        .antMatchers("/usuarios.html")					.hasAnyRole("Administrador", "Administrativo", "Supervisor")
        
        .antMatchers("/tableroPrincipal.html")			.hasAnyAuthority("Administrador", "Supervisor", "Usuario general", "Visitante")
        
        .antMatchers("/configuracion.html")				.hasAnyRole("Administrador", "Supervisor", "Usuario general", "Visitante")
        
        .antMatchers("/reservas.html")					.hasAnyRole("Administrador", "Supervisor", "Usuario general", "Visitante")
        
        //        .antMatchers("/edit.html")						.hasAnyRole("Administrador", "Supervisor", "Usuario general", "Visitante")
        .antMatchers("/edit.html")						.hasAnyRole("Administrador", "Supervisor", "Administrativo")
        
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login.html")
        .permitAll()
        .and().logout().permitAll()
        
        ;

    }


    @Autowired
	protected void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = passwordEncoder();
		
//		UserBuilder users = User.builder().passwordEncoder(encoder::encode); 
//		
//		auth.inMemoryAuthentication()
//		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
//		.withUser(users.username("mario").password("12345").roles("USER"));
//		
		
//		auth.jdbcAuthentication().dataSource(dataSource)
//			.passwordEncoder(encoder)
//        .usersByUsernameQuery("SELECT u.correo, u.contrasena, u.estado FROM usuario u WHERE u.correo=?")
//        .authoritiesByUsernameQuery("SELECT u.correo, p.nombre from perfilusuario p INNER JOIN rol r ON (p.id = r.perfil) INNER JOIN usuario u ON (r.id = u.rol) WHERE u.correo=?")
//        .passwordEncoder(passwordEncoder().encode("pass"))

		auth.userDetailsService(userDetailsService)
		.passwordEncoder(encoder)
		
		
        ;
		
	}
}