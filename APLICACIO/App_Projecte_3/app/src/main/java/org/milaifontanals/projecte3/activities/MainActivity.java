package org.milaifontanals.projecte3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<RespostaLogin> {

    private NavHostFragment navHostFragment;
    private String mTokenActual = null;
    private SQLiteDatabase db = null;
    private Intent intentMove;

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

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        setContentView(R.layout.activity_main);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        /*
        Call<RespostaLogin> call = APIAdapter.getApiService().loginUser("tonitonipuig@gmail.com", "12345678");
        call.enqueue(this);
        */

        Cursor cursor = db.rawQuery("select * from dbInterna", null);
        if (cursor.moveToNext()) {
            Log.d("XXX", "HE POGUT CARREGAR LA BDD");
            String token = cursor.getString(cursor.getColumnIndexOrThrow("token"));
            mTokenActual = token;
        }
        cursor.close();

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

    @Override
    public void onResponse(Call<RespostaLogin> call, Response<RespostaLogin> response) {
        if(response.isSuccessful()){
            RespostaLogin res = response.body();

            //Database configs
            ContentValues values = new ContentValues();
            values.put("token", res.getToken());
            long id = db.insert("dbInterna", null, values);
            mTokenActual = res.getToken();
            Log.d("XXX", res.getToken());
        }
    }

    @Override
    public void onFailure(Call<RespostaLogin> call, Throwable t) {
        Log.d("XXX", "ERROR EN MAIN");
        intentMove = new Intent(this, LogInActivity.class);
        startActivity(intentMove);
    }
}