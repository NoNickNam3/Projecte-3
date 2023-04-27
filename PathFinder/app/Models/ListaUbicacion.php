<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class ListaUbicacion extends Model
{
    use HasFactory;

    /**
     * The table associated with the model.
     *
     * @var string
     */
    protected $table = 'lista_ubicaciones';

    /**
     * The primary key associated with the table.
     *
     * @var string
     */
    protected $primaryKey = null;

    /**
     * Indicates if the IDs are auto-incrementing.
     *
     * @var bool
     */
    public $incrementing = false;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'contacto',
        'empleado',
    ];

    /**
     * Get the ubicacion associated with the lista_ubicacion.
     */
    public function ubicacion(): BelongsTo
    {
        return $this->belongsTo(Ubicacion::class, 'contacto');
    }

    /**
     * Get the user associated with the lista_ubicacion.
     */
    public function user(): BelongsTo
    {
        return $this->belongsTo(User::class, 'empleado');
    }
}
