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
        $employeeIds = [1, 1, 1,1]; // Ejemplo de ids de empleados

        $trackingData = [];

        $coordenadasEjemplo = [
            '41.61759,0.62001',
            '41.15445,1.10654',
            '41.11468,1.25877',
            '41.4035,2.17425'
        ];

        foreach ($employeeIds as $employeeId) {
            for ($i = 0; $i < 1; $i++) {
                $coordenadas = $coordenadasEjemplo[array_rand($coordenadasEjemplo)];

                $trackingData[] = [
                    'empleado' => 1,
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