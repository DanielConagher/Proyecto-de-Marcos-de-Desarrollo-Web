/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

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

/**
 *
 * @author danie
 */
@Entity
@Table(name = "Compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "Compra_Producto")
    private long id_Compra;
    
    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="id_Tipo_Envio")
    private Tipo_Envio tipo_envio;
    
    @ManyToOne
    @JoinColumn(name="id_metodo_pago")
    private Metodo_Pago metodo_pago;
    
    private LocalDateTime fecha_compra;
    
    private BigDecimal precio_total;

    public long getId_Compra() {
        return id_Compra;
    }

    public void setId_Compra(long id_Compra) {
        this.id_Compra = id_Compra;
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

    public Metodo_Pago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(Metodo_Pago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public LocalDateTime getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDateTime fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public BigDecimal getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(BigDecimal precio_total) {
        this.precio_total = precio_total;
    }
    
    
    
    
    
    
    
}
