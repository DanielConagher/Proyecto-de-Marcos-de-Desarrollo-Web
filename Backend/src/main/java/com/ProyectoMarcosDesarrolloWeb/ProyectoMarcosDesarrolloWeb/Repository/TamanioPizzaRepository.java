package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Tamanio_Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Usa la Entidad Tamanio_Pizza y su tipo de ID (Integer)
@Repository
public interface TamanioPizzaRepository extends JpaRepository<Tamanio_Pizza, Integer> {
    // Los métodos CRUD básicos ya están disponibles (findAll, findById, etc.)
}
