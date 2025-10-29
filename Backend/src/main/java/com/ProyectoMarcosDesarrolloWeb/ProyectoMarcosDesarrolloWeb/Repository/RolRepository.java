package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findBynombreRol(String nombre_rol);
}
