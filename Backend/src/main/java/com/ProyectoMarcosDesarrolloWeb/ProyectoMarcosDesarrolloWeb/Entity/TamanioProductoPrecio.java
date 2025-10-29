/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 *
 * @author danie
 */
@Entity
@Table(name="Tamanio_Producto_Precio")
public class TamanioProductoPrecio {
    
    @EmbeddedId
    private TamanioProductoPrecioId id;
    
    @ManyToOne
    @MapsId("id_Tamanio")
    @JoinColumn(name="id_Tamanio")
    private Tamanio_Pizza tamanioPizza;
    
    @ManyToOne
    @MapsId("id_Producto")
    @JoinColumn(name="id_Producto")
    private Producto producto;

    public TamanioProductoPrecioId getId() {
        return id;
    }

    private BigDecimal precio;

    public void setId(TamanioProductoPrecioId id) {
        this.id = id;
    }

    public Tamanio_Pizza getTamanioPizza() {
        return tamanioPizza;
    }

    public void setTamanioPizza(Tamanio_Pizza tamanioPizza) {
        this.tamanioPizza = tamanioPizza;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
}
