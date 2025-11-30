// ==========================================
// GESTIÓN DE CATÁLOGO - ADMIN PANEL
// ==========================================

const API_BASE = 'http://localhost:8080/api';

// Variables globales
let productos = [];
let promociones = [];
let extras = [];

// ==========================================
// INICIALIZACIÓN
// ==========================================
document.addEventListener('DOMContentLoaded', function() {
    cargarTodosLosDatos();
    
    // Configurar preview de imagen para producto
    document.getElementById('productoImagen').addEventListener('change', function(e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('previewImagen').innerHTML = 
                    `<img src="${e.target.result}" class="img-thumbnail" style="max-height: 150px;">`;
            };
            reader.readAsDataURL(file);
        }
    });
});

// ==========================================
// CARGAR DATOS
// ==========================================
async function cargarTodosLosDatos() {
    await Promise.all([
        cargarProductos(),
        cargarPromociones(),
        cargarExtras()
    ]);
}

async function cargarProductos() {
    try {
        const response = await fetch(`${API_BASE}/productos`);
        if (!response.ok) throw new Error('Error al cargar productos');
        productos = await response.json();
        renderizarProductos();
        document.getElementById('totalProductos').textContent = productos.length;
    } catch (error) {
        console.error('Error cargando productos:', error);
        document.getElementById('tablaProductos').innerHTML = 
            '<tr><td colspan="5" class="text-center text-danger">Error al cargar productos</td></tr>';
    }
}

async function cargarPromociones() {
    try {
        const response = await fetch(`${API_BASE}/promociones`);
        if (!response.ok) throw new Error('Error al cargar promociones');
        promociones = await response.json();
        renderizarPromociones();
        
        // Contar promociones activas
        const hoy = new Date();
        const activas = promociones.filter(p => {
            const inicio = new Date(p.fechaInicio);
            const fin = new Date(p.fechaFin);
            return hoy >= inicio && hoy <= fin;
        });
        document.getElementById('totalPromociones').textContent = activas.length;
    } catch (error) {
        console.error('Error cargando promociones:', error);
        document.getElementById('tablaPromociones').innerHTML = 
            '<tr><td colspan="8" class="text-center text-danger">Error al cargar promociones</td></tr>';
    }
}

async function cargarExtras() {
    try {
        const response = await fetch(`${API_BASE}/extras`);
        if (!response.ok) throw new Error('Error al cargar extras');
        extras = await response.json();
        renderizarExtras();
        document.getElementById('totalExtras').textContent = extras.length;
    } catch (error) {
        console.error('Error cargando extras:', error);
        document.getElementById('tablaExtras').innerHTML = 
            '<tr><td colspan="4" class="text-center text-danger">Error al cargar extras</td></tr>';
    }
}

// ==========================================
// RENDERIZAR TABLAS
// ==========================================
function renderizarProductos() {
    const tbody = document.getElementById('tablaProductos');
    
    if (productos.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" class="text-center">No hay productos registrados</td></tr>';
        return;
    }
    
    tbody.innerHTML = productos.map(producto => `
        <tr>
            <td>${producto.idProducto}</td>
            <td>
                ${producto.imagenBase64 
                    ? `<img src="data:image/jpeg;base64,${producto.imagenBase64}" 
                           class="img-thumbnail" style="max-width: 60px; max-height: 60px;">`
                    : '<span class="text-muted">Sin imagen</span>'}
            </td>
            <td><strong>${producto.nombre}</strong></td>
            <td>${producto.descripcion || '-'}</td>
            <td>
                <button class="btn btn-sm btn-outline-primary me-1" onclick="editarProducto(${producto.idProducto})">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger" onclick="prepararEliminar('producto', ${producto.idProducto})">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

function renderizarPromociones() {
    const tbody = document.getElementById('tablaPromociones');
    
    if (promociones.length === 0) {
        tbody.innerHTML = '<tr><td colspan="8" class="text-center">No hay promociones registradas</td></tr>';
        return;
    }
    
    const hoy = new Date();
    
    tbody.innerHTML = promociones.map(promo => {
        const inicio = new Date(promo.fechaInicio);
        const fin = new Date(promo.fechaFin);
        let estado, estadoClass;
        
        if (hoy < inicio) {
            estado = 'Próxima';
            estadoClass = 'bg-info';
        } else if (hoy > fin) {
            estado = 'Vencida';
            estadoClass = 'bg-secondary';
        } else {
            estado = 'Activa';
            estadoClass = 'bg-success';
        }
        
        return `
            <tr>
                <td>${promo.idPromocion}</td>
                <td><strong>${promo.nombre}</strong></td>
                <td>${promo.descripcion || '-'}</td>
                <td><span class="badge bg-warning text-dark">${promo.descuentoPorcentaje}%</span></td>
                <td>${formatearFecha(promo.fechaInicio)}</td>
                <td>${formatearFecha(promo.fechaFin)}</td>
                <td><span class="badge ${estadoClass}">${estado}</span></td>
                <td>
                    <button class="btn btn-sm btn-outline-primary me-1" onclick="editarPromocion(${promo.idPromocion})">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-sm btn-outline-danger" onclick="prepararEliminar('promocion', ${promo.idPromocion})">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>
        `;
    }).join('');
}

function renderizarExtras() {
    const tbody = document.getElementById('tablaExtras');
    
    if (extras.length === 0) {
        tbody.innerHTML = '<tr><td colspan="4" class="text-center">No hay extras registrados</td></tr>';
        return;
    }
    
    tbody.innerHTML = extras.map(extra => `
        <tr>
            <td>${extra.idExtra}</td>
            <td><strong>${extra.nombre}</strong></td>
            <td>S/ ${parseFloat(extra.precio).toFixed(2)}</td>
            <td>
                <button class="btn btn-sm btn-outline-primary me-1" onclick="editarExtra(${extra.idExtra})">
                    <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger" onclick="prepararEliminar('extra', ${extra.idExtra})">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        </tr>
    `).join('');
}

// ==========================================
// MODALES - ABRIR
// ==========================================
function abrirModalProducto() {
    document.getElementById('modalProductoTitulo').textContent = 'Nuevo Producto';
    document.getElementById('formProducto').reset();
    document.getElementById('productoId').value = '';
    document.getElementById('previewImagen').innerHTML = '';
}

function abrirModalPromocion() {
    document.getElementById('modalPromocionTitulo').textContent = 'Nueva Promoción';
    document.getElementById('formPromocion').reset();
    document.getElementById('promocionId').value = '';
}

function abrirModalExtra() {
    document.getElementById('modalExtraTitulo').textContent = 'Nuevo Extra';
    document.getElementById('formExtra').reset();
    document.getElementById('extraId').value = '';
}

// ==========================================
// EDITAR
// ==========================================
function editarProducto(id) {
    const producto = productos.find(p => p.idProducto === id);
    if (!producto) return;
    
    document.getElementById('modalProductoTitulo').textContent = 'Editar Producto';
    document.getElementById('productoId').value = producto.idProducto;
    document.getElementById('productoNombre').value = producto.nombre;
    document.getElementById('productoDescripcion').value = producto.descripcion || '';
    
    if (producto.imagenBase64) {
        document.getElementById('previewImagen').innerHTML = 
            `<img src="data:image/jpeg;base64,${producto.imagenBase64}" class="img-thumbnail" style="max-height: 150px;">`;
    } else {
        document.getElementById('previewImagen').innerHTML = '';
    }
    
    new bootstrap.Modal(document.getElementById('modalProducto')).show();
}

function editarPromocion(id) {
    const promo = promociones.find(p => p.idPromocion === id);
    if (!promo) return;
    
    document.getElementById('modalPromocionTitulo').textContent = 'Editar Promoción';
    document.getElementById('promocionId').value = promo.idPromocion;
    document.getElementById('promocionNombre').value = promo.nombre;
    document.getElementById('promocionDescripcion').value = promo.descripcion || '';
    document.getElementById('promocionDescuento').value = promo.descuentoPorcentaje;
    document.getElementById('promocionFechaInicio').value = promo.fechaInicio;
    document.getElementById('promocionFechaFin').value = promo.fechaFin;
    document.getElementById('promocionImagen').value = promo.imagenReferencia || '';
    
    new bootstrap.Modal(document.getElementById('modalPromocion')).show();
}

function editarExtra(id) {
    const extra = extras.find(e => e.idExtra === id);
    if (!extra) return;
    
    document.getElementById('modalExtraTitulo').textContent = 'Editar Extra';
    document.getElementById('extraId').value = extra.idExtra;
    document.getElementById('extraNombre').value = extra.nombre;
    document.getElementById('extraPrecio').value = extra.precio;
    
    new bootstrap.Modal(document.getElementById('modalExtra')).show();
}

// ==========================================
// GUARDAR
// ==========================================
async function guardarProducto() {
    const id = document.getElementById('productoId').value;
    const nombre = document.getElementById('productoNombre').value.trim();
    const descripcion = document.getElementById('productoDescripcion').value.trim();
    const imagenInput = document.getElementById('productoImagen');
    
    if (!nombre) {
        mostrarNotificacion('Error', 'El nombre es requerido', 'danger');
        return;
    }
    
    const producto = {
        nombre: nombre,
        descripcion: descripcion
    };
    
    // Si hay imagen nueva, convertir a Base64
    if (imagenInput.files.length > 0) {
        const file = imagenInput.files[0];
        producto.imagenBase64 = await convertirABase64(file);
    }
    
    try {
        const url = id ? `${API_BASE}/productos/${id}` : `${API_BASE}/productos`;
        const method = id ? 'PUT' : 'POST';
        
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(producto)
        });
        
        if (!response.ok) throw new Error('Error al guardar');
        
        bootstrap.Modal.getInstance(document.getElementById('modalProducto')).hide();
        mostrarNotificacion('Éxito', `Producto ${id ? 'actualizado' : 'creado'} correctamente`, 'success');
        await cargarProductos();
    } catch (error) {
        console.error('Error:', error);
        mostrarNotificacion('Error', 'No se pudo guardar el producto', 'danger');
    }
}

async function guardarPromocion() {
    const id = document.getElementById('promocionId').value;
    const nombre = document.getElementById('promocionNombre').value.trim();
    const descripcion = document.getElementById('promocionDescripcion').value.trim();
    const descuento = document.getElementById('promocionDescuento').value;
    const fechaInicio = document.getElementById('promocionFechaInicio').value;
    const fechaFin = document.getElementById('promocionFechaFin').value;
    const imagen = document.getElementById('promocionImagen').value.trim();
    
    if (!nombre || !descuento || !fechaInicio || !fechaFin) {
        mostrarNotificacion('Error', 'Complete todos los campos requeridos', 'danger');
        return;
    }
    
    const promocion = {
        nombre: nombre,
        descripcion: descripcion,
        descuentoPorcentaje: parseFloat(descuento),
        fechaInicio: fechaInicio,
        fechaFin: fechaFin,
        imagenReferencia: imagen
    };
    
    try {
        const url = id ? `${API_BASE}/promociones/${id}` : `${API_BASE}/promociones`;
        const method = id ? 'PUT' : 'POST';
        
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(promocion)
        });
        
        if (!response.ok) throw new Error('Error al guardar');
        
        bootstrap.Modal.getInstance(document.getElementById('modalPromocion')).hide();
        mostrarNotificacion('Éxito', `Promoción ${id ? 'actualizada' : 'creada'} correctamente`, 'success');
        await cargarPromociones();
    } catch (error) {
        console.error('Error:', error);
        mostrarNotificacion('Error', 'No se pudo guardar la promoción', 'danger');
    }
}

async function guardarExtra() {
    const id = document.getElementById('extraId').value;
    const nombre = document.getElementById('extraNombre').value.trim();
    const precio = document.getElementById('extraPrecio').value;
    
    if (!nombre || !precio) {
        mostrarNotificacion('Error', 'Complete todos los campos', 'danger');
        return;
    }
    
    const extra = {
        nombre: nombre,
        precio: parseFloat(precio)
    };
    
    try {
        const url = id ? `${API_BASE}/extras/${id}` : `${API_BASE}/extras`;
        const method = id ? 'PUT' : 'POST';
        
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(extra)
        });
        
        if (!response.ok) throw new Error('Error al guardar');
        
        bootstrap.Modal.getInstance(document.getElementById('modalExtra')).hide();
        mostrarNotificacion('Éxito', `Extra ${id ? 'actualizado' : 'creado'} correctamente`, 'success');
        await cargarExtras();
    } catch (error) {
        console.error('Error:', error);
        mostrarNotificacion('Error', 'No se pudo guardar el extra', 'danger');
    }
}

// ==========================================
// ELIMINAR
// ==========================================
function prepararEliminar(tipo, id) {
    document.getElementById('eliminarTipo').value = tipo;
    document.getElementById('eliminarId').value = id;
    new bootstrap.Modal(document.getElementById('modalConfirmarEliminar')).show();
}

async function confirmarEliminar() {
    const tipo = document.getElementById('eliminarTipo').value;
    const id = document.getElementById('eliminarId').value;
    
    let url;
    switch(tipo) {
        case 'producto':
            url = `${API_BASE}/productos/${id}`;
            break;
        case 'promocion':
            url = `${API_BASE}/promociones/${id}`;
            break;
        case 'extra':
            url = `${API_BASE}/extras/${id}`;
            break;
    }
    
    try {
        const response = await fetch(url, { method: 'DELETE' });
        
        if (!response.ok) throw new Error('Error al eliminar');
        
        bootstrap.Modal.getInstance(document.getElementById('modalConfirmarEliminar')).hide();
        mostrarNotificacion('Éxito', 'Elemento eliminado correctamente', 'success');
        
        // Recargar datos según el tipo
        switch(tipo) {
            case 'producto':
                await cargarProductos();
                break;
            case 'promocion':
                await cargarPromociones();
                break;
            case 'extra':
                await cargarExtras();
                break;
        }
    } catch (error) {
        console.error('Error:', error);
        mostrarNotificacion('Error', 'No se pudo eliminar el elemento', 'danger');
    }
}

// ==========================================
// UTILIDADES
// ==========================================
function convertirABase64(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = () => {
            // Remover el prefijo "data:image/xxx;base64," para enviar solo la cadena Base64
            const base64 = reader.result.split(',')[1];
            resolve(base64);
        };
        reader.onerror = reject;
        reader.readAsDataURL(file);
    });
}

function formatearFecha(fecha) {
    if (!fecha) return '-';
    const d = new Date(fecha);
    return d.toLocaleDateString('es-PE', { day: '2-digit', month: '2-digit', year: 'numeric' });
}

function mostrarNotificacion(titulo, mensaje, tipo = 'info') {
    const toast = document.getElementById('toastNotificacion');
    const toastTitulo = document.getElementById('toastTitulo');
    const toastMensaje = document.getElementById('toastMensaje');
    
    toastTitulo.textContent = titulo;
    toastMensaje.textContent = mensaje;
    
    // Cambiar color según tipo
    toast.classList.remove('bg-success', 'bg-danger', 'bg-warning', 'bg-info');
    if (tipo === 'success') toast.classList.add('bg-success', 'text-white');
    else if (tipo === 'danger') toast.classList.add('bg-danger', 'text-white');
    else if (tipo === 'warning') toast.classList.add('bg-warning');
    
    const bsToast = new bootstrap.Toast(toast);
    bsToast.show();
}
