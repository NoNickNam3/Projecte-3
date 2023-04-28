package org.milaifontanals.projecte3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //-------------------------------
        //  Ocultar barra navegación
        //-------------------------------
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        //obreMaps();

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnAgenda:
                navHostFragment.getNavController().navigate(R.id.agendaFragment);
                break;

            case R.id.btnRuta:
                navHostFragment.getNavController().navigate(R.id.rutaFragment);
                break;

            case R.id.btnTracking:
                navHostFragment.getNavController().navigate(R.id.trackingFragment);
                break;
        }
    }

    private void obreMaps() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=47.853355,7.936379&waypoints=Avenida Valmes|Carrer Masquefa, 08700 Igualada");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}