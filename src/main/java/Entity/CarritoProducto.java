package Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Carrito_Producto")
public class CarritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "id_Carrito")
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_Producto") 
    private Menu producto; 
    

    private int cantidad;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Carrito getCarrito() { return carrito; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }
    public Menu getProducto() { return producto; }
    public void setProducto(Menu producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}