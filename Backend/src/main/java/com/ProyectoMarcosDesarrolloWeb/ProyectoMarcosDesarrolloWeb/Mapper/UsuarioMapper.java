/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Direccion;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.DireccionRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.UsuarioPerfilDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioMapper {

    @Autowired
    private DireccionRepository direccionRepository;

    // Entity → DTO
    public UsuarioPerfilDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioPerfilDTO dto = new UsuarioPerfilDTO();
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());

        if (usuario.getDireccion() != null) {
            dto.setDireccion(String.valueOf(usuario.getDireccion().getId_Direccion()));
        } else {
            dto.setDireccion(null);
        }


        return dto;
    }

    // DTO → Entity
    public void actualizarEntidadDesdeDTO(Usuario usuario, UsuarioPerfilDTO dto) {
        if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) usuario.setCorreo(dto.getCorreo());
        if (dto.getTelefono() != 0) usuario.setTelefono(dto.getTelefono());

        if (dto.getDireccion() != null && !dto.getDireccion().isEmpty()) {
            try {
                Long direccionId = Long.parseLong(dto.getDireccion());
                Optional<Direccion> direccionExistente = direccionRepository.findById(direccionId);
                direccionExistente.ifPresent(usuario::setDireccion);
            } catch (NumberFormatException e) {
            }
        } else {
            usuario.setDireccion(null);
        }
    }
}



