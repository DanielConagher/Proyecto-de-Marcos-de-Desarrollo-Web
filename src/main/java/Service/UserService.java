/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entity.Usuario;
import Mapper.UserMapper;
import Repository.UserRepository;
import dto.userDTO;
import java.util.Optional;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author danie
 */
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
//Loguin

    public boolean login(String correo, String contrasena) {
        return userRepository.findByCorreo(correo)
                .map(user -> user.getContrasena().equals(contrasena))
                .orElse(false);
    }

    public userDTO save(userDTO dto) {
        Usuario user = userMapper.toEntity(dto);
        return userMapper.toDTO(userRepository.save(user));
    }

    public String recuperarContrasena(String correo) {
        return userRepository.findByCorreo(correo)
                .map(user -> user.getContrasena())
                .orElse(null);
    }

}
