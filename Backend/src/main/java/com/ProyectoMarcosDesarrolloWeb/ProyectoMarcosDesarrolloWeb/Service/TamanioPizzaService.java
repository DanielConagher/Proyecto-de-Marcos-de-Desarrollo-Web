package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Tamanio_Pizza;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.TamanioPizzaRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.TamanioPizzaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TamanioPizzaService {

    @Autowired
    private TamanioPizzaRepository tamanioPizzaRepository;

    public List<TamanioPizzaDTO> findAll() {
        return tamanioPizzaRepository.findAll().stream()
                .map(this::convertirATamanioDTO)
                .toList();
    }

    private TamanioPizzaDTO convertirATamanioDTO(Tamanio_Pizza tamanio) {
        return new TamanioPizzaDTO(tamanio.getId_Tamanio(), tamanio.getNombre());
    }
}
