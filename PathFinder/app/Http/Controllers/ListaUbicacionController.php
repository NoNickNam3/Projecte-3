<?php

namespace App\Http\Controllers;

use App\Models\ListaUbicacion;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class ListaUbicacionController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        // $listaUbicaciones = ListaUbicacion::all();
        // return view('lista_ubicaciones.index', compact('listaUbicaciones'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        // return view('lista_ubicaciones.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            'contacto' => 'required|exists:ubicaciones,id',
            'empleado' => 'required|exists:users,id',
        ]);

        ListaUbicacion::create($validated);

        return redirect()->route('lista_ubicaciones.index')->with('status', 'ListaUbicacion created successfully');
    }

    public function getLlistaUbicacions()
    {
        $listaUbicaciones = ListaUbicacion::where('empleado',Auth::id())->get();
        if (!$listaUbicaciones) return null;
        return $listaUbicaciones;
    }

    /**
     * Display the specified resource.
     */
    public function show(ListaUbicacion $listaUbicacion)
    {
        return view('lista_ubicaciones.show', compact('listaUbicacion'));
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(ListaUbicacion $listaUbicacion)
    {
        $listaUbicacion->delete();
        return redirect()->route('lista_ubicaciones.index')->with('status', 'ListaUbicacion deleted successfully');
    }
}
