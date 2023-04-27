
addEventListener('DOMContentLoaded',function ()
{
	  let map = L.map('elMeuMapa').setView([41.585, 1.6227], 9);
      L.tileLayer('https://{s}.tile.opentopomap.org/{z}/{x}/{y}.png', {
			maxZoom: 17,
			attribution: 'Map data: &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, <a href="http://viewfinderpanoramas.org">SRTM</a> | Map style: &copy; <a href="https://opentopomap.org">OpenTopoMap</a> (<a href="https://creativecommons.org/licenses/by-sa/3.0/">CC-BY-SA</a>)'
	}).addTo(map);

		let coordenadesPos = [41.581164,10.632229];
		
		let p = new L.Popup({ closeOnClick: false })
					.setContent("Perill!!! zona infectada de programadors")
					.setLatLng(coordenadesPos);
		p.openOn(map);

		let iconoBase = L.Icon.extend({
				options: {
					iconSize:     [24, 39],
					iconAnchor:   [16, 87],
					popupAnchor:  [-3, -76]
				}
			});
		 
		let estiloPopup = {'maxWidth': '500'} 
		
		let iconoVerde = new iconoBase({iconUrl: 'verde.png'});
		let marker = L.marker([41.58401, 1.60136],{icon: iconoVerde}).bindPopup("Informació sobre el Milà i Fontanals. ...",estiloPopup).addTo(map);
		
		marker.on('mouseover', function (e) {
            this.openPopup();
        });
        marker.on('mouseout', function (e) {
            this.closePopup();
        });
});

	