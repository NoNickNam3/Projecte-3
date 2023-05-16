<?php

namespace App\Http\Controllers\Api;

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
        // $coordenadas = '41.5843,1.6126;41.4147,2.1528;41.3809,2.1731;41.5989,1.7004;41.5019,1.8125';

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

        return response()->json([
            'status' => 'success',
            'message' => 'Ruta optimitzada',
            'data' => $result,
        ], 200);
    }
}