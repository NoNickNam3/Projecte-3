package org.milaifontanals.projecte3.utils.direccions;

<<<<<<< HEAD
import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.app.Activity;
=======
<<<<<<< HEAD
>>>>>>> 370f659b68b6421b7714fbd1c6f5575a8e58586a
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;

import androidx.core.app.ActivityCompat;

<<<<<<< HEAD
import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;

import java.security.Permission;
=======
=======
>>>>>>> 775ea72fb5cc56873befd27ccd1c583a105e9cab
>>>>>>> 370f659b68b6421b7714fbd1c6f5575a8e58586a
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DireccionsUtil {
<<<<<<< HEAD
=======
<<<<<<< HEAD
    public static String concatCoordsIfNN(String lat, String lon){
>>>>>>> 370f659b68b6421b7714fbd1c6f5575a8e58586a

    public static String concatCoordsIfNN(Double lat, Double lon) {

        if (lat != null && lon != null) {
            return lat + "," + lon;
        }

        return "";
    }

    public static String getNNStringFromUbicacion(Ubicacion u) {

        if (u.getCoordenadas() != null && !u.getCoordenadas().equals("")) {
            return u.getCoordenadas();
        }

        if (u.getDirecccion() != null && !u.getDirecccion().equals("")) {
            return u.getDirecccion();
        }

        return "";
    }

    public static String getStringFromDoubleList(List<Double> ll){
        String u = "";

        u = ll.get(1) + "," + ll.get(0);

        return u;
    }

    public static void obreMaps(Context c, Ubicacion u) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + getNNStringFromUbicacion(u));
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

    public static void obrirRuta(Context c, List<List<Double>> llUbicacions) {
        String googleURL = "&waypoints=";

        llUbicacions.remove(0);

        String desti = getStringFromDoubleList(llUbicacions.get(llUbicacions.size() - 1));

        int i = 0;
        boolean primeraDone = false;
        List<String> alternativa = new ArrayList<>();
        for (List<Double> u: llUbicacions) {

            String ub = getStringFromDoubleList(u);

            if(!alternativa.contains(ub) && !ub.equals(desti)) {
                alternativa.add(ub);
                if (i != llUbicacions.size() && primeraDone) {
                    googleURL += "|";
                }

                googleURL += ub;
                primeraDone = true;
            }

            i++;
        }

        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + desti + googleURL);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);

    }

    public static void obrirRutaString(Context c, String desti, List<String> llUbicacions) {
        String googleURL = "&waypoints=";

        int i = 0;
        for (String u : llUbicacions) {

            if (i != llUbicacions.size() && i != 0) {
                googleURL += "|";
            }

            googleURL += u;

            i++;
        }

        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + desti + googleURL);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);

    }

    public static void obrirRutaStringAutoOrdenada(Context c, String desti, List<String> llUbicacions) {
        String googleURL = "&waypoints=";

        int i = 0;
        for (String u : llUbicacions) {

            if (i != llUbicacions.size() && i != 0) {
                googleURL += "|";
            }

            googleURL += u;

            i++;
        }

        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + desti + googleURL + "&dirflg=r");
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

    public static boolean setPermissionsTo(Activity a, String permissions) {
        if (a.checkSelfPermission(permissions) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(a,
                    new String[]{permissions},
                    PackageManager.PERMISSION_GRANTED);
            return true;
        }
        return false;
    }

    public static Location getLastKnownLocation(Activity a) {

        giveAllPermissions(a);

        LocationManager mLocationManager = (LocationManager) a.getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {

            if (ActivityCompat.checkSelfPermission(a, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(a, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_DENIED) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
        }
        return bestLocation;
    }

    public static Location getLastKnownLocationC(Context c) {
        LocationManager mLocationManager = (LocationManager) c.getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {

            if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_DENIED) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
        }
        return bestLocation;
    }

    public static void giveAllPermissions(Activity a){
        if(!setPermissionsTo(a, Manifest.permission.ACCESS_FINE_LOCATION) && !setPermissionsTo(a, Manifest.permission.ACCESS_BACKGROUND_LOCATION) && !setPermissionsTo(a, Manifest.permission.ACCESS_COARSE_LOCATION)){
            DialogUtils.toastMessageLong(a, "PathFinder: Accediendo a la ubicación");
        }else{
            DialogUtils.toastMessageLong(a, "Se ha activado la localización");
        }
    }

    public static String convertToJSON(List<String> dir){

        String jsonResult = "[";

        for(String s: dir){
            jsonResult += "{'coord': '" + s + "'}";
            if(!s.equals(dir.get(dir.size() - 1))){
                jsonResult += ",";
            }
        }

        return jsonResult += "]";
    }

}
