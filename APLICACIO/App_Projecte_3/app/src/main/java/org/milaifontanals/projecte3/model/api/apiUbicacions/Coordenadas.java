
package org.milaifontanals.projecte3.model.api.apiUbicacions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordenadas {

    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

}
