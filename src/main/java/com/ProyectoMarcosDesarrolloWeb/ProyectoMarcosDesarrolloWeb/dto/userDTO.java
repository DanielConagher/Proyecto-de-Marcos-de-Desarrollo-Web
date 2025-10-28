/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto;

/**
 *
 * @author danie
 */
public class userDTO {
    private String nombre;
    private String correo;
    private String password;
    private String rol;
    

    public userDTO() { //Necesario para la ejecución del JPA en el futuro
    }

    ;

    public userDTO(String correo, String password, String rol) {
        this.correo = correo;
        this.password = password;
        this.rol=rol;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void setCorreo(String username) {
        this.correo = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    

}
