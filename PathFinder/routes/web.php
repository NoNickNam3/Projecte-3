<?php

use App\Http\Controllers\EmpleadosController;
use App\Http\Controllers\ProfileController;
use App\Http\Controllers\Controller;
use App\Http\Controllers\TrackingController;
use App\Models\ListaUbicacion;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ListaUbicacionController;
use App\Http\Controllers\UbicacionController;
use App\Mail\EnviarMail;
use Illuminate\Facades\Mail;

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
    return Redirect::to('/login');
});

Route::get('/testing', [ListaUbicacionController::class, 'getLlistaUbicacions']);

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    //PERFIL
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');

    //TRACKING
    Route::get('/tracking', [TrackingController::class, 'index'])->name('tracking');
    Route::post('/get_tracking_user', [TrackingController::class, 'get_tracking_user']);

    //UBICACION
    Route::get('/ubicaciones', [UbicacionController::class, 'index'])->name('ubicaciones');
    Route::post('/get_ubicaciones_user', [UbicacionController::class, 'get_ubicaciones_user']);
    Route::post('/ubicaciones', [UbicacionController::class, 'store'])->name('ubicaciones.store');
    Route::post('/eliminar_ubicacion', [UbicacionController::class, 'eliminar_ubicacion'])->name('eliminar_ubicacion');
    Route::put('/ubicaciones/{id}', [UbicacionController::class, 'update'])->name('ubicaciones.update');

    //GESTIONAR EMPLEADOS
    Route::get('/enviar_codigo', [EmpleadosController::class, 'enviar_codigo'])->name('enviar_codigo');
});

//TESTING
Route::get('/ubicaciones/{id}/coordenada', [App\Http\Controllers\UbicacionController::class, 'getCoordenada']);
Route::get('/perfil/getUsers', [App\Http\Controllers\ProfileController::class, 'getUsers']);
// Route::get('/testing/index', [App\Http\Controllers\Controller::class, 'index']);

// Route::get('/tracking', function () {
//     return view('tracking');
// })->middleware(['auth', 'verified'])->name('tracking');

// Route::get('/trackingc', [TrackingController::class, 'get_tracking_user'])->name('trackingc');

// Route::post('/ubicacion_update', function () {
//     $request->validate([
//         'nombre' => 'required|max:100',
//         'coordenada' => 'required|max:100',
//         'observaciones' => 'nullable|max:300',
//         'direccion' => 'nullable|max:100',
//         'fav' => 'required|integer|min:0|max:1',
//     ]);

//     try {
//         $ubicacion = Ubicacion::findOrFail($id);
//         $ubicacion->update($request->all());
//         return redirect()->route('ubicaciones')->with('success', 'Ubicación actualizada exitosamente.');
//     } catch (\Exception $e) {
//         return redirect()->route('ubicaciones')->with('error', 'Error al actualizar la ubicación: ' . $e->getMessage());
//     }
// });


// Route::get('/test_mail', function () use ($email,$nom,$codi) {
//     \Illuminate\Support\Facades\Mail::to($email)->send(new EnviarMail($email,$nom,$codi));
//     exit;
// });


require __DIR__ . '/auth.php';