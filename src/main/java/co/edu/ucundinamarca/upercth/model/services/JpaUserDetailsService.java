package co.edu.ucundinamarca.upercth.model.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.ucundinamarca.upercth.model.dao.UsuarioDAO;
import co.edu.ucundinamarca.upercth.model.entities.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Autowired
	private UsuarioDAO iUsuarioDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Usuario usuario = iUsuarioDao.selectByCorreo(username);
		if (usuario == null) {
			logger.error("no existe el usuario " + usuario);
			throw new UsernameNotFoundException("No existe el usuario en el sistema: " + usuario);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		authorities.add(new SimpleGrantedAuthority(usuario.getRol().getPerfil().getNombre()));
		
//		for (Rol rol : usuario.getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
//		}
		
		if (authorities.isEmpty()) {
			logger.error("El usuario " + usuario + " no tiene roles asignados");
			throw new UsernameNotFoundException("El usuario " + usuario + " no tiene roles asignados");
		}
		
		return new User(usuario.getCorreo(), usuario.getContrasena(), usuario.isEstado(), true, true, true, authorities);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
