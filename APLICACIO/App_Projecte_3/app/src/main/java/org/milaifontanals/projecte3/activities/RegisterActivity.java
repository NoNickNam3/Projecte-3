package org.milaifontanals.projecte3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.api.APIAdapter;
import org.milaifontanals.projecte3.model.userLogin.RespostaLogin;
import org.milaifontanals.projecte3.model.userRegister.RespostaRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements Callback<RespostaRegister> {

    private EditText edtCorreu, edtPasswd, edtPasswdConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);


        //  Assigno els camps del LogIn a els edits.
        edtCorreu = findViewById(R.id.edtCorreuUsuari);
        edtPasswd = findViewById(R.id.edtPasswd);
        edtPasswdConfirm = findViewById(R.id.edtPasswdConfirm);

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnLogIn:
                Intent i = new Intent(this, LogInActivity.class);
                startActivity(i);

                break;
            case R.id.btnRegister:
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2);
                break;
        }
    }

    @Override
    public void onResponse(Call<RespostaRegister> call, Response<RespostaRegister> response) {

    }

    @Override
    public void onFailure(Call<RespostaRegister> call, Throwable t) {

    }
}