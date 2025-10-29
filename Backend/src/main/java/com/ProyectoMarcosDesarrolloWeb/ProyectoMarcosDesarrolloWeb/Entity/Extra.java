package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import static java.awt.SystemColor.menu;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Extra")
public class Extra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Extra;
    
    private String nombre_extra;
    private BigDecimal precio_venta;

    @OneToMany(mappedBy="extra")
    private List<CompraProducto> compraProducto;

    // Getters y Setters

    public long getId_Extra() {
        return id_Extra;
    }

    public void setId_Extra(long id_Extra) {
        this.id_Extra = id_Extra;
    }

    public String getNombre_extra() {
        return nombre_extra;
    }

    public void setNombre_extra(String nombre_extra) {
        this.nombre_extra = nombre_extra;
    }

    public BigDecimal getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(BigDecimal precio_venta) {
        this.precio_venta = precio_venta;
    }

    public List<CompraProducto> getCompraProducto() {
        return compraProducto;
    }

    public void setCompraProducto(List<CompraProducto> compraProducto) {
        this.compraProducto = compraProducto;
    }
    
}