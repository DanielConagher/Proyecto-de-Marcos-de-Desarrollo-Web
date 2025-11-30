// ============================================================================
// MENU.JS - Gestión de productos, tamaños, extras y conexión con el carrito
// ============================================================================

// URL base del API Backend
const API_BASE_URL = 'http://localhost:8080/api';

// Variables globales para almacenar datos cargados
let todosLosProductos = [];
let todosLosExtras = [];
let productoModalActual = null;

// ============================================================================
// INICIALIZACIÓN
// ============================================================================

document.addEventListener('DOMContentLoaded', () => {
    cargarProductos();
    cargarExtras();
    configurarEventosModal();
});

// ============================================================================
// CARGA DE DATOS DESDE EL BACKEND
// ============================================================================

/**
 * Carga todos los productos desde el backend y los muestra en el menú
 */
async function cargarProductos() {
    const container = document.getElementById('productos-container');
    const cargando = document.getElementById('cargando-productos');
    
    try {
        const respuesta = await fetch(`${API_BASE_URL}/productos`);
        if (!respuesta.ok) throw new Error('Error al obtener productos del servidor.');
        
        todosLosProductos = await respuesta.json();
        cargando.style.display = 'none';
        
        if (todosLosProductos.length === 0) {
            container.innerHTML = '<div class="col-12"><p class="text-center text-muted">No se encontraron productos en el menú.</p></div>';
            return;
        }

        // Limpiar contenedor y agregar cada producto
        container.innerHTML = '';
        todosLosProductos.forEach(producto => {
            container.innerHTML += crearCardProducto(producto);
        });

    } catch (error) {
        console.error('Error al cargar productos:', error);
        cargando.innerHTML = `
            <div class="alert alert-danger" role="alert">
                <strong>Error:</strong> No se pudo conectar con el servidor para cargar el menú.
                <br><small>Asegúrate de que el backend esté ejecutándose en ${API_BASE_URL}</small>
            </div>
        `;
    }
}

/**
 * Carga todos los extras disponibles desde el backend
 */
async function cargarExtras() {
    try {
        const respuesta = await fetch(`${API_BASE_URL}/extras`);
        if (!respuesta.ok) throw new Error('Error al obtener extras del servidor.');
        
        todosLosExtras = await respuesta.json();
        console.log('Extras cargados:', todosLosExtras.length);
        
    } catch (error) {
        console.error('Error al cargar extras:', error);
        todosLosExtras = [];
    }
}

// ============================================================================
// RENDERIZADO DE COMPONENTES
// ============================================================================

/**
 * Crea el HTML de una tarjeta de producto
 */
function crearCardProducto(producto) {
    // La imagen viene en formato Base64 desde el backend
    const imagenSrc = producto.imagenBase64 
        ? `data:image/jpeg;base64,${producto.imagenBase64}` 
        : '../Imagenes/default.jpg';

    return `
        <div class="col">
            <div class="card h-100"> 
                <img src="${imagenSrc}" class="card-img-top pizza-circular" alt="${producto.nombre}">
                <div class="card-body">
                    <h5 class="card-title">${producto.nombre}</h5>
                    <p class="card-text">${producto.descripcion || 'Deliciosa pizza artesanal'}</p>
                </div>
                <div class="card-footer text-center">
                    <button class="btn btn-primary btn-agregar-producto"
                        data-bs-toggle="modal"
                        data-bs-target="#modalOpciones"
                        data-id-producto="${producto.idProducto}"
                        data-nombre="${producto.nombre}">
                        Agregar
                    </button>
                </div>
            </div>
        </div>
    `;
}

/**
 * Renderiza las opciones de extras en el modal
 */
function renderizarExtrasEnModal() {
    const contenedorExtras = document.getElementById('opcionesExtras');
    
    if (todosLosExtras.length === 0) {
        contenedorExtras.innerHTML = '<p class="text-muted small">No hay extras disponibles.</p>';
        return;
    }
    
    contenedorExtras.innerHTML = '';
    todosLosExtras.forEach(extra => {
        contenedorExtras.innerHTML += `
            <div class="form-check list-group-item d-flex justify-content-between align-items-center">
                <input class="form-check-input" type="checkbox" name="extra" 
                       id="extra-${extra.idExtra}" 
                       value="${extra.precio}" 
                       data-id-extra="${extra.idExtra}"
                       data-nombre-extra="${extra.nombre}">
                <label class="form-check-label flex-grow-1" for="extra-${extra.idExtra}">
                    ${extra.nombre}
                </label>
                <strong>+S/${parseFloat(extra.precio).toFixed(2)}</strong>
            </div>
        `;
    });
}

// ============================================================================
// GESTIÓN DEL MODAL
// ============================================================================

// Flag para evitar registrar eventos múltiples veces
let eventosModalConfigurados = false;

/**
 * Configura todos los eventos del modal de opciones
 */
function configurarEventosModal() {
    // Evitar registrar eventos múltiples veces
    if (eventosModalConfigurados) return;
    
    const modalOpciones = document.getElementById('modalOpciones');
    
    if (!modalOpciones) {
        console.error('Modal de opciones no encontrado en el DOM');
        return;
    }
    
    // Al abrir el modal, cargar información del producto seleccionado
    modalOpciones.addEventListener('show.bs.modal', function (event) {
        const boton = event.relatedTarget;
        if (!boton) return; // Evitar errores si no hay botón
        
        const idProducto = boton.getAttribute('data-id-producto');
        const nombreProducto = boton.getAttribute('data-nombre');

        // Buscar el producto completo en la lista global
        productoModalActual = todosLosProductos.find(p => p.idProducto == idProducto);

        // Configurar el modal
        document.getElementById('nombreProductoModal').textContent = nombreProducto;
        document.getElementById('cargandoTamanios').style.display = 'block';
        document.getElementById('cantidadProducto').value = 1;
        
        // Resetear checkboxes de extras
        document.querySelectorAll('#opcionesExtras input[type="checkbox"]').forEach(cb => cb.checked = false);
        
        // Cargar tamaños/precios del producto seleccionado
        cargarOpcionesDeTamanio(idProducto);
        
        // Renderizar extras
        renderizarExtrasEnModal();
    });
    
    // Escuchar cambios en tamaños, extras y cantidad para actualizar precio
    modalOpciones.addEventListener('change', actualizarPrecioModal);
    
    // Botones de cantidad
    document.getElementById('btnSumarCantidad').addEventListener('click', () => cambiarCantidad(1));
    document.getElementById('btnRestarCantidad').addEventListener('click', () => cambiarCantidad(-1));

    // Botón de agregar al carrito - usar una sola vez
    const btnAgregar = document.getElementById('btnAgregarAlCarrito');
    // Clonar y reemplazar para eliminar listeners anteriores
    const nuevoBtn = btnAgregar.cloneNode(true);
    btnAgregar.parentNode.replaceChild(nuevoBtn, btnAgregar);
    nuevoBtn.addEventListener('click', agregarProductoAlCarritoFinal);
    
    // Marcar como configurado
    eventosModalConfigurados = true;
}

/**
 * Carga las opciones de tamaño y precio para un producto específico
 */
async function cargarOpcionesDeTamanio(idProducto) {
    const contenedorTamanios = document.getElementById('opcionesTamanios');
    contenedorTamanios.innerHTML = '';
    
    try {
        const respuesta = await fetch(`${API_BASE_URL}/precios/${idProducto}`);
        if (!respuesta.ok) throw new Error('Error al obtener precios.');
        
        const precios = await respuesta.json();

        if (precios.length === 0) {
            contenedorTamanios.innerHTML = '<p class="text-danger">No hay opciones de tamaño disponibles para este producto.</p>';
        } else {
            precios.forEach((item, index) => {
                const checked = index === 0 ? 'checked' : '';
                
                contenedorTamanios.innerHTML += `
                    <div class="form-check list-group-item d-flex justify-content-between align-items-center">
                        <input class="form-check-input" type="radio" name="radioTamanio" 
                               id="tamanio-${item.idTamanio}" 
                               value="${item.precio}" 
                               data-id-tamanio="${item.idTamanio}" 
                               data-nombre-tamanio="${item.nombreTamanio}" ${checked}>
                        <label class="form-check-label flex-grow-1" for="tamanio-${item.idTamanio}">
                            ${item.nombreTamanio}
                        </label>
                        <strong>S/${parseFloat(item.precio).toFixed(2)}</strong>
                    </div>
                `;
            });
        }
        
        document.getElementById('cargandoTamanios').style.display = 'none';
        actualizarPrecioModal();
        
    } catch (error) {
        console.error('Error cargando precios:', error);
        contenedorTamanios.innerHTML = '<p class="text-danger">Error al cargar opciones de tamaño.</p>';
        document.getElementById('cargandoTamanios').style.display = 'none';
    }
}

// ============================================================================
// CÁLCULO DE PRECIOS
// ============================================================================

/**
 * Cambia la cantidad del producto en el modal
 */
function cambiarCantidad(cambio) {
    const inputCantidad = document.getElementById('cantidadProducto');
    let cantidadActual = parseInt(inputCantidad.value) || 1;
    cantidadActual += cambio;
    
    if (cantidadActual < 1) cantidadActual = 1;
    if (cantidadActual > 99) cantidadActual = 99;
    
    inputCantidad.value = cantidadActual;
    actualizarPrecioModal();
}

/**
 * Actualiza el precio total mostrado en el modal
 */
function actualizarPrecioModal() {
    let precioBase = 0;
    const tamanioSeleccionado = document.querySelector('input[name="radioTamanio"]:checked');
    
    if (tamanioSeleccionado) {
        precioBase = parseFloat(tamanioSeleccionado.value) || 0;
    }
    
    // Sumar precio de extras seleccionados
    let precioExtras = 0;
    document.querySelectorAll('#opcionesExtras input[type="checkbox"]:checked').forEach(extra => {
        precioExtras += parseFloat(extra.value) || 0;
    });

    const cantidad = parseInt(document.getElementById('cantidadProducto').value) || 1;
    const total = (precioBase + precioExtras) * cantidad;
    
    document.getElementById('precioFinalModal').textContent = `S/${total.toFixed(2)}`;
}

// ============================================================================
// INTEGRACIÓN CON EL CARRITO
// ============================================================================

/**
 * Agrega el producto configurado al carrito
 */
function agregarProductoAlCarritoFinal() {
    const tamanioSeleccionado = document.querySelector('input[name="radioTamanio"]:checked');
    
    if (!tamanioSeleccionado) {
        alert("Por favor, selecciona un tamaño de pizza.");
        return; 
    }

    if (!productoModalActual) {
        alert("Error: No se encontró información del producto.");
        return;
    }

    // Obtener datos del tamaño seleccionado
    const idTamanio = parseInt(tamanioSeleccionado.getAttribute('data-id-tamanio'));
    const nombreTamanio = tamanioSeleccionado.getAttribute('data-nombre-tamanio');
    const precioTamanio = parseFloat(tamanioSeleccionado.value) || 0;

    // Obtener cantidad
    const cantidad = parseInt(document.getElementById('cantidadProducto').value) || 1;
    
    // Obtener extras seleccionados
    let precioExtras = 0;
    let extrasSeleccionados = [];
    let idsExtras = [];
    
    document.querySelectorAll('#opcionesExtras input[name="extra"]:checked').forEach(extra => {
        precioExtras += parseFloat(extra.value) || 0;
        extrasSeleccionados.push(extra.getAttribute('data-nombre-extra'));
        idsExtras.push(parseInt(extra.getAttribute('data-id-extra')));
    });

    // Calcular precio unitario (tamaño + extras)
    const precioUnitario = precioTamanio + precioExtras;
    
    // Construir nombre descriptivo para el carrito
    const nombreConDetalle = `${productoModalActual.nombre} (${nombreTamanio})`;
    
    // Obtener imagen
    const imagenSrc = productoModalActual.imagenBase64 
        ? `data:image/jpeg;base64,${productoModalActual.imagenBase64}` 
        : '../Imagenes/default.jpg';

    // Crear objeto del item para el carrito
    const itemCarrito = {
        idProducto: productoModalActual.idProducto,
        nombreProducto: nombreConDetalle,
        idTamanio: idTamanio,
        nombreTamanio: nombreTamanio,
        cantidad: cantidad,
        precioUnitario: precioUnitario,
        precioTotal: precioUnitario * cantidad,
        idExtras: idsExtras,
        extras: extrasSeleccionados.length > 0 ? extrasSeleccionados.join(', ') : null,
        imagen: imagenSrc
    };

    // Llamar a la función del carrito (definida en carrito.js)
    if (typeof agregarProducto === 'function') {
        agregarProducto(itemCarrito);
    } else {
        console.error("La función agregarProducto no está definida. Asegúrate de que carrito.js esté cargado antes de Menu.js.");
        return;
    }
    
    // Cerrar el modal
    const modalInstance = bootstrap.Modal.getInstance(document.getElementById('modalOpciones'));
    if (modalInstance) {
        modalInstance.hide();
    }
    
    // Abrir el carrito
    if (typeof abrirCarrito === 'function') {
        abrirCarrito();
    }
}