<?php

namespace Database\Seeders;

use App\Models\Ubicacion;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class UbicacionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */

    public function run()
    {
        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'La Sagrada Familia';
        $ubicacion->coordenada = '41.4036,2.1744';
        $ubicacion->observaciones = 'Mejor visitar temprano en la mañana para evitar las largas filas';
        $ubicacion->direccion = 'Carrer de Mallorca, 401, 08013 Barcelona';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Parque Güell';
        $ubicacion->coordenada = '41.4147,2.1528';
        $ubicacion->observaciones = 'El acceso a la zona monumental del parque es de pago';
        $ubicacion->direccion = 'Carrer d\'Olot, s/n, 08024 Barcelona';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'La Rambla';
        $ubicacion->coordenada = '41.3809,2.1731';
        $ubicacion->observaciones = 'Lugar muy turístico, ten cuidado con los carteristas';
        $ubicacion->direccion = 'La Rambla, 08002 Barcelona';
        $ubicacion->fav = true;
        $ubicacion->save();


        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Castillo de Catuña';
        $ubicacion->coordenada = '42.6699,-8.1149';
        $ubicacion->observaciones = 'Antiguo castillo restaurado, ofrece visitas guiadas';
        $ubicacion->direccion = 'Estrada Catuña, 15, 15807 Santiago de Compostela, A Coruña';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Pazo de Oca';
        $ubicacion->coordenada = '42.6335,-8.3347';
        $ubicacion->observaciones = 'Conocido como los "jardines gallegos de la Alhambra"';
        $ubicacion->direccion = 'Pazo de Oca, 36680 Oca, Pontevedra';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Pazo de Rubianes';
        $ubicacion->coordenada = '42.4238,-8.7477';
        $ubicacion->observaciones = 'Visitas guiadas con cata de vino y destilados de hierbas';
        $ubicacion->direccion = 'Pazo de Rubianes, 36636 Vilagarcía de Arousa, Pontevedra';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Mirador de Ézaro';
        $ubicacion->coordenada = '43.0148,-9.1867';
        $ubicacion->observaciones = 'Impresionantes vistas de la desembocadura del río Xallas al mar';
        $ubicacion->direccion = 'Camiño do Miradoiro, 15113 Dumbria, A Coruña';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Cascada del Ézaro';
        $ubicacion->coordenada = '43.0072,-9.1877';
        $ubicacion->observaciones = 'La caída del agua en el mar es única en Europa';
        $ubicacion->direccion = 'Camiño do Miradoiro, 15113 Dumbria, A Coruña';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Cabo Finisterre';
        $ubicacion->coordenada = '42.9079,-9.2659';
        $ubicacion->observaciones = 'El final del mundo en la antigüedad';
        $ubicacion->direccion = 'Cabo Finisterre, 15155 Fisterra, A Coruña';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Plaza Mayor';
        $ubicacion->coordenada = '40.4154,-3.7074';
        $ubicacion->observaciones = 'Una de las plazas más populares de Madrid';
        $ubicacion->direccion = 'Plaza Mayor, 28012 Madrid';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Puerta del Sol';
        $ubicacion->coordenada = '40.4168,-3.7038';
        $ubicacion->observaciones = 'Lugar icónico de Madrid';
        $ubicacion->direccion = 'Puerta del Sol, 28013 Madrid';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Palacio Real de Madrid';
        $ubicacion->coordenada = '40.4179,-3.7144';
        $ubicacion->observaciones = 'Residencia oficial de la familia real española';
        $ubicacion->direccion = 'Calle de Bailén, s/n, 28071 Madrid';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Parque del Retiro';
        $ubicacion->coordenada = '40.4168,-3.6861';
        $ubicacion->observaciones = 'Gran parque en el centro de Madrid';
        $ubicacion->direccion = 'Plaza de la Independencia, 7, 28001 Madrid';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Museo del Prado';
        $ubicacion->coordenada = '40.4138,-3.6942';
        $ubicacion->observaciones = 'Uno de los museos más importantes del mundo';
        $ubicacion->direccion = 'Calle de Felipe IV, s/n, 28014 Madrid';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Castro de Baroña';
        $ubicacion->coordenada = '42.6355,-9.0642';
        $ubicacion->observaciones = 'Restos de un asentamiento celta en la costa';
        $ubicacion->direccion = 'Castro de Baroña, 15992 Porto do Son, A Coruña';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Barrio Gótico';
        $ubicacion->coordenada = '41.3834,2.1766';
        $ubicacion->observaciones = 'El casco histórico de Barcelona, ideal para pasear';
        $ubicacion->direccion = 'Barri Gòtic, 08002 Barcelona';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Casa Batlló';
        $ubicacion->coordenada = '41.3910,2.1647';
        $ubicacion->observaciones = 'Espectacular edificio modernista diseñado por Gaudí';
        $ubicacion->direccion = 'Passeig de Gràcia, 43, 08007 Barcelona';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'La Pedrera (Casa Milà)';
        $ubicacion->coordenada = '41.3954,2.1618';
        $ubicacion->observaciones = 'Otro edificio modernista impresionante de Gaudí';
        $ubicacion->direccion = 'Carrer de Provença, 261-265, 08008 Barcelona';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Machu Picchu';
        $ubicacion->coordenada = '-13.163141,-72.544963';
        $ubicacion->observaciones = 'Importante sitio arqueológico en Perú';
        $ubicacion->direccion = 'Cusco, Perú';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Torre Eiffel';
        $ubicacion->coordenada = '48.8584,2.2945';
        $ubicacion->observaciones = 'Famosa torre de hierro en Francia';
        $ubicacion->direccion = 'Champ de Mars, 5 Avenue Anatole France, 75007 Paris, Francia';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Cataratas del Niágara';
        $ubicacion->coordenada = '43.0828,-79.0749';
        $ubicacion->observaciones = 'Impresionante conjunto de cataratas en la frontera de EE. UU. y Canadá';
        $ubicacion->direccion = 'Niagara Falls, NY 14303, EE. UU.';
        $ubicacion->fav = false;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Gran Muralla China';
        $ubicacion->coordenada = '40.4319,116.5704';
        $ubicacion->observaciones = 'Megaestructura de piedra que recorre miles de kilómetros en China';
        $ubicacion->direccion = 'China';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Museo del Louvre';
        $ubicacion->coordenada = '48.8606,2.3376';
        $ubicacion->observaciones = 'Famoso museo de arte en París, Francia';
        $ubicacion->direccion = 'Rue de Rivoli, 75001 Paris, Francia';
        $ubicacion->fav = true;
        $ubicacion->save();

        $ubicacion = new Ubicacion();
        $ubicacion->nombre = 'Petra';
        $ubicacion->coordenada = '30.3285,35.4444';
        $ubicacion->observaciones = 'Antigua ciudad tallada en roca en Jordania';
        $ubicacion->direccion = 'Jordan';
        $ubicacion->fav = false;
        $ubicacion->save();
    }
}