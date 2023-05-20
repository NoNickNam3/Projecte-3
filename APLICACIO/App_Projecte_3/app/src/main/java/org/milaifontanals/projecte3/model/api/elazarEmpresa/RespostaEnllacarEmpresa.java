package org.milaifontanals.projecte3.model.api.elazarEmpresa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaEnllacarEmpresa {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
