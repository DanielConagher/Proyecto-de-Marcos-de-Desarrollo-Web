/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author danie
 */
@Entity
@Table(name="Producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Producto;
    
    private String nombre;
    
    private byte[] imagen;
    
    private String descripcion;
    
    @OneToMany(mappedBy= "producto")
    private List<CarritoProducto> carritoProductos;
    
    @OneToMany(mappedBy= "producto")
    private List<CompraProducto> compraProductos;
    
    @ManyToMany
    @JoinTable(
        name = "Promocion_Producto", // tabla intermedia
        joinColumns = @JoinColumn(name = "id_Producto"),
        inverseJoinColumns = @JoinColumn(name = "id_Promocion")
    )
    private List<Promocion> promociones;
    
    
    @OneToMany(mappedBy="producto")
    private List<TamanioProductoPrecio> tamanioProductoPrecio;

    public long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(long id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CarritoProducto> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(List<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }

    public List<CompraProducto> getCompraProductos() {
        return compraProductos;
    }

    public void setCompraProductos(List<CompraProducto> compraProductos) {
        this.compraProductos = compraProductos;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public List<TamanioProductoPrecio> getTamanioProductoPrecio() {
        return tamanioProductoPrecio;
    }

    public void setTamanioProductoPrecio(List<TamanioProductoPrecio> tamanioProductoPrecio) {
        this.tamanioProductoPrecio = tamanioProductoPrecio;
    }
    
    

   
    
}
