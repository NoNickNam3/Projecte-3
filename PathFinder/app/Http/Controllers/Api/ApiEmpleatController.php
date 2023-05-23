<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Ubicacion;
use App\Models\User;
use Auth;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Models\Invitacion;
use App\Models\Tracking;

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

    public function tracking(Request $request){
        try {
            $id = Auth::id();
            $traking = new Tracking(array('empleado' => $id, 'momento' => Carbon::now(), 
            'coordenadas' => $request->input('location')));
            $traking->save();
            return response()->json([
                'status' => 'success',
                'message' => 'Tracking guardado correctamente',
                'data' => $traking
            ], 200);
        } catch (\Throwable $th) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al guardar el tracking',
            ], 500);
        }
    }

    
}