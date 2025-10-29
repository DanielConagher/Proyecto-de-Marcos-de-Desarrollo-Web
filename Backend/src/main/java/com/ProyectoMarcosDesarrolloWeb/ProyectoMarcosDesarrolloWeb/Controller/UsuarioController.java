package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.UsuarioPerfilDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CambioContrasenaDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    private Long getCurrentUserId() { 
        return 1L; 
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioPerfilDTO> obtenerPerfil() {
        Long idUsuario = getCurrentUserId(); 
        UsuarioPerfilDTO dto = usuarioService.obtenerPerfil(idUsuario);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/perfil")
    public ResponseEntity<UsuarioPerfilDTO> actualizarPerfil(
            @RequestBody UsuarioPerfilDTO dto
    ) {
        Long idUsuario = getCurrentUserId(); 
        UsuarioPerfilDTO actualizado = usuarioService.actualizarPerfil(idUsuario, dto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<String> cambiarPassword(
            @RequestBody CambioContrasenaDTO dto
    ) {
        Long idUsuario = getCurrentUserId();
        boolean ok = usuarioService.cambiarContrasena(idUsuario, dto);
        if (ok) {
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        }
        return ResponseEntity.badRequest().body("Error al cambiar la contraseña");
    }
}