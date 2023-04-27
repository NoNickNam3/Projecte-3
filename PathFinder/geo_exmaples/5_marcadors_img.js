
addEventListener('DOMContentLoaded',function ()
{

	let capaRelieve   = L.tileLayer('http://tile.stamen.com/terrain/{z}/{x}/{y}.jpg');
	  
	
    const map = L.map('elMeuMapa', {layers: [capaRelieve] }).setView([42.2, -8.7], 12);
					
	L.marker([42.219577180236797,-8.733678516914864]).addTo(map);					
	
	L.marker([42.21, -8.73], {
            icon: L.icon({
                iconUrl: './town-11.svg', // cuidado con las rutas
                iconSize: [45, 45],
                className: 'town-icon'
            })
        }).addTo(map);
	  
});
