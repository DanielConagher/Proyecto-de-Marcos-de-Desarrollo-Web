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
public class CompraProductoId {
    
    private Long id_Compra;
    private Long id_Producto;

    public CompraProductoId() {
    }

    public CompraProductoId(Long id_Compra, Long id_Producto) {
        this.id_Compra = id_Compra;
        this.id_Producto = id_Producto;
    }

    public Long getId_Compra() {
        return id_Compra;
    }

    public void setId_Compra(Long id_Compra) {
        this.id_Compra = id_Compra;
    }

    public Long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(Long id_Producto) {
        this.id_Producto = id_Producto;
    }
    
    
}
