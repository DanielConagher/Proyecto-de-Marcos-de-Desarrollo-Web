package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.PromocionService;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.PromocionesDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    private final PromocionService service;

    public PromocionController(PromocionService service) {
        this.service = service;
    }

    @GetMapping
    public List<PromocionesDTO> listar() {
        return service.listarPromociones();
    }

    @PostMapping
    public PromocionesDTO guardar(@RequestBody PromocionesDTO dto) {
        return service.guardarPromocion(dto);
    }
}