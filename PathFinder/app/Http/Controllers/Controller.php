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

        var_dump($ubicacionesEmpresa);

        var_dump($usersTrackedHoy);

        var_dump($usersEmpleatsCount);

        var_dump($invitaciones);

        return view('dashboard', [
            'invitaciones' => $invitaciones,
            'usersEmpleatsCount' => $usersEmpleatsCount,
            'usersTrackedHoy' => $usersTrackedHoy,
            'ubicacionesEmpresa' => $ubicacionesEmpresa,
            'user' => $request->user(),
        ]);
        
        // return view('tracking', array(
        //     'users' => 
        //         array(
        //             "id" => 2,
        //             "nombre" => "Toni",
        //             "apellidos" => "Puig Quilez",
        //             "organizacion" => 1,
        //             "email" => "tonitonipuig@gmail.com",
        //             "email_verified_at" => null,
        //             "created_at" => "2023-04-24T14:40:08.000000Z",
        //             "updated_at" => "2023-04-26T15:33:51.000000Z"
        //         ),
        //         array(
        //             "id" => 4,
        //             "nombre" => "toni",
        //             "apellidos" => "Doe",
        //             "organizacion" => 2,
        //             "email" => "john.doe@example.com",
        //             "email_verified_at" => null,
        //             "created_at" => "2023-04-24T15:40:27.000000Z",
        //             "updated_at" => "2023-04-24T15:40:27.000000Z"
        //         ),
        //         array(
        //             "id" => 5,
        //             "nombre" => "toni",
        //             "apellidos" => "Doe",
        //             "organizacion" => 2,
        //             "email" => "ras@example.com",
        //             "email_verified_at" => null,
        //             "created_at" => "2023-04-25T14:15:06.000000Z",
        //             "updated_at" => "2023-04-25T14:15:06.000000Z"
        //         ),
        //         array(
        //             "id" => 9,
        //             "nombre" => "hector",
        //             "apellidos" => "asurza",
        //             "organizacion" => 1,
        //             "email" => "hasurza@gmail.com",
        //             "email_verified_at" => null,
        //             "created_at" => "2023-04-26T15:27:41.000000Z",
        //             "updated_at" => "2023-04-26T15:27:41.000000Z"
        //         )
        //     ,
        //     'user' => Auth::user(),
        //     'proba' => 'a',
        // ));
    }
}