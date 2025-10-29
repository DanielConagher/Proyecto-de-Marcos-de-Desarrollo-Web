document.addEventListener("DOMContentLoaded", async () => {
    const contenedor = document.getElementById("contenedor-promos");

    try {
        const response = await fetch("http://localhost:8080/api/promociones");
        const promociones = await response.json();

        contenedor.innerHTML = ""; // limpiar contenido anterior

        promociones.forEach(promo => {
            const card = document.createElement("div");
            card.classList.add("promos", "rounded-5", "d-flex", "flex-column");

            // Aseguramos que el precio sea numérico y válido
            const precio = promo.precio ? Number(promo.precio).toFixed(2) : "0.00";

            card.innerHTML = `
                <img src="../Imagenes/pizza.png" alt="${promo.nombre ?? "Sin nombre"}" class="img-fluid rounded-top-5">
                <div class="p-3 d-flex flex-column justify-content-between flex-grow-1">
                    <div>
                        <h5 class="fw-bolder">${promo.nombre ?? "Sin nombre"}</h5>
                        <p>${promo.descripcion ?? ""}</p>
                        <p class="text-muted small">${promo.condiciones ?? ""}</p>
                    </div>
                    <div class="d-flex justify-content-between align-items-center mt-auto">
                        <p class="fw-bolder fs-5 mb-0">S/.${precio}</p>
                        <button class="btn btn-success btn-sm agregar-carrito">
                            Agregar al carrito
                        </button>
                    </div>
                </div>
            `;

            // Evento para agregar al carrito
            const btnAgregar = card.querySelector(".agregar-carrito");
            btnAgregar.addEventListener("click", () => {
                agregarProducto(promo.nombre ?? "Sin nombre", Number(promo.precio) || 0, "../Imagenes/pizza.png", 1);
                mostrarAviso("Producto agregado al carrito");
            });


            contenedor.appendChild(card);
        });

    } catch (error) {
        console.error("Error al cargar promociones:", error);
        contenedor.innerHTML = `<p class="text-danger">No se pudieron cargar las promociones.</p>`;
    }
});

function mostrarAviso(mensaje) {
    const aviso = document.createElement("div");
    aviso.classList.add(
        "toast",
        "align-items-center",
        "text-bg-success",
        "border-0",
        "position-fixed",
        "bottom-0",
        "end-0",
        "m-3",
        "show"
    );
    aviso.role = "alert";
    aviso.style.zIndex = "9999";
    aviso.innerHTML = `
        <div class="d-flex">
            <div class="toast-body">${mensaje}</div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Cerrar"></button>
        </div>
    `;
    document.body.appendChild(aviso);

    // Se elimina automáticamente después de 2.5 segundos
    setTimeout(() => aviso.remove(), 2500);
}
