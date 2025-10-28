package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CarritoItemDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    private Long getCurrentUserId() { return 1L; } 

    // ENDPOINT: GET /api/carrito
    @GetMapping
    public List<CarritoItemDTO> getCarrito() {
        return carritoService.obtenerCarritoPorUsuario(getCurrentUserId());
    }

    // ENDPOINT: POST /api/carrito/agregar
    @PostMapping("/agregar")
    public ResponseEntity<Void> agregarAlCarrito(@RequestBody CarritoItemDTO item) {
        carritoService.agregarItem(getCurrentUserId(), item);
        return ResponseEntity.ok().build(); 
    }

    // ENDPOINT: DELETE /api/carrito/{idItem}
    @DeleteMapping("/{idItem}")
    public ResponseEntity<Void> eliminarDelCarrito(@PathVariable Long idItem) {
        carritoService.eliminarItem(idItem);
        return ResponseEntity.ok().build(); 
    }
}
