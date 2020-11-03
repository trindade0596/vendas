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

import com.example.demo1.model.Pedido;
import com.example.demo1.service.PedidoRepositoryService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "pedido")
public class PedidoController {


private PedidoRepositoryService pedidoRepositoryService;

@Autowired
public PedidoController(PedidoRepositoryService pedidoRepositoryService) {
	this.pedidoRepositoryService = pedidoRepositoryService;
}



	@GetMapping(path = "/pedidos")
    public List<Pedido> listaPedidos(){
        return pedidoRepositoryService.listaPedidos();
    }
	
	@GetMapping("/{id}")
	public Optional<Pedido> listaPedidoUnico(@PathVariable(value="id") long id) {
		return pedidoRepositoryService.listaPedidoUnico(id);
	}
	
	@PostMapping(path = "/enviar")
	public Pedido save(@RequestBody Pedido pedido){
		return pedidoRepositoryService.save(pedido);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<Pedido>> deleteById(@PathVariable Long id){
		return pedidoRepositoryService.deleteById(id);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido newPedido){
		return pedidoRepositoryService.update(id, newPedido);
	}

	
	


}
