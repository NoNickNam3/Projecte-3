package org.milaifontanals.projecte3.utils.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import org.milaifontanals.projecte3.model.api.apiUser.User;
import org.milaifontanals.projecte3.utils.comprovacions.ComprovacionsUtil;

public class dbUtils {

    public static long guardarUsuariBDD(String token, User user, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("nombre", user.getNombre());
        values.put("apellidos", user.getApellidos());
        values.put("organizacion", ComprovacionsUtil.getStringNN(user.getOrganizacion()));
        values.put("token", token);
        values.put("email", user.getEmail());
        long id = db.insert("dbInterna", null, values);

        return id;
    }
    public static long eliminarUsuariBDD(SQLiteDatabase db){
        return db.delete("dbInterna", null, null);
    }

}
