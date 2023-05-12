<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class UsersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert([
            [
                'nombre' => 'Toni',
                'apellidos' => 'Puig',
                'organizacion' => null,
                'email' => 'tonitonipuig@gmail.com',
                'email_verified_at' => null,
                'password' => '$2y$10$vBxnC/wwH97njw8DAVt9U.6/kiYNTXb1aMgewsy.QqHubbKaIVUS.',
                'remember_token' => null,
            ],
        ]);

        DB::table('users')->insert([
            [
                'nombre' => 'Maria',
                'apellidos' => 'Lopez',
                'organizacion' => null,
                'email' => 'marialopez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña456'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Hector',
                'apellidos' => 'Asurza',
                'organizacion' => 3,
                'email' => 'hasurza@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('hasurza98'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Juan',
                'apellidos' => 'Garcia',
                'organizacion' => 3,
                'email' => 'juangarcia@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña789'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Laura',
                'apellidos' => 'Martinez',
                'organizacion' => 3,
                'email' => 'lauramartinez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña123'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Pablo',
                'apellidos' => 'Gonzalez',
                'organizacion' => 1,
                'email' => 'pablogonzalez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña456'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Carla',
                'apellidos' => 'Ramirez',
                'organizacion' => 3,
                'email' => 'carlaramirez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña789'),
                'remember_token' => null,
            ]
        ]);

        
    }
}
