package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.PromocionService;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.PromocionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}