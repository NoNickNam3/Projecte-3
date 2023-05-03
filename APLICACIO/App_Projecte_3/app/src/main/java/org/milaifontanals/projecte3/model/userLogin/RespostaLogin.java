
package org.milaifontanals.projecte3.model.userLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.rxjava3.annotations.Nullable;

public class RespostaLogin {
    @Nullable
    @SerializedName("token")
    @Expose
    private String token;
    @Nullable
    @SerializedName("user")
    @Expose
    private User user;
    @Nullable
    @SerializedName("error")
    @Expose
    private String error;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

}
