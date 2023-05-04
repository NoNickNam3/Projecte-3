package org.milaifontanals.projecte3.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion implements Parcelable {
    private int id;
    private String nombre;
    private String coordenadas;
    private String direcccion;
    private String observacion;
    private boolean fav;

    /**
     * ===========================================================================
     * Lista de ubicaciones, tengo que conseguir que las descargue de el endpoint
     * ===========================================================================
     */
    private static ArrayList<Ubicacion> _mUbicaciones;

    public static List<Ubicacion> getUbicaciones(){

        if(_mUbicaciones==null) {
            _mUbicaciones = new ArrayList<Ubicacion>();
            _mUbicaciones.add(new Ubicacion(
                    0,
                    "Prova Primera",
                    "0, 0",
                    "Milà i Fontanals Igualada",
                    "Centro de estudios",
                    true
            ));

            _mUbicaciones.add(new Ubicacion(
                    1,
                    "Prova Segona",
                    "1, 1",
                    "Manresa, Barcelona",
                    "Ciudad de Manresa",
                    false
            ));

            _mUbicaciones.add(new Ubicacion(
                    2,
                    "",
                    "1, 1",
                    "Calle Jaén 27",
                    "Lorem ipsum is a dummy text...",
                    true
            ));

            _mUbicaciones.add(new Ubicacion(
                    3,
                    "",
                    "1, 1",
                    "",
                    "Area de servicio de prueba",
                    false
            ));

            _mUbicaciones.add(new Ubicacion(
                    4,
                    "",
                    "1, 1",
                    "Av. Emili Vallés 32",
                    "Milà i Fontanals",
                    true
            ));

            _mUbicaciones.add(new Ubicacion(
                    5,
                    "Frutería Mari Pili",
                    "1, 1",
                    "",
                    "Area de servicio de prueba",
                    false
            ));

            _mUbicaciones.add(new Ubicacion(
                    6,
                    "Anonymous Place 01",
                    "-115, 865",
                    "Not available",
                    "Anonymous",
                    true
            ));

            _mUbicaciones.add(new Ubicacion(
                    7,
                    "Lorem ipsum?",
                    "3, 5",
                    "",
                    "",
                    false
            ));
        }
        return _mUbicaciones;
    }

    public Ubicacion(int id, String nombre, String coordenadas, String direcccion, String observacion, boolean fav) {
        setId(id);
        setNombre(nombre);
        setCoordenadas(coordenadas);
        setDirecccion(direcccion);
        setObservacion(observacion);
        setFav(fav);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getDirecccion() {
        return direcccion;
    }

    public void setDirecccion(String direcccion) {
        this.direcccion = direcccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(coordenadas);
        dest.writeString(direcccion);
        dest.writeString(observacion);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(isFav());
        }
    }

    protected Ubicacion(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        coordenadas = in.readString();
        direcccion = in.readString();
        observacion = in.readString();
        fav = in.readByte() != 0;
    }

    public static final Creator<Ubicacion> CREATOR = new Creator<Ubicacion>() {
        @Override
        public Ubicacion createFromParcel(Parcel in) {
            return new Ubicacion(in);
        }

        @Override
        public Ubicacion[] newArray(int size) {
            return new Ubicacion[size];
        }
    };
}
