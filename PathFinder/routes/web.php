<?php

use App\Http\Controllers\EmpleadosController;
use App\Http\Controllers\ProfileController;
use App\Http\Controllers\Controller;
use App\Http\Controllers\TrackingController;
use App\Models\ListaUbicacion;
use App\Models\Ruta;
use App\Models\Tracking;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ListaUbicacionController;
use App\Http\Controllers\UbicacionController;
use App\Models\Ubicacion;
use App\Models\PuntoDeRuta;
use App\Mail\EnviarMail;
use App\Http\Controllers\OptimizadorController;
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

// Route::get('/dashboard', function () {
//     return view('dashboard');
// })->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    //INICIO
    Route::get('/dashboard',[Controller::class, 'index'])->name('dashboard');

    //PERFIL
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');

    //TRACKING
    Route::get('/tracking', [TrackingController::class, 'index'])->name('tracking');
    Route::post('/get_tracking_user', [TrackingController::class, 'get_tracking_user']);
    Route::post('/get_dias_user', [TrackingController::class, 'get_dias_user']);

    //UBICACION
    Route::get('/ubicaciones', [UbicacionController::class, 'index'])->name('ubicaciones');
    Route::post('/get_ubicaciones_user', [UbicacionController::class, 'get_ubicaciones_user']);
    Route::post('/ubicaciones', [UbicacionController::class, 'store'])->name('ubicaciones.store');
    Route::post('/eliminar_ubicacion', [UbicacionController::class, 'eliminar_ubicacion'])->name('eliminar_ubicacion');
    Route::put('/ubicaciones/{id}', [UbicacionController::class, 'update'])->name('ubicaciones.update');
    Route::post('/importar_ubicaciones', [UbicacionController::class, 'importar_ubicaciones'])->name('importar');

    //GESTIONAR EMPLEADOS
    Route::get('/empleados', [EmpleadosController::class, 'index'])->name('empleados');
    Route::post('/enviar_codigo', [EmpleadosController::class, 'enviar_codigo'])->name('enviar_codigo');
    Route::post('/desvincular_emp', [EmpleadosController::class, 'desvincular_emp'])->name('desvincular_emp');
});

//TESTING
Route::get('/ubicaciones/{id}/coordenada', [App\Http\Controllers\UbicacionController::class, 'getCoordenada']);
Route::get('/perfil/getUsers', [App\Http\Controllers\ProfileController::class, 'getUsers']);

Route::get('/test',function(){

    $sortida = "43.41547166666667,-7.371056666666667";

    $data = '[{
        "coord": "43.0072,-9.1877"
    }, {
        "coord": "40.4168,-3.6861"
    }]';

    $data = json_decode($data);

    $coordenadas = '';

    if(count($data) > 10) {
        return response()->json([
            'status' => 'error',
            'message' => 'No es poden enviar més de 10 parades',
        ]);
    }

    foreach ($data as $coord) { 
        $coordenadas .= $coord->coord . ';';
    }


    $coordenadas = rtrim($coordenadas, ';');

    $coordenadas = $sortida . ';' . $coordenadas;

    echo $coordenadas;
    exit;

    $baseUrl = 'https://api.mapbox.com/optimized-trips/v1/mapbox/driving/';

    $client = new Client();

    $response = $client->get($baseUrl . $coordenadas, [
        'query' => [
            'access_token' => env('MAPBOX_KEY'),
            'steps' => 'false',
        ]
    ]);

    if($response->getStatusCode() != 200) {
        return response()->json([
            'status' => 'error',
            'message' => 'Error al calcular la ruta',
        ]);
    }

    $dataArr = json_decode($response->getBody(), true);

    $locations = [];
    foreach ($dataArr['waypoints'] as $waypoint) {
        $locations[$waypoint['waypoint_index']] = $waypoint['location'];
    }

    ksort($locations);

    $tripDuration = $dataArr['trips'][0]['duration'];
    $tripDistance = $dataArr['trips'][0]['distance'];

    $result = [
        'locations' => $locations,
        'duracioTotal' => $tripDuration,
        'distanciaTotal' => $tripDistance
    ];

    try {
        $r = new Ruta(array('usuario'=>Auth::id()));
        $r->save();
        $idRuta = $r->id;

        foreach ($result['locations'] as $key => $location) {
            $p = new PuntoDeRuta(array('ruta'=>$idRuta, 
                                'coordenada'=>$location[0].','.$location[1],
                                'ordre'=>$key+1));
            $p->save();
        }
    } catch (\Exception $e) {
        return response()->json([
            'status' => 'error',
            'message' => 'Error al guardar la ruta',
        ]);
    }

    return response()->json([
        'status' => 'success',
        'message' => 'Ruta optimitzada',
        'data' => $result,
    ], 200);
});

// Route::post('/optim', [App\Http\Controllers\OptimizadorController::class, 'optimizar']);
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