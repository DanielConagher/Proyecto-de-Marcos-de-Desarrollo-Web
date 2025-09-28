/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import Entity.Rol;
import Entity.Usuario;
import dto.userDTO;
import java.time.LocalDateTime;

/**
 *
 * @author danie
 */
public class UserMapper {

    public userDTO toDTO(Usuario usuario) {
        userDTO dto = new userDTO();
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol().getNombre_rol());
        dto.setPassword(usuario.getContrasena());
        return dto;
    }

    public Usuario toEntity(userDTO dto) {
        Usuario user = new Usuario();
        user.setNombre(dto.getNombre());
        user.setCorreo(dto.getCorreo());
        Rol rol = new Rol();
        rol.setNombre_rol(dto.getRol());
        user.setRol(rol);
        user.setContrasena(dto.getPassword());
        return user;
    }
}
