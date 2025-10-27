package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promociones")
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Promocion;

    private LocalDate fecha_Inicio;
    
    private LocalDate fecha_Fin;

    private String descuento_porcentaje;

    private byte[] imagen_referencia;

    private String descripcion;
    
    private String nombre;
    
    @ManyToMany(mappedBy="promociones")
    private List<Producto> productos;

    // Getters y Setters

    public Long getId_Promocion() {
        return id_Promocion;
    }

    public void setId_Promocion(Long id_Promocion) {
        this.id_Promocion = id_Promocion;
    }

    public LocalDate getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(LocalDate fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public LocalDate getFecha_Fin() {
        return fecha_Fin;
    }

    public void setFecha_Fin(LocalDate fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }

    public String getDescuento_porcentaje() {
        return descuento_porcentaje;
    }

    public void setDescuento_porcentaje(String descuento_porcentaje) {
        this.descuento_porcentaje = descuento_porcentaje;
    }

    public byte[] getImagen_referencia() {
        return imagen_referencia;
    }

    public void setImagen_referencia(byte[] imagen_referencia) {
        this.imagen_referencia = imagen_referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    

}
