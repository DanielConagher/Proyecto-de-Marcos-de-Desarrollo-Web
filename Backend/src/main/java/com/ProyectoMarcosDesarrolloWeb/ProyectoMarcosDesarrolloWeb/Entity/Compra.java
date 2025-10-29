/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "Compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Compra;
    
    @OneToMany(mappedBy="compra")
    private List<CompraProducto> compraProductos;
    
    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="id_Tipo_Envio")
    private Tipo_Envio tipo_envio;
    
    @ManyToOne
    @JoinColumn(name="id_metodo_pago")
    private Metodo_Pago metodoPago;
    
    private LocalDateTime fecha_Compra;
    
    private BigDecimal precio_total;

    public long getId_Compra() {
        return id_Compra;
    }

    public void setId_Compra(long id_Compra) {
        this.id_Compra = id_Compra;
    }

    public List<CompraProducto> getCompraProductos() {
        return compraProductos;
    }

    public void setCompraProductos(List<CompraProducto> compraProductos) {
        this.compraProductos = compraProductos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tipo_Envio getTipo_envio() {
        return tipo_envio;
    }

    public void setTipo_envio(Tipo_Envio tipo_envio) {
        this.tipo_envio = tipo_envio;
    }

    public Metodo_Pago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Metodo_Pago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDateTime getFecha_Compra() {
        return fecha_Compra;
    }

    public void setFecha_Compra(LocalDateTime fecha_Compra) {
        this.fecha_Compra = fecha_Compra;
    }

    public BigDecimal getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(BigDecimal precio_total) {
        this.precio_total = precio_total;
    }
}
