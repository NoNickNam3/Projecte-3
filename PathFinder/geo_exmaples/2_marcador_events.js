
addEventListener('DOMContentLoaded',function ()
{
	
    let latitud = 41.58406;
    let longitud = 1.60141;
	let center = [latitud, longitud];

    let zoom = 10; // número + gran, més aprop
    // número + petit, més lluny
    
	
	let mapa = L.map('elMeuMapa').setView(center, zoom);
    
	L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
	maxZoom: 18
	}).addTo(mapa);


	// afegir un MARCADOR que es pot moure
	let marcador = L.marker([41.66, 1.71],{draggable: true}).addTo(mapa);
	//let marcador = L.marker([41.66, 1.71]).addTo(mapa);
	
	// afegir un ESCOLTADOR, quan es finalitza el moviment, es mostren les coordenades on està el marcador
	marcador.on('dragend', function()
	{
		let contingut = 'El marcador s\'ha mogut al punt: ['+marcador.getLatLng().lat.toFixed(6)+','+marcador.getLatLng().lng.toFixed(6)+']  Zoom: '+mapa.getZoom();
		crearMissatge(contingut);
		
	});
	
	// afegir un ESCOLTADOR, quan es fa click sobre el mapa, el marcador es mou allà on s'ha fet click
	mapa.on('click', function (e) {
		
		let contingut = 'Has fet click al punt: ['+e.latlng.lat.toFixed(6)+','+e.latlng.lng.toFixed(6)+']  Zoom: '+mapa.getZoom();
		crearMissatge(contingut);
		marcador.setLatLng(e.latlng);
		
	});
	
});

function crearMissatge(contingut)
{
	let p = document.createElement('p');
	let txt = document.createTextNode(contingut);
	
	p.appendChild(txt);
	
	document.getElementById('Resultat').appendChild(p);
}