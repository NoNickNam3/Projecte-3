package org.milaifontanals.projecte3.ui.ruta;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.databinding.FragmentRutaBinding;
import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.api.apiRuta.RespostaRuta;
import org.milaifontanals.projecte3.model.api.apiUbicacions.RespostaGetUbicaciones;
import org.milaifontanals.projecte3.model.api.apiUbicacions.UbicacionApi;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.ui.adapters.UbicacionRutaAdapter;
import org.milaifontanals.projecte3.utils.db.dbUtils;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;
import org.milaifontanals.projecte3.utils.direccions.DireccionsUtil;
import org.milaifontanals.projecte3.utils.intentMoves.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RutaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RutaFragment extends Fragment implements Callback<RespostaGetUbicaciones>, View.OnClickListener {

    private List<Ubicacion> uSeleccionades;
    private String uDesti;
    private FragmentRutaBinding binding;
    private UbicacionRutaAdapter adapter, adapterSeleccionats;
    public String mTokenActual = null;
    private int tries;

    public RutaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RutaFragment newInstance(String param1, String param2) {
        RutaFragment fragment = new RutaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uSeleccionades = new ArrayList<>();
        tries = 0;
        Log.d("XXX", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("XXX", "onCreateView");
        binding = FragmentRutaBinding.inflate(getLayoutInflater());
        binding.btnNetejar.setOnClickListener(this);
        binding.btnAnar.setOnClickListener(this);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sp = this.requireContext().getSharedPreferences("tokenUsuari", MODE_PRIVATE);
        mTokenActual = sp.getString("token", null);

        if(mTokenActual != null){
            Call<RespostaGetUbicaciones> callUbicacions = APIAdapter.getApiService().getLlistaUbicacions(" Bearer " + mTokenActual);
            callUbicacions.enqueue(this);
        }else{
            IntentUtils.anarLogin(requireContext(), this);
        }
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnNetejar:

                for(Ubicacion u : uSeleccionades){
                    Ubicacion.addUbicacion(u);
                }

                uSeleccionades.clear();

                adapter.notifyDataSetChanged();
                adapterSeleccionats.notifyDataSetChanged();
                break;

            case R.id.btnAnar:

                List<String> parades = new ArrayList<>();

                for(Ubicacion u : uSeleccionades){
                    parades.add(u.getCoordenadas());
                }
/*
                Location sortida = DireccionsUtil.getLastKnownLocation(getActivity());
                DireccionsUtil.obrirRutaStringAutoOrdenada(requireContext(), sortida.getLatitude() + "," + sortida.getLongitude(), parades);
*/
                /*      INTENTO DE RUTA CON ENDPOINT
                 */

                Location sortida = DireccionsUtil.getLastKnownLocation(getActivity());

                String sParades = DireccionsUtil.convertToJSON(parades);

                Call<RespostaRuta> call = APIAdapter.getApiService().getOptimitzador(" Bearer " + mTokenActual, sortida.getLatitude() + "," + sortida.getLongitude(), sParades);
                call.enqueue(new Callback<RespostaRuta>() {
                    @Override
                    public void onResponse(Call<RespostaRuta> call, Response<RespostaRuta> response) {
                        if(response.isSuccessful()){
                            DialogUtils.toastMessageLong(requireActivity(), "RUTA RECIBIDA");
                            RespostaRuta rr = response.body();
                            if(rr.getData().getLocations() != null){
                                List<Double> desti = rr.getData().getLocations().get(rr.getData().getLocations().size() - 1);
                                uDesti = DireccionsUtil.getStringFromDoubleList(desti);
                                DireccionsUtil.obrirRuta(requireContext(), uDesti, rr.getData().getLocations());
                            }else{
                                uDesti = parades.get(parades.size() - 1);
                                DireccionsUtil.obrirRutaString(requireContext(), uDesti, parades);
                            }

                        }else{
                            DialogUtils.toastMessageLong(requireActivity(), "ERROR CALCULANDO LA RUTA");
                        }
                    }

                    @Override
                    public void onFailure(Call<RespostaRuta> call, Throwable t) {
                        DialogUtils.toastMessageLong(requireActivity(), "ERROR EN LA CONEXION CON EL SERVIDOR");
                    }
                });

                break;
        }
    }

    @Override
    public void onResponse(Call<RespostaGetUbicaciones> call, Response<RespostaGetUbicaciones> response) {

        if(response.isSuccessful()){
            RespostaGetUbicaciones res = response.body();
            List<UbicacionApi> apiUbicacions = res.getData();

            Ubicacion.setLlUbicacionsRebudes(apiUbicacions);

            //---------------------------------
            // Configuració del RecyclerView
            //---------------------------------

            adapter = new UbicacionRutaAdapter(Ubicacion.getUbicaciones(), requireContext());
            adapterSeleccionats = new UbicacionRutaAdapter(uSeleccionades, requireContext());

            binding.rcvUbicacion.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.rcvUbicacion.setHasFixedSize(true);
            binding.rcvUbicacion.setAdapter(adapter);

            binding.rcvSeleccionats.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.rcvSeleccionats.setHasFixedSize(true);
            binding.rcvSeleccionats.setAdapter(adapterSeleccionats);
            adapter.setOnUbicacioClickedListener(new UbicacionRutaAdapter.OnUbicacioClickedListener() {
                @Override
                public void onUbicacioClicked(Ubicacion u) {
                    uSeleccionades.add(u);
                    adapterSeleccionats.notifyDataSetChanged();

                    Ubicacion.removeUbicacion(u);
                    adapter.notifyDataSetChanged();
                }
            });

            adapterSeleccionats.setOnUbicacioClickedListener(new UbicacionRutaAdapter.OnUbicacioClickedListener() {
                @Override
                public void onUbicacioClicked(Ubicacion u) {
                    uSeleccionades.remove(u);
                    adapterSeleccionats.notifyDataSetChanged();

                    Ubicacion.addUbicacion(u);
                    adapter.notifyDataSetChanged();
                }
            });


        }else{
            retryCallIfPossible();
        }

    }

    @Override
    public void onFailure(Call<RespostaGetUbicaciones> call, Throwable t) {

        Log.d("XXX", "Error connectant amb el servidor");
        retryCallIfPossible();

    }

    private void retryCallIfPossible(){
        if(tries < 3){
            tries++;
            Call<RespostaGetUbicaciones> callUbicacions = APIAdapter.getApiService().getLlistaUbicacions(" Bearer " + mTokenActual);
            callUbicacions.enqueue(this);
        }else{
            dbUtils.eliminarUsuariBDD(new MyDatabaseHelper(requireContext()).getWritableDatabase());
            IntentUtils.anarLogin(requireContext(), this);
        }
    }
}