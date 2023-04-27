<?php

namespace App\Http\Controllers;

use App\Models\Ubicacion;
use Illuminate\Http\Request;

class UbicacionController extends Controller
{
    public function index()
    {
        // $ubicaciones = Ubicacion::all();
        // return view('ubicaciones.index', compact('ubicaciones'));
    }

    public function create()
    {
        // return view('ubicaciones.create');
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
        $ubicacion = Ubicacion::create($request->all());
        return redirect()->route('ubicaciones.index')->with('success', 'Ubicación creada exitosamente.');
    }

    public function getCoordenada($id)
    {
        $ubicacion = Ubicacion::find($id);
        if (!$ubicacion) return null;
        return $ubicacion->coordenada;
    }

    public function show(Ubicacion $ubicacion)
    {
        return view('ubicaciones.show', compact('ubicacion'));
    }

    public function edit(Ubicacion $ubicacion)
    {
        // return view('ubicaciones.edit', compact('ubicacion'));
    }

    public function update(Request $request, Ubicacion $ubicacion)
    {
        $request->validate([
            'nombre' => 'required|max:100',
            'coordenada' => 'required|max:100',
            'observaciones' => 'nullable|max:300',
            'direccion' => 'nullable|max:100',
            'fav' => 'required|integer|min:0|max:1',
        ]);

        $ubicacion->update($request->all());
        return redirect()->route('ubicaciones.index')->with('success', 'Ubicación actualizada exitosamente.');
    }

    public function destroy(Ubicacion $ubicacion)
    {
        $ubicacion->delete();
        return redirect()->route('ubicaciones.index')->with('success', 'Ubicación eliminada exitosamente.');
    }
}
