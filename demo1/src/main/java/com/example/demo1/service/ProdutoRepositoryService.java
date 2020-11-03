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

import com.example.demo1.model.Produto;
import com.example.demo1.repository.ProdutoRepository;

@Service
public class ProdutoRepositoryService {
	
	private ProdutoRepository produtoRepository;
	
	@Autowired
    public ProdutoRepositoryService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}


	public List<Produto> listaProdutos(){
        return produtoRepository.findAll();
    }
	
	
	public Optional<Produto> listaPedidoUnico(@PathVariable(value="id") long id) {
		return produtoRepository.findById(id);
	}
	

	public Produto save(@RequestBody Produto produto){
		return produtoRepository.save(produto);
	}
	

	public ResponseEntity<Optional<Produto>> deleteById(@PathVariable Long id){
		try {
			produtoRepository.deleteById(id);
			return new ResponseEntity<Optional<Produto>>(HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Produto>>(HttpStatus.NOT_FOUND);
		}
	}
	

	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto newProduto){
		return produtoRepository.findById(id)
				.map(produto -> {
					produto.setNome(newProduto.getNome());
					produto.setDescricao(newProduto.getDescricao());
					produto.setSku(newProduto.getSku());
					produto.setPreco(newProduto.getPreco());
					produto.setQuantidade(newProduto.getQuantidade());
					Produto produtoUpdate = produtoRepository.save(produto);
					return ResponseEntity.ok().body(produtoUpdate);
				}).orElse(ResponseEntity.notFound().build());
	}
	




}
