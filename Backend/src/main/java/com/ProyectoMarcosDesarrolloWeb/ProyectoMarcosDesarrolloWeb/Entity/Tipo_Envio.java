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
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "Tipo_Envio")
public
class Tipo_Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Tipo; 

    private String descripcion;

    private BigDecimal costo_envio;

    @OneToMany(mappedBy = "tipo_envio")
    private List<Compra> compras;


    // --- Getters y Setters  ---

    public Long getId_Tipo() {
        return id_Tipo;
    }

    public void setId_Tipo(Long id_Tipo) {
        this.id_Tipo = id_Tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCosto_envio() {
        return costo_envio;
    }

    public void setCosto_envio(BigDecimal costo_envio) {
        this.costo_envio = costo_envio;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

}
