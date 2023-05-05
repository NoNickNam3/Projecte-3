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

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtCorreu, edtPasswd, edtPasswdConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                break;
        }
    }
}