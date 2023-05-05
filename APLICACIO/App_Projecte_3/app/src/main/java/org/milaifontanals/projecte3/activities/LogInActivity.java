package org.milaifontanals.projecte3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.api.APIAdapter;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity implements Callback<RespostaLogin> {

    private SQLiteDatabase db = null;
    private String mTokenActual = null;
    private EditText edtCorreu, edtPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Configuració de la bdd SQLite
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        //  Comprovació del Token a la bdd, si existeix, em crec que és el bó
        Cursor cursor = db.rawQuery("select * from dbInterna", null);
        if (cursor.moveToNext()) {
            Log.d("XXX", "HE POGUT CARREGAR LA BDD");
            String token = cursor.getString(cursor.getColumnIndexOrThrow("token"));
            mTokenActual = token;
        }
        cursor.close();

        setContentView(R.layout.activity_log_in);

        //  Assigno els camps del LogIn a els edits.
        edtCorreu = findViewById(R.id.edtCorreuUsuari);
        edtPasswd = findViewById(R.id.edtPasswd);

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnLogIn:
                Log.d("XXX", edtCorreu.getText().toString() + " - " + edtPasswd.getText().toString());
                Call<RespostaLogin> call = APIAdapter.getApiService().loginUser(edtCorreu.getText().toString(), edtPasswd.getText().toString());
                call.enqueue(this);
                break;
            case R.id.btnRegister:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onResponse(Call<RespostaLogin> call, Response<RespostaLogin> response) {
        Log.d("XXX", "He rebut contestació:");
        if(response.isSuccessful()){
            Log.d("XXX", "Resposta correcta del servidor");
            RespostaLogin res = response.body();

            //Database configs
            ContentValues values = new ContentValues();
            values.put("token", res.getToken());
            values.put("email", res.getUser().getEmail());
            long id = db.insert("dbInterna", null, values);
            mTokenActual = res.getToken();
            Log.d("XXX", res.getToken());
        }
    }

    @Override
    public void onFailure(Call<RespostaLogin> call, Throwable t) {
        Log.d("XXX", "NO HE POGUT FER EL CALL");
        ContentValues values = new ContentValues();
        values.remove("token");
        db.insert("dbInterna", null, values);
    }

}