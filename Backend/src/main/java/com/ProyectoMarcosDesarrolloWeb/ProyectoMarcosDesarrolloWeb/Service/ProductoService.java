package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.TamanioProductoPrecio;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ProductoDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ProductoDTO.PrecioTamanio;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Optional;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> findAll() {
        return productoRepository.findAll().stream()
                .map(this::convertirAProductoDTO)
                .toList();
    }

    public ProductoDTO obtenerPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(this::convertirAProductoDTO).orElse(null);
    }

    public ProductoDTO guardar(ProductoDTO dto) {
        Producto producto;
        
        if (dto.getIdProducto() != null) {
            // Actualizar existente
            Optional<Producto> existente = productoRepository.findById(dto.getIdProducto());
            if (existente.isPresent()) {
                producto = existente.get();
            } else {
                producto = new Producto();
            }
        } else {
            // Crear nuevo
            producto = new Producto();
        }

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        
        // Si se proporciona imagen en Base64, convertirla a bytes
        if (dto.getImagenBase64() != null && !dto.getImagenBase64().isEmpty()) {
            // Remover prefijo data:image si existe
            String base64Data = dto.getImagenBase64();
            if (base64Data.contains(",")) {
                base64Data = base64Data.split(",")[1];
            }
            producto.setImagen(Base64.getDecoder().decode(base64Data));
        }

        Producto guardado = productoRepository.save(producto);
        return convertirAProductoDTO(guardado);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    private ProductoDTO convertirAProductoDTO(Producto producto) {
        String imagenBase64 = null;
        if (producto.getImagen() != null) {
            imagenBase64 = Base64.getEncoder().encodeToString(producto.getImagen());
        }
        
        // Obtener precios por tamaño
        List<PrecioTamanio> precios = new ArrayList<>();
        if (producto.getTamanioProductoPrecio() != null) {
            for (TamanioProductoPrecio tpp : producto.getTamanioProductoPrecio()) {
                if (tpp.getTamanioPizza() != null && tpp.getPrecio() != null) {
                    precios.add(new PrecioTamanio(
                        tpp.getTamanioPizza().getId_Tamanio().longValue(),
                        tpp.getTamanioPizza().getNombre(),
                        tpp.getPrecio()
                    ));
                }
            }
        }

        ProductoDTO dto = new ProductoDTO(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getDescripcion(),
            imagenBase64
        );
        dto.setPrecios(precios);
        
        return dto;
    }
}
