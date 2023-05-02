<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('Tracking') }}
        </h2>
    </x-slot>
    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white  shadow-sm sm:rounded-lg">
                <div class="p-6 text-gray-900">

                    @php
                        $items = ['Opción 1', 'Opción 2', 'Opción 3','Opción 1', 'Opción 2', 'Opción 3','Opción 1', 'Opción 2', 'Opción 3','Opción 1', 'Opción 2', 'Opción 3'];
                    @endphp
                    <x-dropdown align="left" width="48" content-classes="py-1 bg-white">
                        <x-slot name="trigger">
                            <button class="py-2 px-4 bg-gray-700 text-gray-900 font-semibold rounded-lg shadow-md hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-gray-400 focus:ring-opacity-75">
                                Lista de clientes
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
                    </x-dropdown>
                </div>
            </div>
        </div>
    </div>
</x-app-layout>