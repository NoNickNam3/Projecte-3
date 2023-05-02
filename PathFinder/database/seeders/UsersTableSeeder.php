<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

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
                'nombre' => 'toni',
                'apellidos' => 'puig',
                'organizacion' => 1,
                'email' => 'tonitonipuig@gmail.com',
                'email_verified_at' => null,
                'password' => '$2y$10$vBxnC/wwH97njw8DAVt9U.6/kiYNTXb1aMgewsy.QqHubbKaIVUS.',
                'remember_token' => null,
            ],
        ]);
    }
}
