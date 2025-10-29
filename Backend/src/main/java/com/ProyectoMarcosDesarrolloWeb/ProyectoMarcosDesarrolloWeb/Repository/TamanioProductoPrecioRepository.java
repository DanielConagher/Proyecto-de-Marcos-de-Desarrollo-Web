package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.TamanioProductoPrecio;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.TamanioProductoPrecioId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Importante para devolver una lista

public interface TamanioProductoPrecioRepository 
    extends JpaRepository<TamanioProductoPrecio, TamanioProductoPrecioId> { 

    // 💡 ¡Añade esta línea! Spring Data JPA genera la lógica SQL por ti.
    List<TamanioProductoPrecio> findByProductoIdProducto(Long idProducto);

}