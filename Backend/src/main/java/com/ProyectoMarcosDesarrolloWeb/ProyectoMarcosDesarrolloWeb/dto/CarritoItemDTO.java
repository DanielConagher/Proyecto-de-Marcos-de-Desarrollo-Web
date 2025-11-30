package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.math.BigDecimal;
import java.util.List;

public class CarritoItemDTO {
    
    private Long idProducto; 
    private String nombreProducto; 
    private Long idTamanio;
    private String tamano;
    private int cantidad;
    private BigDecimal precioUnitario; 
    private BigDecimal precioTotal;
    private List<Long> idExtras; 
    private String imagenBase64;

    // Getters y Setters
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public Long getIdTamanio() {
        return idTamanio;
    }
    public void setIdTamanio(Long idTamanio) {
        this.idTamanio = idTamanio;
    }
    public String getTamano() {
        return tamano;
    }
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }
    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }
    public List<Long> getIdExtras() {
        return idExtras;
    }
    public void setIdExtras(List<Long> idExtras) {
        this.idExtras = idExtras;
    }
    public String getImagenBase64() {
        return imagenBase64;
    }
    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
}
    