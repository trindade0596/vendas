package com.example.demo1.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo1.model.Cliente;
import com.example.demo1.repository.ClienteRepository;

@Service
public class ClienteRepositoryService {
	
	
	private ClienteRepository clienteRepository;
	
	@Autowired
    public ClienteRepositoryService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}


	public List<Cliente> listaClientes(){
        return clienteRepository.findAll();
    }
	

	public Optional<Cliente> listaProdutoUnico(@PathVariable(value="id") long id) {
		return clienteRepository.findById(id);
	}
	

	public Cliente save(@RequestBody Cliente cliente){
		return clienteRepository.save(cliente);
	}
	

	public ResponseEntity<Optional<Cliente>> deleteById(@PathVariable Long id){
		try {
			clienteRepository.deleteById(id);
			return new ResponseEntity<Optional<Cliente>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Cliente>>(HttpStatus.NOT_FOUND);
		}
	}
	

	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente newCliente){
		return clienteRepository.findById(id)
				.map(cliente -> {
					cliente.setCpf(newCliente.getCpf());
					cliente.setNome(newCliente.getNome());
					cliente.setNascimento(newCliente.getNascimento());
					Cliente clienteUpdate = clienteRepository.save(cliente);
					return ResponseEntity.ok().body(clienteUpdate);
				}).orElse(ResponseEntity.notFound().build());
	}
	



}
