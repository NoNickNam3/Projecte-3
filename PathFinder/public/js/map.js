document.addEventListener('DOMContentLoaded', f_principal);

function f_principal() {
    if (window.location.pathname == '/tracking') {
        let cliente = document.getElementsByName('selectClients')[0];
        let fecha = document.getElementsByName('fechaClient')[0];

        cliente.selectedIndex = 0;
        cliente.addEventListener('change', f_peticionMapa);
        fecha.addEventListener('change', f_peticionMapa);

        f_omplirMapa([]);
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

function f_limpiarMapa() {
    if (typeof mapa !== 'undefined') {
        mapa.eachLayer((layer) => {
            if (layer instanceof L.Marker || layer instanceof L.Polyline) {
                mapa.removeLayer(layer);
            }
        });
    }
}

function f_peticionMapa() {
    let cliente = document.getElementsByName('selectClients')[0];
    let fecha = document.getElementsByName('fechaClient')[0];
    let dataSend = {
        id: cliente.value,
        fecha: fecha.value
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
                        // console.log('La petición fue enviada exitosamente');
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

function f_mascaraDisabled(valor) {
    if (valor) {
        document.getElementsByClassName("mascara")[0].style.display = "block";
    } else {
        document.getElementsByClassName("mascara")[0].style.display = "none";
    }

}

function f_comprobacionParams(cli, data) {
    if (cli.selectedIndex != 0 && data.value != '') {
        return true;
    }
    return false;
}