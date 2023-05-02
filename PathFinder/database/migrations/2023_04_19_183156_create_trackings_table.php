<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up()
    {
        Schema::create('tracking', function (Blueprint $table) {
            $table->unsignedBigInteger('empleado');
            $table->timestamp('momento');
            $table->string('coordenadas');
            $table->primary(['empleado', 'momento']);
            $table->foreign('empleado')
                  ->references('id')
                  ->on('users')
                  ->onDelete('cascade');
            $table->timestamps();
        });
    }

    public function down()
    {
        Schema::dropIfExists('tracking');
    }
};
