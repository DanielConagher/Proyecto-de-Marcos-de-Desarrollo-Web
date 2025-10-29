// Archivo: TamanioProductoPrecioMapper.java
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.TamanioProductoPrecio;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.TamanioPrecioDTO;
import org.springframework.stereotype.Component;

@Component
public class TamanioProductoPrecioMapper {

    // Conversión de Entidad (DB) a DTO (Web/JSON)
    public TamanioPrecioDTO toDTO(TamanioProductoPrecio tpp) {
        if (tpp == null) {
            return null;
        }
        return new TamanioPrecioDTO(
            tpp.getTamanioPizza().getId_Tamanio(), // Usar el Getter correcto
            tpp.getTamanioPizza().getNombre(),
            tpp.getPrecio()
        );
    }
}