<?php
namespace App\Http\Controllers;

use App\Models\ListaUbicacion;
use App\Models\Ubicacion;
use Illuminate\Foundation\Auth\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class UbicacionController extends Controller
{
    public function index(Request $request)
    {
        $userId = Auth::id();
        $lista_ubicaciones = ListaUbicacion::where('empleado', $userId)->get();

        $list = array();
        foreach ($lista_ubicaciones as $lista_ubicacion) {
            $ubicacion = $lista_ubicacion->ubicacion;
            array_push($list, $ubicacion);
        }

        $users = User::where('organizacion', $userId)->where('id', '!=', $userId)->get();

        return view('ubicaciones', [
            'lista_ubicaciones' => json_encode($list),
            'user' => $request->user(),
            'users' => $users,
        ]);
    }

    public function importar_ubicaciones(Request $request)
    {
        $userId = Auth::id();
        $id = $request->input('id');
        
        $lista_ubicacion = ListaUbicacion::where('contacto', $id)->get();
        if ($lista_ubicacion->isEmpty()) {
            return redirect()->route('ubicaciones')->with('error', 'Error al importar el contacto.');
        } else {
            ListaUbicacion::where('contacto', $id)->delete();
        
            $nueva_lista_ubicacion = new ListaUbicacion();
            $nueva_lista_ubicacion->contacto = $id;
            $nueva_lista_ubicacion->empleado = $userId;
            $nueva_lista_ubicacion->save();
        
            return redirect()->route('ubicaciones')->with('success', 'Contacto importado correctamente.');
        }
    }


    public function eliminar_ubicacion(Request $request)
    {
        $id = $request->input('id');
            try {
                $ubicacion = Ubicacion::findOrFail($id);
                $ubicacion->delete();
                return redirect()->route('ubicaciones')->with('success', 'Contacto eliminado correctamente.');
            } catch (\Exception $e) {
                return redirect()->route('ubicaciones')->with('error', 'Error al eliminar contacto.');
            }
    }

    public function get_ubicaciones_user(Request $request)
    {
        $id = $request->input('id');
        $nombre = $request->input('nombre');
        $fav = $request->input('fav');

        $lista_ubicaciones = ListaUbicacion::where('empleado', $id)
            ->join('ubicaciones', 'lista_ubicaciones.contacto', '=', 'ubicaciones.id')
            ->where('ubicaciones.nombre', 'like', $nombre . '%')
            ->when($fav == 1, function ($query) {
                return $query->orderBy('ubicaciones.fav','desc');
            })
            ->get();

        $list = array();
        foreach ($lista_ubicaciones as $lista_ubicacion) {
            $ubicacion = $lista_ubicacion->ubicacion;
            $co = explode(",", $ubicacion->coordenada);
            $ubicacion->coordenada = array('latitud' => $co[0], 'longitud' => $co[1]);
        
            array_push($list, $ubicacion);

        }

        return response()->json($list);
    }

    public function create()
    {
        // return view('ubicaciones.create');
    }

    public function store(Request $request)
    {
        var_dump($request->all());

        try {
            $request->validate([
                'nombre' => 'required|max:100',
                'coordenada' => 'required|max:100',
                'observaciones' => 'nullable|max:300',
                'direccion' => 'nullable|max:100',
                'fav' => 'required|integer|min:0|max:1',
            ]);
            $ubicacion = Ubicacion::create($request->all());
            $nueva_lista_ubicacion = new ListaUbicacion(array('contacto' => $ubicacion->id, 'empleado' => Auth::id()));
            $nueva_lista_ubicacion->save();
            return redirect()->route('ubicaciones')->with('success', 'Ubicación creada exitosamente.');
        } catch (\Exception $e) {
            return redirect()->route('ubicaciones')->with('error', 'Error al crear la ubicación: ' . $e->getMessage());
        }
    }

    public function getCoordenada($id)
    {
        $ubicacion = Ubicacion::find($id);
        if (!$ubicacion)
            return null;
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

    public function update(Request $request, $id)
    {    
        try {

            $request->validate([
                'nombre' => 'required|max:100',
                'coordenada' => 'required|max:100',
                'observaciones' => 'nullable|max:300',
                'direccion' => 'nullable|max:100',
                'fav' => 'required|integer|min:0|max:1',
            ]);
            $ubicacion = Ubicacion::findOrFail($id);
            $ubicacion->update($request->all());
            return redirect()->route('ubicaciones')->with('success', 'Ubicación actualizada exitosamente.');
        } catch (\Exception $e) {
            // var_dump('asddddddddddddddddddddddddddd');
            return redirect()->route('ubicaciones')->with('error', 'Error al actualizar la ubicación: ' . $e->getMessage());
        }
    }



    public function destroy(Ubicacion $ubicacion)
    {
        $ubicacion->delete();
        return redirect()->route('ubicaciones.index')->with('success', 'Ubicación eliminada exitosamente.');
    }
}