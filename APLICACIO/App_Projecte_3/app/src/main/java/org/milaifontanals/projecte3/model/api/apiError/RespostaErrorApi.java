
package org.milaifontanals.projecte3.model.api.apiError;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaErrorApi {

    @SerializedName("error_register")
    @Expose
    private ErrorRegister errorRegister;

    public ErrorRegister getErrorRegister() {
        return errorRegister;
    }

    public void setErrorRegister(ErrorRegister errorRegister) {
        this.errorRegister = errorRegister;
    }

}
