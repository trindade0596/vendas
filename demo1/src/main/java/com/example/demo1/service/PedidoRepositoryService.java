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

import com.example.demo1.model.Pedido;
import com.example.demo1.repository.PedidoRepository;

@Service
public class PedidoRepositoryService {
	
	private PedidoRepository pedidoRepository;
	
	@Autowired
	public PedidoRepositoryService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}



	    public List<Pedido> listaPedidos(){
	        return pedidoRepository.findAll();
	    }
		
		
		public Optional<Pedido> listaPedidoUnico(@PathVariable(value="id") long id) {
			return pedidoRepository.findById(id);
		}
		
		
		public Pedido save(@RequestBody Pedido pedido){
			return pedidoRepository.save(pedido);
		}
		
		
		public ResponseEntity<Optional<Pedido>> deleteById(@PathVariable Long id){
			try {
				pedidoRepository.deleteById(id);
				return new ResponseEntity<Optional<Pedido>>(HttpStatus.OK);
			}catch (NoSuchElementException nsee) {
				return new ResponseEntity<Optional<Pedido>>(HttpStatus.NOT_FOUND);
			}
		}
		
		
		public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido newPedido){
			return pedidoRepository.findById(id)
					.map(pedido -> {
						pedido.setIdCliente(newPedido.getIdCliente());
						pedido.setDataCompra(newPedido.getDataCompra());
						pedido.setTotalCompra(newPedido.getTotalCompra());
						pedido.setIdProdutos(newPedido.getIdProdutos());
						Pedido pedidoUpdate = pedidoRepository.save(pedido);
						return ResponseEntity.ok().body(pedidoUpdate);
					}).orElse(ResponseEntity.notFound().build());
		}

		
		



}
