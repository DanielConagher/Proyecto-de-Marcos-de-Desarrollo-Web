package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CompraDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.CompraMapper;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.CompraRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.CarritoProductoRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Compra;
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