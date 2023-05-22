<x-app-layout>
    @section('tracking')
        <script src={{asset('js/map.js')}}></script>
        <!-- Leaflet -->
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css" integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin="" />
        <script src="https://unpkg.com/leaflet@1.9.3/dist/leaflet.js" integrity="sha256-WBkoXOwTeyKclOHuWtc+i2uENFpDZ9YPdf5Hf+D7ewM=" crossorigin=""></script>    
        <!-- FullCalendar -->
        
        @endsection
    <x-slot name="header">
        <div class="flex items-center separacion">
            <i class="fa-solid fa-location-dot iconHeader"></i>
            <h2 class="font-semibold text-xl text-white leading-tight">
                {{ __('Tracking') }}
            </h2>
        </div>
    </x-slot>
    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white  shadow-sm sm:rounded-lg pdbottom">
                <div class="p-6 flex items-center">
                    <h3 class="font-semibold text-xl text-gray-800 leading-tight mrg-l4">Lista de empleados:</h3>
                    <select name="selectClients" class="selectClients" >
                        <option class="bg-white" value="0" disabled>Selecciona un empleado...</option>
                        @foreach ($users as $user)
                            <option class="bg-white block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" value="{{ $user->id }}">{{ $user->nombre}} {{$user->apellidos}}</option>
                        @endforeach
                    </select>
                    {{-- <h3 class="font-semibold text-xl text-gray-800 leading-tight mrg-l4">Fecha del Tracking:</h3> --}}
                    {{-- <input name="fechaClient" type="date"> --}}
                </div>
                <div class='contMapa'>
                    <div id="calendar" class="calendar"></div>
                    <div id="elMeuMapa"></div>
                    <div class="mascara"></div>
                </div>
                
            </div>
        </div>
    </div>
</x-app-layout>