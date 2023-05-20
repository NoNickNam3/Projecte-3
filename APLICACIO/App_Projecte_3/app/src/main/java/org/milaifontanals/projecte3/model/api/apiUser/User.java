
package org.milaifontanals.projecte3.model.api.apiUser;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
/*

    "user": {
        "nombre": "asd",
        "apellidos": "asd",
        "email": "asdddd@asd.asd",
        "updated_at": "2023-05-10T15:48:59.000000Z",
        "created_at": "2023-05-10T15:48:59.000000Z",
        "id": 13
    },
 */
    @SerializedName("id")
    @Expose
    private Integer id;
    @Nullable
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @Nullable
    @SerializedName("apellidos")
    @Expose
    private String apellidos;
    @Nullable
    @SerializedName("organizacion")
    @Expose
    private Object organizacion;
    @Nullable
    @SerializedName("email")
    @Expose
    private String email;
    @Nullable
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @Nullable
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @Nullable
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Object getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Object organizacion) {
        this.organizacion = organizacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
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
