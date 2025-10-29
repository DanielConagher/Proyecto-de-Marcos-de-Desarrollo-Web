package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ProductoDTO;
import java.util.Base64;

import java.util.List;

@Service // Indica que esta clase es un componente de servicio de Spring
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository; // Inyecta el repositorio

    public List<ProductoDTO> findAll() {
        return productoRepository.findAll().stream()
                .map(this::convertirAProductoDTO) // Usa el método de mapeo
                .toList();
    }

    // Método de mapeo (conversión de Entidad a DTO)
    private ProductoDTO convertirAProductoDTO(Producto producto) {
        String imagenBase64 = null;
        if (producto.getImagen() != null) {
            // Convierte el array de bytes a una cadena Base64
            imagenBase64 = Base64.getEncoder().encodeToString(producto.getImagen());
        }

        return new ProductoDTO(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getDescripcion(),
            imagenBase64
        );
    }
}
