package Service;

import dto.CompraDTO;
import Mapper.CompraMapper;
import Repository.CompraRepository;
import Repository.CarritoProductoRepository;
import Entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;
    
    @Autowired
    private CompraMapper compraMapper; 
    
    @Autowired
    private CarritoService carritoService; 
    
    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    public CompraDTO procesarCompra(Long idUsuario, Long idMetodoPago, Long idTipoEnvio) {
    
        Compra nuevaCompra = new Compra();
        
        compraRepository.save(nuevaCompra);
        
        return compraMapper.toDto(nuevaCompra); 
    }

    public List<CompraDTO> obtenerHistorial(Long idUsuario) {
        return compraMapper.toDtoList(compraRepository.findByUsuarioId(idUsuario));
    }
}