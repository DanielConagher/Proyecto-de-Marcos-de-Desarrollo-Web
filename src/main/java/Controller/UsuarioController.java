/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dto.UsuarioPerfilDTO;
import dto.CambioContrasenaDTO;
import service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET Perfil
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioPerfilDTO> obtenerPerfil(@PathVariable Long id) {
        UsuarioPerfilDTO dto = usuarioService.obtenerPerfil(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // PUT Actualizar perfil
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioPerfilDTO> actualizarPerfil(
            @PathVariable Long id,
            @RequestBody UsuarioPerfilDTO dto
    ) {
        UsuarioPerfilDTO actualizado = usuarioService.actualizarPerfil(id, dto);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // PUT Cambiar contraseña
    @PutMapping("/{id}/cambiar-password")
    public ResponseEntity<String> cambiarPassword(
            @PathVariable Long id,
            @RequestBody CambioContrasenaDTO dto
    ) {
        boolean ok = usuarioService.cambiarContrasena(id, dto);
        if (ok) {
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        }
        return ResponseEntity.badRequest().body("Error al cambiar la contraseña");
    }
}
