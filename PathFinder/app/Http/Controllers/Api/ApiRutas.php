<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\ListaUbicacion;
use App\Models\Ubicacion;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Models\Ruta;
use App\Models\PuntoDeRuta;
use Illuminate\Support\Facades\Auth;
use PhpParser\Node\Stmt\TryCatch;

class ApiRutas extends Controller
{
    public function get_rutas(Request $request){
        try {
            $id = Auth::user()->id;
            $rutas = Ruta::where('usuario', $id)->get();
            return response()->json([
                'status' => 'success',
                'message' => 'Rutas obtenidas',
                'data' => $rutas
            ], 200);
        } catch (\Throwable $th) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al obtener rutas',
            ], 500);
        }
    }
}