package org.milaifontanals.projecte3.model.logOut;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaLogOut {
    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
