// Variable global para almacenar todos los productos una vez cargados
let todosLosProductos = [];

// Variable para almacenar temporalmente la información del producto actual del modal
let productoModalActual = null;

// Ejecutar al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    cargarProductos();
    configurarEventosModal();
});

// --------------------------------------------------------------------------
// LÓGICA DE CARGA DE PRODUCTOS
// --------------------------------------------------------------------------

async function cargarProductos() {
    const container = document.getElementById('productos-container');
    const cargando = document.getElementById('cargando-productos');
    
    try {
        const respuesta = await fetch('http://localhost:8080/api/productos');
        if (!respuesta.ok) throw new Error('Error en la API de productos.');
        
        todosLosProductos = await respuesta.json(); // Guardamos los DTOs completos
        cargando.style.display = 'none'; // Ocultar mensaje de carga
        
        if (todosLosProductos.length === 0) {
            container.innerHTML = '<p class="text-center w-100">No se encontraron productos en el menú.</p>';
            return;
        }

        todosLosProductos.forEach(producto => {
            container.innerHTML += crearCardProducto(producto);
        });

    } catch (error) {
        console.error('Error al cargar productos:', error);
        cargando.textContent = 'Error: No se pudo conectar con el servidor para cargar el menú.';
    }
}

function crearCardProducto(producto) {
    // La imagen viene en formato Base64. Usamos el prefijo 'data:image/jpeg;base64,'
    // Puedes ajustar 'jpeg' por el tipo de imagen real si lo conoces (png, webp, etc.)
    const imagenSrc = producto.imagenBase64 
        ? `data:image/jpeg;base64,${producto.imagenBase64}` 
        : '../Imagenes/default.jpg'; // Imagen por defecto si falla la Base64

    return `
        <div class="col">
            <div class="card h-100"> 
                <img src="${imagenSrc}" class="card-img-top pizza-circular" alt="${producto.nombre}">
                <div class="card-body">
                    <h5 class="card-title">${producto.nombre}</h5>
                    <p class="card-text">${producto.descripcion}</p>
                    </div>
                <div class="card-footer text-center">
                    <button class="btn btn-primary btn-modal-agregar"
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

// --------------------------------------------------------------------------
// LÓGICA DEL MODAL Y PRECIOS
// --------------------------------------------------------------------------

function configurarEventosModal() {
    const modalOpciones = document.getElementById('modalOpciones');
    
    // Al abrir el modal, cargamos la información específica del producto
    modalOpciones.addEventListener('show.bs.modal', function (event) {
        const boton = event.relatedTarget;
        const idProducto = boton.getAttribute('data-id-producto');
        const nombreProducto = boton.getAttribute('data-nombre');

        // Busca el producto DTO completo en la lista global
        productoModalActual = todosLosProductos.find(p => p.idProducto == idProducto);

        document.getElementById('nombreProductoModal').textContent = nombreProducto;
        document.getElementById('cargandoTamanios').style.display = 'block';
        document.getElementById('cantidadProducto').value = 1; // Resetear cantidad
        
        // Deseleccionar extras previos
        document.querySelectorAll('#opcionesExtras input[type="checkbox"]').forEach(checkbox => checkbox.checked = false);

        cargarOpcionesDeTamanio(idProducto);
    });
    
    // Escuchar cualquier cambio en el modal (radio buttons, checkboxes, cantidad)
    modalOpciones.addEventListener('change', actualizarPrecioModal);
    
    // Eventos de cantidad
    document.getElementById('btnSumarCantidad').addEventListener('click', () => cambiarCantidad(1));
    document.getElementById('btnRestarCantidad').addEventListener('click', () => cambiarCantidad(-1));

    // Evento del botón final de agregar al carrito
    document.getElementById('btnAgregarAlCarrito').addEventListener('click', agregarProductoAlCarritoFinal);
}

async function cargarOpcionesDeTamanio(idProducto) {
    const contenedorTamanios = document.getElementById('opcionesTamanios');
    contenedorTamanios.innerHTML = ''; // Limpia
    
    try {
        const respuesta = await fetch(`http://localhost:8080/api/precios/${idProducto}`);
        if (!respuesta.ok) throw new Error('Error al obtener precios.');
        
        const precios = await respuesta.json();

        if (precios.length === 0) {
            contenedorTamanios.innerHTML = '<p class="text-danger">No hay opciones de tamaño/precio disponibles.</p>';
        } else {
            precios.forEach((item, index) => {
                const checked = index === 0 ? 'checked' : ''; // Selecciona el primero por defecto
                
                contenedorTamanios.innerHTML += `
                    <div class="form-check list-group-item d-flex justify-content-between align-items-center">
                        <input class="form-check-input" type="radio" name="radioTamanio" id="tamanio-${item.idTamanio}" 
                               value="${item.precio}" data-id-tamanio="${item.idTamanio}" data-nombre-tamanio="${item.nombreTamanio}" ${checked}>
                        <label class="form-check-label flex-grow-1" for="tamanio-${item.idTamanio}">
                            ${item.nombreTamanio}
                        </label>
                        <strong>S/${parseFloat(item.precio).toFixed(2)}</strong>
                    </div>
                `;
            });
        }
        document.getElementById('cargandoTamanios').style.display = 'none';
        actualizarPrecioModal(); // Actualiza el precio con la opción por defecto
    } catch (error) {
        console.error('Error cargando precios:', error);
        contenedorTamanios.innerHTML = '<p class="text-danger">Error de conexión con el servidor.</p>';
    }
}

function cambiarCantidad(cambio) {
    const inputCantidad = document.getElementById('cantidadProducto');
    let cantidadActual = parseInt(inputCantidad.value);
    cantidadActual += cambio;
    
    if (cantidadActual < 1) cantidadActual = 1;
    
    inputCantidad.value = cantidadActual;
    actualizarPrecioModal();
}

function actualizarPrecioModal() {
    let precioBase = 0;
    const tamanioSeleccionado = document.querySelector('input[name="radioTamanio"]:checked');
    
    if (tamanioSeleccionado) {
        precioBase = parseFloat(tamanioSeleccionado.value);
    }
    
    let precioExtras = 0;
    document.querySelectorAll('#opcionesExtras input[type="checkbox"]:checked').forEach(extra => {
        precioExtras += parseFloat(extra.value);
    });

    const cantidad = parseInt(document.getElementById('cantidadProducto').value);
    const total = (precioBase + precioExtras) * cantidad;
    
    document.getElementById('precioFinalModal').textContent = `S/${total.toFixed(2)}`;
}

function agregarProductoAlCarritoFinal() {
    const tamanioSeleccionado = document.querySelector('input[name="radioTamanio"]:checked');
    if (!tamanioSeleccionado) {
        alert("Por favor, selecciona un tamaño de pizza.");
        return; 
    }

    const cantidad = parseInt(document.getElementById('cantidadProducto').value);
    const precioUnitario = parseFloat(tamanioSeleccionado.value);
    
    let precioExtras = 0;
    let extrasSeleccionados = [];
    document.querySelectorAll('#opcionesExtras input[name="extra"]:checked').forEach(extra => {
        precioExtras += parseFloat(extra.value);
        extrasSeleccionados.push(extra.getAttribute('data-nombre-extra'));
    });

    const precioTotalUnitario = precioUnitario + precioExtras;
    const precioFinal = precioTotalUnitario * cantidad;
    
    // Detalles del Producto para el Carrito
    const nombreTamanio = tamanioSeleccionado.getAttribute('data-nombre-tamanio');
    const nombreConDetalle = `${productoModalActual.nombre} (${nombreTamanio})`;
    const imagenSrc = `data:image/jpeg;base64,${productoModalActual.imagenBase64}`; // Imagen Base64

    const detalles = {
        extras: extrasSeleccionados.length > 0 ? extrasSeleccionados.join(', ') : 'Ninguno',
    };
    
    // Llamada a la función global (de carrito.js)
    if (typeof agregarProducto === 'function') {
        agregarProducto(nombreConDetalle, precioFinal, imagenSrc, cantidad, detalles);
    } else {
        console.error("La función agregarProducto no está definida. Asegúrate de que carrito.js esté cargado.");
        return;
    }
    
    // Cerrar el modal y abrir el carrito
    const modalInstance = bootstrap.Modal.getInstance(document.getElementById('modalOpciones'));
    modalInstance.hide();
    
    if (typeof abrirCarrito === 'function') {
        abrirCarrito();
    }
}