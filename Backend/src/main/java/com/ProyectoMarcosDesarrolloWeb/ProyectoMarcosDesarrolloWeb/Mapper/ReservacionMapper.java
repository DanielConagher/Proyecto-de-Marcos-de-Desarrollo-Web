/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ReservacionDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Reservacion;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservacionMapper {

    public ReservacionDTO toDto(Reservacion e) {
        if (e == null) return null;
        ReservacionDTO d = new ReservacionDTO();
        d.setNombre(e.getNombre());
        d.setCorreo(e.getCorreo());
        d.setTelefono(e.getTelefono());
        d.setNumeroMesa(e.getNumeroMesa());
        d.setFechaHora(e.getFechaHora());
        d.setNumPersonas(e.getNumPersonas());
        d.setEstado(e.getEstado());
        return d;
    }

    public Reservacion toEntity(ReservacionDTO d) {
        if (d == null) return null;
        Reservacion e = new Reservacion();
        e.setNombre(d.getNombre());
        e.setCorreo(d.getCorreo());
        e.setTelefono(d.getTelefono());
        e.setNumeroMesa(d.getNumeroMesa());
        e.setFechaHora(d.getFechaHora());
        e.setNumPersonas(d.getNumPersonas());
        e.setEstado(d.getEstado());
        return e;
    }

    public List<ReservacionDTO> toDtoList(List<Reservacion> list){
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}

