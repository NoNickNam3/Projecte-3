document.addEventListener('DOMContentLoaded', f_principal);

let map, marker, filaUbis, contEdit, inputSearch, nombre, direccion, lat, lng, observaciones, direMsg, tbod, idUbicacion, coordenada, no_result, direSearch;
let buttonEditar, buttonEliminar, buttonCancelar, buttonGuardar, buttonCrear, buttonImportar;
let nombreForm, userForm, favForm;
let formUpdate, formDelete, formAdd, formImportar;
let idUb;
let msgError = "";

function f_principal() {

    if (window.location.pathname == '/ubicaciones') {
        inputSearch = document.getElementById('search-input');
        filaUbis = document.getElementsByClassName('filaUbi');
        contEdit = document.querySelector('.contEdit');
        contBotones = document.getElementById('contBotones');
        nombre = document.getElementById('nombre');
        direccion = document.getElementById('direccion');
        observaciones = document.getElementById('observaciones');
        favSelect = document.getElementById('favSelect');
        favValue = document.getElementById('favValue');
        direMsg = document.querySelectorAll('.inputNombre')[2];
        tbod = document.getElementById("listaUbicaciones");
        idUbicacion = document.getElementsByName('id');

        nombreForm = document.getElementsByName('nombreForm')[0];
        userForm = document.getElementsByName('selectClientsUbis')[0];
        favForm = document.getElementsByName('favForm')[0];

        buttonEditar = document.getElementById('buttonEditar');
        buttonEliminar = document.getElementById('buttonEliminar');
        buttonCancelar = document.getElementById('buttonCancelar');
        buttonGuardar = document.getElementById('buttonGuardar');
        buttonCrear = document.getElementById('buttonCrear');
        buttonImportar = document.getElementById('buttonImportar');

        formAdd = document.getElementById('formAdd');
        formDelete = document.getElementById('formDelete');
        formUpdate = document.getElementById('formUpdate');
        formImportar = document.getElementById('formImportar');
        coordenada = document.getElementById('coordenada');

        put = document.getElementsByName('_method')[0];
        no_result = document.getElementById('no-result');
        direSearch = document.getElementById('direSearch');

        listaUbicaciones();
        agregarListeners();
        checkFav();
        cargarUbisUser();
    }

}

function agregarListeners() {

    buttonEditar.addEventListener('click', () => {
        disabledFormEdit(false);
    });
    buttonCancelar.addEventListener('click', () => {
        contEdit.classList.remove('show');
        limipiarMsg();
        disabledFormEdit(true);
    });

    direccion.addEventListener("keydown", function (event) {
        if (event.keyCode === 13) {
            buscarCoordenada(direccion.value);
        }
    });

    direSearch.addEventListener('click', () => {
        buscarCoordenada(direccion.value);
    });
    direSearch.addEventListener('mouseover', function () {
        direSearch.classList.add('fa-fade');
    });

    direSearch.addEventListener('mouseout', function () {
        direSearch.classList.remove('fa-fade');
    });

    favSelect.addEventListener('click', checkFav);

    nombreForm.addEventListener('input', listaUbicaciones);
    userForm.addEventListener('input', listaUbicaciones);
    favForm.addEventListener('input', listaUbicaciones);

    formAdd.addEventListener('click', () => {

        if (map == undefined) {
            location.reload();
        }

        animacionCambiarFila();

        nombre.value = "";
        direccion.value = "";
        idUbicacion[0].value = "";
        idUbicacion[1].value = "";
        observaciones.value = "";
        coordenada.value = "";
        favSelect.checked = false;
        limipiarMsg();
        crearAutocomplete();
        disabledFormEdit(false);
        ocultarBotones(true);
        actualizarIdUpdate("");
        buttonImportar.classList.add('oculto');

    });

    formUpdate.addEventListener("keydown", function (event) {
        if (event.keyCode === 13) { // 13 es el código para la tecla "Enter"
            event.preventDefault(); // evita que se active el submit
            // Aquí puedes hacer lo que necesites con el texto ingresado
        }
    });

    formUpdate.addEventListener('submit', (event) => {
        // Prevenimos que el formulario se envíe automáticamente
        event.preventDefault();
        // console.info(nombre.length);

        // Mostramos la ventana emergente de confirmación
        if (comprobarFormulario()) {
            Swal.fire({
                icon: 'warning',
                title: 'Eeeeeps!',
                text: msgError,
            });
        } else {
            formUpdate.submit();
        }
    });

    formDelete.addEventListener('submit', (event) => {
        // Prevenimos que el formulario se envíe automáticamente
        event.preventDefault();

        // Mostramos la ventana emergente de confirmación
        Swal.fire({
            icon: 'warning',
            title: '¿Estás seguro?',
            text: 'Estás a punto de eliminar la ubicación',
            showCancelButton: true,
            confirmButtonText: 'Sí, eliminar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            // Si el usuario confirma la eliminación, enviamos el formulario
            if (result.isConfirmed) {
                formDelete.submit();
            }
        });
    });

    formImportar.addEventListener('submit', (event) => {
        // Prevenimos que el formulario se envíe automáticamente
        event.preventDefault();

        // Mostramos la ventana emergente de confirmación
        Swal.fire({
            icon: 'warning',
            title: '¿Estás seguro de importar?',
            text: 'Al importar esta ubicacion dejará de pertenecer al empleado y pasará a ser de la empresa',
            showCancelButton: true,
            confirmButtonText: 'Sí, importar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            // Si el usuario confirma la eliminación, enviamos el formulario
            if (result.isConfirmed) {
                formImportar.submit();
            }
        });
    });

}

function comprobarFormulario() {
    if (nombre.value.length == 0) {
        msgError = "El nombre es obligatorio";
        return true;
    }
    if (direccion.value.length == 0) {
        msgError = "La direccion es obligatoria";
        return true;
    }
    if (nombre.disabled) {
        msgError = "Primero modifica la ubicación!";
        return true;
    }
    if (direccion.classList.toString().includes('error')) {
        msgError = "Dirección incorrecta!";
        return true;
    }
    return false;
}

function ocultarBotones(estado) {
    if (estado) {
        buttonEditar.classList.add('oculto');
        buttonEliminar.classList.add('oculto');
        buttonGuardar.classList.add('oculto');
        buttonCrear.classList.remove('oculto');
        buttonImportar.classList.remove('oculto');
        put.disabled = estado;
    } else {
        buttonEditar.classList.remove('oculto');
        buttonEliminar.classList.remove('oculto');
        buttonGuardar.classList.remove('oculto');
        buttonCrear.classList.add('oculto');
        buttonImportar.classList.add('oculto');
        put.disabled = estado
    }
}
function checkFav() {
    if (favSelect.checked) {
        favValue.value = 1;
    } else {
        favValue.value = 0;
    }
}

function agregarList(fila) {
    fila.addEventListener('dblclick', () => {

        animacionCambiarFila();
        disabledFormEdit(true);
        let celdas = fila.querySelectorAll('td');

        nombre.value = celdas[0].textContent;
        direccion.value = celdas[1].textContent;
        idUbicacion[0].value = celdas[3].textContent;
        idUbicacion[1].value = celdas[3].textContent;
        observaciones.value = celdas[4].textContent;
        coordenada.value = celdas[5].textContent + "," + celdas[6].textContent;
        actualizarIdUpdate(celdas[3].textContent);

        if (celdas[2].querySelector('i').classList.toString().includes('fa-solid')) {
            favSelect.checked = true;
        } else {
            favSelect.checked = false;
        }

        actualizarMap(celdas[5].textContent, celdas[6].textContent);
        limipiarMsg();
        crearAutocomplete();

        if (userForm.selectedIndex != 0) {
            ocultarBotones(true);
            buttonCrear.classList.add('oculto');

        } else {
            ocultarBotones(false);
        }

    });
}

function animacionCambiarFila() {
    if (contEdit.classList.toString().includes('show')) {
        contEdit.classList.add('moverDerecha');
        setTimeout(() => {
            contEdit.classList.remove('moverDerecha');
        }, 500);
    }
    contEdit.classList.add('show'); /* Al hacer clic en la fila, se agrega la clase "show" al segundo div */
}

function actualizarIdUpdate(id) {

    let url = formUpdate.getAttribute("action");
    let partesUrl = url.split("/");
    let nuevaUrl = partesUrl.slice(0, -1).join("/") + "/" + id;
    formUpdate.setAttribute("action", nuevaUrl);
}


function initMap() {
    var myLatLng = { lat: 41.583740, lng: 1.626167 };
    var icon = {
        url: '../img/PathFinderLogo.png',
        scaledSize: new google.maps.Size(50, 50),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(25, 50)
    };
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14,
        center: myLatLng
    });
    marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        draggable: false,
        icon: icon
    });
}

function actualizarMap(lat, lng) {

    lat = parseFloat(lat);
    lng = parseFloat(lng);

    var newLatLng = { lat: lat, lng: lng };
    if (map == undefined) {
        location.reload();
    } else {
        map.setCenter(newLatLng);
        marker.setPosition(newLatLng);
    }

}

function crearAutocomplete() {
    let autocomplete = new google.maps.places.Autocomplete(direccion);
    autocomplete.addListener('place_changed', function () {
        var place = autocomplete.getPlace();
        // Mostrar información de la dirección en la página
        if (place.geometry) {
            // Mover el mapa a la ubicación seleccionada
            map.setCenter(place.geometry.location);
            map.setZoom(15);

            // Agregar un marcador en la ubicación seleccionada
            marker.setPosition(place.geometry.location);
        }
    });
}


function buscarCoordenada(direccion) {

    var geocoder = new google.maps.Geocoder();

    geocoder.geocode({ 'address': direccion }, function (results, status) {

        limipiarMsg();

        if (status === 'OK') {
            marker.setPosition(results[0].geometry.location);
            map.setCenter(results[0].geometry.location);
            direMsg.classList.add('success');
            coordenada.value = marker.getPosition().lat() + "," + marker.getPosition().lng();
            let latLng = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());
            buscarDireccion(marker.getPosition());
        } else {
            coordenada.value = "";
            direMsg.classList.add('error');
        }
    });
}

function buscarDireccion(coordenadas) {
    var geocoder = new google.maps.Geocoder();
    var latLng = new google.maps.LatLng(coordenadas.lat(), coordenadas.lng());

    geocoder.geocode({ 'location': latLng }, function (results, status) {
        if (status === 'OK') {
            if (results[0]) {
                var dire = results[0].formatted_address;
                console.log(direccion); // muestra la dirección en la consola
                direccion.value = dire;
            }
        }
    });
}

function limipiarMsg() {
    if (direMsg.classList.toString().includes('error')) {
        direMsg.classList.remove('error');
    }

    if (direMsg.classList.toString().includes('success')) {
        direMsg.classList.remove('success');
    }
}

function disabledFormEdit(estado) {
    nombre.disabled = estado;
    direccion.disabled = estado;
    observaciones.disabled = estado;
    favSelect.disabled = estado;
    if (estado) {
        nombre.classList.add('no-edit');
        direccion.classList.add('no-edit');
        observaciones.classList.add('no-edit');
        favSelect.classList.add('no-edit');
    } else {
        nombre.classList.remove('no-edit');
        direccion.classList.remove('no-edit');
        observaciones.classList.remove('no-edit');
        favSelect.classList.remove('no-edit');
    }
}

function listaUbicaciones() {

    let csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    let dataSend = {
        id: userForm.value,
        nombre: nombreForm.value,
        fav: favForm.checked
    }
    // console.info(dataSend);
    fetch('/get_ubicaciones_user', {
        method: 'POST',
        body: JSON.stringify(dataSend),
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
        .then(response => {
            if (response.status == 200) {
                if (response.ok) {

                    response.json().then(data => llenarTabla(data));

                } else {
                    console.error('Error al enviar la petición');
                }
            } else {
                console.info("es null");
                console.info(response);
            }
        })
        .catch(error => {
            console.error('Error al enviar la petición:', error);
        });
}

function llenarTabla(ubicaciones) {

    eliminarContenido();
    ubicaciones.forEach(function (ubicacion) {
        // Crear un elemento tr para representar la fila
        var fila = document.createElement("tr");
        agregarList(fila);
        fila.setAttribute("class", "filaUbi");

        // Crear un elemento td para cada columna de la fila y agregar el contenido
        var nombre = document.createElement("td");
        nombre.textContent = ubicacion.nombre;
        fila.appendChild(nombre);

        var direccion = document.createElement("td");
        direccion.textContent = ubicacion.direccion;
        fila.appendChild(direccion);

        var favorito = document.createElement("td");
        if (ubicacion.fav) {
            var icono = document.createElement("i");
            icono.setAttribute("class", "fa-solid fa-star");
            favorito.appendChild(icono);
        } else {
            var icono = document.createElement("i");
            icono.setAttribute("class", "fa-regular fa-star");
            favorito.appendChild(icono);
        }
        fila.appendChild(favorito);

        var id = document.createElement("td");
        id.setAttribute("class", "oculto");
        id.textContent = ubicacion.id;
        fila.appendChild(id);

        var observaciones = document.createElement("td");
        observaciones.setAttribute("class", "oculto");
        observaciones.textContent = ubicacion.observaciones;
        fila.appendChild(observaciones);

        var latitud = document.createElement("td");
        latitud.setAttribute("class", "oculto");
        latitud.textContent = ubicacion.coordenada.latitud;
        fila.appendChild(latitud);

        var longitud = document.createElement("td");
        longitud.setAttribute("class", "oculto");
        longitud.textContent = ubicacion.coordenada.longitud;
        fila.appendChild(longitud);

        // Agregar la fila al tbody
        tbod.appendChild(fila);
    });
    if (ubicaciones.length == 0) {
        no_result.classList.remove('oculto');
    } else {
        no_result.classList.add('oculto');
    }
}

function eliminarContenido() {
    if (tbod != undefined) {
        while (tbod.firstChild) {
            tbod.removeChild(tbod.firstChild);
        }
    }
}

function cargarUbisUser() {
    if (sessionStorage.getItem('clickedIndex') != null) {
        userForm.selectedIndex = parseInt(sessionStorage.getItem('clickedIndex')) + 1;
        listaUbicaciones();
        sessionStorage.setItem('clickedIndex', null);
    }
}