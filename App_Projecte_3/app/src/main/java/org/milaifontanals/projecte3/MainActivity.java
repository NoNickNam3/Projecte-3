package org.milaifontanals.projecte3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import org.milaifontanals.projecte3.adapters.UbicacionAdapter;
import org.milaifontanals.projecte3.model.Ubicacion;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvUbicacion;
    private UbicacionAdapter adapter;

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



        //---------------------------------
        // Configuració del RecyclerView
        //---------------------------------
        rcvUbicacion = findViewById(R.id.rcvUbicacion);
        rcvUbicacion.setLayoutManager(new LinearLayoutManager(this));
        rcvUbicacion.setHasFixedSize(true);

        adapter = new UbicacionAdapter(Ubicacion.getUbicaciones(), this);
        rcvUbicacion.setAdapter(adapter);

        //obreMaps();

    }

    private void obreMaps() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=48.852864,7.939082&via=47.853355, 7.936379");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}