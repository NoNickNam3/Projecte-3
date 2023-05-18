<?php

namespace Database\Seeders;

use App\Models\Ruta;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Carbon;

class RutaSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $ruta = new Ruta();
        $ruta->usuario = 1;
        $ruta->momento_temporal = Carbon::now()->subMinutes(rand(1, 160440));
        $ruta->observacion = 'Esta es una observación de la ruta 1';
        $ruta->fav = 1;
        $ruta->nombre = 'Ruta 1';
        $ruta->save();
        
        $ruta = new Ruta();
        $ruta->usuario = 1;
        $ruta->momento_temporal = Carbon::now()->subMinutes(rand(1, 160440));
        $ruta->observacion = 'Esta es una observación de la ruta 2';
        $ruta->fav = 0;
        $ruta->nombre = 'Ruta 2';
        $ruta->save();

        for ($i = 1; $i <= 14; $i++) {
            $ruta = new Ruta();
            $ruta->usuario = rand(1, 3);
            $ruta->momento_temporal = Carbon::now()->subMinutes(rand(1, 160440));
            $ruta->observacion = 'Esta es una observación de la ruta ' . ($i + 2); 
            $ruta->fav = 0;
            $ruta->nombre = 'Ruta ' . ($i + 2);
            $ruta->save();
        
            sleep(0.5);
        }
        
    }
}
