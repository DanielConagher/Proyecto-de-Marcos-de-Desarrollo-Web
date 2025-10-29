package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.CompraDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    private Long getCurrentUserId() { return 1L; }

    // ENDPOINT: GET /api/compras
    @GetMapping
    public List<CompraDTO> getHistorialCompras() {
        return compraService.obtenerHistorial(getCurrentUserId());
    }

    // ENDPOINT: POST /api/compras/finalizar
    @PostMapping("/finalizar")
    public ResponseEntity<CompraDTO> finalizarCompra(@RequestParam Long idMetodoPago, @RequestParam Long idTipoEnvio) {
        CompraDTO nuevaCompra = compraService.procesarCompra(getCurrentUserId(), idMetodoPago, idTipoEnvio);
        return ResponseEntity.ok(nuevaCompra);
    }
}
