package com.example.demo1.auth;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo1.model.Usuario;
import com.example.demo1.service.UsuariosRepositoryService;


@Repository
@Service
public class ApplicationUserService implements UserDetailsService {
	

	private UsuariosRepositoryService usuario;
	
	@Autowired
	public ApplicationUserService(UsuariosRepositoryService usuario) {
		this.usuario = usuario;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Usuario user = this.usuario.findByUsername(username);
		ApplicationUser applicationUser = new ApplicationUser(user);
		
		return applicationUser;
	}
	
	
	

}
