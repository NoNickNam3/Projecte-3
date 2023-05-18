<?php

namespace App\Http\Controllers;

use App\Models\Invitacion;
use App\Models\ListaUbicacion;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Auth\User;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Spatie\FlareClient\View;

class Controller extends BaseController
{
    use AuthorizesRequests, ValidatesRequests;

    public function index(Request $request)
    {
        $orgId = Auth::id();

        $invitaciones = Invitacion::all()->count();

        $usersEmpleatsCount = User::where('organizacion', $orgId)
            ->where('id', '!=', $orgId)
            ->count();

        $usersTrackedHoy = User::where('organizacion', $orgId)
            ->where('id', '!=', $orgId)
            ->join('tracking', 'users.id', '=', 'tracking.empleado')
            ->whereDate('tracking.momento', '=', date('Y-m-d'))
            ->count();

        $ubicacionesEmpresa = ListaUbicacion::where('empleado', $orgId)->count();

        $users = User::where('organizacion', $orgId)->where('id', '!=', $orgId)->get();
        $listNombres = array();
        $listValores = array();
        foreach ($users as $user) {
            $lista_ubicaciones = ListaUbicacion::where('empleado', $user->id)->get();
            array_push($listNombres, $user->nombre . ' ' . $user->apellidos);
            array_push($listValores, $lista_ubicaciones->count());
        }

        //GRAFICO DE BARRAS
        $results = DB::select("
            SELECT 
                YEAR(momento_temporal) AS anio,
                MONTH(momento_temporal) AS mes, 
                COUNT(*) AS cantidad_rutas
            FROM 
                rutas
            GROUP BY 
                YEAR(momento_temporal),
                MONTH(momento_temporal)
            ORDER BY 
                anio, 
                mes
        ");

        $meses = array(1 => 0,2 => 0,3 => 0,4 => 0,5 => 0,6 => 0,7 => 0,8 => 0,9 => 0,10 => 0,11 => 0,12 => 0);
        $qt =array();
        foreach ($meses as $key => $value) {
            foreach ($results as $result) {
                if ($result->mes == $key) {
                    $meses[$key] = $result->cantidad_rutas;
                }
            }
            array_push($qt, $meses[$key]);
        }

        return view('dashboard', [
            'invitaciones' => $invitaciones,
            'usersEmpleatsCount' => $usersEmpleatsCount,
            'usersTrackedHoy' => $usersTrackedHoy,
            'ubicacionesEmpresa' => $ubicacionesEmpresa,
            'rutasCreadasPorMes' => $qt,
            'user' => $request->user(),
            'listNombres' => $listNombres,
            'listValores' => $listValores
        ]);
    }
}