/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ReservacionDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.ReservacionMapper;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.ReservacionRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Reservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservacionService {

    @Autowired
    private ReservacionRepository reservacionRepository;
    @Autowired
    private ReservacionMapper reservacionMapper;

    public ReservacionDTO create(ReservacionDTO dto) {
        if (dto.getEstado() == null) dto.setEstado("PENDIENTE");
        Reservacion e = reservacionMapper.toEntity(dto);
        Reservacion saved = reservacionRepository.save(e);
        return reservacionMapper.toDto(saved);
    }

    public ReservacionDTO findById(Long id) {
        Optional<Reservacion> opt = reservacionRepository.findById(id);
        return opt.map(reservacionMapper::toDto).orElse(null);
    }

    public List<ReservacionDTO> findAll() {
        return reservacionMapper.toDtoList(reservacionRepository.findAll());
    }

    public ReservacionDTO update(ReservacionDTO dto) {
        Reservacion e = reservacionMapper.toEntity(dto);
        Reservacion saved = reservacionRepository.save(e);
        return reservacionMapper.toDto(saved);
    }

    public boolean delete(Long id) {
        if (!reservacionRepository.existsById(id)) return false;
        reservacionRepository.deleteById(id);
        return true;
    }
}
