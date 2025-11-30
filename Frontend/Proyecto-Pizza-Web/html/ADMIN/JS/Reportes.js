// ==========================================
// REPORTES - ADMIN PANEL
// ==========================================

const API_BASE = 'http://localhost:8080/api';

// Variables globales
let reporteData = null;

// ==========================================
// INICIALIZACIÓN
// ==========================================
document.addEventListener('DOMContentLoaded', function() {
    cargarReporte();
});

// ==========================================
// CARGAR REPORTE
// ==========================================
async function cargarReporte() {
    try {
        mostrarCargando(true);
        
        const response = await fetch(`${API_BASE}/reportes`);
        if (!response.ok) throw new Error('Error al cargar reporte');
        
        reporteData = await response.json();
        
        renderizarPeriodos();
        renderizarProductosMasVendidos();
        renderizarMetodosPago();
        renderizarVentasPorHora();
        
        mostrarCargando(false);
    } catch (error) {
        console.error('Error cargando reporte:', error);
        mostrarError('Error al cargar los datos del reporte');
        mostrarCargando(false);
    }
}

// ==========================================
// RENDERIZAR PERIODOS DE VENTA
// ==========================================
function renderizarPeriodos() {
    if (!reporteData) return;
    
    // Hoy
    actualizarPeriodo('periodoHoy', reporteData.hoy);
    // Esta Semana
    actualizarPeriodo('periodoSemana', reporteData.estaSemana);
    // Este Mes
    actualizarPeriodo('periodoMes', reporteData.esteMes);
    // Este Año
    actualizarPeriodo('periodoAnio', reporteData.esteAnio);
}

function actualizarPeriodo(elementId, datos) {
    const elemento = document.getElementById(elementId);
    if (!elemento || !datos) return;
    
    const valor = elemento.querySelector('.periodo-valor');
    const detalle = elemento.querySelector('.periodo-detalle span');
    const trend = elemento.querySelector('.periodo-trend');
    
    if (valor) valor.textContent = `S/ ${formatearNumero(datos.totalVentas)}`;
    if (detalle) detalle.textContent = `${datos.cantidadOrdenes} órdenes`;
    
    if (trend) {
        const porcentaje = datos.porcentajeCambio || 0;
        const esPositivo = porcentaje >= 0;
        
        trend.className = `periodo-trend ${esPositivo ? 'trend-positive' : 'trend-negative'}`;
        trend.innerHTML = `
            <i class="bi bi-arrow-${esPositivo ? 'up' : 'down'}-right me-1"></i>
            ${esPositivo ? '+' : ''}${porcentaje}%
        `;
    }
}

// ==========================================
// RENDERIZAR PRODUCTOS MÁS VENDIDOS
// ==========================================
function renderizarProductosMasVendidos() {
    const contenedor = document.getElementById('productosMasVendidos');
    if (!contenedor || !reporteData?.productosMasVendidos) return;
    
    const productos = reporteData.productosMasVendidos;
    
    if (productos.length === 0) {
        contenedor.innerHTML = '<p class="text-muted text-center">No hay datos de ventas este mes</p>';
        return;
    }
    
    // Calcular máximo para las barras de progreso
    const maxCantidad = Math.max(...productos.map(p => p.cantidadVendida));
    
    contenedor.innerHTML = productos.map(producto => {
        const porcentajeBarra = maxCantidad > 0 ? (producto.cantidadVendida / maxCantidad * 100) : 0;
        const rankingClass = producto.ranking <= 3 ? `ranking-${producto.ranking}` : '';
        
        return `
            <div class="producto-vendido-card">
                <div class="producto-vendido-header">
                    <div class="producto-nombre">${producto.nombre}</div>
                    <div class="producto-precio">S/ ${formatearNumero(producto.totalVentas)}</div>
                </div>
                <div class="producto-vendido-info">
                    <div class="producto-unidades">${producto.cantidadVendida} unidades vendidas</div>
                    <span class="producto-ranking ${rankingClass}">#${producto.ranking}</span>
                </div>
                <div class="barra-progreso">
                    <div class="barra-progreso-fill" style="width: ${porcentajeBarra}%"></div>
                </div>
            </div>
        `;
    }).join('');
}

// ==========================================
// RENDERIZAR MÉTODOS DE PAGO
// ==========================================
function renderizarMetodosPago() {
    const contenedor = document.getElementById('metodosPago');
    if (!contenedor || !reporteData?.ventasPorMetodoPago) return;
    
    const metodos = reporteData.ventasPorMetodoPago;
    
    if (metodos.length === 0) {
        contenedor.innerHTML = '<p class="text-muted text-center">No hay datos de métodos de pago</p>';
        return;
    }
    
    // Iconos para métodos de pago
    const iconos = {
        'Efectivo': 'bi-cash-coin',
        'Tarjeta de Crédito': 'bi-credit-card',
        'Tarjeta de Débito': 'bi-credit-card-2-front',
        'Yape': 'bi-phone',
        'Plin': 'bi-phone-fill'
    };
    
    contenedor.innerHTML = metodos.map(metodo => {
        const icono = iconos[metodo.metodoPago] || 'bi-wallet2';
        
        return `
            <div class="metodo-pago-card">
                <div class="metodo-pago-header">
                    <div class="metodo-pago-icon">
                        <i class="bi ${icono}"></i>
                    </div>
                    <div class="metodo-pago-info">
                        <div class="metodo-pago-nombre">${metodo.metodoPago}</div>
                        <div class="metodo-pago-ordenes">${metodo.cantidadOrdenes} órdenes</div>
                    </div>
                </div>
                <div class="metodo-pago-valores">
                    <div class="metodo-pago-total">S/ ${formatearNumero(metodo.totalVentas)}</div>
                    <div class="metodo-pago-porcentaje">${metodo.porcentaje}%</div>
                </div>
                <div class="barra-progreso">
                    <div class="barra-progreso-fill" style="width: ${metodo.porcentaje}%"></div>
                </div>
            </div>
        `;
    }).join('');
}

// ==========================================
// RENDERIZAR VENTAS POR HORA
// ==========================================
function renderizarVentasPorHora() {
    const contenedor = document.getElementById('ventasPorHora');
    if (!contenedor || !reporteData?.ventasPorHora) return;
    
    const ventas = reporteData.ventasPorHora;
    
    if (ventas.length === 0) {
        contenedor.innerHTML = '<p class="text-muted text-center">No hay datos de ventas por hora</p>';
        return;
    }
    
    contenedor.innerHTML = `
        <div class="row">
            ${ventas.map(venta => `
                <div class="col-md-3 mb-4">
                    <div class="card horario-card bg-light">
                        <div class="horario-rango">${venta.rangoHora}</div>
                        <div class="horario-valor">S/ ${formatearNumero(venta.totalVentas)}</div>
                        <div class="horario-ordenes">${venta.cantidadOrdenes} órdenes</div>
                        ${venta.esHoraPico ? '<div class="horario-etiqueta etiqueta-pico">Hora Pico</div>' : ''}
                    </div>
                </div>
            `).join('')}
        </div>
    `;
}

// ==========================================
// UTILIDADES
// ==========================================
function formatearNumero(numero) {
    if (numero === null || numero === undefined) return '0.00';
    return parseFloat(numero).toLocaleString('es-PE', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}

function mostrarCargando(mostrar) {
    const elementos = document.querySelectorAll('.periodo-valor, .producto-vendido-card, .metodo-pago-card');
    elementos.forEach(el => {
        if (mostrar) {
            el.style.opacity = '0.5';
        } else {
            el.style.opacity = '1';
        }
    });
}

function mostrarError(mensaje) {
    // Toast de error
    const toastContainer = document.querySelector('.toast-container') || crearToastContainer();
    const toastHtml = `
        <div class="toast show bg-danger text-white" role="alert">
            <div class="toast-header bg-danger text-white">
                <strong class="me-auto">Error</strong>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="toast"></button>
            </div>
            <div class="toast-body">${mensaje}</div>
        </div>
    `;
    toastContainer.innerHTML = toastHtml;
    
    setTimeout(() => {
        toastContainer.innerHTML = '';
    }, 5000);
}

function crearToastContainer() {
    const container = document.createElement('div');
    container.className = 'toast-container position-fixed bottom-0 end-0 p-3';
    document.body.appendChild(container);
    return container;
}

// Actualizar reporte cada 5 minutos
setInterval(cargarReporte, 300000);
