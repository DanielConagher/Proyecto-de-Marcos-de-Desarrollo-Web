package Repository;

import Entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    // Encuentra un carrito por el ID del usuario
    Carrito findByUsuarioId(Long idUsuario); 
}
