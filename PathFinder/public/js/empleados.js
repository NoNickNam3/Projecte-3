document.addEventListener('DOMContentLoaded', f_principal);
let buttonSend, buttonDesv;
let formSend, formDesv;
function f_principal() {
    if (window.location.pathname == '/empleados') {
        buttonDesv = document.getElementById('buttonDesv');
        buttonSend = document.getElementById('buttonSend');

        formDesv = document.getElementById('formDesv');
        formSend = document.getElementById('formSend');

        agregarEventos();
    }
}

function agregarEventos() {
    buttonSend.addEventListener('click', () => {
        formSend.submit();
    });

    buttonDesv.addEventListener('click', () => {
        formDesv.submit();
    });
}
