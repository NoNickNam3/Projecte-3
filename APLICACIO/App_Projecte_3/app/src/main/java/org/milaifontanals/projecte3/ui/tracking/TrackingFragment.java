package org.milaifontanals.projecte3.ui.tracking;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.databinding.FragmentHistoryBinding;
import org.milaifontanals.projecte3.model.Ruta;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.api.apiRuta.RespostaGetRutas;
import org.milaifontanals.projecte3.model.api.apiRuta.RespostaRuta;
import org.milaifontanals.projecte3.ui.adapters.RutaAdapter;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;
import org.milaifontanals.projecte3.utils.direccions.DireccionsUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackingFragment extends Fragment {

    private FragmentHistoryBinding binding;
    public String mTokenActual = null;
    private RutaAdapter adapter;

    public TrackingFragment() {
        // Required empty public constructor
    }

    public static TrackingFragment newInstance(String param1, String param2) {
        TrackingFragment fragment = new TrackingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sp = this.requireContext().getSharedPreferences("tokenUsuari", MODE_PRIVATE);
        mTokenActual = sp.getString("token", null);

        Call<RespostaGetRutas> call = APIAdapter.getApiService().getAllRutes(" Bearer " + mTokenActual);
        call.enqueue(new Callback<RespostaGetRutas>() {
            @Override
            public void onResponse(Call<RespostaGetRutas> call, Response<RespostaGetRutas> response) {
                if(response.isSuccessful()){
                    RespostaGetRutas rgr = response.body();
                    //---------------------------------
                    // Configuració del RecyclerView
                    //---------------------------------
                    binding.rcvRutes.setLayoutManager(new LinearLayoutManager(requireContext()));
                    binding.rcvRutes.setHasFixedSize(true);

                    adapter = new RutaAdapter(Ruta.setRutes(rgr), requireContext());
                    binding.rcvRutes.setAdapter(adapter);
                    adapter.setOnRutaClickedListener(new RutaAdapter.OnRutaClickedListener() {
                        @Override
                        public void onRutaClicked(Ruta r) {
                            Call<RespostaRuta> call = APIAdapter.getApiService().getRutaClick(" Bearer " + mTokenActual, r.getId());
                            call.enqueue(new Callback<RespostaRuta>() {
                                @Override
                                public void onResponse(Call<RespostaRuta> call, Response<RespostaRuta> response) {
                                    if(response.isSuccessful()){
                                        String uDesti = "";
                                        DialogUtils.toastMessageLong(requireActivity(), "RUTA RECIBIDA");
                                        RespostaRuta rr = response.body();
                                        if(rr.getData().getLocations() != null){
                                            DireccionsUtil.obrirRuta(requireContext(), rr.getData().getLocations());
                                        }else{
                                            DialogUtils.toastMessageLong(requireActivity(), "NO S'HA POGUT RECUPERAR LA RUTA");
                                        }

                                    }else{
                                        DialogUtils.toastMessageLong(requireActivity(), "ERROR CALCULANDO LA RUTA");
                                    }
                                }

                                @Override
                                public void onFailure(Call<RespostaRuta> call, Throwable t) {

                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<RespostaGetRutas> call, Throwable t) {

            }
        });



    }
}