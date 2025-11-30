package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Extra;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Repository.ExtraRepository;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ExtraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/extras")
public class ExtraController {

    @Autowired
    private ExtraRepository extraRepository;

    @GetMapping
    public List<ExtraDTO> obtenerTodosLosExtras() {
        return extraRepository.findAll().stream()
                .map(this::convertirAExtraDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtraDTO> obtenerPorId(@PathVariable Long id) {
        Optional<Extra> extra = extraRepository.findById(id);
        if (extra.isPresent()) {
            return ResponseEntity.ok(convertirAExtraDTO(extra.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExtraDTO> crear(@RequestBody ExtraDTO dto) {
        Extra extra = convertirAEntidad(dto);
        Extra guardado = extraRepository.save(extra);
        return ResponseEntity.ok(convertirAExtraDTO(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExtraDTO> actualizar(@PathVariable Long id, @RequestBody ExtraDTO dto) {
        Optional<Extra> existente = extraRepository.findById(id);
        if (existente.isPresent()) {
            Extra extra = existente.get();
            extra.setNombreExtra(dto.getNombre());
            extra.setPrecioVenta(dto.getPrecio());
            Extra actualizado = extraRepository.save(extra);
            return ResponseEntity.ok(convertirAExtraDTO(actualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (extraRepository.existsById(id)) {
            extraRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ExtraDTO convertirAExtraDTO(Extra extra) {
        ExtraDTO dto = new ExtraDTO();
        dto.setIdExtra(extra.getIdExtra());
        dto.setNombre(extra.getNombreExtra());
        dto.setPrecio(extra.getPrecioVenta());
        return dto;
    }

    private Extra convertirAEntidad(ExtraDTO dto) {
        Extra extra = new Extra();
        if (dto.getIdExtra() != null) {
            extra.setIdExtra(dto.getIdExtra());
        }
        extra.setNombreExtra(dto.getNombre());
        extra.setPrecioVenta(dto.getPrecio());
        return extra;
    }
}
