package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Tamanio_Pizza;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.TamanioPizzaDTO;
import org.springframework.stereotype.Component;

@Component
public class TamanioPizzaMapper {

 
    public TamanioPizzaDTO toDTO(Tamanio_Pizza tamanio) {
        if (tamanio == null) {
            return null;
        }
        TamanioPizzaDTO dto = new TamanioPizzaDTO();
        
        dto.setIdTamanio(tamanio.getId_Tamanio());
        dto.setNombre(tamanio.getNombre());
        
        return dto;
    }

   
    public Tamanio_Pizza toEntity(TamanioPizzaDTO dto) {
        if (dto == null) {
            return null;
        }
        Tamanio_Pizza tamanio = new Tamanio_Pizza();
        
        tamanio.setId_Tamanio(dto.getIdTamanio());
        tamanio.setNombre(dto.getNombre());
        
        
        return tamanio;
    }
}
