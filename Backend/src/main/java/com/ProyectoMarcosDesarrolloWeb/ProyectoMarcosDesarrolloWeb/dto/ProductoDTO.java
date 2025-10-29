package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

public class ProductoDTO {
    
    private Long idProducto;
    private String nombre;
    private String descripcion;
    // La imagen se envía como String Base64 para el frontend
    private String imagenBase64; 

    // Constructor vacío (necesario para la deserialización)
    public ProductoDTO() {}

    // Constructor con campos
    public ProductoDTO(Long idProducto, String nombre, String descripcion, String imagenBase64) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenBase64 = imagenBase64;
    }

    // --- Getters y Setters ---
    // (Asegúrate de generarlos todos)

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
}
