package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CompraDTO; 
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Compra; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompraMapper {
    

    public CompraDTO toDto(Compra compra) {
        if (compra == null) {
            return null;
        }
        
        CompraDTO dto = new CompraDTO();
        dto.setIdCompra(compra.getId_Compra());
        dto.setFechaCompra(compra.getFecha_Compra());
        dto.setPrecioTotal(compra.getPrecio_total());
        
        // --- Mapear relaciones ManyToOne con chequeo de NULOS ---
        
        // Usuario
        if (compra.getUsuario() != null) {
            dto.setNombreUsuario(compra.getUsuario().getNombre());
        } else {
            dto.setNombreUsuario("N/A");
        }
        
        // Tipo de Envío 
        if (compra.getTipo_envio() != null) {
            dto.setTipoEnvio(compra.getTipo_envio().getDescripcion()); 
        } else {
            dto.setTipoEnvio("Sin definir");
        }
        
        // Método de Pago 
        if (compra.getMetodoPago() != null) {
            dto.setMetodoPago(compra.getMetodoPago().getNombre());
        } else {
            dto.setMetodoPago("Sin definir");
        }
        
        
        return dto;
    }

    public List<CompraDTO> toDtoList(List<Compra> compras) {
        return compras.stream().map(this::toDto).collect(Collectors.toList());
    }
}
