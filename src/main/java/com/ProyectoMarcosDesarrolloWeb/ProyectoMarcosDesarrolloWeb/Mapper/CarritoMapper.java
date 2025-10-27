package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CarritoItemDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.CarritoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarritoMapper {
    public CarritoItemDTO toDto(CarritoProducto item) {
        CarritoItemDTO dto = new CarritoItemDTO();
        dto.setIdProducto(item.getProducto().getId_Menu());
        dto.setNombreProducto(item.getProducto().getNombre());
        dto.setCantidad(item.getCantidad());
        
        return dto;
    }

    public List<CarritoItemDTO> toDtoList(List<CarritoProducto> items) {
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }
}