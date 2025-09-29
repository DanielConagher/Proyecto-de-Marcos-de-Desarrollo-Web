package Controller;

import Service.MenuService;
import dto.MenuDTO; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuDTO> getPizzas() { 
        return menuService.obtenerTodasLasPizzas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getPizzaById(@PathVariable Long id) {
        return menuService.obtenerPizzaPorId(id)
                .map(pizzaDTO -> ResponseEntity.ok(pizzaDTO)) 
                .orElse(ResponseEntity.notFound().build()); 
    }
}
