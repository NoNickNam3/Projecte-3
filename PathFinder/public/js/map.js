document.addEventListener('DOMContentLoaded', f_principal);

function f_principal() {
    if (window.location.pathname == '/tracking') {
        let cliente = document.getElementsByName('selectClients')[0];
        // let fecha = document.getElementsByName('fechaClient')[0];

        cliente.selectedIndex = 0;
        cliente.addEventListener('change', f_peticionUsers);
        // fecha.addEventListener('change', f_peticionMapa);

        let arrayFechas = [
            // Array de fechas que deseas marcar
            {
                title: 'Tracking',
                start: '2023-05-19', // Fecha a marcar
                color: 'green' // Color personalizado para el evento
            },
            {
                title: 'Tracking',
                start: '2023-05-21', // Fecha a marcar
                color: 'green' // Color personalizado para el evento
            },
            // Más eventos...
        ];

        f_omplirMapa([]);
        f_omplirCalendar([]);

    }
}

let mapa;

function f_omplirMapa(listTracking) {
    if (listTracking.length == 0) {
        f_mascaraDisabled(true);
    }
    f_limpiarMapa();
    let latitut = 41.41468; // nord - sud
    let longitut = 1.45877; // est - oest
    let zoom = 8.5; // número + gran, més aprop
    // número + petit, més lluny

    if (!mapa) {
        // Si el mapa aún no se ha inicializado, inicialízalo
        mapa = L.map("elMeuMapa").setView([latitut, longitut], zoom);
        L.tileLayer("http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
            attribution:
                'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
            maxZoom: 18,
        }).addTo(mapa);
    }

    let myIcon = L.icon({
        iconUrl: "../img/PathFinderLogo.png",
        iconSize: [50, 50], // el tamaño de tu icono en píxeles
        iconAnchor: [25, 50],
    });

    let pointList = [];

    listTracking.forEach((track) => {
        let point = new L.LatLng(track.latitud, track.longitud);
        pointList.push(point);

        let marcador = L.marker([track.latitud, track.longitud], {
            icon: myIcon,
        }).addTo(mapa);
        marcador.bindPopup("<b>Hora:</b> <i>" + track.momento + "<i>");
    });

    let linia = new L.Polyline(pointList, {
        color: "red",
        weight: 5,
        opacity: 1,
        smoothFactor: 1,
    });
    linia.addTo(mapa);

    if (pointList.length != 0) {
        let bounds = L.latLngBounds(pointList);
        mapa.fitBounds(bounds);
    }
}

function f_omplirCalendar(arrayFechas) {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl);

    // Eliminar todos los eventos existentes
    calendar.removeAllEvents();
    calendar.setOption('locale', 'ES');
    calendar.setOption('initialView', 'dayGridMonth');
    calendar.setOption('headerToolbar', {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,dayGridYear'
    });
    calendar.setOption('firstDay', '1');
    calendar.setOption('buttonText', {
        prev: 'Anterior',
        next: 'Siguiente',
        today: 'Hoy',
        month: 'Mes',
        year: 'Año',
        day: 'Día',
        list: 'Lista'
    });
    // calendar.setOption('buttonIcons', {
    //     prev: 'left-single-arrow',
    //     next: 'right-single-arrow',
    //     prevYear: 'left-double-arrow',
    //     nextYear: 'right-double-arrow'
    // });
    calendar.setOption('eventClassNames', 'hover-event');
    calendar.setOption('events', arrayFechas);

    calendar.setOption('eventClick', function (info) {

        let event = info.event;
        let start = event.start;
        let year = start.getFullYear();
        let month = String(start.getMonth() + 1).padStart(2, '0');
        let day = String(start.getDate()).padStart(2, '0');

        let stringdata = `${year}-${month}-${day}`;
        f_peticionMapa(stringdata);
    });
    calendar.setOption('themeSystem', 'bootstrap');

    calendar.render();
}

function f_limpiarMapa() {
    if (typeof mapa !== 'undefined') {
        mapa.eachLayer((layer) => {
            if (layer instanceof L.Marker || layer instanceof L.Polyline) {
                mapa.removeLayer(layer);
            }
        });
    }
}

function f_peticionMapa(fecha) {
    let cliente = document.getElementsByName('selectClients')[0];
    let dataSend = {
        id: cliente.value,
        fecha: fecha
    }

    if (f_comprobacionParams(cliente, fecha)) {
        let csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
        fetch('/get_tracking_user', {
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
                        response.json().then(data => f_omplirMapa(data));
                    } else {
                        console.error('Error al enviar la petición');
                    }
                } else if (response.status == 419) {
                    window.location.href = '/login';
                } else {
                    console.info("es null");
                    console.info(response);
                }
            })
            .catch(error => {
                console.error('Error al enviar la petición:', error);
            });
        f_mascaraDisabled(false);
    }
}

function f_peticionUsers() {
    let cliente = document.getElementsByName('selectClients')[0];
    let dataSend = {
        id: cliente.value,
    }
    if (f_comprobacionParams(cliente)) {
        let csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
        fetch('/get_dias_user', {
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
                        response.json().then(data => f_omplirCalendar(data));
                    } else {
                        console.error('Error al enviar la petición');
                    }
                } else if (response.status == 419) {
                    window.location.href = '/login';
                } else {
                    console.info("es null");
                    console.info(response);
                }
            })
            .catch(error => {
                console.error('Error al enviar la petición:', error);
            });
    }
}

function f_mascaraDisabled(valor) {
    if (valor) {
        document.getElementsByClassName("mascara")[0].style.display = "block";
    } else {
        document.getElementsByClassName("mascara")[0].style.display = "none";
    }

}

function f_comprobacionParams(cli, data) {
    if (arguments.length == 1 && cli.selectedIndex != 0) {
        return true;
    }
    if (arguments.length == 2 && cli.selectedIndex != 0 && data.value != '') {
        return true;
    }
    return false;
}


