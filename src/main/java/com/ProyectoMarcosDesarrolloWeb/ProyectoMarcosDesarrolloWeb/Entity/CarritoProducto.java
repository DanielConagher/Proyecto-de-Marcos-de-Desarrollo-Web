package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Carrito_Producto")
public class CarritoProducto {
    
    @EmbeddedId
    private CarritoProductoId id;  //Clave compuesta
    
    @ManyToOne
    @MapsId("id_Carrito")
    @JoinColumn(name = "id_Carrito")
    private Carrito carrito;

    
    @ManyToOne
    @MapsId("id_Producto")
    @JoinColumn(name = "id_Producto") 
    private Producto producto; 
    

    private int cantidad;

    // Getters y Setters

    public CarritoProductoId getId() {
        return id;
    }

    public void setId(CarritoProductoId id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}