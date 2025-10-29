package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.TamanioPrecioDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.TamanioProductoPrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/precios")
@CrossOrigin(origins = "*")
public class TamanioProductoPrecioController {

    @Autowired
    private TamanioProductoPrecioService service;

    // Endpoint: http://localhost:8080/api/precios/{idProducto}
    @GetMapping("/{idProducto}")
    public List<TamanioPrecioDTO> obtenerPreciosPorProducto(@PathVariable Long idProducto) {
        return service.findPreciosByProductoId(idProducto);
    }
}
