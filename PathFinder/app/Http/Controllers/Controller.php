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

        $usersTrackedHoy= User::where('organizacion', $orgId)
            ->where('id', '!=', $orgId)
            ->join('tracking', 'users.id', '=', 'tracking.empleado')
            ->whereDate('tracking.momento', '=', date('Y-m-d'))
            ->count();

        $ubicacionesEmpresa = ListaUbicacion::where('empleado', $orgId)->count();

        //Select de los usuarios de la organizacion = 1 y hacer un count de las lista_ubicaciones de cada uno
        $users = User::where('organizacion', $orgId)->where('id', '!=', $orgId)->get();
        $listNombres = array();
        $listValores = array();
        foreach ($users as $user) {
            $lista_ubicaciones = ListaUbicacion::where('empleado', $user->id)->get();
            // $a = array('user' => $user->nombre.' '.$user->apellidos, 'count' => $lista_ubicaciones->count());
            array_push($listNombres, $user->nombre.' '.$user->apellidos);
            array_push($listValores, $lista_ubicaciones->count());
        }

        var_dump($listNombres);
        var_dump($listValores);   

        return view('dashboard', [
            'invitaciones' => $invitaciones,
            'usersEmpleatsCount' => $usersEmpleatsCount,
            'usersTrackedHoy' => $usersTrackedHoy,
            'ubicacionesEmpresa' => $ubicacionesEmpresa,
            'user' => $request->user(),
        ]);
    }
}