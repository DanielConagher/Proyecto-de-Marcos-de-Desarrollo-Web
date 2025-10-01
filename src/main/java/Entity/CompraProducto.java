package Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Compra_Producto")
public class CompraProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria de la tabla intermedia

    @ManyToOne
    @JoinColumn(name = "id_Compra")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_Producto")
    private Menu producto; 
    
    // Aquí también necesitarías el tamaño y la relación a extras

    private int cantidad;
    
    @Column(name = "precio_venta")
    private BigDecimal precioVenta; // El precio unitario al momento de la venta

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }
    public Menu getProducto() { return producto; }
    public void setProducto(Menu producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }
}