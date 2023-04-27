
addEventListener('DOMContentLoaded',function ()
{
	// es poden trobar més layers a: https://leaflet-extras.github.io/leaflet-providers/preview/
	
	let capaOSM = L.tileLayer('http://tile.openstreetmap.org/{z}/{x}/{y}.png');
    
	let capaRelieve   = L.tileLayer('http://tile.stamen.com/terrain/{z}/{x}/{y}.jpg');

	let capaCatastro = L.tileLayer.wms('http://ovc.catastro.meh.es/Cartografia/WMS/ServidorWMS.aspx', {
			format: 'image/png',
			transparent: false,
			continuousWorld : true,
			attribution: '<a href="https://www.sedecatastro.gob.es/"" target="_blank">Dirección General de Catastro</a>',
			maxZoom: 20
		});
			
    let capaPNOA = L.tileLayer.wms('http://www.ign.es/wms-inspire/pnoa-ma?', {
            layers: 'OI.OrthoimageCoverage',
            attribution: 'PNOA cedido por © <a href="http://www.ign.es/ign/main/index.do" target="_blank">Instituto Geográfico Nacional de España</a>'
        })
				

	let map = L.map('elMeuMapa', {
							center: [40.872040,0.153191],
							zoom: 8,
								});
								
	 capaOSM.addTo(map); // opció dels radios seleccionada
	 //capaRelieve.addTo(map);
	 //capaPNOA.addTo(map);
	
	let capasBase = {
		"Relieve": capaRelieve,
		"OpenStreetMap": capaOSM,
		 "Catastro": capaCatastro,
		 "PNOA":capaPNOA
	};

	let selectorCapas = new L.control.layers(capasBase);
	selectorCapas.addTo(map);

});
