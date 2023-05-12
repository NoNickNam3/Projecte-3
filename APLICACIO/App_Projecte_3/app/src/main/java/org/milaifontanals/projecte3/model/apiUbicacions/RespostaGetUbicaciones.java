
package org.milaifontanals.projecte3.model.apiUbicacions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaGetUbicaciones {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<UbicacionApi> data;

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

    public List<UbicacionApi> getData() {
        return data;
    }

    public void setData(List<UbicacionApi> data) {
        this.data = data;
    }

}
