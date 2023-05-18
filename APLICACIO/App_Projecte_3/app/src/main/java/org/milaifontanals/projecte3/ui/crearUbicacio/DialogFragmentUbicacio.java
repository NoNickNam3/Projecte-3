package org.milaifontanals.projecte3.ui.crearUbicacio;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
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
import org.milaifontanals.projecte3.model.apiUbicacions.RespostaGetUbicaciones;
import org.milaifontanals.projecte3.model.apiUbicacions.UbicacionApi;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogFragmentUbicacio extends DialogFragment implements Callback<RespostaCrearUbicacio> {

    private String nombre;
    private String direccion;
    private String obeservacion;
    private Boolean favorito;
    private String coordenada;
    private String mTokenActual;

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

                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocationName(direccion, 1);
                } catch (IOException e) {
                    tancaDialog("Fallo al obtener la coordenada");
                }
                if (addresses.size() > 0) {
                    coordenada = addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude();
                }

                Call<RespostaCrearUbicacio> callUbicacions = APIAdapter.getApiService().crearUbicacio(" Bearer " + mTokenActual, nombre, direccion, coordenada, obeservacion, favorito?1:0);
                callUbicacions.enqueue(DialogFragmentUbicacio.this);

            }
        });

        return v;
    }

    @Override
    public void onResponse(Call<RespostaCrearUbicacio> call, Response<RespostaCrearUbicacio> response) {
        DataUbicacio uData = response.body().getData();
        Ubicacion u = new Ubicacion(uData.getId(), uData.getNombre(), uData.getCoordenada(), uData.getDireccion(), uData.getObservaciones(), (Integer.parseInt(uData.getFav())>0?true:false));
        Ubicacion.addUbicacion(u);
        Ubicacion.getUbicaciones();
        dismiss();
    }

    @Override
    public void onFailure(Call<RespostaCrearUbicacio> call, Throwable t) {

        tancaDialog("Fallo de servidor");

    }

    private void tancaDialog(String message){
        dismiss();
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
