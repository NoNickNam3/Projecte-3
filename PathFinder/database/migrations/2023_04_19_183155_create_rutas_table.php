<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up()
    {
        Schema::create('rutas', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->unsignedBigInteger('usuario');
            $table->timestamp('momento_temporal');
            $table->string('observacion', 500)->nullable();
            $table->tinyInteger('fav')->default(0);
            $table->string('nombre', 100)->nullable();
        
            $table->foreign('usuario')
                  ->references('id')
                  ->on('users')
                  ->onUpdate('cascade')
                  ->onDelete('cascade');
                  
            $table->timestamps();
        });
    }

    public function down()
    {
        Schema::dropIfExists('rutas');
    }
};