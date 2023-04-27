<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up()
    {
        Schema::create('ubicaciones', function (Blueprint $table) {
            $table->id();
            $table->string('nombre', 100);
            $table->string('coordenada', 100);
            $table->string('observaciones', 300)->nullable();
            $table->string('direccion', 100)->nullable();
            $table->tinyInteger('fav')->default(0);
            $table->timestamps();
        });
    }

    public function down()
    {
        Schema::dropIfExists('ubicaciones');
    }
};
