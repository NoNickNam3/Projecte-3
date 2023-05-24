<?php

use App\Http\Controllers\Api\ApiEmpleatController;
use App\Http\Controllers\Auth\RegisteredUserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Api\ApiAuthController;
use App\Http\Controllers\Api\ApiPasswordController;
use App\Http\Controllers\ProfileController;
use App\Http\Controllers\Api\ApiUbicacionController;
use App\Http\Controllers\Api\OptimizadorController;
use App\Http\Controllers\Api\ApiRutas;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::post('/register', [RegisteredUserController::class, 'store']);
Route::post('/login', [ApiAuthController::class, 'login']);

Route::middleware('auth:sanctum')->post('/password/update', [ApiPasswordController::class, 'update']);
Route::middleware('auth:sanctum')->post('/logout', [ApiAuthController::class, 'logout']);
Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::middleware(['auth:sanctum'])->group(function () {
    Route::post('/enlazar_codigo',[ApiEmpleatController::class, 'enlazar_codigo']);
    Route::post('/ubicacion/crear', [ApiUbicacionController::class, 'store']);
    Route::delete('/ubicacion/{id}', [ApiUbicacionController::class, 'destroy']);
    Route::put('/perfil/update', [ProfileController::class, 'update']);
    Route::get('/ubicacion/{id}/coordenada', [ApiUbicacionController::class, 'getCoordenada']);
    Route::get('/getListaUbicaciones', [ApiUbicacionController::class, 'getUbicaciones']);

    //OPTIMIZAR RUTAS
    Route::post('/optimizar', [OptimizadorController::class, 'optimizar']);

    //CONSULTAR RUTAS
    Route::get('/getListaRutas', [ApiRutas::class, 'get_rutas']);
    Route::post('/getRuta', [ApiRutas::class, 'get_ruta']);

    //TRAKING
    Route::post('/tracking', [ApiEmpleatController::class, 'tracking']);
});
