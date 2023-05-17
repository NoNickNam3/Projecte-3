package org.milaifontanals.projecte3.utils.direccions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.ui.ruta.RutaFragment;

import java.util.ArrayList;
import java.util.List;

public class DireccionsUtil {
    public static String concatCoordsIfNN(String lat, String lon){

        if(lat != null && lon != null){
            return lat + "," + lon;
        }

        return "";
    }

    public static String getNNStringFromUbicacion(Ubicacion u){

        if(u.getCoordenadas() != null && !u.getCoordenadas().equals("")){
            return u.getCoordenadas();
        }

        if(u.getDirecccion() != null && !u.getDirecccion().equals("")){
            return u.getDirecccion();
        }

        return "";
    }

    public static void obreMaps(Context c, Ubicacion u) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + getNNStringFromUbicacion(u));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }

    public static void obrirRuta(Context c, Ubicacion desti, List<Ubicacion> llUbicacions) {
        String googleURL = "&waypoints=";

        if(llUbicacions.contains(desti)){
            llUbicacions.remove(desti);
        }

        int i = 0;
        for(Ubicacion u : llUbicacions){

            if(i != llUbicacions.size() && i != 0){
                googleURL += "|";
            }

            googleURL += getNNStringFromUbicacion(u);

            i++;
        }

        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + getNNStringFromUbicacion(desti) + googleURL);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);

    }

    public static void obreMaps(Context c) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=47.853355,7.936379&waypoints=Avenida Valmes|Carrer Masquefa, 08700 Igualada");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }
}
