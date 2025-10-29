package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Compra_Producto")
public class CompraProducto {

    @EmbeddedId
    private CompraProductoId id;

    @ManyToOne
    @MapsId("id_Compra")
    @JoinColumn(name = "id_Compra")
    private Compra compra;

    @ManyToOne
    @MapsId("id_Producto")
    @JoinColumn(name = "id_Producto")
    private Producto producto; 
    
    // Aquí también necesitarías el tamaño y la relación a extras

    private int cantidad;
    
    private BigDecimal precio_venta; // El precio unitario al momento de la venta
    
    @ManyToOne
    @JoinColumn(name="id_Extra")
    private Extra extra;
    
    

    // Getters y Setters

    public CompraProductoId getId() {
        return id;
    }

    public void setId(CompraProductoId id) {
        this.id = id;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(BigDecimal precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }
    
    
    
}