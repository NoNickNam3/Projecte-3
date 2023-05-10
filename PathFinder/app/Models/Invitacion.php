<?php

// app/Models/Invitacion.php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Invitacion extends Model
{
    use HasFactory;

    protected $table = 'invitaciones';
    protected $fillable = [
        'empresa',
        'empleado',
        'codigo',
    ];
}