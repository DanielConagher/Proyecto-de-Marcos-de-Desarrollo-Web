package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

public class TamanioPizzaDTO {

    private Integer idTamanio;
    private String nombre;

    // Constructor vacío y con campos (necesarios)
    public TamanioPizzaDTO() {}

    public TamanioPizzaDTO(Integer idTamanio, String nombre) {
        this.idTamanio = idTamanio;
        this.nombre = nombre;
    }

    // --- Getters y Setters ---

    public Integer getIdTamanio() {
        return idTamanio;
    }

    public void setIdTamanio(Integer idTamanio) {
        this.idTamanio = idTamanio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}