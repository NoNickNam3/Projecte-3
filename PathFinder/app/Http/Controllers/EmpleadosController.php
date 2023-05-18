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
    public function index(Request $request)
    {
        $empleados = Auth::id();
        $users = User::where('organizacion', $empleados)->where('id', '!=', $empleados)->get();
        return view('empleados', [
            'users' => $users,
            'user' => $request->user(),
        ]);
    }
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

    public function desvincular_emp(Request $request)
    {
        try {
            $id = $request->input('id');
            $user = User::findOrFail($id);
            $user->organizacion = null;
            $user->save();
            return redirect()->route('empleados')->with('success', 'Empleado desvinculado exitosamente.');   
        } catch (\Throwable $th) {
            return redirect()->route('empleados')->with('error', 'Error al desvincular empleado.');
        }
    }
}
