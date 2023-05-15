package org.milaifontanals.projecte3.utils.direccions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class DireccionsUtil {

    /***
     *
     * @param entrada
     * entrada es una coordenada separada per ',' en format double
     * @return retorna una llista de coordenades (2 coords)
     */
    public static List<Double> parseCoords(String entrada){
        ArrayList<Double> coords = new ArrayList<>();
        String [] strCoords;

        strCoords = entrada.split(",");

        for(String x: strCoords){
            coords.add(Double.parseDouble(x));
        }

        if(coords.size() == 2){
            return coords;
        }

        return null;
    }

    public static void obreMaps(Context c) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=47.853355,7.936379&waypoints=Avenida Valmes|Carrer Masquefa, 08700 Igualada");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }

}
