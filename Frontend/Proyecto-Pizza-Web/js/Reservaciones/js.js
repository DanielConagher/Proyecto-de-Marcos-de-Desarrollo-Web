document.addEventListener('DOMContentLoaded', () => {

    const mesasContainer = document.getElementById('mesas-container');
    const formularioReserva = document.getElementById('formulario-reserva');
    const mesaSeleccionadaSpan = document.getElementById('mesa-seleccionada');
    const horaReservaSelect = document.getElementById('hora-reserva');
    const timeForm = document.getElementById('time-form');
    let mesaActiva = null;

    function populateTimes() {
        horaReservaSelect.innerHTML = '';
        for (let hora = 14; hora <= 22; hora++) {
            // Hora en punto (e.g., 14:00)
            let option1 = document.createElement('option');
            let horaStr1 = `${hora}:00`;
            option1.value = horaStr1;
            option1.textContent = horaStr1;
            horaReservaSelect.appendChild(option1);

            // Media hora (e.g., 14:30)
            let option2 = document.createElement('option');
            let horaStr2 = `${hora}:30`;
            option2.value = horaStr2;
            option2.textContent = horaStr2;
            horaReservaSelect.appendChild(option2);
        }
        // Añadir la última hora: 23:00
        let lastOption = document.createElement('option');
        lastOption.value = '23:00';
        lastOption.textContent = '23:00';
        horaReservaSelect.appendChild(lastOption);
    }

    // Evento de clic en el contenedor de mesas
    mesasContainer.addEventListener('click', (e) => {
        if (e.target && e.target.classList.contains('mesa')) {
            const mesa = e.target;
            document.querySelectorAll('.mesa').forEach(m => m.classList.remove('selected'));

            mesa.classList.add('selected');

            mesaActiva = mesa.dataset.table;

            mesaSeleccionadaSpan.textContent = `#${mesaActiva}`;
            populateTimes();
            formularioReserva.style.display = 'block';
        }
    });

    timeForm.addEventListener('submit', (e) => {
        e.preventDefault(); // Evitar que la página se recargue

        const horaSeleccionada = horaReservaSelect.value;

        if (!mesaActiva) {
            alert('Por favor, selecciona una mesa primero.');
            return;
        }

        alert(`¡Reserva realizada con éxito!\nMesa: ${mesaActiva}\nHora: ${horaSeleccionada}`);

        document.querySelector(`.mesa[data-table="${mesaActiva}"]`).classList.remove('selected');
        formularioReserva.style.display = 'none';
        mesaActiva = null;
    });

});