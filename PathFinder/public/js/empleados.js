document.addEventListener('DOMContentLoaded', f_principal);
let buttonSend, buttonDesv;
let formSend, formDesv;
let inputInv, listClients;
function f_principal() {
    if (window.location.pathname == '/empleados') {
        buttonDesv = document.getElementById('buttonDesv');
        buttonSend = document.getElementById('buttonSend');

        formDesv = document.getElementById('formDesv');
        formSend = document.getElementById('formSend');

        inputInv = document.getElementById('inputInv');
        listClients = document.getElementById('listClients');

        agregarEventos();
        listClients.selectedIndex = 0;
    }
}

function comprobarEnvio() {

}

function agregarEventos() {
    buttonSend.addEventListener('click', () => {
        if (inputInv.value.length == 0) {
            Swal.fire({
                icon: 'warning',
                title: 'Eeeeeps!',
                text: 'Primero escribe un correo!',
            });
        } else {
            formSend.submit();
        }

    });

    buttonDesv.addEventListener('click', () => {
        if (listClients.selectedIndex == 0) {
            Swal.fire({
                icon: 'warning',
                title: 'Eeeeeps!',
                text: 'Primero selecciona un empleado!',
            });
        } else {
            formDesv.submit();
        }

    });
}
