package Mapper;

import Entity.Promocion;
import dto.PromocionesDTO;

public class PromocionMapper {

    public static PromocionesDTO toDTO(Promocion entity) {
        PromocionesDTO dto = new PromocionesDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        dto.setCondiciones(entity.getCondiciones());
        return dto;
    }

    public static Promocion toEntity(PromocionesDTO dto) {
        Promocion entity = new Promocion();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setCondiciones(dto.getCondiciones());
        return entity;
    }
}
