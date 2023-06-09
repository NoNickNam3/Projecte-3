package org.milaifontanals.projecte3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.model.api.userLogin.RespostaLogin;
import org.milaifontanals.projecte3.utils.db.dbUtils;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity implements Callback<RespostaLogin> {

    private SQLiteDatabase db = null;
    private String mTokenActual = null;
    private EditText edtCorreu, edtPasswd;
    private Intent intentMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        //  Configuració de la bdd SQLite
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();


        setContentView(R.layout.activity_log_in);

        //  Assigno els camps del LogIn a els edits.
        edtCorreu = findViewById(R.id.edtCorreuUsuari);
        edtPasswd = findViewById(R.id.edtPasswd);

        try{
            Cursor cursor = db.rawQuery("select * from dbInterna", null);
            if (cursor.moveToNext()) {
                Log.d("XXX", "HE POGUT CARREGAR LA BDD");
                String token = cursor.getString(cursor.getColumnIndexOrThrow("token"));
                mTokenActual = token;
                Log.d("XXX", mTokenActual);

                SharedPreferences sp = this.getSharedPreferences("tokenUsuari", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("token", mTokenActual);
                ed.commit();

                intentMove = new Intent(this, MainActivity.class);
                startActivity(intentMove);
            }else{
                dbUtils.eliminarUsuariBDD(db);
            }
            cursor.close();
        }catch(Exception e){}

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnLogIn:
                Call<RespostaLogin> call = APIAdapter.getApiService().loginUser(edtCorreu.getText().toString(), edtPasswd.getText().toString());
                call.enqueue(this);
                break;
            case R.id.btnRegister:
                intentMove = new Intent(this, RegisterActivity.class);
                startActivity(intentMove);
                break;
        }
    }

    @Override
    public void onResponse(Call<RespostaLogin> call, Response<RespostaLogin> response) {
        if(response.isSuccessful()){
            DialogUtils.toastMessageLong(this, "LOGGED IN");
            RespostaLogin res = response.body();

            //  Enregistra l'usuari a la bdd
            dbUtils.guardarUsuariBDD(res.getToken(), res.getUser(), db);

            mTokenActual = res.getToken();
            SharedPreferences sp = this.getSharedPreferences("tokenUsuari", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("token", mTokenActual);
            ed.commit();

            intentMove = new Intent(this, MainActivity.class);
            startActivity(intentMove);

        }
    }

    @Override
    public void onFailure(Call<RespostaLogin> call, Throwable t) {
        DialogUtils.toastMessageLong(this, "NO SE HA PODIDO INICIAR SESIÓN");
    }

}