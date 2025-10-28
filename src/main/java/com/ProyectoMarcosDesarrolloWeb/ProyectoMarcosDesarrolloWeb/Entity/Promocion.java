package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Promocion")
@Data
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Promocion")
    private Long idPromocion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "descuento_porcentaje")
    private Double descuentoPorcentaje;

    @Column(name = "imagen_referencia")
    private String imagenReferencia;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "nombre")
    private String nombre;


    @ManyToMany
    @JoinTable(
            name = "Promocion_Producto",
            joinColumns = @JoinColumn(name = "id_Promocion"),
            inverseJoinColumns = @JoinColumn(name = "id_Producto")
    )
    private Set<Producto> productos = new HashSet<>();
}
