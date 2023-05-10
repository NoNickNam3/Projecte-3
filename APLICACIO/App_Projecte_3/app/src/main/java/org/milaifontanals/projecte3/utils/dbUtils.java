package org.milaifontanals.projecte3.utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import org.milaifontanals.projecte3.model.apiUser.User;

public class dbUtils {

    public static long guardarUsuariBDD(String token, User user, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("nombre", user.getNombre());
        values.put("apellidos", user.getApellidos());
        values.put("organizacion", user.getOrganizacion().toString());
        values.put("token", token);
        values.put("email", user.getEmail());
        long id = db.insert("dbInterna", null, values);

        return id;
    }

}
