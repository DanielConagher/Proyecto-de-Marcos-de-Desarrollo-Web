package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.TamanioPizzaService;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.TamanioPizzaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// El frontend lo llamará con: http://localhost:8080/api/tamanios
@RequestMapping("/api/tamanios")
public class TamanioPizzaController {

    @Autowired
    private TamanioPizzaService tamanioPizzaService;

    @GetMapping
    public List<TamanioPizzaDTO> obtenerTodosLosTamanios() {
        return tamanioPizzaService.findAll();
    }
}