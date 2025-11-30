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

    // TODO: Implementar autenticación real para obtener el ID del usuario
    private Long getCurrentUserId() { return 1L; } 

    // ENDPOINT: GET /api/carrito
    // Obtiene todos los items del carrito del usuario actual
    @GetMapping
    public ResponseEntity<List<CarritoItemDTO>> getCarrito() {
        List<CarritoItemDTO> items = carritoService.obtenerCarritoPorUsuario(getCurrentUserId());
        return ResponseEntity.ok(items);
    }

    // ENDPOINT: POST /api/carrito/agregar
    // Agrega un item al carrito
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarAlCarrito(@RequestBody CarritoItemDTO item) {
        try {
            carritoService.agregarItem(getCurrentUserId(), item);
            return ResponseEntity.ok("Producto agregado al carrito correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar producto: " + e.getMessage());
        }
    }

    // ENDPOINT: POST /api/carrito/agregar-multiples
    // Agrega múltiples items al carrito (para sincronización desde localStorage)
    @PostMapping("/agregar-multiples")
    public ResponseEntity<String> agregarMultiplesAlCarrito(@RequestBody List<CarritoItemDTO> items) {
        try {
            for (CarritoItemDTO item : items) {
                carritoService.agregarItem(getCurrentUserId(), item);
            }
            return ResponseEntity.ok("Productos agregados al carrito correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar productos: " + e.getMessage());
        }
    }

    // ENDPOINT: PUT /api/carrito/{idItem}/cantidad
    // Actualiza la cantidad de un item en el carrito
    @PutMapping("/{idItem}/cantidad")
    public ResponseEntity<String> actualizarCantidad(
            @PathVariable Long idItem, 
            @RequestParam int cantidad) {
        try {
            carritoService.actualizarCantidad(idItem, cantidad);
            return ResponseEntity.ok("Cantidad actualizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar cantidad: " + e.getMessage());
        }
    }

    // ENDPOINT: DELETE /api/carrito/{idItem}
    // Elimina un item del carrito
    @DeleteMapping("/{idItem}")
    public ResponseEntity<String> eliminarDelCarrito(@PathVariable Long idItem) {
        try {
            carritoService.eliminarItem(idItem);
            return ResponseEntity.ok("Producto eliminado del carrito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar producto: " + e.getMessage());
        }
    }

    // ENDPOINT: DELETE /api/carrito/vaciar
    // Vacía completamente el carrito del usuario
    @DeleteMapping("/vaciar")
    public ResponseEntity<String> vaciarCarrito() {
        try {
            carritoService.vaciarCarrito(getCurrentUserId());
            return ResponseEntity.ok("Carrito vaciado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al vaciar carrito: " + e.getMessage());
        }
    }
}
