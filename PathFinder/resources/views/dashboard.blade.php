<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-white leading-tight">
            {{ __('Dashboard') }}
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white overflow-hidden shadow-sm sm:rounded-lg">
                <div class="p-6 flex items-center separacion">
                    <div class="contInfo">
                        <div class="infoHeader bgcol1A brcol1">
                            <h4>Empleados</h4>
                        </div>
                        <div class="infoBody bgcol1B">
                            <h1>30</h1>
                        </div>
                        <div class="infoBottom bgcol1A brcol1">

                        </div>
                    </div>
                    <div class="contInfo">
                        <div class="infoHeader bgcol2A brcol2">
                            <h4>Ubicaciones</h4>
                        </div>
                        <div class="infoBody bgcol2B">
                            <h1>134</h1>
                        </div>
                        <div class="infoBottom bgcol2A brcol2">
                            
                        </div>
                    </div>
                    <div class="contInfo">
                        <div class="infoHeader bgcol3A brcol3">
                            <h4>Invitaciones</h4>
                        </div>
                        <div class="infoBody bgcol3B">
                            <h1>6</h1>
                        </div>
                        <div class="infoBottom bgcol3A brcol3">
                            
                        </div>
                    </div>
                    <div class="contInfo">
                        <div class="infoHeader bgcol4A brcol4">
                            <h4>Tracking (Hoy)</h4>
                        </div>
                        <div class="infoBody bgcol4B">
                            <h1>59</h1>
                        </div>
                        <div class="infoBottom bgcol4A brcol4">
                            
                        </div>
                    </div>
                    <div class="contChart">
                        <div class="contHeaderChart">
                            <h4>Gráfico de Empleados</h4>
                        </div>
                        <div class="contCanvas">
                            <canvas id="circular"></canvas>
                        </div>
                    </div>
                    <div class="contChart">
                        <div class="contHeaderChart">
                            <h4>Gráfico de Rutas</h4>
                        </div>
                        <div class="contCanvas">
                            <canvas id="barras"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</x-app-layout>
