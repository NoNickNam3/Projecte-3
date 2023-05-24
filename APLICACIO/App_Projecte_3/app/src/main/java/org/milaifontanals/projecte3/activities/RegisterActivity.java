package org.milaifontanals.projecte3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.model.api.userRegister.RespostaRegister;
import org.milaifontanals.projecte3.utils.db.dbUtils;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements Callback<RespostaRegister> {

    private SQLiteDatabase db = null;
    private String mTokenActual = null;
    private EditText edtNom, edtCognom, edtCorreu, edtPasswd, edtPasswdConfirm;
    private Intent intentMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);


        //  Assigno els camps del LogIn a els edits.
        edtNom = findViewById(R.id.edtNomUsuari);
        edtCognom = findViewById(R.id.edtCognomUsuari);
        edtCorreu = findViewById(R.id.edtCorreuUsuari);
        edtPasswd = findViewById(R.id.edtPasswd);
        edtPasswdConfirm = findViewById(R.id.edtPasswdConfirm);

        //  Configuració de la bdd SQLite
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnLogIn:
                Intent i = new Intent(this, LogInActivity.class);
                startActivity(i);
                break;
            case R.id.btnRegister:
                Call<RespostaRegister> call = APIAdapter.getApiService().registerUser(
                        edtNom.getText().toString(),
                        edtCognom.getText().toString(),
                        edtCorreu.getText().toString(),
                        edtPasswd.getText().toString(),
                        edtPasswdConfirm.getText().toString()
                        );
                call.enqueue(this);
                break;
        }
    }

    @Override
    public void onResponse(Call<RespostaRegister> call, Response<RespostaRegister> response) {
        if(response.isSuccessful()) {
            Log.d("XXX", "Resposta correcta del servidor");
            DialogUtils.toastMessageLong(this, "SE HA REGISTRADO CORRECTAMENTE");
            RespostaRegister res = response.body();

            //Database configs
            ContentValues values = new ContentValues();
            //  Enregistra l'usuari a la bdd
            dbUtils.guardarUsuariBDD(res.getToken(), res.getUser(), db);
            mTokenActual = res.getToken();
            Log.d("XXX", res.getToken());

            SharedPreferences sp = this.getSharedPreferences("tokenUsuari", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("token", mTokenActual);
            ed.commit();
            Log.d("XXX", res.getToken());

            intentMove = new Intent(this, MainActivity.class);
            startActivity(intentMove);
        }else{
            DialogUtils.toastMessageLong(this, "NO SE HA PODIDO CONECTAR CON EL SERVICIO");
        }

    }

    @Override
    public void onFailure(Call<RespostaRegister> call, Throwable t) {
        Log.d("XXX", t.getMessage());
        DialogUtils.toastMessageLong(this, "ERROR AL REGISTRARSE");
        intentMove = new Intent(this, RegisterActivity.class);
        startActivity(intentMove);
    }
}