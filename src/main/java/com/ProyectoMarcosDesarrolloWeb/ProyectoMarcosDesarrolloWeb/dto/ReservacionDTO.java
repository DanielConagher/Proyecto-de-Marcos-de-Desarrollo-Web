/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

import java.time.LocalDateTime;

public class ReservacionDTO {

    private String nombre;
    private String correo;
    private String telefono;
    private Integer numeroMesa;
    private LocalDateTime fechaHora;
    private Integer numPersonas;
    private String estado;

    public ReservacionDTO() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Integer getNumeroMesa() { return numeroMesa; }
    public void setNumeroMesa(Integer numeroMesa) { this.numeroMesa = numeroMesa; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Integer getNumPersonas() { return numPersonas; }
    public void setNumPersonas(Integer numPersonas) { this.numPersonas = numPersonas; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
