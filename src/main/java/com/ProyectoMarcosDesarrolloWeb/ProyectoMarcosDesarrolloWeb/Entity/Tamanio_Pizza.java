/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author danie
 */
@Entity
@Table(name="Tamanio_Pizza")
public class Tamanio_Pizza {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Tamanio;
    
    private String nombre;
    
    @OneToMany(mappedBy="tamanioPizza")
    private List<TamanioProductoPrecio> tamanioProductoPrecio;

    public Integer getId_Tamanio() {
        return id_Tamanio;
    }

    public void setId_Tamanio(Integer id_Tamanio) {
        this.id_Tamanio = id_Tamanio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<TamanioProductoPrecio> getTamanioProductoPrecio() {
        return tamanioProductoPrecio;
    }

    public void setTamanioProductoPrecio(List<TamanioProductoPrecio> tamanioProductoPrecio) {
        this.tamanioProductoPrecio = tamanioProductoPrecio;
    }
    
    
}
