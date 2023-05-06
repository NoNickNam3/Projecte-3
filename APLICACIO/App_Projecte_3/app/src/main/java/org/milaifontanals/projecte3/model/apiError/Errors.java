
package org.milaifontanals.projecte3.model.apiError;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

    @SerializedName("name")
    @Expose
    private List<String> name;
    @SerializedName("apellidos")
    @Expose
    private List<String> apellidos;
    @SerializedName("email")
    @Expose
    private List<String> email;
    @SerializedName("password")
    @Expose
    private List<String> password;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getApellidos() {
        return apellidos;
    }

    public void setApellidos(List<String> apellidos) {
        this.apellidos = apellidos;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

}
