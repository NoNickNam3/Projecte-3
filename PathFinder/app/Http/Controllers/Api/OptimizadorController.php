<?php

namespace App\Http\Controllers\Api;

use App\Models\PuntoDeRuta;
use App\Models\Ruta;
use Auth;
use Carbon\Carbon;
use GuzzleHttp\Client;
use App\Http\Controllers\Controller;
use GuzzleHttp\Exception\GuzzleException;
use Illuminate\Http\Request;
use App\Http\Controllers\Exception;
class OptimizadorController extends Controller
{
    public function optimizar(Request $request)
    {
        if (!$request->has('coordenadas')) {
            return response()->json([
                'status' => 'error',
                'message' => 'No s\'ha enviat cap coordenada',
            ]);
        }

        $data = json_decode($request->input('coordenadas'), true);

        if (!isset($data['sortida'])) {
            return response()->json([
                'status' => 'error',
                'message' => 'No s\'ha enviat la coordenada de sortida',
            ]);
        }
        $sortida = $data['sortida'];

        $parades = $data['parades'];
        $coordenadas = '';

        if(count($parades) > 10) {
            return response()->json([
                'status' => 'error',
                'message' => 'No es poden enviar més de 10 parades',
            ]);
        }

        foreach ($parades as $coord) {
            $coordenadas .= $coord . ';';
        }

        $coordenadas = rtrim($coordenadas, ';');

        $coordenadas = $sortida . ';' . $coordenadas;

        $baseUrl = 'https://api.mapbox.com/optimized-trips/v1/mapbox/driving/';

        $client = new Client();

        $response = $client->get($baseUrl . $coordenadas, [
            'query' => [
                'access_token' => env('MAPBOX_KEY'),
                'steps' => 'false',
            ]
        ]);

        if($response->getStatusCode() != 200) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al calcular la ruta',
            ]);
        }

        $dataArr = json_decode($response->getBody(), true);

        $locations = [];
        foreach ($dataArr['waypoints'] as $waypoint) {
            $locations[$waypoint['waypoint_index']] = $waypoint['location'];
        }

        ksort($locations);

        $tripDuration = $dataArr['trips'][0]['duration'];
        $tripDistance = $dataArr['trips'][0]['distance'];

        $result = [
            'locations' => $locations,
            'duracioTotal' => $tripDuration,
            'distanciaTotal' => $tripDistance
        ];

        try {
            $r = new Ruta(array('usuario'=>Auth::id()));
            $r->save();
            $idRuta = $r->id;
    
            foreach ($result['locations'] as $key => $location) {
                $p = new PuntoDeRuta(array('ruta'=>$idRuta, 
                                    'coordenada'=>$location[0].','.$location[1],
                                    'ordre'=>$key+1));
                $p->save();
            }
        } catch (\Exception $e) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al guardar la ruta',
            ]);
        }

        return response()->json([
            'status' => 'success',
            'message' => 'Ruta optimitzada',
            'data' => $result,
        ], 200);
    }
}