/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Rol;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.userDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 *
 * @author danie
 */
@Repository
public class UserMapper {

    public userDTO toDTO(Usuario usuario) {
        userDTO dto = new userDTO();
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol().getNombreRol());
        dto.setPassword(usuario.getContrasena());
        return dto;
    }

    public Usuario toEntity(userDTO dto) {
        Usuario user = new Usuario();
        user.setNombre(dto.getNombre());
        user.setCorreo(dto.getCorreo());
        Rol rol = new Rol();
        rol.setNombreRol(dto.getRol());
        user.setRol(rol);
        user.setContrasena(dto.getPassword());
        return user;
    }
}
