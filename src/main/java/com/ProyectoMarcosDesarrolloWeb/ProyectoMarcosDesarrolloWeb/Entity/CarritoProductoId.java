/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Embeddable;

/**
 *
 * @author danie
 */
@Embeddable
class CarritoProductoId {
    private Long id_Producto;
    private Long id_Carrito;

    public CarritoProductoId() {
    }

    public CarritoProductoId(Long id_Producto, Long id_Carrito) {
        this.id_Producto = id_Producto;
        this.id_Carrito = id_Carrito;
    }

    public Long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(Long id_Producto) {
        this.id_Producto = id_Producto;
    }

    public Long getId_Carrito() {
        return id_Carrito;
    }

    public void setId_Carrito(Long id_Carrito) {
        this.id_Carrito = id_Carrito;
    }
    
    
    
}
