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
                'organizacion' => 1,
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
                'nombre' => 'Ana',
                'apellidos' => 'Sánchez',
                'organizacion' => 1,
                'email' => 'anasanchez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña789'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Pedro',
                'apellidos' => 'Fernández',
                'organizacion' => 3,
                'email' => 'pedrofernandez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña123'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'María',
                'apellidos' => 'López',
                'organizacion' => 3,
                'email' => 'marialopezas@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña456'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Sergio',
                'apellidos' => 'González',
                'organizacion' => 3,
                'email' => 'sergiogonzalez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña789'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Marta',
                'apellidos' => 'Rodríguez',
                'organizacion' => 1,
                'email' => 'martarodriguez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña123'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Alejandro',
                'apellidos' => 'Hernández',
                'organizacion' => 1,
                'email' => 'alejandrohernandez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña456'),
                'remember_token' => null,
            ],
            [
                'nombre' => 'Silvia',
                'apellidos' => 'Gómez',
                'organizacion' => 1,
                'email' => 'silviagomez@gmail.com',
                'email_verified_at' => null,
                'password' => Hash::make('contraseña789'),
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
