package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "extra")
public class Extra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_extra")
    private Long idExtra;
    
    @Column(name = "nombre_extra")
    private String nombreExtra;
    
    @Column(name = "precio_venta")
    private BigDecimal precioVenta;

    @OneToMany(mappedBy = "extra")
    private List<CompraProducto> compraProductos;

    // Getters y Setters

    public Long getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(Long idExtra) {
        this.idExtra = idExtra;
    }

    public String getNombreExtra() {
        return nombreExtra;
    }

    public void setNombreExtra(String nombreExtra) {
        this.nombreExtra = nombreExtra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public List<CompraProducto> getCompraProductos() {
        return compraProductos;
    }

    public void setCompraProductos(List<CompraProducto> compraProductos) {
        this.compraProductos = compraProductos;
    }
}