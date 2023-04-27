<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up()
    {
        Schema::create('lista_ubicaciones', function (Blueprint $table) {
            $table->unsignedBigInteger('contacto');
            $table->unsignedBigInteger('empleado');
            $table->primary(['contacto', 'empleado']);
            $table->foreign('contacto')
                ->references('id')
                ->on('ubicaciones')
                ->onDelete('cascade');
            $table->foreign('empleado')
                ->references('id')
                ->on('users')
                ->onDelete('cascade');
            $table->timestamps();
        });
    }

    public function down()
    {
        Schema::dropIfExists('lista_ubicaciones');
    }
};
