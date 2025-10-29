/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Direccion;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.UsuarioPerfilDTO;

public class UsuarioMapper {

    // Entity → DTO
    public static UsuarioPerfilDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioPerfilDTO dto = new UsuarioPerfilDTO();
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());

        // Como usuario tiene una relación @ManyToOne con Direccion
        if (usuario.getDireccion() != null) {
            dto.setDireccion(String.valueOf(usuario.getDireccion().getId_Direccion()));
            // o también podrías usar usuario.getDireccion().getNombreCalle() si quieres mostrar la dirección textual
        } else {
            dto.setDireccion(null);
        }

        dto.setAvatar(null); // puedes luego completar si tienes un campo avatar
        return dto;
    }

    // DTO → Entity (actualización)
    public static void actualizarEntidadDesdeDTO(Usuario usuario, UsuarioPerfilDTO dto) {
        if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) usuario.setCorreo(dto.getCorreo());
        if (dto.getTelefono() != 0) usuario.setTelefono(dto.getTelefono());

        // Convertir la dirección si el DTO trae el id como texto
        if (dto.getDireccion() != null) {
            try {
                Direccion direccion = new Direccion();
                direccion.setId_Direccion(Long.parseLong(dto.getDireccion()));
                usuario.setDireccion(direccion);
            } catch (NumberFormatException e) {
                // No se asigna dirección si el valor no es numérico
            }
        }
    }
}



