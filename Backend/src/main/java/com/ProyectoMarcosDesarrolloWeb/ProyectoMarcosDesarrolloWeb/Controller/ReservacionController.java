/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Controller;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Service.ReservacionService;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.ReservacionDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {

    @Autowired
    private ReservacionService reservacionService;

    @PostMapping
    public ResponseEntity<ReservacionDTO> crear(@RequestBody ReservacionDTO r){
        if (r.getEstado() == null) r.setEstado("PENDIENTE");
        return ResponseEntity.status(201).body(reservacionService.create(r));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservacionDTO> obtener(@PathVariable Long id){
        ReservacionDTO r = reservacionService.findById(id);
        return r==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

    @GetMapping
    public ResponseEntity<List<ReservacionDTO>> listar(){ return ResponseEntity.ok(reservacionService.findAll()); }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<ReservacionDTO> confirmar(@PathVariable Long id){
        ReservacionDTO r = reservacionService.findById(id);
        if (r==null) return ResponseEntity.notFound().build();
        r.setEstado("CONFIRMADA");
        return ResponseEntity.ok(reservacionService.update(r));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ReservacionDTO> cancelar(@PathVariable Long id){
        ReservacionDTO r = reservacionService.findById(id);
        if (r==null) return ResponseEntity.notFound().build();
        r.setEstado("CANCELADA");
        return ResponseEntity.ok(reservacionService.update(r));
    }
}
