/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.UsuarioPerfilDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CambioContrasenaDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.UsuarioMapper;
import org.springframework.stereotype.Service;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener perfil
    public UsuarioPerfilDTO obtenerPerfil(Long idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        return usuarioOpt.map(UsuarioMapper::toDTO).orElse(null);
    }

    // Actualizar perfil
    public UsuarioPerfilDTO actualizarPerfil(Long idUsuario, UsuarioPerfilDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            UsuarioMapper.actualizarEntidadDesdeDTO(usuario, dto);
            Usuario actualizado = usuarioRepository.save(usuario);
            return UsuarioMapper.toDTO(actualizado);
        }
        return null;
    }

    // Cambiar contraseña
    public boolean cambiarContrasena(Long idUsuario, CambioContrasenaDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContrasena().equals(dto.getContrasenaActual())) { 
                usuario.setContrasena(dto.getNuevaContrasena());
                usuarioRepository.save(usuario);
                return true;
            }
        }
        return false;
    }
}


