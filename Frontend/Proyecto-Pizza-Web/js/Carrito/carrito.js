// ============================================================================
// CARRITO.JS - Gestión del carrito de compras
// ============================================================================

// URL base del API Backend
const CARRITO_API_URL = 'http://localhost:8080/api/carrito';

// Array para almacenar los productos del carrito
let carrito = [];

// ============================================================================
// FUNCIONES DE UI DEL CARRITO
// ============================================================================

/**
 * Abre el panel lateral del carrito
 */
function abrirCarrito() {
    const carritoElement = document.getElementById("sidebarCarrito");
    if (carritoElement) {
        const offcanvas = bootstrap.Offcanvas.getOrCreateInstance(carritoElement);
        offcanvas.show();
    }
}

/**
 * Cierra el panel lateral del carrito
 */
function cerrarCarrito() {
    const carritoElement = document.getElementById("sidebarCarrito");
    if (carritoElement) {
        const offcanvas = bootstrap.Offcanvas.getOrCreateInstance(carritoElement);
        offcanvas.hide();
    }
}

/**
 * Vacía completamente el carrito
 */
function vaciarCarrito() {
    carrito = [];
    const lista = document.getElementById("lista-Productos");
    if (lista) lista.innerHTML = "";

    const contenidoProductos = document.getElementById("contenidoProductos");
    const carritoVacio = document.getElementById("carritoVacio");
    const totalCarrito = document.getElementById("totalCarrito");

    if (contenidoProductos) contenidoProductos.classList.add("d-none");
    if (carritoVacio) carritoVacio.classList.remove("d-none");
    if (totalCarrito) totalCarrito.textContent = "S/0.00";

    guardarCarritoEnStorage();
}

/**
 * Actualiza la visibilidad de las secciones del carrito según si tiene productos o no
 */
function actualizarEstadoCarrito() {
    const lista = document.getElementById("lista-Productos");
    const contenidoProductos = document.getElementById("contenidoProductos");
    const carritoVacio = document.getElementById("carritoVacio");

    if (!lista || !contenidoProductos || !carritoVacio) return;

    if (carrito.length > 0) {
        carritoVacio.classList.add("d-none");
        contenidoProductos.classList.remove("d-none");
    } else {
        contenidoProductos.classList.add("d-none");
        carritoVacio.classList.remove("d-none");
    }
}

async function obtenerProductosPromoDesdeBackend(idPromo) {
    if (!idPromo) return new Set();
    try {
        const resp = await fetch(`http://localhost:8080/api/promociones/${idPromo}/productos`);
        if (!resp.ok) return new Set();
        const idsArray = await resp.json(); // espera un array JSON [1, 2, 3]
        return new Set(idsArray.map(Number));
    } catch (err) {
        console.error("Error al obtener productos de la promo:", err);
        return new Set();
    }
}

// ============================================================================
// FUNCIONES DE GESTIÓN DE PRODUCTOS
// ============================================================================

/**
 * Agrega un producto al carrito
 * @param {Object} item - Objeto con información del producto
 * @param {number} item.idProducto - ID del producto
 * @param {string} item.nombreProducto - Nombre del producto con detalle de tamaño
 * @param {number} item.idTamanio - ID del tamaño seleccionado
 * @param {string} item.nombreTamanio - Nombre del tamaño
 * @param {number} item.cantidad - Cantidad a agregar
 * @param {number} item.precioUnitario - Precio unitario (tamaño + extras)
 * @param {number} item.precioTotal - Precio total
 * @param {Array} item.idExtras - Array de IDs de extras
 * @param {string} item.extras - Nombres de extras separados por coma
 * @param {string} item.imagen - URL de la imagen (base64 o ruta)
 */
async function agregarProducto(item) {

    // Verificar promo aplicada en localStorage
    const promoAplicada = Number(localStorage.getItem("promoAplicada")) || null;

    if (promoAplicada) {
        try {
            // Obtener productos incluidos en la promo
            const productosPromoSet = await obtenerProductosPromoDesdeBackend(promoAplicada);

            // Si el producto está dentro de la promoción
            if (productosPromoSet.has(Number(item.idProducto))) {

                // Obtener información completa de la promoción (incluye porcentaje)
                const respPromo = await fetch(`http://localhost:8080/api/promociones/${promoAplicada}`);

                if (respPromo.ok) {
                    const promoData = await respPromo.json();
                    const descuento = Number(promoData.descuentoPorcentaje) || 0;

                    if (descuento > 0 && item.precioUnitario) {
                        const factor = (100 - descuento) / 100;

                        item.precioUnitario = Number((item.precioUnitario * factor).toFixed(2));

                        item.precioTotal = Number(
                            (item.precioUnitario * (item.cantidad || 1)).toFixed(2)
                        );

                        item.promoAplicadaId = promoAplicada;
                        item.descuentoAplicado = descuento;
                    }
                }
            }

        } catch (err) {
            console.error("Error aplicando promoción:", err);
        }
    }

    // ========== CÓDIGO ORIGINAL DEL CARRITO (NO TOCADO) ==========

    const extrasKey = item.idExtras ? item.idExtras.sort().join('-') : 'none';
    const itemKey = `${item.idProducto}-${item.idTamanio}-${extrasKey}`;

    const existente = carrito.find(p => {
        const pExtrasKey = p.idExtras ? p.idExtras.sort().join('-') : 'none';
        const pKey = `${p.idProducto}-${p.idTamanio}-${pExtrasKey}`;
        return pKey === itemKey;
    });

    if (existente) {
        existente.cantidad += item.cantidad;
        existente.precioTotal = existente.precioUnitario * existente.cantidad;
    } else {
        const nuevoItem = {
            id: Date.now(),
            idProducto: item.idProducto,
            nombreProducto: item.nombreProducto,
            idTamanio: item.idTamanio,
            nombreTamanio: item.nombreTamanio,
            cantidad: item.cantidad,
            precioUnitario: item.precioUnitario,
            precioTotal: item.precioTotal,
            idExtras: item.idExtras || [],
            extras: item.extras,
            imagen: item.imagen
        };
        carrito.push(nuevoItem);
    }

    renderizarCarrito();
}

/**
 * Elimina un producto del carrito por su ID
 * @param {number} itemId - ID del item en el carrito
 */
function eliminarProducto(itemId) {
    carrito = carrito.filter(item => item.id !== itemId);
    renderizarCarrito();
}

/**
 * Modifica la cantidad de un producto en el carrito
 * @param {number} itemId - ID del item en el carrito
 * @param {number} cambio - Valor a sumar/restar a la cantidad
 */
function modificarCantidad(itemId, cambio) {
    const item = carrito.find(p => p.id === itemId);
    if (item) {
        item.cantidad += cambio;

        if (item.cantidad <= 0) {
            eliminarProducto(itemId);
        } else {
            item.precioTotal = item.precioUnitario * item.cantidad;
            renderizarCarrito();
        }
    }
}

// ============================================================================
// RENDERIZADO DEL CARRITO
// ============================================================================

/**
 * Renderiza todos los productos del carrito en el panel lateral
 */
function renderizarCarrito() {
    const contenedor = document.getElementById("lista-Productos");
    const totalElement = document.getElementById("totalCarrito");

    if (!contenedor || !totalElement) return;

    contenedor.innerHTML = "";
    let suma = 0;

    carrito.forEach(item => {
        // Compatibilidad con datos antiguos del localStorage
        const precioTotal = item.precioTotal || (item.precioUnitario * item.cantidad) || (item.precio * item.cantidad) || 0;
        suma += precioTotal;

        const div = document.createElement("div");
        div.classList.add("card", "mb-2", "shadow-sm");
        div.dataset.itemId = item.id;

        // Mostrar extras si los hay
        const extrasHtml = item.extras
            ? `<small class="text-muted d-block">Extras: ${item.extras}</small>`
            : '';

        // Nombre del producto (compatibilidad)
        const nombreProducto = item.nombreProducto || item.nombre || 'Producto';
        const imagen = item.imagen || '../Imagenes/default.jpg';

        div.innerHTML = `
            <div class="card-body p-2">
                <div class="d-flex align-items-start">
                    <img src="${imagen}" alt="${nombreProducto}" 
                         class="rounded me-2" style="width: 60px; height: 60px; object-fit: cover;">
                    <div class="flex-grow-1">
                        <h6 class="mb-1 small fw-bold">${nombreProducto}</h6>
                        ${extrasHtml}
                        <div class="d-flex align-items-center mt-1">
                            <button class="btn btn-sm btn-outline-secondary py-0 px-2" 
                                    onclick="modificarCantidad(${item.id}, -1)">-</button>
                            <span class="mx-2 small">${item.cantidad}</span>
                            <button class="btn btn-sm btn-outline-secondary py-0 px-2" 
                                    onclick="modificarCantidad(${item.id}, 1)">+</button>
                            <span class="ms-auto fw-bold text-success">S/${precioTotal.toFixed(2)}</span>
                        </div>
                    </div>
                    <button class="btn btn-sm text-danger ms-2 p-0" 
                            onclick="eliminarProducto(${item.id})" title="Eliminar">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                        </svg>
                    </button>
                </div>
            </div>
        `;
        contenedor.appendChild(div);
    });

    totalElement.textContent = `S/${suma.toFixed(2)}`;
    guardarCarritoEnStorage();
    actualizarEstadoCarrito();
}

// ============================================================================
// PERSISTENCIA EN LOCALSTORAGE
// ============================================================================

/**
 * Guarda el carrito en localStorage
 */
function guardarCarritoEnStorage() {
    localStorage.setItem("carrito", JSON.stringify(carrito));
}

/**
 * Carga el carrito desde localStorage
 */
function cargarCarritoDeStorage() {
    const productosGuardados = JSON.parse(localStorage.getItem("carrito")) || [];
    if (productosGuardados.length > 0) {
        carrito = productosGuardados;
        renderizarCarrito();
    }
}

// ============================================================================
// FUNCIONES DE COMPRA/CHECKOUT
// ============================================================================

/**
 * Obtiene los datos del carrito formateados para enviar al backend
 * @returns {Array} Array de objetos CarritoItemDTO
 */
function obtenerDatosCarritoParaBackend() {
    return carrito.map(item => ({
        idProducto: item.idProducto,
        nombreProducto: item.nombreProducto,
        idTamanio: item.idTamanio,
        tamano: item.nombreTamanio,
        cantidad: item.cantidad,
        precioUnitario: item.precioUnitario,
        precioTotal: item.precioTotal,
        idExtras: item.idExtras,
        imagenBase64: item.imagen ? item.imagen.replace(/^data:image\/\w+;base64,/, '') : null
    }));
}

/**
 * Obtiene el total del carrito
 * @returns {number} Total del carrito
 */
function obtenerTotalCarrito() {
    return carrito.reduce((total, item) => total + item.precioTotal, 0);
}

/**
 * Redirige a la página de checkout/método de pago
 */
function realizarPedido() {
    if (carrito.length === 0) {
        alert("Tu carrito está vacío. Agrega productos antes de realizar el pedido.");
        return;
    }
    window.location.href = '../html/Compra/MetodoCompra.html';
}

// ============================================================================
// INICIALIZACIÓN
// ============================================================================

document.addEventListener("DOMContentLoaded", function () {
    // Cargar carrito desde localStorage
    cargarCarritoDeStorage();
    actualizarEstadoCarrito();

    // Configurar evento del icono del carrito
    const iconoCarrito = document.querySelector("#iconoCarrito");
    if (iconoCarrito) {
        iconoCarrito.addEventListener("click", abrirCarrito);
    }

    // También buscar por clase para compatibilidad
    const iconoCarritoClase = document.querySelector(".icono-carrito");
    if (iconoCarritoClase) {
        iconoCarritoClase.addEventListener("click", abrirCarrito);
    }
});

// Exportar funciones globales necesarias
window.abrirCarrito = abrirCarrito;
window.cerrarCarrito = cerrarCarrito;
window.vaciarCarrito = vaciarCarrito;
window.realizarPedido = realizarPedido;
window.agregarProducto = agregarProducto;
window.eliminarProducto = eliminarProducto;
window.modificarCantidad = modificarCantidad;
window.obtenerDatosCarritoParaBackend = obtenerDatosCarritoParaBackend;
window.obtenerTotalCarrito = obtenerTotalCarrito;