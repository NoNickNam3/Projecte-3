package org.milaifontanals.projecte3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.Manifest;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.databinding.ActivityMainBinding;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.model.api.logOut.RespostaLogOut;
import org.milaifontanals.projecte3.model.scheduler.ScheduleJobService;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;
import org.milaifontanals.projecte3.utils.direccions.DireccionsUtil;
import org.milaifontanals.projecte3.utils.intentMoves.IntentUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<RespostaLogOut> {
    private ActivityMainBinding bind;
    private NavHostFragment navHostFragment;
    private String mTokenActual = null;
    private SQLiteDatabase db = null;
    private int callTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callTry = 0;

        scheduleJob();

        DireccionsUtil.setPermissionsTo(this, Manifest.permission.ACCESS_FINE_LOCATION);
        DireccionsUtil.setPermissionsTo(this, Manifest.permission.ACCESS_COARSE_LOCATION);

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
            case R.id.itemPedirPermisos:
                DireccionsUtil.giveAllPermissions(this);
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
        if(callTry < 3){
            callTry++;
            call.enqueue(this);
        }else{
            DialogUtils.toastMessageLong(this, "Error, no es pot conectar amb el servidor");
            IntentUtils.anarLogin(this, navHostFragment);
        }
    }

    public void scheduleJob(){
        PersistableBundle extras = new PersistableBundle();
        extras.putString("activity_package", this.getPackageName());
        ComponentName cn = new ComponentName(this, ScheduleJobService.class);
        JobInfo info = new JobInfo.Builder(123, cn).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setPersisted(true).setExtras(extras).setPeriodic(15 * 60 * 1000).build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);

        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d("XXX", "Scheduler funcionando");
        }
    }

    public Activity getCurrentActivity(){
        return this;
    }

    public void cancelJob(View v){
        JobScheduler js = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        js.cancel(123);
        Log.d("XXX" , "Scheduler cancelado");
    }

}