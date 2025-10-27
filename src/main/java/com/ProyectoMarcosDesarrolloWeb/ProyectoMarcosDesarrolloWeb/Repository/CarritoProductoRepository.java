package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.CarritoProducto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Carrito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    // Encuentra todos los ítems para un carrito específico
    List<CarritoProducto> findByCarrito(Carrito carrito);
}
