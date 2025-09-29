package Service;


import dto.MenuDTO;
import Mapper.MenuMapper;
import Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    public List<MenuDTO> obtenerTodasLasPizzas() {
        return menuMapper.toDtoList(menuRepository.findAll());
    }

    public Optional<MenuDTO> obtenerPizzaPorId(Long id) {
        return menuRepository.findById(id)
                .map(menuMapper::toDto);
    }
}
