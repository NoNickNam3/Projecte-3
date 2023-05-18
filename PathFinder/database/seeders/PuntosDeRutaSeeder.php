<?php

namespace Database\Seeders;

use App\Models\PuntoDeRuta;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class PuntosDeRutaSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $rutaId = 1;
        $ordre = 1;
        $coordenadas = [
            '40.4168,-3.7038',
            '41.3851,2.1734',    
            '37.3891,-5.9845',   
            '28.4593,-16.2844',  
            '39.4699,-0.3763',   
            '43.2627,-2.9253',   
            '37.1773,-3.5986',   
            '39.8628,-4.0273',   
            '41.6522,-4.7245',   
            '28.1237,-15.4360',
            '42.2670,2.9608',
            '41.5952,1.8374',
            '41.6488,-0.8891',
            '41.1498,1.1055',
            '42.7043,0.7934',
            '41.1498,1.1055',   
            '42.7043,0.7934',
        ];

        foreach ($coordenadas as $coordenada) {
            PuntoDeRuta::create([
                'ruta' => $rutaId,
                'coordenada' => $coordenada,
                'ordre' => $ordre,
                'completado' => null,
            ]);
            $ordre++;
        }
    }
}
