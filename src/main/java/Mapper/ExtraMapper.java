package Mapper;

import dto.ExtraDTO;
import Entity.Extra;
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
