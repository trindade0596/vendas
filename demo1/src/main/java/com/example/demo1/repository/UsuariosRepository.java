package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Usuario;



@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String username);

}

