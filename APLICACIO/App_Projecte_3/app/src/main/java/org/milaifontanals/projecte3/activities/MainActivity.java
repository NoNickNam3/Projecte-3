package org.milaifontanals.projecte3.activities;

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
import android.view.View;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.databinding.ActivityMainBinding;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
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
}