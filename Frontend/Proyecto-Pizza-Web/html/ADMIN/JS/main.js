// Cambio de estadoo del sidebar
document.addEventListener('DOMContentLoaded', function () {
    const sidebarToggle = document.getElementById('sidebarToggle');
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');

    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', function () {
            if (window.innerWidth > 768) {
                // Vista de escritorio
                sidebar.classList.toggle('collapsed');
                content.classList.toggle('expanded');
            } else {
                // Vista en móvil
                sidebar.classList.toggle('mobile-expanded');
                content.classList.toggle('mobile-expanded');
            }
        });
    }

// Función para establecer la fecha actual
const dateAct = document.getElementById('currentDate');
    if (dateAct) {
        const now = new Date();
        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        dateAct.textContent = now.toLocaleDateString('es-ES', options);
    }

// Inicializador de la fecha al cargar la página
document.querySelectorAll('.sidebar-menu a').forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            const target = this.getAttribute('href');

            if (target && target !== '#') {
                window.location.href = target;
            }
        });
    });
});