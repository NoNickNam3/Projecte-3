<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
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
        return view('tracking', array('us'=>1));
    }
}
