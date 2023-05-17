<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-white leading-tight">
            {{ __('Empleados') }}
        </h2>
    </x-slot>

    @php
        $users = [
        (Object) array ('id' => 1,'nombre'=>'Pedro', 'apellidos'=>'Suarez')
        ] 
    @endphp

    <div class="contBig">
        <div class="contElem">
            <div class="contBasic shadow-sm sm:rounded-lg">
                <h1>Invitación Empleado</h1>
                {{-- <form action="{{asset('enviar_codigo')}}" class="contInv" method="post"> --}}
                    <form id="formSend" action="" class="contInv" method="post">
                    <input type="text" name="email" class="inputInv">
                <div  id="buttonSend" class="contBot">
                    <h5>Enviar</h5>
                    <i class="fa-solid fa-paper-plane"></i>
                </div>
                </form>
                
            </div>
            <div class="contBasic  shadow-sm sm:rounded-lg">
                <h1>Desvincular Empleado</h1>
                {{-- <form action="{{route('desvincular_emp')}}" class="contInv sep2 " method="post"> --}}
                <form  id="formDesv" action="" class="contInv sep2 " method="post">
                    <select name="id" class="selectClients">
                        <option class="bg-white" value="0" disabled>Selecciona un empleado...</option>
                        @foreach ($users as $user)
                        <option class="bg-white block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" value="{{ $user->id }}">{{ $user->nombre}} {{$user->apellidos}}</option>
                        @endforeach
                    </select>
                    <div id="buttonDesv" class="buttonDesv">
                        <h5>Desvincular</h5>
                        <i class="fa-solid fa-user-slash"></i>
                    </div>
                </form>
            </div>
        </div>
    </div>
</x-app-layout>
