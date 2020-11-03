package com.example.demo1;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo1.model.Usuario;
import com.example.demo1.repository.UsuariosRepository;


@SpringBootApplication
public class Demo1Application {
	
	private static PasswordEncoder pass;
	private static UsuariosRepository usuario;
	
	
	@Autowired
	public Demo1Application( PasswordEncoder pass, UsuariosRepository usuario) {
		this.pass = pass;
		this.usuario = usuario;
	
	}



	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		
		Usuario convidado = new Usuario("convidado",pass.encode("user"),1,"ROLE_USER");
		Usuario admin = new Usuario("lucas",pass.encode("admin"),1,"ROLE_ADMIN");
		List<Usuario> usuarios = Arrays.asList(convidado,admin);
		usuario.saveAll(usuarios);
		
		System.out.println(new BCryptPasswordEncoder().encode("user"));
		
	
	}

}
