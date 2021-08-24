package co.edu.ucundinamarca.upercth.util;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import co.edu.ucundinamarca.upercth.model.entities.Usuario;

public class HelpersUperc {
	
	private Collection<SimpleGrantedAuthority> rolesok;


	public HelpersUperc() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Cambio del color en la barra de navegación dependiendo del perfil de usuario
	 * 
	 * @param auth
	 * @return las clases bootstrap css en string
	 */
	public String colorNav(Authentication auth) {

		String perfil = auth.getAuthorities().stream().reduce((prev, next) -> next).get().getAuthority();

		String coloresnav = "";
		switch (perfil) {

		case "Usuario general":
			coloresnav = "navbar-dark bg-info ";
			break;
		case "Supervisor":
			coloresnav = "navbar-dark bg-success ";
			break;
		case "Visitante":
			coloresnav = "navbar-light bg-light ";
			break;
		case "Administrador":
			coloresnav = "navbar-dark bg-dark";
			break;

		default:
			coloresnav = "navbar-dark bg-danger ";
			break;
		}

		return coloresnav;

	}


	/**
	 * Comprueba si el usuario es ok
	 * 
	 * @param usuario
	 * @return true si el usuario tiene los campo completos, false en caso contrario
	 */
	public Boolean usuarioOk(Usuario usuario) {

		if (usuario.getRol() != null
//				&& usuario.isEstado() 
//				&& usuario.getFechaReg() != null 
				&& usuario.getFechaNac() != null && usuario.getCorreo() != null && usuario.getContrasena() != null
				&& usuario.getNumId() != null && usuario.getTipoId() != '\0' && usuario.getApellidos() != null
				&& usuario.getNombres() != null

		) {
			return true;
		} else
			return false;
	}

	
	
	/**
	 * @return the rolesok
	 */
	public Collection<SimpleGrantedAuthority> getRolesok() {
		
		rolesok = new ArrayList<SimpleGrantedAuthority>();
		
		rolesok.add(new SimpleGrantedAuthority("Administrador"));
		rolesok.add(new SimpleGrantedAuthority("Administrativo"));
//		rolesok.add(new SimpleGrantedAuthority("Supervisor"));
		
		return rolesok;
	}


}
