package org.milaifontanals.projecte3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import org.milaifontanals.projecte3.ui.agenda.UbicacionAdapter;

public class MainActivity extends AppCompatActivity {


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

        //obreMaps();

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnAgenda:

                break;

            case R.id.btnRuta:

                break;

            case R.id.btnTracking:

                break;
        }
    }

    private void obreMaps() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=47.853355,7.936379&waypoints=Andorra|Barcelona|Paris|Galicia|Portugal");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}