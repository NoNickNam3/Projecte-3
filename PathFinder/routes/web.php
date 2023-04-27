<?php

use App\Http\Controllers\ProfileController;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ListaUbicacionController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/testing',[ListaUbicacionController::class, 'getLlistaUbicacions']);

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::get('/tracking', function () {
    return view('tracking');
})->middleware(['auth', 'verified'])->name('tracking');

// Route::get('/tracking', [ProfileController::class, 'getUsers'])->middleware(['auth', 'verified'])->name('tracking');


Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

//TESTING
Route::get('/ubicaciones/{id}/coordenada', [App\Http\Controllers\UbicacionController::class, 'getCoordenada']);
Route::get('/perfil/getUsers', [App\Http\Controllers\ProfileController::class, 'getUsers']);
// Route::get('/testing/index', [App\Http\Controllers\Controller::class, 'index']);


require __DIR__.'/auth.php';
