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
public class TamanioProductoPrecioId implements Serializable{
    
    private Integer id_Tamanio;
    private Integer id_Producto;

    public TamanioProductoPrecioId() {
    }

    public TamanioProductoPrecioId(Integer id_Tamanio, Integer id_Producto) {
        this.id_Tamanio = id_Tamanio;
        this.id_Producto = id_Producto;
    }

    public Integer getId_Tamanio() {
        return id_Tamanio;
    }

    public void setId_Tamanio(Integer id_Tamanio) {
        this.id_Tamanio = id_Tamanio;
    }

    public Integer getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(Integer id_Producto) {
        this.id_Producto = id_Producto;
    }
    
    @Override
    public int hashCode() {
        // Genera el código hash basado en los dos campos de la clave
        return Objects.hash(id_Tamanio, id_Producto); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TamanioProductoPrecioId other = (TamanioProductoPrecioId) obj;
        
        // Compara los dos campos para determinar si dos claves son iguales
        return Objects.equals(this.id_Tamanio, other.id_Tamanio) && 
               Objects.equals(this.id_Producto, other.id_Producto);
    }
    
}
