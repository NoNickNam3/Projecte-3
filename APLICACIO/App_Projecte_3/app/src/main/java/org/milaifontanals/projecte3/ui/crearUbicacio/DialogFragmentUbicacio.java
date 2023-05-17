package org.milaifontanals.projecte3.ui.crearUbicacio;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.apiUbicacions.RespostaGetUbicaciones;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragmentUbicacio extends DialogFragment implements Callback<CrearUbicacio> {

    String nombre;
    String direccion;
    String obeservacion;
    Boolean favorito;
    String mTokenActual;

    public DialogFragmentUbicacio(){
        super(R.layout.fragment_crear_ubicacio);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);

        mTokenActual = this.requireContext().getSharedPreferences("tokenUsuari", MODE_PRIVATE).getString("token", null);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button bContacteCancel = v.findViewById(R.id.btnCancelarCreacioContacte);
        Button bContacteSave = v.findViewById(R.id.btnGuardarCreacioContacte);
        EditText edtNombre = v.findViewById(R.id.edtNombreUbicacion);
        EditText edtDireccion = v.findViewById(R.id.edtDireccionUbicacion);
        EditText edtObservaciones = v.findViewById(R.id.edtObservacionUbicacion);
        CheckBox cbxFav = v.findViewById(R.id.cbxFavoritoUbicacion);

        bContacteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        bContacteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = edtNombre.getText().toString();
                direccion = edtDireccion.getText().toString();
                obeservacion = edtObservaciones.getText().toString();
                favorito = cbxFav.isChecked();
                Log.d("XXX", "Nombre: " + nombre + " -> Dir: " + direccion + " -> Obs: " + obeservacion + " -> Fav: " + favorito);

                Call<CrearUbicacio> callUbicacions = APIAdapter.getApiService().crearUbicacio(" Bearer " + mTokenActual, nombre, direccion, "41.579222, 1.602742", obeservacion, favorito?1:0);
                callUbicacions.enqueue(DialogFragmentUbicacio.this);

            }
        });

        return v;
    }

    @Override
    public void onResponse(Call<CrearUbicacio> call, Response<CrearUbicacio> response) {
        dismiss();
    }

    @Override
    public void onFailure(Call<CrearUbicacio> call, Throwable t) {

    }
}
