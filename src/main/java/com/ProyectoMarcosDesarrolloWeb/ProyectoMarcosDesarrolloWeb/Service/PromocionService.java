package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Promocion;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.PromocionMapper;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.PromocionRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.PromocionesDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocionService {
    private final PromocionRepository repository;

    public PromocionService(PromocionRepository repository) {
        this.repository = repository;
    }

    public List<PromocionesDTO> listarPromociones() {
        return repository.findAll()
                .stream()
                .map(PromocionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PromocionesDTO guardarPromocion(PromocionesDTO dto) {
        Promocion entity = PromocionMapper.toEntity(dto);
        return PromocionMapper.toDTO(repository.save(entity));
    }
}
