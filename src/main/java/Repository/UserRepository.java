/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 *
 * @author danie
 */
public interface UserRepository  extends JpaRepository<Usuario, Long>  {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> registrar(String nombre, String correo, String password);
    Optional<Usuario> olvideMiContrasena (String correo);
}
