package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Hereda de JpaRepository, pasándole la Entidad (Producto) y el tipo de su ID (Long)
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Spring Data JPA crea automáticamente métodos como findAll(), findById(), save(), etc.
}
