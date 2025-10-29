package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CompraDTO {
    
    private Long idCompra;
    private String nombreUsuario;
    private String tipoEnvio;
    private String metodoPago;
    private LocalDateTime fechaCompra;
    private BigDecimal precioTotal;
    private List<CarritoItemDTO> items; 

    // Getters y Setters
    public Long getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getTipoEnvio() {
        return tipoEnvio;
    }
    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }
    public String getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }
    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }
    public List<CarritoItemDTO> getItems() {
        return items;
    }
    public void setItems(List<CarritoItemDTO> items) {
        this.items = items;
    }
    

}
