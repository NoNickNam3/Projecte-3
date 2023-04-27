addEventListener('DOMContentLoaded',function ()
{
	
    let latitut = 41.21468; // nord - sud
    let longitut = 1.85877; // est - oest

    let zoom = 8; // número + gran, més aprop
    // número + petit, més lluny
    
	let mapa = L.map('elMeuMapa').setView([latitut, longitut], zoom);
    

	L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
	maxZoom: 18
	}).addTo(mapa);

	let tarragona = new L.LatLng(41.11468, 1.25877);
	let barcelona = new L.LatLng(41.4035, 2.17425);
	let pointList = [tarragona, barcelona];

	let linia = new L.Polyline(pointList, {
		color: 'red',
		weight: 5,
		opacity: 1,
		smoothFactor: 1
	});
	linia.addTo(mapa);
	
});
