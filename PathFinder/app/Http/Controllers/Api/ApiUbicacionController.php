<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Ubicacion;
use Illuminate\Http\Request;

class ApiUbicacionController extends Controller
{
    public function getCoordenada(Request $request, $id)
    {
        $ubicacion = Ubicacion::find($id);
        if (!$ubicacion) {
            return response()->json([
                'status' => 'error',
                'message' => 'Ubicación no encontrada'
            ], 404);
        }
        return response()->json([
            'status' => 'success',
            'message' => 'Coordenada obtenida',
            'coordenada' => $ubicacion->coordenada
        ]);
    }
}