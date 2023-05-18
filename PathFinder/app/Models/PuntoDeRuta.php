<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class PuntoDeRuta extends Model
{
    protected $table = 'puntos_de_ruta';
    
    protected $primaryKey = ['ruta', 'ordre'];
    
    public $incrementing = false;
    
    protected $keyType = 'array';
    
    protected $fillable = [
        'ruta',
        'coordenada',
        'ordre',
        'completado',
    ];
    
    protected $casts = [
        'completado' => 'datetime',
    ];
    
    public function ruta()
    {
        return $this->belongsTo(Ruta::class, 'ruta');
    }
}
