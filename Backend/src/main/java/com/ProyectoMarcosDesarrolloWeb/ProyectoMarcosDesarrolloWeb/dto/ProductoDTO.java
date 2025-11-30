package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductoDTO {
    
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private String imagenBase64;
    private List<PrecioTamanio> precios;

    // Constructor vacío (necesario para la deserialización)
    public ProductoDTO() {}

    // Constructor con campos
    public ProductoDTO(Long idProducto, String nombre, String descripcion, String imagenBase64) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenBase64 = imagenBase64;
    }
    
    // Constructor completo con precios
    public ProductoDTO(Long idProducto, String nombre, String descripcion, String imagenBase64, List<PrecioTamanio> precios) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenBase64 = imagenBase64;
        this.precios = precios;
    }

    // --- Getters y Setters ---

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
    
    public List<PrecioTamanio> getPrecios() {
        return precios;
    }

    public void setPrecios(List<PrecioTamanio> precios) {
        this.precios = precios;
    }
    
    // Clase interna para representar precio por tamaño
    public static class PrecioTamanio {
        private Long idTamanio;
        private String nombreTamanio;
        private BigDecimal precio;
        
        public PrecioTamanio() {}
        
        public PrecioTamanio(Long idTamanio, String nombreTamanio, BigDecimal precio) {
            this.idTamanio = idTamanio;
            this.nombreTamanio = nombreTamanio;
            this.precio = precio;
        }

        public Long getIdTamanio() {
            return idTamanio;
        }

        public void setIdTamanio(Long idTamanio) {
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
}
