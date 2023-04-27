addEventListener('DOMContentLoaded',function ()
{
	
    let latitut = 41.68406;
    let longitut = 1.1941;

    let zoom = 11; // número + gran, més aprop
    // número + petit, més lluny
    
	let mapa = L.map('elMeuMapa').setView([latitut, longitut], zoom);
    
	

	L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
	maxZoom: 18
	}).addTo(mapa);

	// Un grup de cercles 
	let group1 = L.featureGroup();
	let group2 = L.featureGroup();
	
	// cercles on es pot fer click
	let circle1 = L.circle([41.6663888888889, 1.175],{
		radius: 150, 
		color:'red',
		weight:.5, 
		opacity:1,
		fillColor: 'red',
		fillOpacity:1
	}).addTo(group1);
	let circle2 = L.circle([41.6502777777778, 1.19333333333333], {
		radius: 150, 
		color:'red',
		weight:.5, 
		opacity:1,
		fillColor: 'red',
		fillOpacity:1
	}).addTo(group1);
	
	// cercles que es mostraran segon l'esdeveniment
	let circle3 = L.circle([41.6191666666667, 1.21222222222222], {
		radius: 150, 
		color:'white',
		weight:.5, 
		opacity:1,
		fillColor: '#008000',
		fillOpacity:1
	}).addTo(group2);
	let circle4 = L.circle([41.6761111111111, 1.195], {
		radius: 150, 
		color:'white',
		weight:.5, 
		opacity:1,
		fillColor: '#008000',
		fillOpacity:1
	}).addTo(group2);
	
	mapa.addLayer(group1);
	// Programar esdeveniment per quan als cercles del grup1 es prem:
	group1.on('click', function(){
		  // si el segon grup ja és al mapa
		  if(mapa.hasLayer(group2)){
			mapa.removeLayer(group2);
		  }
		  else {
			mapa.addLayer(group2);
		  }
		})	
	
});
