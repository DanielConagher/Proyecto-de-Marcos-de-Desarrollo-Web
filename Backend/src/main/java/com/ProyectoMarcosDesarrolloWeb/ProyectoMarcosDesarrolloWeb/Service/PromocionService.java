package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Producto;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Promocion;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.PromocionMapper;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.PromocionRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.PromocionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PromocionService {
    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private PromocionMapper promocionMapper;

    public List<PromocionesDTO> listarPromociones() {
        return promocionRepository.findAll()
                .stream()
                .map(promocionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PromocionesDTO obtenerPorId(Long id) {
        return promocionRepository.findById(id)
                .map(promocionMapper::toDTO)
                .orElse(null);
    }

    public PromocionesDTO guardar(PromocionesDTO dto) {
        Promocion promocion = promocionMapper.toEntity(dto);
        Promocion guardada = promocionRepository.save(promocion);
        return promocionMapper.toDTO(guardada);
    }

    public void eliminar(Long id) {
        promocionRepository.deleteById(id);
    }

    public Set<Long> obtenerProductosIdsPorPromocion(Long idPromocion) {
        return promocionRepository.findById(idPromocion)
                .map(p -> p.getProductos().stream().map(Producto::getIdProducto).collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }

}
