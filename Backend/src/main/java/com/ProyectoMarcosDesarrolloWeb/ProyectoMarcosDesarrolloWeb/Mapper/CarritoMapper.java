package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CarritoItemDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.CarritoProducto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarritoMapper {
    
    public CarritoItemDTO toDto(CarritoProducto item) {
        CarritoItemDTO dto = new CarritoItemDTO();
        Producto producto = item.getProducto();
        
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombreProducto(producto.getNombre());
        dto.setCantidad(item.getCantidad());
        
        // Convertir imagen a Base64 si existe
        if (producto.getImagen() != null) {
            dto.setImagenBase64(Base64.getEncoder().encodeToString(producto.getImagen()));
        }
        
        // Nota: El precio y tamaño se deben obtener de TamanioProductoPrecio
        // Por ahora dejamos estos campos como null o valores por defecto
        // ya que la relación no está en CarritoProducto directamente
        
        return dto;
    }

    public List<CarritoItemDTO> toDtoList(List<CarritoProducto> items) {
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }
}