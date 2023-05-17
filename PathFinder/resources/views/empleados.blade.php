<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-white leading-tight">
            {{ __('Empleados') }}
        </h2>
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


    <div class="contBig">
        <div class="contElem">
            <div class="contBasic shadow-sm sm:rounded-lg">
                <h1>Invitación Empleado</h1>
                <form id="formSend" action="{{asset('enviar_codigo')}}" class="contInv" method="post">
                    @csrf
                    <input type="text" name="email" class="inputInv">
                    <input type='submit' class="buttonInv" value="Enviar">
                {{-- <div id="buttonSend" class="contBot">
                    <h5>Enviar</h5>
                    <i class="fa-solid fa-paper-plane"></i>
                </div> --}}
                </form>
                
            </div>
            <div class="contBasic  shadow-sm sm:rounded-lg">
                <h1>Desvincular Empleado</h1>
                <form id="formDesv" action="{{route('desvincular_emp')}}" class="contInv sep2 " method="post"> 
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
