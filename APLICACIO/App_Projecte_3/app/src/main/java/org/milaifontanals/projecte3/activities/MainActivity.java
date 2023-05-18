package org.milaifontanals.projecte3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.databinding.ActivityMainBinding;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.api.APIInterface;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.model.elazarEmpresa.RespostaEnllacarEmpresa;
import org.milaifontanals.projecte3.model.logOut.RespostaLogOut;
import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;
import org.milaifontanals.projecte3.utils.intentMoves.IntentUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<RespostaLogOut> {
    private ActivityMainBinding bind;
    private NavHostFragment navHostFragment;
    private String mTokenActual = null;
    private SQLiteDatabase db = null;
    private Intent intentMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().hide();

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkPaletteBlack)));

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        setContentView(R.layout.activity_main);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        bind = ActivityMainBinding.inflate(getLayoutInflater());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSignOut:
                Call<RespostaLogOut> call = APIAdapter.getApiService().logout(" Bearer " + this.getBaseContext().getSharedPreferences("tokenUsuari", MODE_PRIVATE).getString("token", null));
                call.enqueue(this);
                break;
            case R.id.itemEnlaceEmpresa:
                DialogUtils.obrirDialogenllacEmpresa(getSupportFragmentManager());
                break;
        }
        return true;
    }

    @Override
    public void onResponse(Call<RespostaLogOut> call, Response<RespostaLogOut> response) {
        if(response.isSuccessful()){
            IntentUtils.anarLogin(getBaseContext(), navHostFragment);
        }
    }

    @Override
    public void onFailure(Call<RespostaLogOut> call, Throwable t) {

    }

}