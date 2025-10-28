package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.time.LocalDate;

public class PromocionesDTO {
    private Long idPromocion;
    private String nombre;
    private String descripcion;
    private Double descuentoPorcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String imagenReferencia;

    public Long getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Long idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(Double descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getImagenReferencia() {
        return imagenReferencia;
    }

    public void setImagenReferencia(String imagenReferencia) {
        this.imagenReferencia = imagenReferencia;
    }
}
