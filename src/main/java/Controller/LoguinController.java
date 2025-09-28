/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Service.UserService;
import dto.userDTO;
import java.util.Map;
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

    private final UserService userService;

    public LoguinController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody userDTO userDTO) {
        String correo = userDTO.getCorreo();
        String contrasena = userDTO.getPassword();
        return userService.login(correo, contrasena);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody userDTO userDTO) {
        // Verificar si el correo ya existe
        if (userService.recuperarContrasena(userDTO.getCorreo()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El correo ya está registrado");
        }

        // Guardar usuario
        userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado correctamente");
    }

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<String> recuperarContrasena(@RequestBody userDTO userDTO) {
        String contrasena = userService.recuperarContrasena(userDTO.getCorreo());
        if (contrasena == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Correo no encontrado");
        }

        // Nota: nunca se debe devolver la contraseña real en producción
        return ResponseEntity.ok("La contraseña es: " + contrasena);
    }
}
