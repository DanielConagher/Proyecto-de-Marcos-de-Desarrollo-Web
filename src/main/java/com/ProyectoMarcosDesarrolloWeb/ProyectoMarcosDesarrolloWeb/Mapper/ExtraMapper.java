package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ExtraDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Extra;
import org.springframework.stereotype.Component;

@Component
public class ExtraMapper {

    public ExtraDTO toDto(Extra extra) {
        if (extra == null) {
            return null;
        }
        ExtraDTO dto = new ExtraDTO();
        dto.setNombre(extra.getNombre());
        dto.setPrecio(extra.getPrecio());
        return dto;
    }
}
