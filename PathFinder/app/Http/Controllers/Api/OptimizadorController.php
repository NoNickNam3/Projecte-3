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
use Illuminate\Support\Facades\Storage;

class OptimizadorController extends Controller
{
    public function optimizar(Request $request)
    {
        if (!$request->has('sortida') || !$request->has('parades')) {
            return response()->json([
                'status' => 'error',
                'message' => 'No s\'han enviat be les coordenadas',
            ], 400);
        }

        Storage::prepend('file.log', $request->input('parades'));

        $nuevoString = str_replace("'", '"', $request->input('parades'));

        $data = json_decode($nuevoString);

        $coordenadas = '';

        if (count($data) > 10) {
            return response()->json([
                'status' => 'error',
                'message' => 'No es poden enviar més de 10 parades',
            ]);
        }

        foreach ($data as $coord) {
            //CONCATENAR I INVERTIR COORDENADES
            $coordenadasArray = explode(",", $coord->coord);
            $coordenadasArrayInvertidas = array_reverse($coordenadasArray);
            $coordenadasInvertidas = implode(",", $coordenadasArrayInvertidas);
            $coordenadas .= $coordenadasInvertidas . ';';
        }

        $coordenadas = rtrim($coordenadas, ';');

        //INVERTIR LA COORDENADA DE SORTIDA
        $coordenadasArray = explode(",", $request->input('sortida'));
        $coordenadasArrayInvertidas = array_reverse($coordenadasArray);
        $coordenadasInvertidas = implode(",", $coordenadasArrayInvertidas);

        $coordenadas = $coordenadasInvertidas . ';' . $coordenadas;

        $baseUrl = 'https://api.mapbox.com/optimized-trips/v1/mapbox/driving/';

        $client = new Client();

        $api = $client->get($baseUrl . $coordenadas, [
            'query' => [
                'access_token' => env('MAPBOX_KEY'),
                'steps' => 'false',
                'roundtrip' => 'true',
                'source' => 'first',
                'destination' => 'any',
            ]
        ]);

        if ($api->getStatusCode() != 200) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al calcular la ruta',
            ]);
        }

        $dataArr = json_decode($api->getBody(), true);

        try {
            $locations = [];
            foreach ($dataArr['waypoints'] as $waypoint) {
                $locations[$waypoint['waypoint_index']] = $waypoint['location'];
            }

            ksort($locations);

            $duracioTotal = $dataArr['trips'][0]['duration'];
            $distanciaTotal = $dataArr['trips'][0]['distance'];

            $result = [
                'locations' => $locations,
                'duracioTotal' => $duracioTotal,
                'distanciaTotal' => $distanciaTotal
            ];
        } catch (\Throwable $th) {
            return response()->json([
                'status' => 'error',
                'message' => 'No s\'ha pogut trobar cap ruta optimitzada',
                'data' => []
            ], 200);
        }

        try {
            //CREAR NOVA RUTA
            $r = new Ruta(array('usuario' => Auth::id()));
            $r->save();
            $idRuta = $r->id;

            //GUARDAR PUNTS DE RUTA JA ORDENATS
            foreach ($result['locations'] as $key => $location) {
                $p = new PuntoDeRuta(
                    array(
                        'ruta' => $idRuta,
                        'coordenada' => $location[0] . ',' . $location[1],
                        'ordre' => $key + 1
                    )
                );
                $p->save();
            }
        } catch (\Exception $e) {
            return response()->json([
                'status' => 'error',
                'message' => 'Error al guardar la ruta',
            ], 500);
        }

        return response()->json([
            'status' => 'success',
            'message' => 'Ruta optimitzada',
            'data' => $result,
        ], 200);
    }
}