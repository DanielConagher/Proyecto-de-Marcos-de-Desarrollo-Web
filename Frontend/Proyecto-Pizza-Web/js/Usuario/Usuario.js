document.addEventListener('DOMContentLoaded', function() {
    // URL base de tu API de Spring Boot
    const API_URL = 'http://localhost:8080/api/usuarios';

    // 1. VERIFICAR SI EL USUARIO ESTÁ LOGUEADO
    // Por ahora, asumimos que si llegas aquí, estás logueado y tu backend usa el ID 1.
    // **NOTA:** Esto debería ser reemplazado por la verificación de un token JWT en un entorno real.
    const estaLogueado = localStorage.getItem("usuarioLogueado") === "true";
    
    if (!estaLogueado) {
        alert("Debes iniciar sesión");
        window.location.href = "loguin.html";
        return;
    }
    
    // 2. OCULTAR CAMPOS DE CONTRASEÑA AL INICIO
    const campoPasswordActual = document.getElementById("campoPasswordActual");
    const camposNuevaPassword = document.getElementById("camposNuevaPassword");
    campoPasswordActual.style.display = "none";
    camposNuevaPassword.style.display = "none";
    
    
    // 3. CARGAR DATOS DEL USUARIO DESDE EL BACKEND
    async function cargarDatosUsuario() {
        try {
            const response = await fetch(`${API_URL}/perfil`);
            if (!response.ok) {
                if (response.status === 404) {
                    throw new Error("Perfil de usuario no encontrado en la API.");
                }
                throw new Error(`Error al cargar el perfil: ${response.statusText}`);
            }
            
            const userData = await response.json();

            // Mapeo del DTO del Backend a la vista del Frontend
            document.getElementById("nombre").value = userData.nombre || "";
            document.getElementById("email").value = userData.correo || "";
            document.getElementById("telefono").value = userData.telefono || "";
            document.getElementById("direccion").value = userData.direccion || "";
            
            // Asumiendo que el campo 'avatar' del DTO del backend es nulo
            // Por ahora, el avatar se seguirá gestionando en localStorage o se cargará un valor por defecto.
            const avatar = localStorage.getItem("avatar") || "../Imagenes/PerfilDefecto.jpg";
            document.getElementById("fotoPerfilActual").src = avatar;

            // Almacenamos el correo actual para la validación de la contraseña
            localStorage.setItem("temp_current_email", userData.correo || "");

        } catch (error) {
            console.error("Error en cargarDatosUsuario:", error.message);
            mostrarMensaje("No se pudieron cargar los datos del perfil.", "error");
        }
    }
    
    // Cargar datos iniciales
    cargarDatosUsuario();

    // 4. VARIABLES GLOBALES
    let modoEdicion = false;
    let avatarSeleccionado = localStorage.getItem("avatar") || "../Imagenes/PerfilDefecto.jpg";

    // 5. ELEMENTOS
    const botonEditar = document.getElementById("btnEditar");
    const botonGuardar = document.getElementById("btnGuardar");
    const botonCambiarAvatar = document.getElementById("btnCambiarAvatar");
    const enlaceCambiarPassword = document.getElementById("enlaceCambiarPassword");
    const selectorAvatar = document.getElementById("selectorAvatar");
    const opcionesAvatar = document.querySelectorAll(".opcion-avatar");
    const formulario = document.getElementById("formularioPerfil");
    
    // 6. DESHABILITAR BOTÓN CAMBIAR AVATAR AL INICIO
    botonCambiarAvatar.disabled = true;
    botonCambiarAvatar.style.opacity = "0.6";
    botonCambiarAvatar.style.cursor = "not-allowed";
    
    // 7. FUNCIÓN PARA CAMBIAR MODO EDICIÓN
    function toggleModoEdicion() {
        modoEdicion = !modoEdicion;
        const campos = formulario.querySelectorAll("input, textarea");
        
        if (modoEdicion) {
            // ACTIVAR MODO EDICIÓN
            campos.forEach(campo => {
                if (campo.id !== "email") campo.removeAttribute("readonly");
            });
            botonGuardar.disabled = false;
            botonEditar.textContent = "Cancelar Edición";
            botonEditar.classList.remove("btn-primary");
            botonEditar.classList.add("btn-secondary");
            
            // HABILITAR botón cambiar avatar
            botonCambiarAvatar.disabled = false;
            botonCambiarAvatar.style.opacity = "1";
            botonCambiarAvatar.style.cursor = "pointer";
            
        } else {
            // DESACTIVAR MODO EDICIÓN
            campos.forEach(campo => campo.setAttribute("readonly", true));
            botonGuardar.disabled = true;
            botonEditar.textContent = "Editar Información";
            botonEditar.classList.remove("btn-secondary");
            botonEditar.classList.add("btn-primary");
            
            // DESHABILITAR botón cambiar avatar
            botonCambiarAvatar.disabled = true;
            botonCambiarAvatar.style.opacity = "0.6";
            botonCambiarAvatar.style.cursor = "not-allowed";
            
            // Ocultar selector de avatar si está visible
            selectorAvatar.style.display = "none";
            botonCambiarAvatar.textContent = "Cambiar Avatar";
            
            // Ocultar campos de contraseña
            campoPasswordActual.style.display = "none";
            camposNuevaPassword.style.display = "none";
            enlaceCambiarPassword.textContent = "¿Deseas cambiar tu contraseña?";
            
            // Recargar datos por si canceló
            cargarDatosUsuario();
        }
    }
    
    // 8. FUNCIÓN PARA SELECTOR DE AVATAR (sin cambios)
    function toggleSelectorAvatar() {
        if (!modoEdicion) return;
        if (selectorAvatar.style.display === "none" || !selectorAvatar.style.display) {
            selectorAvatar.style.display = "block";
            botonCambiarAvatar.textContent = "Ocultar Avatares";
        } else {
            selectorAvatar.style.display = "none";
            botonCambiarAvatar.textContent = "Cambiar Avatar";
        }
    }
    
    // 9. FUNCIÓN PARA SELECCIONAR AVATAR (sin cambios)
    function seleccionarAvatar(event) {
        if (!modoEdicion) return;
        if (event.target.classList.contains("opcion-avatar")) {
            avatarSeleccionado = event.target.getAttribute("data-avatar-src");
            const fotoGrande = document.getElementById("fotoPerfilActual");
            fotoGrande.src = avatarSeleccionado;
            
            // Resaltar avatar seleccionado
            opcionesAvatar.forEach(opcion => {
                opcion.style.border = "2px solid transparent";
            });
            event.target.style.border = "2px solid #3498db";
        }
    }
    
    // 10. FUNCIÓN PARA CAMBIO DE CONTRASEÑA (sin cambios en la visibilidad)
    function toggleCamposPassword(event) {
        event.preventDefault();
        if (!modoEdicion) {
            alert("Debes estar en modo edición para cambiar la contraseña");
            return;
        }
        
        if (campoPasswordActual.style.display === "none" || !campoPasswordActual.style.display) {
            campoPasswordActual.style.display = "block";
            camposNuevaPassword.style.display = "block";
            enlaceCambiarPassword.textContent = "Ocultar cambio de contraseña";
        } else {
            campoPasswordActual.style.display = "none";
            camposNuevaPassword.style.display = "none";
            enlaceCambiarPassword.textContent = "¿Deseas cambiar tu contraseña?";
            // Limpiar campos
            document.getElementById("passwordActual").value = "";
            document.getElementById("nuevaPassword").value = "";
            document.getElementById("confirmarPassword").value = "";
        }
    }
    
    // 11. FUNCIÓN PARA VALIDAR Y GUARDAR DATOS EN EL BACKEND
    async function guardarDatos(event) {
        event.preventDefault();
        
        const nombre = document.getElementById("nombre").value;
        const telefono = document.getElementById("telefono").value;
        const direccion = document.getElementById("direccion").value;
        
        // Objeto DTO a enviar al backend
        const dto = {
            nombre,
            correo: localStorage.getItem("temp_current_email"), // El correo no se cambia, se envía para coherencia
            telefono: parseInt(telefono), // Aseguramos que sea número
            direccion, // Aquí se envía la dirección (que debe ser el ID si el campo se edita)
            avatar: avatarSeleccionado // El avatar se maneja en el frontend por ahora
        };

        let passwordsChanged = false;

        // Validar y enviar cambio de contraseñas si se están cambiando
        if (campoPasswordActual.style.display !== "none") {
            const nuevaPassword = document.getElementById("nuevaPassword").value;
            const confirmarPassword = document.getElementById("confirmarPassword").value;
            const passwordActual = document.getElementById("passwordActual").value;
            
            if (!passwordActual || !nuevaPassword || !confirmarPassword) {
                alert("Todos los campos de contraseña son obligatorios");
                return;
            }
            if (nuevaPassword !== confirmarPassword) {
                alert("Las nuevas contraseñas no coinciden");
                return;
            }
            if (nuevaPassword.length < 6) {
                alert("La nueva contraseña debe tener al menos 6 caracteres");
                return;
            }

            try {
                const responsePassword = await fetch(`${API_URL}/cambiar-password`, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        contrasenaActual: passwordActual,
                        nuevaContrasena: nuevaPassword
                    })
                });

                if (!responsePassword.ok) {
                    const errorText = await responsePassword.text();
                    alert(`Error al cambiar contraseña: ${errorText}`);
                    return;
                }
                passwordsChanged = true;
            } catch (error) {
                console.error("Error al cambiar contraseña:", error);
                alert("Hubo un error de red al cambiar la contraseña.");
                return;
            }
        }
        
        // 11.2. GUARDAR OTROS DATOS EN EL BACKEND
        try {
            const responsePerfil = await fetch(`${API_URL}/perfil`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(dto)
            });

            if (!responsePerfil.ok) {
                throw new Error("No se pudo actualizar la información del perfil.");
            }
            
            // Guardar solo el avatar localmente (si lo cambiaste)
            localStorage.setItem("avatar", avatarSeleccionado);
            
            // Mostrar mensaje de éxito
            if (passwordsChanged) {
                mostrarMensaje("Contraseña y perfil actualizados correctamente.", "success");
            } else {
                mostrarMensaje("Información guardada correctamente.", "success");
            }

        } catch (error) {
            console.error("Error al guardar datos:", error);
            mostrarMensaje("Error: No se pudo guardar la información.", "error");
            return;
        }

        // Salir del modo edición
        toggleModoEdicion();
    }
    
    // 12. FUNCIÓN PARA MOSTRAR MENSAJES BONITOS (sin cambios)
    function mostrarMensaje(mensaje, tipo) {
        const alerta = document.createElement("div");
        alerta.className = `alert alert-${tipo === "error" ? "danger" : "success"} alert-dismissible fade show`;
        alerta.style.position = "fixed";
        alerta.style.top = "20px";
        alerta.style.right = "20px";
        alerta.style.zIndex = "1000";
        alerta.style.minWidth = "300px";
        alerta.innerHTML = `
            ${mensaje}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        document.body.appendChild(alerta);

        setTimeout(() => {
            if (alerta.parentNode) {
                alerta.parentNode.removeChild(alerta);
            }
        }, 2500);
    }
    
    // 13. ASIGNAR EVENT LISTENERS
    botonEditar.addEventListener("click", toggleModoEdicion);
    botonCambiarAvatar.addEventListener("click", toggleSelectorAvatar);
    enlaceCambiarPassword.addEventListener("click", toggleCamposPassword);
    
    opcionesAvatar.forEach(opcion => {
        opcion.addEventListener("click", seleccionarAvatar);
    });
    
    formulario.addEventListener("submit", guardarDatos);
});

// 14. FUNCIÓN PARA CERRAR SESIÓN 
function cerrarSesion() {
    if (confirm("¿Estás seguro de que quieres cerrar sesión?")) {
        localStorage.removeItem("usuarioLogueado");
        localStorage.removeItem("temp_current_email"); 
        localStorage.removeItem("avatar");
        alert("👋 Sesión cerrada correctamente");
        window.location.href = "../html/loguin.html";
    }
}