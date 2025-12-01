package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.PromocionService;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.PromocionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public List<PromocionesDTO> listar() {
        return promocionService.listarPromociones();
    }

    @GetMapping("/{id}")
    public PromocionesDTO obtenerPorId(@PathVariable Long id) {
        return promocionService.obtenerPorId(id);
    }

    @PostMapping
    public PromocionesDTO crear(@RequestBody PromocionesDTO dto) {
        return promocionService.guardar(dto);
    }

    @PutMapping("/{id}")
    public PromocionesDTO actualizar(@PathVariable Long id, @RequestBody PromocionesDTO dto) {
        dto.setIdPromocion(id);
        return promocionService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        promocionService.eliminar(id);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<Set<Long>> obtenerProductosDePromocion(@PathVariable Long id) {
        Set<Long> productosIds = promocionService.obtenerProductosIdsPorPromocion(id);
        return ResponseEntity.ok(productosIds);
    }

}