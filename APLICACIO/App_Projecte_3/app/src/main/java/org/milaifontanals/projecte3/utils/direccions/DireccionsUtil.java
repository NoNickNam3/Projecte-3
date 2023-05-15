package org.milaifontanals.projecte3.utils.direccions;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.milaifontanals.projecte3.model.Ubicacion;

=======
>>>>>>> 775ea72fb5cc56873befd27ccd1c583a105e9cab
import java.util.ArrayList;
import java.util.List;

public class DireccionsUtil {
<<<<<<< HEAD
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
=======

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


>>>>>>> 775ea72fb5cc56873befd27ccd1c583a105e9cab

}
