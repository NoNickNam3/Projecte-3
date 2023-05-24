package org.milaifontanals.projecte3.utils.intentMoves;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.fragment.app.Fragment;

import org.milaifontanals.projecte3.activities.LogInActivity;

public class IntentUtils {
    public static void anarLogin(Context c, Fragment f){
        Log.d("XXX", "De camino al login...");
        Intent intentMove = new Intent(c, LogInActivity.class);
        f.startActivity(intentMove);
    }

    public static void anarMain(Context c, Fragment f) {
        Intent intentMove = new Intent(c, LogInActivity.class);
        f.startActivity(intentMove);
    }
}
