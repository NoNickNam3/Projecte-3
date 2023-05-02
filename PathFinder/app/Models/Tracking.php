<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Laravel\Sanctum\HasApiTokens;

class Tracking extends Model
{
    use HasFactory;

    protected $table = 'tracking';
    protected $primaryKey = ['empleado', 'momento'];
    public $incrementing = false;

    protected $fillable = [
        'empleado',
        'momento',
        'coordenadas',
    ];

    protected $casts = [
        'momento' => 'datetime',
    ];

    public function user()
    {
        return $this->belongsTo(User::class, 'empleado');
    }
}
