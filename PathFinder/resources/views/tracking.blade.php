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

                    {{-- @php
                        $items = ['Jhonny Melavo','Armando Paredes', 'Broco Li','Débora Meltrozo'];
                    @endphp --}}
                    {{-- <x-dropdown align="left" width="48" content-classes="py-1 bg-white" onchange="f_client">
                        <x-slot name="trigger">
                            <button class="flex items-center color5 py-2 px-4 bg-gray-700 whiteletter font-semibold rounded-lg shadow-md hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-opacity-75">
                                <div>Lista de clientes</div> 
                                <div class="ml-1">
                                    <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                                        <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
                                    </svg>
                                </div>
                            </button>
                        </x-slot>

                        <x-slot name="content">
                            <div class=" overflow-y-auto dropdown">
                                @foreach ($users as $item)
                                    <button class="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" type="button">
                                        {{ $item->nombre }}
                                        {{ $item->id }}
                                    </button>
                                @endforeach
                            </div>
                        </x-slot>
                    </x-dropdown> --}}
                    <h3 class="font-semibold text-xl text-gray-800 leading-tight mrg-l4">Lista de clientes:</h3>
                    <select name="selectClients" class="selectClients" >
                            {{-- @foreach ($items as $item)
                                <option class="bg-white block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" value=''>{{$item}}</option>
                            @endforeach --}}
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