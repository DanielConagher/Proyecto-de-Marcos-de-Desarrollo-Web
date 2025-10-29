function validarDatos() {
    const correo = document.getElementById("correo").value;
    const contrasena = document.getElementById("contraseña").value;

    const data = {
        correo: correo,
        contrasena: contrasena
    };

    fetch("http://localhost:8080/api/user/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if(!response.ok) throw new Error("Error en la llamada al servidor");
        return response.json();
    })
    .then(isLogged => {
        if (isLogged) {
            alert("Login exitoso");
            // Redireccionar a página principal
            window.location.href = "menu.html";
        } else {
            alert("Correo o contraseña incorrectos");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Ocurrió un error, inténtalo más tarde");
    });
}