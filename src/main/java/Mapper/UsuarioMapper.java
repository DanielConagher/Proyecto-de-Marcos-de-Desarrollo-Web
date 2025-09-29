/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import Entity.Usuario;
import dto.UsuarioPerfilDTO;

public class UsuarioMapper {

    // Entity → DTO
    public static UsuarioPerfilDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioPerfilDTO dto = new UsuarioPerfilDTO();
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setTelefono(usuario.getTelefono());
        dto.setDireccion(String.valueOf(usuario.getId_Direccion()));
        dto.setAvatar(null);
        return dto;
    }

    // DTO → Entity (actualización)
    public static void actualizarEntidadDesdeDTO(Usuario usuario, UsuarioPerfilDTO dto) {
        if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) usuario.setCorreo(dto.getCorreo());
        if (dto.getTelefono() != 0) usuario.setTelefono(dto.getTelefono());
        if (dto.getDireccion() != null) {
            try {
                usuario.setId_Direccion(Long.parseLong(dto.getDireccion()));
            } catch (NumberFormatException e) {
            }
        }
    }
}



