/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.UserMapper;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.UserRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.userDTO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author danie
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //  LOGIN
    public boolean login(String correo, String contrasena) {
        return userRepository.findByCorreoAndContrasena(correo, contrasena).isPresent();
    }

    //  REGISTRO
    public userDTO registrarUsuario(userDTO dto) {
        Usuario user = userMapper.toEntity(dto);
        Usuario guardado = userRepository.save(user);
        return userMapper.toDTO(guardado);
    }

    //  RECUPERAR CONTRASEÑA
    public String recuperarContrasena(String correo) {
        return userRepository.findByCorreo(correo)
                .map(Usuario::getContrasena)
                .orElse(null);
    }

}
