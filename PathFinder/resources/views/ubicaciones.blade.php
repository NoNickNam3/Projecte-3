<x-app-layout>
    <x-slot name="header">
        <div class="flex items-center separacion">
            <i class="fa-sharp fa-solid fa-map-location-dot iconHeader"></i>
            <h2 class="font-semibold text-xl text-white leading-tight">
                {{ __('Dashboard') }}
            </h2>
        </div>
        <input id="formAdd" type="button" class="formAdd" value="Crear nueva Ubicacion">
    </x-slot>

        @if(session()->has('error'))
            <script>
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: '{{ session()->get('error') }}',
            })
            </script>
        @endif
        @if(session()->has('success'))
            <script>
                Swal.fire({
                icon: 'success',
                title: '¡Éxito!',
                text: '{{ session()->get('success') }}',
                })
            </script>
        @endif

        <div class=" mx-auto sm:px-6 lg:px-3 flex contUbis">
            <div class="contFormUbis">
                <div class="contFilter borRad">
                    <div class="contInput borderColor1">
                        <div class="contIcon color5">
                            <i class="fa-solid fa-magnifying-glass-location"></i>
                        </div>
                        <x-text-input class="inputNombre color5" type="text" name="nombreForm" placeholder="Nombre Ubicación..."  />
                        
                    </div>
                    
                    <select name="selectClientsUbis" class="selectClients">
                        <option class="bg-white block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" value="{{auth()->user()->id}}" selected>Empresa</option>
                        @foreach ($users as $user)
                        <option class="bg-white block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" value="{{ $user->id }}">{{ $user->nombre}} {{$user->apellidos}}</option>
                        @endforeach
                    </select>
                    <h5 class="font-semibold text-xl text-gray-800 leading-tight ">Favorito:</h5>
                    <input name="favForm" type="checkbox" class="fav"/>
                </div>
                <div class="contTabla">
                    <table>
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Dirección</th>
                                <th>Favorito</th>
                            </tr>
                        </thead>
                        <tbody id="listaUbicaciones">
                            
                        </tbody>
                    </table>
                    <img id="no-result" src="{{asset('img/no-result-color.png')}}" alt="" class="no-result oculto">
                </div>
            </div>
            <div class="shadow-sm sm:rounded-lg contFormUbis borRad contEdit">
                <div id="contBotones" class="contBotones">
                    <i id="buttonEditar" class="fa-solid fa-pen-to-square"></i>
                    <i id="buttonEliminar" class="fa-solid fa-trash">
                        <form id="formDelete" action="{{ route('eliminar_ubicacion') }}" method="post">
                            @csrf
                            <input name="id" class="oculto" type="text">
                            <input type="submit" class="buttonEliminar" value="" >
                        </form>
                    </i>
                    <i id="buttonImportar" class="oculto fa-solid fa-cloud-arrow-up">
                        <form id="formImportar" action="{{ route('importar') }}" method="post">
                            @csrf
                            <input name="id" class="oculto" type="text">
                            <input type="submit" class="buttonEliminar" value="" >
                        </form>
                    </i>
                </div>
                <form id="formUpdate" class ="formUpdate" action="{{ route('ubicaciones.store') }}/" method="POST">
                    @csrf
                    @method('PUT')
                    <input id="coordenada" type="text" name="coordenada" class="oculto" />
                    <div class="contEditInput" style="gap:20px">
                        <h5 class="font-semibold text-xl text-gray-800 leading-tight">Nombre:</h5>
                        <div class="contInput borderColor2">
                            
                            <x-text-input id="nombre" class="inputNombre color5" type="text" name="nombre" placeholder="Ejm: Mila i Fontanals" />
                            <div class="contIcon color3">
                                <i class="fa-solid fa-file-signature"></i>
                            </div>
                        </div>
                    </div>
                    <div class="contEditInput">
                        <h5 class="font-semibold text-xl text-gray-800 leading-tight">Dirección:</h5>
                        <div class="contInput borderColor2">
                            <x-text-input id="direccion" class="inputNombre color5 max-input" type="text" name="direccion" placeholder="Ejm: Av.Barcelona,158"  />
                            <div id="direSearch" class="contIcon color3 pointer">
                                <i class="fa-solid fa-signs-post"></i>
                            </div>
                        </div>
                        <h5 class="font-semibold text-xl text-gray-800 leading-tight ">Favorito:</h5>
                        <input id="favSelect" type="checkbox" class="fav2"/>
                        <input id="favValue" type="text" name="fav" class="oculto" >
                    </div>

                    <div id="map" class="googleMap">
                        
                    </div>
                    <div class="contObservaciones">
                        <h5 class="font-semibold text-xl text-gray-800 leading-tight">Observaciones:</h5>
                        <textarea id="observaciones" name="observaciones" cols="40" rows="3" placeholder="Ejm: Es una calle cerrada, solo abren de 8 a 12 am"></textarea>
                    </div>
                    <div class="contBotones2">
                        <input id="buttonCancelar" class="buttonForm" name="cancelar" value="Cerrar">
                        <input id="buttonGuardar" class="buttonForm" name="guardar" type="submit" value="Guardar">
                        <input id="buttonCrear" class="buttonForm oculto" name="crear" type="submit" value="Crear">
                    </div>
                </form>
            </div>
        </div>
</x-app-layout>
