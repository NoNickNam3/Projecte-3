package org.milaifontanals.projecte3.ui.dialogFragments;

import static android.content.Context.MODE_PRIVATE;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.apiUbicacions.DataUbicacio;
import org.milaifontanals.projecte3.model.apiUbicacions.RespostaCrearUbicacio;
import org.milaifontanals.projecte3.model.elazarEmpresa.RespostaEnllacarEmpresa;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragmentEnllacEmpresa extends DialogFragment implements Callback<RespostaEnllacarEmpresa> {

    private String codiEmpresa;
    private String mTokenActual;

    public DialogFragmentEnllacEmpresa(){
        super(R.layout.fragment_enllac_empresa);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);

        mTokenActual = this.requireContext().getSharedPreferences("tokenUsuari", MODE_PRIVATE).getString("token", null);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button bEnllacaEmpresa = v.findViewById(R.id.btnEnllacarEmpresa);
        Button bCancelaEnllac = v.findViewById(R.id.btnCancelarEnllacEmpresa);
        EditText edtCodi = v.findViewById(R.id.edtCodiEmpresa);

        bCancelaEnllac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        bEnllacaEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codiEmpresa = edtCodi.getText().toString();

                Call<RespostaEnllacarEmpresa> callUbicacions = APIAdapter.getApiService().enllacEmpresa(" Bearer " + mTokenActual, codiEmpresa);
                callUbicacions.enqueue(DialogFragmentEnllacEmpresa.this);

            }
        });

        return v;
    }

    @Override
    public void onResponse(Call<RespostaEnllacarEmpresa> call, Response<RespostaEnllacarEmpresa> response) {
        if(response.isSuccessful()){
            RespostaEnllacarEmpresa uData = response.body();
            Log.d("XXX", uData.getMessage());
            tancaDialog("Se ha unido a la empresa correctamente");
        }

    }

    @Override
    public void onFailure(Call<RespostaEnllacarEmpresa> call, Throwable t) {

        tancaDialog("Fallo del enlace");

    }

    private void tancaDialog(String message){
        dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
