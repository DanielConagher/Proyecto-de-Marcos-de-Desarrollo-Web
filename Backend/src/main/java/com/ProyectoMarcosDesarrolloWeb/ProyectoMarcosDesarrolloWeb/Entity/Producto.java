/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Producto",schema = "dbo")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Producto")
    private Long idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "descripcion")
    private String descripcion;


    @OneToMany(mappedBy = "producto")
    private List<CarritoProducto> carritoProductos;

    @OneToMany(mappedBy = "producto")
    private List<CompraProducto> compraProductos;

    @ManyToMany
    @JoinTable(
            name = "Promocion_Producto", // Tabla intermedia
            joinColumns = @JoinColumn(name = "id_Producto"),
            inverseJoinColumns = @JoinColumn(name = "id_Promocion")
    )
    private List<Promocion> promociones;

    @OneToMany(mappedBy = "producto")
    private List<TamanioProductoPrecio> tamanioProductoPrecio;


    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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
