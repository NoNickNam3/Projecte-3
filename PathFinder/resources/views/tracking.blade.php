<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-white leading-tight">
            {{ __('Tracking') }}
        </h2>
    </x-slot>
    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white  shadow-sm sm:rounded-lg pdbottom">
                <div class="p-6 flex items-center">
                    <h3 class="font-semibold text-xl text-gray-800 leading-tight mrg-l4">Lista de clientes:</h3>
                    <select name="selectClients" class="selectClients" >
                        <option value="0" selected>Selecciona un cliente...</option>
                        @php
                            // echo "<option value='0'>Seleciona un client...</option>";
                            @foreach ($users as $user)
                                echo "<option value= '"+ $user.id +"'>" + $user.nombre +"</option>";
                            @endforeach
                        @endphp
                    </select>
                    <h3 class="font-semibold text-xl text-gray-800 leading-tight mrg-l4">Fecha del Tracking:</h3>
                    <input name="fechaClient" type="date">
                </div>
                <div class='contMapa'>
                    <div id="elMeuMapa"></div>
                </div>
                
            </div>
        </div>
    </div>
</x-app-layout>