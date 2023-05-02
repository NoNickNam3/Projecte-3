<?php

namespace App\Http\Controllers;

use App\Models\Tracking;
use Illuminate\Foundation\Auth\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class TrackingController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(Request $request)
    {
        $userId = Auth::id();
        $users = User::where('organizacion', $userId)->get();
        return view('tracking', [
            'users' => $users,
            'user' => $request->user(),
        ]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('tracking.create');
    }

    public function get_tracking_user()
    {
        if (isset($_POST['id']) && isset($_POST['fecha'])) {
            $id = $_POST['id'];
        $fecha = $_POST['fecha'];
        $trackings = Tracking::where('empleado', 1)
            ->whereRaw("DATE_FORMAT(momento, '%Y-%m-%d') LIKE '2023-05-02%'")
            ->get();

        $coordenadas = array();
        foreach ($trackings as $tracking) {
            $co = explode(",", $tracking->coordenadas);
            $timestamp = $tracking->momento;
            $formattedTimestamp = $timestamp->format('Y-m-d H:i:s');

            $array = array('longitud' => $co[0], 'altitud' => $co[1], 'momento' => $formattedTimestamp);
            array_push($coordenadas, $array);
        }
        // var_dump($coordenadas);
        // exit;
        return $coordenadas;
        }
        // }
    }
    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            'empleado' => 'required|exists:users,id',
            'momento' => 'required|date',
            'coordenadas' => 'required|numeric',
        ]);

        Tracking::create($validated);

        return redirect()->route('tracking.index')->with('status', 'Tracking created successfully');
    }

    /**
     * Display the specified resource.
     */
    public function show(Tracking $tracking)
    {
        return view('tracking.show', compact('tracking'));
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Tracking $tracking)
    {
        $tracking->delete();
        return redirect()->route('tracking.index')->with('status', 'Tracking deleted successfully');
    }
}