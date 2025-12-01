package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Promocion;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.ProductoRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.PromocionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PromocionMapper {

    @Autowired
    private ProductoRepository productoRepository;

    public PromocionesDTO toDTO(Promocion promocion) {
        PromocionesDTO dto = new PromocionesDTO();
        dto.setIdPromocion(promocion.getIdPromocion());
        dto.setNombre(promocion.getNombre());
        dto.setDescripcion(promocion.getDescripcion());
        dto.setDescuentoPorcentaje(promocion.getDescuentoPorcentaje());
        dto.setFechaInicio(promocion.getFechaInicio());
        dto.setFechaFin(promocion.getFechaFin());
        dto.setImagenReferencia(promocion.getImagenReferencia());

        if (promocion.getProductos() != null) {
            dto.setProductosIds(promocion.getProductos()
                    .stream()
                    .map(Producto::getIdProducto)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    public Promocion toEntity(PromocionesDTO dto) {
        Promocion promocion = new Promocion();
        promocion.setIdPromocion(dto.getIdPromocion());
        promocion.setNombre(dto.getNombre());
        promocion.setDescripcion(dto.getDescripcion());
        promocion.setDescuentoPorcentaje(dto.getDescuentoPorcentaje());
        promocion.setFechaInicio(dto.getFechaInicio());
        promocion.setFechaFin(dto.getFechaFin());
        promocion.setImagenReferencia(dto.getImagenReferencia());

        if (dto.getProductosIds() != null && !dto.getProductosIds().isEmpty()) {
            Set<Producto> productos = dto.getProductosIds().stream()
                    .map(id -> productoRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            promocion.setProductos(productos);
        } else {
            promocion.setProductos(new HashSet<>());
        }

        return promocion;
    }
}
