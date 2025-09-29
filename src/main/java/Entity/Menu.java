/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Map;
import java.util.List;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "Menu") 
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_Menu; // Cambia el nombre del ID

    @Column(nullable = false)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @ElementCollection
    @CollectionTable(name = "Menu_Precios", joinColumns = @JoinColumn(name = "menu_id"))
    @MapKeyColumn(name = "tamano")
    @Column(name = "precio")
    private Map<String, BigDecimal> preciosPorTamano;

    @OneToMany(mappedBy = "menu") 
    private List<Extra> extras;

    // Getters y Setters
    public long getId_Menu() {
        return id_Menu;
    }

    public void setId_Menu(long id_Menu) {
        this.id_Menu = id_Menu;
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

    public Map<String, BigDecimal> getPreciosPorTamano() {
        return preciosPorTamano;
    }

    public void setPreciosPorTamano(Map<String, BigDecimal> preciosPorTamano) {
        this.preciosPorTamano = preciosPorTamano;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }
}