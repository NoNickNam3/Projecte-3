<?php

namespace App\Http\Controllers;

use GuzzleHttp\Client;
use GuzzleHttp\Exception\GuzzleException;
use Illuminate\Http\Request;
use App\Http\Controllers\Exception;

class OptimizadorController extends Controller
{
    public function optimizar()
    {
        $baseUrl = 'https://api.mapbox.com/optimized-trips/v1/mapbox/driving/';
        $coordenadas = '41.5843,1.6126;41.4147,2.1528;41.3809,2.1731;41.5989,1.7004;41.5019,1.8125';

        $client = new Client();

        $response = $client->get($baseUrl . $coordenadas, [
            'query' => [
                'access_token' => env('MAPBOX_KEY'),
                'steps' => 'false',
            ]
        ]);

        $dataArr = json_decode($response->getBody(), true);

        $locations = [];

        foreach ($dataArr['waypoints'] as $waypoint) {
            $locations[] = $waypoint['location'];
        }

        usort($locations, function ($a, $b) {
            return $a['waypoint_index'] - $b['waypoint_index'];
        });

        $tripDuration = $dataArr['trips'][0]['duration'];
        $tripDistance = $dataArr['trips'][0]['distance'];

        $result = [
            'locations' => $locations,
            'tripDuration' => $tripDuration,
            'tripDistance' => $tripDistance
        ];
        return $locations;
    }
}