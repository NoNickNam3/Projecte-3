<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use App\Models\ListaUbicacion;
use App\Models\Ubicacion;
use App\Models\User;

class ListaUbicacionesSeeder extends Seeder
{
    public function run()
    {
        $ubicaciones = Ubicacion::all();
        $users = User::all();

        foreach ($ubicaciones as $ubicacion) {
            // Escogemos un usuario aleatorio para enlazarlo con la ubicación
            $user = $users->random();

            DB::table('lista_ubicaciones')->insert([
                'contacto' => $ubicacion->id,
                'empleado' => $user->id
            ]);
        }
    }
}

