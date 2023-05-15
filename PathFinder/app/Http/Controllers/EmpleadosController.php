<?php

namespace App\Http\Controllers;

use App\Models\Invitacion;
use Illuminate\Http\Request;
use App\Mail\EnviarMail;
use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Facades\Auth;
use App\Models\User;

class EmpleadosController extends Controller
{
    //
    public function enviar_codigo(Request $request)
    {
        $email = $request->input('email');
        $nom = User::where('email', $email)->first()->nombre;
        if ($nom == null) {
            return redirect()->route('empleados')->with('error', 'No hay ninún usuario con ese email.');
        }
        $id = User::where('email', $email)->first()->id;
        $codi = rand(100000, 999999);
        Invitacion::create([
            'empresa' => Auth::id(),
            'empleado' => $id,
            'codigo' => $codi,
        ]);
        Mail::to($email)->send(new EnviarMail($email, $nom, $codi));
        return redirect()->route('empleados')->with('success', 'Correo enviado exitosamente.');
    }
}