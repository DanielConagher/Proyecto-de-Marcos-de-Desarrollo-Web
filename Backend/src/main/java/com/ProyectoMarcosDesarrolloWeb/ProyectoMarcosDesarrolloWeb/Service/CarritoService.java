package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CarritoItemDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.CarritoMapper;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.CarritoProductoRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.CarritoRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.ProductoRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.UsuarioRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.CarritoProducto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.CarritoProductoId;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Carrito;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CarritoMapper carritoMapper;
    
    /**
     * Obtiene todos los items del carrito de un usuario
     */
    public List<CarritoItemDTO> obtenerCarritoPorUsuario(Long idUsuario) {
        Carrito carrito = carritoRepository.findByUsuarioIdUsuario(idUsuario);
        if (carrito == null) {
            return List.of(); // Lista vacía si no hay carrito
        }
        
        List<CarritoProducto> items = carritoProductoRepository.findByCarrito(carrito);
        return carritoMapper.toDtoList(items);
    }

    /**
     * Agrega un item al carrito del usuario
     */
    @Transactional
    public void agregarItem(Long idUsuario, CarritoItemDTO itemDTO) {
        // Buscar o crear el carrito del usuario
        Carrito carrito = carritoRepository.findByUsuarioIdUsuario(idUsuario);
        
        if (carrito == null) {
            // Crear nuevo carrito para el usuario
            carrito = new Carrito();
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
            if (usuarioOpt.isPresent()) {
                carrito.setUsuario(usuarioOpt.get());
                carrito = carritoRepository.save(carrito);
            } else {
                throw new RuntimeException("Usuario no encontrado con ID: " + idUsuario);
            }
        }
        
        // Buscar el producto
        Optional<Producto> productoOpt = productoRepository.findById(itemDTO.getIdProducto());
        if (!productoOpt.isPresent()) {
            throw new RuntimeException("Producto no encontrado con ID: " + itemDTO.getIdProducto());
        }
        Producto producto = productoOpt.get();
        
        // Crear el ID compuesto
        CarritoProductoId carritoProductoId = new CarritoProductoId();
        carritoProductoId.setId_Carrito(carrito.getId_Carrito());
        carritoProductoId.setId_Producto(producto.getIdProducto());
        
        // Verificar si ya existe el producto en el carrito
        Optional<CarritoProducto> existente = carritoProductoRepository.findById(carritoProductoId);
        
        if (existente.isPresent()) {
            // Actualizar cantidad
            CarritoProducto carritoProducto = existente.get();
            carritoProducto.setCantidad(carritoProducto.getCantidad() + itemDTO.getCantidad());
            carritoProductoRepository.save(carritoProducto);
        } else {
            // Crear nuevo item en el carrito
            CarritoProducto carritoProducto = new CarritoProducto();
            carritoProducto.setId(carritoProductoId);
            carritoProducto.setCarrito(carrito);
            carritoProducto.setProducto(producto);
            carritoProducto.setCantidad(itemDTO.getCantidad());
            carritoProductoRepository.save(carritoProducto);
        }
    }

    /**
     * Actualiza la cantidad de un item en el carrito
     */
    @Transactional
    public void actualizarCantidad(Long idItem, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            eliminarItem(idItem);
            return;
        }
        
        // Nota: Aquí necesitarías adaptar según cómo identificas los items
        // Por ahora usamos el ID del producto directamente
        // En una implementación real, podrías usar un ID único de CarritoProducto
    }

    /**
     * Elimina un item del carrito
     */
    @Transactional
    public void eliminarItem(Long idItemCarrito) {
        carritoProductoRepository.deleteById(idItemCarrito);
    }
    
    /**
     * Vacía completamente el carrito de un usuario
     */
    @Transactional
    public void vaciarCarrito(Long idUsuario) {
        Carrito carrito = carritoRepository.findByUsuarioIdUsuario(idUsuario);
        if (carrito != null) {
            List<CarritoProducto> items = carritoProductoRepository.findByCarrito(carrito);
            carritoProductoRepository.deleteAll(items);
        }
    }
}
