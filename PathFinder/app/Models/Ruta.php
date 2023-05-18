<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Laravel\Sanctum\HasApiTokens;

class Ruta extends Model
{
    protected $table = 'rutas';
    
    protected $fillable = [
        'usuario',
        'momento_temporal',
        'observacion',
        'fav',
        'nombre',
    ];
    
    public function user()
    {
        return $this->belongsTo(User::class, 'usuario');
    }
}
