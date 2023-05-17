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
        $employeeIds = [1, 2, 3,4,5,6]; // Ejemplo de ids de empleados

        $trackingData = [];

        $coordenadasEjemplo = [
            '41.61759,0.62001',
            '41.15445,1.10654',
            '41.11468,1.25877',
            '41.4035,2.17425',
            '41.3851,2.1734', // Barcelona
            '39.4699,-0.3763', // Valencia
            '37.3891,-5.9845', // Sevilla
            '43.2627,-2.9253', // Bilbao
            '36.7213,-4.4214', // Málaga
            '37.1773,-3.5985', // Granada
            '39.8628,-4.0273', // Toledo
            '42.8125,-1.6458', // Pamplona
            '43.3603,-5.8448', // Oviedo
            '41.6488,-0.8891'  // Zaragoza
        ];

        foreach ($employeeIds as $employeeId) {
            for ($i = 0; $i < count($employeeIds) ; $i++) {
                $coordenadas = $coordenadasEjemplo[array_rand($coordenadasEjemplo)];

                $trackingData[] = [
                    'empleado' => $employeeId,
                    'momento' => Carbon::now()->subHours(rand(1,1000))->toDateTimeString(),
                    'coordenadas' => $coordenadas,
                    'created_at' => Carbon::now(),
                    'updated_at' => Carbon::now(),
                ];
            }
        }

        DB::table('tracking')->insert($trackingData);
    }
}