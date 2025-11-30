package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CompraDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CarritoItemDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Compra;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.CompraProducto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
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
        
        // --- Mapear los productos de la compra ---
        if (compra.getCompraProductos() != null && !compra.getCompraProductos().isEmpty()) {
            List<CarritoItemDTO> items = new ArrayList<>();
            for (CompraProducto cp : compra.getCompraProductos()) {
                CarritoItemDTO item = new CarritoItemDTO();
                
                if (cp.getProducto() != null) {
                    item.setIdProducto(cp.getProducto().getIdProducto());
                    item.setNombreProducto(cp.getProducto().getNombre());
                    
                    // Imagen en Base64
                    if (cp.getProducto().getImagen() != null) {
                        item.setImagenBase64(Base64.getEncoder().encodeToString(cp.getProducto().getImagen()));
                    }
                }
                
                item.setCantidad(cp.getCantidad());
                item.setPrecioUnitario(cp.getPrecio_venta());
                
                // Calcular precio total del item
                if (cp.getPrecio_venta() != null) {
                    item.setPrecioTotal(cp.getPrecio_venta().multiply(BigDecimal.valueOf(cp.getCantidad())));
                }
                
                // Extra si existe
                if (cp.getExtra() != null) {
                    List<Long> extras = new ArrayList<>();
                    extras.add(cp.getExtra().getIdExtra());
                    item.setIdExtras(extras);
                }
                
                items.add(item);
            }
            dto.setItems(items);
        } else {
            dto.setItems(new ArrayList<>());
        }
        
        return dto;
    }

    public List<CompraDTO> toDtoList(List<Compra> compras) {
        return compras.stream().map(this::toDto).collect(Collectors.toList());
    }
}
