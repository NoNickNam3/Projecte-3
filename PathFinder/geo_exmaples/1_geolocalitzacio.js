/*
GIS (Geographic Information System) 
Leaflet
https://leafletjs.com/examples/quick-start/
https://en.wikipedia.org/wiki/Leaflet_(software)
*/
let coordenades = [];

addEventListener('DOMContentLoaded',function ()
{
    if (navigator.geolocation)
    {
        //alert("Permet la geolocalització");
        //console.info(navigator.geolocation);
        navigator.geolocation.getCurrentPosition(funcioOk,funcioKO);
        // més informació a:
        // https://developer.mozilla.org/es/docs/Web/API/Geolocation/getCurrentPosition
        
        /**
         * La funció prepararMapa s'espera 5 segons per ser executada per tal
         * de que l'usuari tingui temps d'acceptar la Geolocalització
         * i així la variable coordenades es carrega amb els valors adequats
         * gràcies a la funció "funcioOk"
         */
        setTimeout(prepararMapa,3000)
        
    }
});


function funcioOk(posicio)
{
    console.info(posicio);
    console.info(posicio.coords.latitude);
    console.info(posicio.coords.longitude);
    coordenades.push(posicio.coords.latitude);
    coordenades.push(posicio.coords.longitude);
    //console.info(new Date(posicio.timestamp));
    
    /**
    L’objecte Position conté els següents valors:
    •	coords.latitude: La latitud com un número decimal
    •	coords.longitude: La longitud de la posició
    •	coords.accuracy: La precisió de la localització
    •	coords.altitude: L’altitud en metres per damunt del nivell del mar
    •	coords.altitudeAccuracy: La precisió de l’altitud
    •	coords.heading: Els graus de desplaçament respecte al Pol Nord en el sentit de les agulles del rellotge
    •	coords.speed: La velocitat en metres/segon
    •	timestamp: la data i hora de la resposta
    */
}

function funcioKO(error)
{
    console.info("ERROR: ",error);
    
    /**
    L’objecte "error" ens indica la causa per la qual no s’ha pogut determinar la ubicació de l’usuari. Consta de dos atributs, 'code' que ens diu el codi d’error i 'message' que ens dóna un missatge de l’error. 
    El 'code' pot ser:
    •	PERMISSION_DENIED: valor numèric 1, vol dir que l’usuari ha denegat l’obtenció de la localització
    •	POSITION_UNAVAILABLE: valor numèric 2, no s’ha pogut obtenir la ubicació de l’usuari per alguna raó
    •	TIMEOUT: valor numèric 3, s’ha esgotat el temps d’espera per a obtenir la localització
    •	UNKNOWN_ERROR: valor numèric 4, error desconegut

    */
    
}

function prepararMapa()
{
    //coordenades
    let latitut = 41.58406;
    let longitut = 1.60141;
    // o alternativament amb un array center:
    // center of the map
    //let center = [41.66, 1.72];

    let zoom = 13; // número + gran, més aprop
    // número + petit, més lluny

    // L -> la llibreria, objecte Leaflet
    // map -> mètode 
    // zoom inicial


    let mapa = L.map('elMeuMapa').setView([latitut, longitut], zoom);

    // tyleLayer -> el llençol, on està dibuixat el mapa-con-leaflet/
    // sempre cal afegir amb el mètode addTo

    // El següent enllaç ens permet escollir entre diferents tipus de mapes disponibles
    // https://leaflet-extras.github.io/leaflet-providers/preview/
    
    
    // opció 1: (OpenTopoMap) topografic
    L.tileLayer('https://{s}.tile.opentopomap.org/{z}/{x}/{y}.png', {
    maxZoom: 17,
    attribution: 'Map data: &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, <a href="http://viewfinderpanoramas.org">SRTM</a> | Map style: &copy; <a href="https://opentopomap.org">OpenTopoMap</a> (<a href="https://creativecommons.org/licenses/by-sa/3.0/">CC-BY-SA</a>)'
    }).addTo(mapa);

    
    // opció 2: (OpenStreetMap)  
    // L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    //     attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    // }).addTo(mapa);


    // afegir un control d'Escala
    L.control.scale().addTo(mapa); // es veu en el marge inferior esquerra

    // poligon de 3 costats
    polygon = L.polygon([
    [41.584,1.631],
    [41.580,1.623],
    [41.582,1.610]
    ]).addTo(mapa);
    
    console.info(coordenades);
    console.info([41.58406,1.60141]);
    // afegir un marcador amb missatge, que apareix quan es fa click sobre
    let marcador1 = L.marker([41.58406,1.60141]).addTo(mapa);
    marcador1.bindPopup("<b>Missatge Personalitzat</b> <i>Hola!<i>")//.openPopup();

    // afegir un MARCADOR 
    L.marker([41.66, 1.71]).addTo(mapa);
    L.marker([coordenades[0],coordenades[1]]).addTo(mapa);

    //CIRCle
     let circle = L.circle([41.58406, 1.60141], {
     color: 'red',
     fillColor: '#f03',
     fillOpacity: 0.2,
     radius: 100
     }).addTo(mapa);

    let circle2 = L.circle([coordenades[0],coordenades[1]], {
    color: 'yellow',
    fillColor: 'lime',
    fillOpacity: 0.2,
    radius: 500
    }).addTo(mapa);

}