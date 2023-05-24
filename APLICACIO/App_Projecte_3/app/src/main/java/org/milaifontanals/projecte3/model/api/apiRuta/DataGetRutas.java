package org.milaifontanals.projecte3.model.api.apiRuta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataGetRutas {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("usuario")
    @Expose
    private Integer usuario;
    @SerializedName("momento_temporal")
    @Expose
    private String momentoTemporal;
    @SerializedName("observacion")
    @Expose
    private Object observacion;
    @SerializedName("fav")
    @Expose
    private Integer fav;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getMomentoTemporal() {
        return momentoTemporal;
    }

    public void setMomentoTemporal(String momentoTemporal) {
        this.momentoTemporal = momentoTemporal;
    }

    public Object getObservacion() {
        return observacion;
    }

    public void setObservacion(Object observacion) {
        this.observacion = observacion;
    }

    public Integer getFav() {
        return fav;
    }

    public void setFav(Integer fav) {
        this.fav = fav;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
