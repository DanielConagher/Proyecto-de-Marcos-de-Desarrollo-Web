/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author danie
 */
@Embeddable
public class CarritoProductoId implements Serializable {
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarritoProductoId that = (CarritoProductoId) o;
        return Objects.equals(id_Producto, that.id_Producto) &&
               Objects.equals(id_Carrito, that.id_Carrito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Producto, id_Carrito);
    }
}
