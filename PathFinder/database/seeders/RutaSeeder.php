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
        $ruta->momento_temporal = Carbon::now();
        $ruta->observacion = 'Esta es una observación de la ruta 1';
        $ruta->fav = 1;
        $ruta->nombre = 'Ruta 1';
        $ruta->save();
        
        $ruta = new Ruta();
        $ruta->usuario = 1;
        $ruta->momento_temporal = Carbon::now();
        $ruta->observacion = 'Esta es una observación de la ruta 2';
        $ruta->fav = 0;
        $ruta->nombre = 'Ruta 2';
        $ruta->save();
    }
}
