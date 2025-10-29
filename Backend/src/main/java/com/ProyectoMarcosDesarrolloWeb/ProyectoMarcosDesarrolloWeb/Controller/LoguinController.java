/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.UserService;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.LoguinUserDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.RegistroUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author danie
 */
@RestController
@RequestMapping("/api/user")
public class LoguinController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public boolean login(@RequestBody LoguinUserDTO userDTO) {
        String correo = userDTO.getCorreo();
        String contrasena = userDTO.getContrasena();
        return userService.login(correo, contrasena);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistroUserDTO userDTO) {
        // Verificar si el correo ya existe
        if (userService.recuperarContrasena(userDTO.getCorreo()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El correo ya está registrado");
        }

        // Guardar usuario
        userService.registrarUsuario(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }

}
