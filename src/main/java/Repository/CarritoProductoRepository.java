package Repository;

import Entity.CarritoProducto;
import Entity.Carrito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    // Encuentra todos los ítems para un carrito específico
    List<CarritoProducto> findByCarrito(Carrito carrito);
}
