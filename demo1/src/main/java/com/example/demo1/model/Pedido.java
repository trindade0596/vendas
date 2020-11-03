package com.example.demo1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cliente;
    private float totalCompra;
    private Date dataCompra;
    private String produtos;

    public Pedido(String cliente, float totalCompra, Date dataCompra, String produtos) {
        
        this.cliente = cliente;
        this.totalCompra = totalCompra;
        this.dataCompra = dataCompra;
        this.produtos = produtos;
    }
    
    @ManyToOne
    private Cliente clientes;
    
    @OneToMany(mappedBy = "pedido")
    private List<Produto> produto; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCliente() {
        return cliente;
    }

    public void setIdCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(float totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getIdProdutos() {
        return produtos;
    }

    public void setIdProdutos(String produtos) {
        this.produtos = produtos;
    }
}
