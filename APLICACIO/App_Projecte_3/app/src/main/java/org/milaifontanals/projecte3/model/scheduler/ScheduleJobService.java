package org.milaifontanals.projecte3.model.scheduler;

import android.app.Activity;
import android.app.NativeActivity;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import org.milaifontanals.projecte3.activities.MainActivity;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.api.apiJobScheduling.RespostaJobScheduling;
import org.milaifontanals.projecte3.utils.direccions.DireccionsUtil;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class ScheduleJobService extends JobService {
    private boolean jobCancelled = false;
    private String mTokenActual;
    public Activity mActivity;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("XXX", "JOB SERVICE STARTED");

        SharedPreferences sp = getBaseContext().getSharedPreferences("tokenUsuari", MODE_PRIVATE);
        mTokenActual = sp.getString("token", null);

        //mActivity = getActivityFromContext(getBaseContext());

        sendCoordsToServer(params);

        return true;
    }

    private void sendCoordsToServer(JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(jobCancelled){return;}
                Location l = DireccionsUtil.getLastKnownLocationC(getBaseContext());
                Call<RespostaJobScheduling> call = APIAdapter.getApiService().trackingLocation(" Bearer " + mTokenActual, l.getLatitude() + "," + l.getLongitude());
                call.enqueue(new retrofit2.Callback<RespostaJobScheduling>() {
                    @Override
                    public void onResponse(Call<RespostaJobScheduling> call, Response<RespostaJobScheduling> response) {

                        if(response.isSuccessful()) {
                            Log.d("XXX", "Localització enregistrada correctament");
                        }else{
                            Log.d("XXX", "El servidor es queixa de la localització del dispositiu");
                        }

                    }

                    @Override
                    public void onFailure(Call<RespostaJobScheduling> call, Throwable t) {
                        Log.d("XXX", "Error del servidor durant el tracking");

                    }
                });
                }

        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("XXX", "JOB CANCELLED");
        jobCancelled = true;
        return true;
    }

    public static Activity getActivityFromContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
