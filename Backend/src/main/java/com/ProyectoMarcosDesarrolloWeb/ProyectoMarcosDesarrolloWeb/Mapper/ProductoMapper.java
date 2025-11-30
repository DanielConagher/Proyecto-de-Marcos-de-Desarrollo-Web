package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ProductoDTO; 
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());

        // Convertir imagen binaria a Base64
        if (producto.getImagen() != null) {
            String base64Image = Base64.getEncoder().encodeToString(producto.getImagen());
            dto.setImagenBase64(base64Image);
        }

        return dto;
    }

    public Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        
        producto.setIdProducto(dto.getIdProducto());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());

        // Convertir Base64 a imagen binaria
        if (dto.getImagenBase64() != null && !dto.getImagenBase64().isEmpty()) {
            byte[] imagenBytes = Base64.getDecoder().decode(dto.getImagenBase64());
            producto.setImagen(imagenBytes);
        }

        return producto;
    }
}