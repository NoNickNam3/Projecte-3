package org.milaifontanals.projecte3.ui.agenda;

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

import org.milaifontanals.projecte3.databinding.FragmentAgendaBinding;
import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.api.apiUbicacions.RespostaGetUbicaciones;
import org.milaifontanals.projecte3.model.api.apiUbicacions.UbicacionApi;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.ui.adapters.UbicacionAdapter;
import org.milaifontanals.projecte3.utils.db.dbUtils;
import org.milaifontanals.projecte3.utils.dialogs.DialogUtils;
import org.milaifontanals.projecte3.utils.direccions.DireccionsUtil;
import org.milaifontanals.projecte3.utils.intentMoves.IntentUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment implements Callback<RespostaGetUbicaciones> {

    private FragmentAgendaBinding binding;
    private UbicacionAdapter adapter;
    public String mTokenActual = null;
    private int tries;

    public AgendaFragment() {
        // Required empty public constructor
    }
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        tries = 0;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_agenda, container, false);

        binding = FragmentAgendaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sp = this.requireContext().getSharedPreferences("tokenUsuari", MODE_PRIVATE);
        mTokenActual = sp.getString("token", null);
        binding.btnAfegirUbicacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.obrirDialogAddUbicacio(getFragmentManager());
            }
        });

        if(mTokenActual != null){
            Call<RespostaGetUbicaciones> callUbicacions = APIAdapter.getApiService().getLlistaUbicacions(" Bearer " + mTokenActual);
            callUbicacions.enqueue(this);
        }else{
            IntentUtils.anarLogin(requireContext(), this);
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
            binding.rcvUbicacion.setLayoutManager(new LinearLayoutManager(this.getContext()));
            binding.rcvUbicacion.setHasFixedSize(true);

            adapter = new UbicacionAdapter(Ubicacion.getUbicaciones(), this.getContext());
            binding.rcvUbicacion.setAdapter(adapter);
            adapter.setOnUbicacioClickedListener(new UbicacionAdapter.OnUbicacioClickedListener() {
                @Override
                public void onUbicacioClicked(Ubicacion u) {
                    DireccionsUtil.obreMaps(requireContext(), u);
                }
            });
        }else{
            retryCallIfPossible();
        }

    }

    @Override
    public void onFailure(Call<RespostaGetUbicaciones> call, Throwable t) {
        DialogUtils.toastMessageLong(requireActivity(), "TOKEN NO VALIDO, INICIE SESIÓN");

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