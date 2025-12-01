document.addEventListener("DOMContentLoaded", async () => {
    const contenedor = document.getElementById("contenedor-promos");

    try {
        const response = await fetch("http://localhost:8080/api/promociones");
        const promociones = await response.json();

        contenedor.innerHTML = ""; // limpiar contenido anterior

        promociones.forEach(promo => {
            const card = document.createElement("div");
            card.classList.add("promos", "rounded-5", "d-flex", "flex-column");

            card.innerHTML = `
                <img src="../Imagenes/pizza.png" alt="${promo.nombre ?? "Sin nombre"}" class="img-fluid rounded-top-5">
                
                <div class="p-3 d-flex flex-column justify-content-between flex-grow-1">
                    <div>
                        <h5 class="fw-bolder">${promo.nombre ?? "Sin nombre"}</h5>
                        <p>${promo.descripcion ?? ""}</p>
                        <p class="text-muted small">${promo.condiciones ?? ""}</p>
                    </div>
                </div>
            `;

            contenedor.appendChild(card);
        });

    } catch (error) {
        console.error("Error al cargar promociones:", error);
        contenedor.innerHTML = `<p class="text-danger">No se pudieron cargar las promociones.</p>`;
    }
});

