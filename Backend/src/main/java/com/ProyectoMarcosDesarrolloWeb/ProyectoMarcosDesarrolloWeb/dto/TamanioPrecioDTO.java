package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.math.BigDecimal; // Usa BigDecimal o Double según tu tipo de dato

public class TamanioPrecioDTO {

    private Integer idTamanio;
    private String nombreTamanio; // Viene de Tamanio_Pizza.nombre
    private BigDecimal precio; 

    // Constructor vacío (necesario)
    public TamanioPrecioDTO() {}

    public TamanioPrecioDTO(Integer idTamanio, String nombreTamanio, BigDecimal precio) {
        this.idTamanio = idTamanio;
        this.nombreTamanio = nombreTamanio;
        this.precio = precio;
    }

    public Integer getIdTamanio() {
        return idTamanio;
    }
    public void setIdTamanio(Integer idTamanio) {
        this.idTamanio = idTamanio;
    }
    public String getNombreTamanio() {
        return nombreTamanio;
    }
    public void setNombreTamanio(String nombreTamanio) {
        this.nombreTamanio = nombreTamanio;
    }
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
