package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.math.BigDecimal;

public class ExtraDTO {
    
    private Long idExtra;
    private String nombre;
    private BigDecimal precio;

    // Constructor vacío
    public ExtraDTO() {}

    // Constructor completo
    public ExtraDTO(Long idExtra, String nombre, BigDecimal precio) {
        this.idExtra = idExtra;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(Long idExtra) {
        this.idExtra = idExtra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}