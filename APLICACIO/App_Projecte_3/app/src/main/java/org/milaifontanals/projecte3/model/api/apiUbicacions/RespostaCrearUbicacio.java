package org.milaifontanals.projecte3.model.api.apiUbicacions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaCrearUbicacio {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataUbicacio data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataUbicacio getData() {
        return data;
    }

    public void setData(DataUbicacio data) {
        this.data = data;
    }
}
