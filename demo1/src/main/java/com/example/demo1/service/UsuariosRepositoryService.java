package com.example.demo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo1.model.Usuario;
import com.example.demo1.repository.UsuariosRepository;
@Service
public class UsuariosRepositoryService {
	
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	public UsuariosRepositoryService(UsuariosRepository usuariosRepository) {
		this.usuariosRepository = usuariosRepository;
	}


	public Usuario findByUsername(String username) {
		return usuariosRepository.findByUsername(username);
	}

}
