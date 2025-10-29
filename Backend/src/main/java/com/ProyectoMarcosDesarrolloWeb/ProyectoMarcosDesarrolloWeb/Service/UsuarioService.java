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
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Obtener perfil
    public UsuarioPerfilDTO obtenerPerfil(Long idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        return usuarioOpt.map(usuarioMapper::toDTO).orElse(null);
    }

    // Actualizar perfil
    public UsuarioPerfilDTO actualizarPerfil(Long idUsuario, UsuarioPerfilDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuarioMapper.actualizarEntidadDesdeDTO(usuario, dto);
            Usuario actualizado = usuarioRepository.save(usuario);
            return usuarioMapper.toDTO(actualizado);
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


