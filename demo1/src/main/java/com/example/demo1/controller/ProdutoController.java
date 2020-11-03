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

import com.example.demo1.model.Produto;
import com.example.demo1.service.ProdutoRepositoryService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(path = "/produto")
public class ProdutoController {



	private ProdutoRepositoryService produtoRepositoryService;

	@Autowired
	public ProdutoController(ProdutoRepositoryService produtoRepositoryService) {
	this.produtoRepositoryService = produtoRepositoryService;
}

	@GetMapping(path = "/produtos")
    public List<Produto> listaProdutos(){
        return produtoRepositoryService.listaProdutos();
    }
	
	@GetMapping("/{id}")
	public Optional<Produto> listaPedidoUnico(@PathVariable(value="id") long id) {
		return produtoRepositoryService.listaPedidoUnico(id);
	}
	
	@PostMapping(name = "/enviar")
	public Produto save(@RequestBody Produto produto){
		return produtoRepositoryService.save(produto);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<Produto>> deleteById(@PathVariable Long id){
		return produtoRepositoryService.deleteById(id);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto newProduto){
		return produtoRepositoryService.update(id, newProduto);
	}
	


}