<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Carbon\Carbon;

class TrackingTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // Asegúrate de que haya algunos empleados en la tabla 'users' antes de ejecutar este seeder
        $employeeIds = [1,1,1]; // Ejemplo de ids de empleados

        $trackingData = [];

        foreach ($employeeIds as $employeeId) {
            for ($i = 0; $i < 1; $i++) {
                $coordenadas = rand(100000, 999999) . ',' . rand(100000, 999999);
                $trackingData[] = [
                    'empleado' => $employeeId,
                    'momento' => Carbon::now()->subHours(rand(1, 100))->toDateTimeString(),
                    'coordenadas' => $coordenadas,
                    'created_at' => Carbon::now(),
                    'updated_at' => Carbon::now(),
                ];
            }
        }        

        DB::table('tracking')->insert($trackingData);
    }
}
