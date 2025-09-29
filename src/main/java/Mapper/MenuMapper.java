package Mapper;

import dto.MenuDTO;
import Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuMapper {

    @Autowired
    private ExtraMapper extraMapper; // Inyección de la dependencia

    public MenuDTO toDto(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuDTO dto = new MenuDTO();
        dto.setNombre(menu.getNombre());
        dto.setDescripcion(menu.getDescripcion());
        dto.setPreciosPorTamano(menu.getPreciosPorTamano());
        
        dto.setExtras(menu.getExtras().stream()
                .map(extraMapper::toDto)
                .collect(Collectors.toList()));

        return dto;
    }

    public List<MenuDTO> toDtoList(List<Menu> menus) {
        return menus.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
