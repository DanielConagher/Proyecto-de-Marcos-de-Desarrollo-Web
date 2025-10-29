// Archivo: TamanioProductoPrecioService.java (Refactorizado)
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.TamanioProductoPrecioRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.TamanioProductoPrecioMapper; // ¡Importa el nuevo Mapper!
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.TamanioPrecioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TamanioProductoPrecioService {

    @Autowired
    private TamanioProductoPrecioRepository repository;
    
    // 💡 INYECTAR EL MAPPER DEDICADO
    @Autowired 
    private TamanioProductoPrecioMapper mapper; 

    // Obtener los precios y tamaños para un ID de producto específico
    public List<TamanioPrecioDTO> findPreciosByProductoId(Long idProducto) {
        // 💡 DELEGAR EL MAPEO AL MAPPER
        return repository.findByProductoIdProducto(idProducto).stream()
                .map(mapper::toDTO) // Usa la referencia al método del Mapper
                .toList();
    }
    
    // NOTA: El método 'convertirADTO' YA NO EXISTE aquí
}