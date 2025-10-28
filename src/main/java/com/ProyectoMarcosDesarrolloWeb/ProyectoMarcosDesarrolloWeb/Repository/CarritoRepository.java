package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByUsuarioIdUsuario(Long idUsuario);
}
