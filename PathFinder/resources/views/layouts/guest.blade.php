<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <title>{{ config('app.name', 'Laravel') }}</title>

        <!-- Fonts -->
        <link rel="stylesheet" href="{{ asset('css/mycss.css') }}">
        <link rel="preconnect" href="https://fonts.bunny.net">
        <link href="https://fonts.bunny.net/css?family=figtree:400,500,600&display=swap" rel="stylesheet" />

        <!-- Scripts -->
        @vite(['resources/css/app.css', 'resources/js/app.js'])
    </head>
    <body class="font-sans text-gray-900 antialiased">
        <div class="contLogin ">
            <div class="contIzq">
    
                <div class="w-full sm:max-w-md  px-6 py-4 bg-white  overflow-hidden sm:rounded-lg">
                    <div class="contLogo">
                        <a href="/">
                            {{-- <x-application-logo class="w-20 h-20 fill-current text-gray-500" /> --}}
                            <x-application-logo style="width: 5rem" />
                        </a>
                        {{-- <h1 class="text-gray-900 textLogin">PATHFINDER</h1> --}}
                    </div>
                    
                    {{ $slot }}
                </div>
            </div>
            <div class="contDer">
                <h1 class="eslogan">DESCUBRE LA MEJOR RUTA CON SOLO UN TOQUE</h1>
                <div class="contFondo" >
                    <img src="{{asset('img/fondoPathFinder.png')}}">
                </div>
            </div>
        </div>
    </body>
</html>
