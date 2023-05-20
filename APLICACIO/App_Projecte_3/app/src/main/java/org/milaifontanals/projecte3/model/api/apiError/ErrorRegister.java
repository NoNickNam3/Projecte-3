
package org.milaifontanals.projecte3.model.api.apiError;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorRegister {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private Errors errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

}
