document.addEventListener('DOMContentLoaded', f_principal);

function f_principal() {
    let cliente = document.getElementsByName('selectClients')[0];
    let fecha = document.getElementsByName('fechaClient')[0];
    cliente.addEventListener('change', f_peticionMapa);
    fecha.addEventListener('change', f_peticionMapa);

    f_omplirSelect();

    let listTracking = [
        {
            "latitud": "41.61759",
            "longitud": "0.62001",
            "momento": "2023-05-02 09:37:36"
        },
        {
            "latitud": "41.15445",
            "longitud": "1.10654",
            "momento": "2023-05-02 09:37:36"
        },
        {
            "latitud": "41.11468",
            "longitud": "1.25877",
            "momento": "2023-05-02 09:37:36"
        },
        {
            "latitud": "41.4035",
            "longitud": "2.17425",
            "momento": "2023-05-02 09:37:36"
        },
    ];
    // let listTracking = f_peticionMapa();
    f_omplirMapa(listTracking);
}

function f_omplirMapa(listTracking) {
    let latitut = 41.41468; // nord - sud
    let longitut = 1.45877; // est - oest

    let zoom = 8.5; // número + gran, més aprop
    // número + petit, més lluny

    let mapa = L.map('elMeuMapa').setView([latitut, longitut], zoom);
    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
        maxZoom: 18
    }).addTo(mapa);

    let myIcon = L.icon({
        iconUrl: '../img/PathFinderLogo.png',
        iconSize: [50, 50], // el tamaño de tu icono en píxeles
        iconAnchor: [25, 50],
    });

    let pointList = [];

    listTracking.forEach((track) => {
        let point = new L.LatLng(track.latitud, track.longitud);
        pointList.push(point);

        let marcador = L.marker([track.latitud, track.longitud], { icon: myIcon }).addTo(mapa);
        marcador.bindPopup("<b>Fecha:</b> <i>" + track.momento + "<i>")
    });

    let linia = new L.Polyline(pointList, {
        color: 'red',
        weight: 5,
        opacity: 1,
        smoothFactor: 1
    });
    linia.addTo(mapa);

}

// function f_omplirMapa() {

//     let pointList = [];
//     let latitut = 41.36759; // Latitud inicial cercana al centro de las otras latitudes
//     let longitut = 1.25877; // Longitud inicial cercana al centro de las otras longitudes

//     // Crear cuatro puntos aleatorios cerca entre sí
//     for (let i = 0; i < 4; i++) {
//         latitut += (Math.random() - 0.5) * 0.2; // Variación aleatoria en la latitud
//         longitut += (Math.random() - 0.5) * 0.2; // Variación aleatoria en la longitud
//         pointList.push({ lat: latitut, lng: longitut });
//     }

//     let latitudes = pointList.map(point => point.lat);
//     let longitudes = pointList.map(point => point.lng);

//     let zoom = 8.5;
//     let mapa = L.map('elMeuMapa').setView([latitut, longitut], zoom);

//     L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
//         attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
//         maxZoom: 18
//     }).addTo(mapa);

//     if (pointList.length > 1) {
//         let maxLat = Math.max(...latitudes);
//         let minLat = Math.min(...latitudes);
//         let maxLng = Math.max(...longitudes);
//         let minLng = Math.min(...longitudes);
//         let southWest = L.latLng(minLat, minLng);
//         let northEast = L.latLng(maxLat, maxLng);
//         let bounds = L.latLngBounds(southWest, northEast);
//         // let extraMargin = 0.1;
//         // bounds = bounds.pad(extraMargin);
//         mapa.fitBounds(bounds);
//     }

//     let myIcon = L.icon({
//         iconUrl: '../img/PathFinderLogo.png',
//         iconSize: [50, 50],
//         iconAnchor: [25, 50],
//     });

//     for (let i = 0; i < pointList.length; i++) {
//         let point = pointList[i];
//         let marker = L.marker([point.lat, point.lng], { icon: myIcon }).addTo(mapa);
//         marker.bindPopup(`<b>Hora:</b> <i>${i + 8}:00<i>`);
//     }

//     let polyline = L.polyline(pointList, {
//         color: 'red',
//         weight: 5,
//         opacity: 1,
//         smoothFactor: 1
//     }).addTo(mapa);
// }


function f_omplirSelect() {
    let selectCli = document.getElementsByTagName('select')[0]; // Seleccionamos el primer select encontrado
    let allCli = f_peticionClientes();

    let opt0 = document.createElement('option');
    opt0.text = "Selecciona un cliente...";
    opt0.value = -1;
    opt0.className = "bg-white ";
    opt0.disabled = true;
    selectCli.add(opt0);

    allCli.forEach((cliente) => {
        let option = document.createElement('option');
        option.text = cliente.nomCli;
        option.value = cliente.idCli;
        option.className = "bg-white block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100";
        selectCli.add(option);
    });

    selectCli.selectedIndex = 0;
}

function f_peticionClientes() {
    // fetch('url-del-servidor', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     }
    // })
    // .then(response => {
    //     if (response.ok) {
    //         console.log('La petición fue enviada exitosamente');
    //     } else {
    //         console.error('Error al enviar la petición');
    //     }
    // })
    // .catch(error => {
    //     console.error('Error al enviar la petición:', error);
    // });
    let cli2 = [{ idCli: 121, nomCli: 'Jhonny Melavo' }, { idCli: 122, nomCli: 'Armando Paredes' }, { idCli: 123, nomCli: 'Broco Li' }, { idCli: 123, nomCli: 'Débora Meltrozo' }];

    return cli2;
}

function f_peticionMapa() {
    let cliente = document.getElementsByName('selectClients')[0];
    let fecha = document.getElementsByName('fechaClient')[0];
    let dataSend = {
        cliente: cliente.value,
        fecha: fecha.value
    }
    if (f_comprobacionParams(cliente, fecha)) {

        // fetch('url-del-servidor', {
        //     method: 'POST',
        //     body: JSON.stringify(dataSend),
        //     headers: {
        //         'Content-Type': 'application/json'
        //     }
        // })
        // .then(response => {
        //     if (response.ok) {
        //         console.log('La petición fue enviada exitosamente');
        //     } else {
        //         console.error('Error al enviar la petición');
        //     }
        // })
        // .catch(error => {
        //     console.error('Error al enviar la petición:', error);
        // });
        console.info(dataSend);
    }


}

function f_comprobacionParams(cli, data) {
    if (cli.selectedIndex != 0 && data.value != '') {
        return true;
    }
    return false;
}