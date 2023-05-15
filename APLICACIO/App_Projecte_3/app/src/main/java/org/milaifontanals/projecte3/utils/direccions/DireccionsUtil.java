package org.milaifontanals.projecte3.utils.direccions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.milaifontanals.projecte3.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class DireccionsUtil {
    public static String concatCoordsIfNN(String lat, String lon){

        if(lat != null && lon != null){
            return lat + "," + lon;
        }

        return "";
    }


    public static String getNNString(String s){
        if(s==null){
            return"";
        }
        return s;
    }

    public static void obreMaps(Context c, Ubicacion u) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + getNNString(u.getDirecccion()));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }

}
