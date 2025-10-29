/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Rol;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper.UserLoginRegisterMapper;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.RolRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.UserRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.RegistroUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author danie
 */
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RolRepository rolRepository;

    @Autowired
    private final UserLoginRegisterMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, RolRepository rolRepository, UserLoginRegisterMapper userMapper) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.userMapper = userMapper;
    }

    //  LOGIN
    public boolean login(String correo, String contrasena) {
        return userRepository.findByCorreoAndContrasena(correo, contrasena).isPresent();
    }

    //  REGISTRO
    public boolean registrarUsuario(RegistroUserDTO dto) {
        try {
            Rol rolPorDefecto = rolRepository.findBynombreRol("Cliente");
            if (rolPorDefecto == null) {
                return false; // no existe el rol por defecto
            }
            Usuario nuevo = userMapper.toEntityFromRegister(dto, rolPorDefecto);
            Usuario guardado = userRepository.save(nuevo);

            // Validamos que se haya guardado correctamente
            return guardado != null && guardado.getIdUsuario() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //  RECUPERAR CONTRASEÑA
    public String recuperarContrasena(String correo) {
        return userRepository.findByCorreo(correo)
                .map(Usuario::getContrasena)
                .orElse(null);
    }
}
