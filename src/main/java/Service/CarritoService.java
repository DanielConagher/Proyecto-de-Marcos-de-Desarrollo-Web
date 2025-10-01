package Service;

import dto.CarritoItemDTO;
import Mapper.CarritoMapper;
import Repository.CarritoProductoRepository;
import Repository.CarritoRepository;
import Entity.CarritoProducto;
import Entity.Carrito;
import Entity.Usuario; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;
    
    @Autowired
    private CarritoMapper carritoMapper;
    

    public List<CarritoItemDTO> obtenerCarritoPorUsuario(Long idUsuario) {
       
        Carrito carrito = carritoRepository.findByUsuarioId(idUsuario);
        if (carrito == null) {
            // Lógica para buscar el usuario y crear un nuevo carrito
            return List.of(); //lista vacia si no hay nada
        }
        
        // 2. Obtener los ítems del carrito
        List<CarritoProducto> items = carritoProductoRepository.findByCarrito(carrito);
        
        // 3. Mapear a DTO
        return carritoMapper.toDtoList(items);
    }

    public void agregarItem(Long idUsuario, CarritoItemDTO itemDTO) {
        // Lógica: Buscar/crear Carrito, crear CarritoProducto, guardar
        // ...
    }

    public void eliminarItem(Long idItemCarrito) {
        carritoProductoRepository.deleteById(idItemCarrito);
    }
}
