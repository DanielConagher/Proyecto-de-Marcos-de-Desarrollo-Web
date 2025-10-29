package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Promocion;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.PromocionesDTO;
import org.springframework.stereotype.Component;

@Component
public class PromocionMapper {

    public PromocionesDTO toDTO(Promocion promocion) {
        PromocionesDTO dto = new PromocionesDTO();
        dto.setIdPromocion(promocion.getIdPromocion());
        dto.setNombre(promocion.getNombre());
        dto.setDescripcion(promocion.getDescripcion());
        dto.setDescuentoPorcentaje(promocion.getDescuentoPorcentaje());
        dto.setFechaInicio(promocion.getFechaInicio());
        dto.setFechaFin(promocion.getFechaFin());
        dto.setImagenReferencia(promocion.getImagenReferencia());
        return dto;
    }

    public Promocion toEntity(PromocionesDTO dto) {
        Promocion promocion = new Promocion();
        promocion.setIdPromocion(dto.getIdPromocion());
        promocion.setNombre(dto.getNombre());
        promocion.setDescripcion(dto.getDescripcion());
        promocion.setDescuentoPorcentaje(dto.getDescuentoPorcentaje());
        promocion.setFechaInicio(dto.getFechaInicio());
        promocion.setFechaFin(dto.getFechaFin());
        promocion.setImagenReferencia(dto.getImagenReferencia());
        return promocion;
    }
}
