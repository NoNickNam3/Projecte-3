<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up()
    {
        Schema::create('puntos_de_ruta', function (Blueprint $table) {
            $table->unsignedBigInteger('ruta');
            $table->string('coordenada', 100);
            $table->unsignedBigInteger('ordre');
            $table->timestamp('completado')->nullable();
            $table->primary(['ruta', 'ordre']);
            
            $table->foreign('ruta')
                  ->references('id')
                  ->on('rutas')
                  ->onUpdate('cascade')
                  ->onDelete('cascade');
            $table->timestamps();
        });
    }

    public function down()
    {
        Schema::dropIfExists('puntos_de_ruta');
    }
};