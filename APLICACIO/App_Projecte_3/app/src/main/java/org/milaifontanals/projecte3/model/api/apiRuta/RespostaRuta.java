package org.milaifontanals.projecte3.model.api.apiRuta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RespostaRuta {
    @SerializedName("locations")
    @Expose
    private List<List<Double>> locations;
    @SerializedName("duracioTotal")
    @Expose
    private Double duracioTotal;
    @SerializedName("distanciaTotal")
    @Expose
    private Double distanciaTotal;

    public List<List<Double>> getLocations() {
        return locations;
    }

    public void setLocations(List<List<Double>> locations) {
        this.locations = locations;
    }

    public Double getDuracioTotal() {
        return duracioTotal;
    }

    public void setDuracioTotal(Double duracioTotal) {
        this.duracioTotal = duracioTotal;
    }

    public Double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(Double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }
}
