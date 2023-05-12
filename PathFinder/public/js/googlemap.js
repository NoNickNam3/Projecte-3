document.addEventListener('DOMContentLoaded', f_principal);

let map, marker, filaUbis, contEdit, inputSearch, nombre, direccion, lat, lng, observaciones, direMsg, tbod, idUbicacion, coordenada;
let buttonEditar, buttonEliminar, buttonCancelar, buttonGuardar;
let nombreForm, userForm, favForm;
let formUpdate, formDelete, formAdd;

function f_principal() {
    if (window.location.pathname == '/ubicaciones') {
        inputSearch = document.getElementById('search-input');
        filaUbis = document.getElementsByClassName('filaUbi');
        // console.info(filaUbis);
        contEdit = document.querySelector('.contEdit');
        nombre = document.getElementById('nombre');
        direccion = document.getElementById('direccion');
        observaciones = document.getElementById('observaciones');
        favSelect = document.getElementById('favSelect');
        favValue = document.getElementById('favValue');
        direMsg = document.querySelectorAll('.inputNombre')[2];
        tbod = document.getElementById("listaUbicaciones");
        idUbicacion = document.getElementById('idUbicacion');

        nombreForm = document.getElementsByName('nombreForm')[0];
        userForm = document.getElementsByName('selectClientsUbis')[0];
        favForm = document.getElementsByName('favForm')[0];

        buttonEditar = document.getElementById('buttonEditar');
        buttonEliminar = document.getElementById('buttonEliminar');
        buttonCancelar = document.getElementById('buttonCancelar');
        buttonGuardar = document.getElementById('buttonGuardar');

        formAdd = document.getElementById('formAdd');
        formDelete = document.getElementById('formDelete');
        formUpdate = document.getElementById('formUpdate');
        coordenada = document.getElementById('coordenada');

        // console.log("La ruta actual es: " + window.location.pathname);
        // llenarTabla();
        listaUbicaciones();
        agregarListeners();
        checkFav();
        disabledFormEdit(true);
    }

}

function agregarListeners() {

    // Array.from(filaUbis).forEach(function (fila) {
    // });
    buttonEditar.addEventListener('click', () => {
        disabledFormEdit(false);
    });
    // buttonEliminar.addEventListener('click', () => {
    //     eliminarUbicacion(idUbicacion.value);
    // });
    buttonCancelar.addEventListener('click', () => {
        contEdit.classList.remove('show');
        limipiarMsg();
        disabledFormEdit(true);
    });

    // inputSearch.addEventListener('change', initMap);
    direccion.addEventListener("keydown", function (event) {
        // Comprobar si la tecla presionada es Enter
        if (event.keyCode === 13) {
            // Ejecutar la acción deseada (por ejemplo, enviar un formulario)
            buscarCoordenada(direccion.value);
        }
    });

    favSelect.addEventListener('click', checkFav);

    nombreForm.addEventListener('input', listaUbicaciones);
    userForm.addEventListener('input', listaUbicaciones);
    favForm.addEventListener('input', listaUbicaciones);

    formAdd.addEventListener('click', () => {
        if (contEdit.classList.toString().includes('show')) {
            contEdit.classList.add('moverDerecha');
            setTimeout(() => {
                contEdit.classList.remove('moverDerecha');
            }, 500);
        }
        contEdit.classList.add('show');

        nombre.value = "";
        direccion.value = "";
        idUbicacion.value = "";
        observaciones.value = "";
        coordenada.value = "";
        limipiarMsg();
        crearAutocomplete();
        disabledFormEdit(false);
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

        // Mostramos la ventana emergente de confirmación
        if (nombre.disabled) {
            Swal.fire({
                icon: 'warning',
                title: 'Eeeeeps!',
                text: 'Primero modifica la ubicación!',
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
        if (contEdit.classList.toString().includes('show')) {
            contEdit.classList.add('moverDerecha');
            setTimeout(() => {
                contEdit.classList.remove('moverDerecha');
                disabledFormEdit(true);
            }, 500);
        }
        contEdit.classList.add('show'); /* Al hacer clic en la fila, se agrega la clase "show" al segundo div */

        let celdas = fila.querySelectorAll('td');

        nombre.value = celdas[0].textContent;
        direccion.value = celdas[1].textContent;
        idUbicacion.value = celdas[3].textContent;
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
    });
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
    map.setCenter(newLatLng);
    marker.setPosition(newLatLng);
}

function crearAutocomplete() {
    let autocomplete = new google.maps.places.Autocomplete(direccion);
    autocomplete.addListener('place_changed', function () {
        var place = autocomplete.getPlace();
        // Mostrar información de la dirección en la página

        if (place.geometry) {
            // Mover el mapa a la ubicación seleccionada
            // direccion.value = place.formatted_address;
            // console.info(place);
            map.setCenter(place.geometry.location);
            map.setZoom(15);

            // Agregar un marcador en la ubicación seleccionada
            marker.setPosition(place.geometry.location);
        }
    });
}


function buscarCoordenada(direccion) {
    // var direccion = document.getElementById('search-input').value;

    var geocoder = new google.maps.Geocoder();

    geocoder.geocode({ 'address': direccion }, function (results, status) {

        limipiarMsg();

        if (status === 'OK') {
            marker.setPosition(results[0].geometry.location);
            map.setCenter(results[0].geometry.location);
            direMsg.classList.add('success');
            coordenada.value = marker.getPosition().lat() + "," + marker.getPosition().lng();
            let latLng = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());
            // console.info("esta es la coordenada:" + latLng);
            buscarDireccion(marker.getPosition());
        } else {
            // alert('No se ha podido encontrar la dirección ingresada. Inténtelo de nuevo.');
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
                    // console.log('La petición fue enviada exitosamente');
                    // console.info(data);
                    response.json().then(data => llenarTabla(data));
                    // response.json().then(data => console.info(data));

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
    // ubicaciones = [
    //     {
    //         "id": "1",
    //         "nombre": "La Sagrada Familia",
    //         "direccion": "Carrer de Mallorca, 401, 08013 Barcelona",
    //         "coordenadas": { "latitud": 41.4036, "longitud": 2.1744 },
    //         "favorito": true,
    //         "observaciones": "Mejor visitar temprano en la mañana para evitar las largas filas"
    //     },
    //     {
    //         "id": "2",
    //         "nombre": "Parque Güell",
    //         "direccion": "Carrer d'Olot, s/n, 08024 Barcelona",
    //         "coordenadas": { "latitud": 41.4147, "longitud": 2.1528 },
    //         "favorito": false,
    //         "observaciones": "El acceso a la zona monumental del parque es de pago"
    //     },
    //     {
    //         "id": "3",
    //         "nombre": "La Rambla",
    //         "direccion": "La Rambla, 08002 Barcelona",
    //         "coordenadas": { "latitud": 41.3809, "longitud": 2.1731 },
    //         "favorito": true,
    //         "observaciones": "Lugar muy turístico, ten cuidado con los carteristas"
    //     },
    //     {
    //         "id": "4",
    //         "nombre": "Barrio Gótico",
    //         "direccion": "Barri Gòtic, 08002 Barcelona",
    //         "coordenadas": { "latitud": 41.3834, "longitud": 2.1766 },
    //         "favorito": true,
    //         "observaciones": "El casco histórico de Barcelona, ideal para pasear"
    //     },
    //     {
    //         "id": "5",
    //         "nombre": "Casa Batlló",
    //         "direccion": "Passeig de Gràcia, 43, 08007 Barcelona",
    //         "coordenadas": { "latitud": 41.3910, "longitud": 2.1647 },
    //         "favorito": false,
    //         "observaciones": "Espectacular edificio modernista diseñado por Gaudí"
    //     },
    //     {
    //         "id": "6",
    //         "nombre": "La Pedrera (Casa Milà)",
    //         "direccion": "Carrer de Provença, 261-265, 08008 Barcelona",
    //         "coordenadas": { "latitud": 41.3954, "longitud": 2.1618 },
    //         "favorito": false,
    //         "observaciones": "Otro edificio modernista impresionante de Gaudí"
    //     }
    // ];
    eliminarContenido();
    ubicaciones.forEach(function (ubicacion) {
        // Crear un elemento tr para representar la fila
        var fila = document.createElement("tr");
        // fila.addEventListener('dblclick',);
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
        // console.info(ubicacion);
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
}

function eliminarContenido() {
    if (tbod != undefined) {
        while (tbod.firstChild) {
            tbod.removeChild(tbod.firstChild);
        }
    }
}

// function eliminarUbicacion(idUbi) {
//     let dataSend = {
//         id: idUbi,
//     }

//     fetch('/get_ubicaciones_user', {
//         method: 'POST',
//         body: JSON.stringify(dataSend),
//         headers: {
//             'Content-Type': 'application/json',
//             'X-CSRF-TOKEN': csrfToken
//         }
//     })
//         .then(response => {
//             if (response.status == 200) {
//                 if (response.ok) {
//                     // console.log('La petición fue enviada exitosamente');
//                     response.json().then(data => llenarTabla(data));
//                 } else {
//                     console.error('Error al enviar la petición');
//                 }
//             } else {
//                 console.info("es null");
//                 console.info(response);
//             }
//         })
//         .catch(error => {
//             console.error('Error al enviar la petición:', error);
//         });
// }