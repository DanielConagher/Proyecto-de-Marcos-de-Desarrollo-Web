package com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Mapper;

import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Direccion;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Rol;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.Entity.Usuario;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.LoguinUserDTO;
import com.ProyectoMarcosDesarrolloWeb.ProyectoMarcosDesarrolloWeb.dto.RegistroUserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserLoginRegisterMapper {
    public Usuario toEntityFromRegister(RegistroUserDTO dto, Rol rolPorDefecto){
        Usuario usuario= new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getPassword());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(rolPorDefecto);


        Direccion direccion= new Direccion();
        direccion.setDistrito(dto.getDistrito());
        direccion.setCalle(dto.getCalle());
        direccion.setCiudad(dto.getCiudad());
        direccion.setNumero(dto.getNumero());
        direccion.setReferencia(dto.getReferencia());

        usuario.setDireccion(direccion);

        return usuario;
    }

    public static Usuario toEntityFromLogin(LoguinUserDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());
        return usuario;
    }
}
