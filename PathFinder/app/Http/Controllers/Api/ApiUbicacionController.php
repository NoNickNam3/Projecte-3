<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Ubicacion;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

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
            'data' => $ubicacion->coordenada
        ],200);
    }

    public function store(Request $request)
    {
        $request->validate([
            'nombre' => 'required|max:100',
            'coordenada' => 'required|max:100',
            'observaciones' => 'nullable|max:300',
            'direccion' => 'nullable|max:100',
            'fav' => 'required|integer|min:0|max:1',
        ]);
    
        try {
            $ubicacion = Ubicacion::create($request->all());
            return response()->json([
                'status' => 'success',
                'message' => 'Ubicación creada exitosamente.',
                'data' => $ubicacion,
            ], Response::HTTP_CREATED);
        } catch (\Exception $e) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al crear la ubicación: ' . $e->getMessage(),
                'data' => [],
            ], Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }

    public function destroy($id)
    {
    try {
        $ubicacion = Ubicacion::findOrFail($id);
        $ubicacion->delete();

        return response()->json([
            'status' => 'success',
            'message' => 'Ubicación eliminada exitosamente.'
        ]);
    } catch (\Exception $e) {
        return response()->json([
            'status' => 'error',
            'message' => 'Error al eliminar la ubicación. '
        ]);
    }
}


    
}