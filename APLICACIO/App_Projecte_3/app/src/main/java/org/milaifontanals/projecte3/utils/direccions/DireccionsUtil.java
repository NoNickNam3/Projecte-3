package org.milaifontanals.projecte3.utils.direccions;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.milaifontanals.projecte3.activities.MainActivity;
import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.model.optimitzarRequest.CoordsParada;
import org.milaifontanals.projecte3.ui.ruta.RutaFragment;

import java.util.ArrayList;
import java.util.List;

public class DireccionsUtil {

    public static String concatCoordsIfNN(String lat, String lon) {

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

    public static void obreMaps(Context c, Ubicacion u) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + getNNStringFromUbicacion(u));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        c.startActivity(mapIntent);
    }

    public static void obrirRuta(Context c, Ubicacion desti, List<Ubicacion> llUbicacions) {
        String googleURL = "&waypoints=";

        if (llUbicacions.contains(desti)) {
            llUbicacions.remove(desti);
        }

        int i = 0;
        for (Ubicacion u : llUbicacions) {

            if (i != llUbicacions.size() && i != 0) {
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

//    public static Location getCurrentLocation(Activity activity) {
//        LocationManager locationManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
//
//
//
//        // Check if location permission is granted
//        if (locationManager != null && activity.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            // Get the last known location from the GPS provider
//            Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//            // Get the last known location from the network provider
//            Location networkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//            // Choose the most recent location
//            if (gpsLocation != null && networkLocation != null) {
//                if (gpsLocation.getTime() > networkLocation.getTime()) {
//                    return gpsLocation;
//                } else {
//                    return networkLocation;
//                }
//            } else if (gpsLocation != null) {
//                return gpsLocation;
//            } else if (networkLocation != null) {
//                return networkLocation;
//            }
//        }
//
//        // Return null if location is not available
//        return null;
//    }

    public static Location getLastKnownLocation(Activity a) {

        if (a.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(a,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PackageManager.PERMISSION_GRANTED);
        }

        LocationManager mLocationManager = (LocationManager) a.getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {

            if (ActivityCompat.checkSelfPermission(a, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(a, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

}
