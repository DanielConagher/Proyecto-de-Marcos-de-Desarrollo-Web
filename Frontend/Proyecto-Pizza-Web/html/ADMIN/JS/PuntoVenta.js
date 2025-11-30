// Cambiar categorías
document.querySelectorAll('.categoria-btn').forEach(btn => {        
    btn.addEventListener('click', function() {    
        document.querySelectorAll('.categoria-btn').forEach(b => b.classList.remove('active'));
        this.classList.add('active');
        const categoria = this.getAttribute('data-categoria');
        // Aagregar la lógica para filtrar productos
        console.log(`Categoría seleccionada: ${categoria}`);
    });
});

// Agregar productos al carrito
document.querySelectorAll('.producto-card').forEach(card => {
    card.addEventListener('click', function() {
        const producto = this.getAttribute('data-producto');
        // Agregar lógica para agregar el producto
        console.log(`Producto agregado: ${producto}`);
        // Mensaje por el momento
        const carritoVacio = document.querySelector('.carrito-vacio');
        
        if (carritoVacio) {
            carritoVacio.innerHTML = `
            <div class="alert alert-info">
            <i class="bi bi-info-circle me-2"></i>
            Producto "${producto}" agregado al carrito
            </div>
            <p class="mt-3">Funcionalidad en desarrollo...</p>
            `;
        }
    }); 
});