<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Ubicacion;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Models\Invitacion;

class ApiEmpleatController extends Controller
{
    public function enlazar_codigo(Request $request)
    {
        $codigo = $request->input('codigo');
        $invitacio = Invitacion::where('codigo', $codigo)->first();
        if (!$invitacio) {
            return response()->json([
                'status' => 'error',
                'message' => 'Invitacion no encontrada con este codigo.'
            ], 404);
        }
        try {
            User::where('id', $invitacio->empleado)->update(['organizacion' => $invitacio->empresa]);
            Invitacion::where('codigo', $codigo)->delete();
        } catch (\Throwable $th) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al enlazar cuenta.'
            ], 404);
        }
        return response()->json([
            'status' => 'success',
            'message' => 'Usuario enlazado correctamente.'
        ], 200);
    }

    
}