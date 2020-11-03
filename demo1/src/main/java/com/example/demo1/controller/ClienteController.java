package com.example.demo1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.Cliente;
import com.example.demo1.service.ClienteRepositoryService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(path ="/cliente")
public class ClienteController {


private ClienteRepositoryService clienteRepositoryService;

	@Autowired
	public ClienteController(ClienteRepositoryService clienteRepositoryService) {
	this.clienteRepositoryService = clienteRepositoryService;
}

	@GetMapping(path = "/clientes")
    public List<Cliente> listaClientes(){
        return clienteRepositoryService.listaClientes();
    }
	
	@GetMapping("/{id}")
	public Optional<Cliente> listaProdutoUnico(@PathVariable(value="id") long id) {
		return clienteRepositoryService.listaProdutoUnico(id);
	}
	
	@PostMapping(path = "enviar")
	public Cliente save(@RequestBody Cliente cliente){
		return clienteRepositoryService.save(cliente);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<Cliente>> deleteById(@PathVariable Long id){
		return clienteRepositoryService.deleteById(id);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente newCliente){
		return clienteRepositoryService.update(id, newCliente);
	}
	


}

