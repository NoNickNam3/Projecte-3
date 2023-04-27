<?php

namespace App\Http\Controllers;

use App\Models\Tracking;
use Illuminate\Http\Request;

class TrackingController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        // $trackings = Tracking::all();
        // return view('tracking.index', compact('trackings'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('tracking.create');
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
