package org.milaifontanals.projecte3.ui.agenda;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.projecte3.activities.LogInActivity;
import org.milaifontanals.projecte3.activities.MainActivity;
import org.milaifontanals.projecte3.databinding.FragmentAgendaBinding;
import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.model.api.APIAdapter;
import org.milaifontanals.projecte3.model.apiUbicacions.RespostaGetUbicaciones;
import org.milaifontanals.projecte3.model.apiUbicacions.UbicacionApi;
import org.milaifontanals.projecte3.model.db.MyDatabaseHelper;
import org.milaifontanals.projecte3.utils.direccions.DireccionsUtil;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        if(mTokenActual != null){
            Call<RespostaGetUbicaciones> callUbicacions = APIAdapter.getApiService().getLlistaUbicacions(" Bearer " + mTokenActual);
            callUbicacions.enqueue(this);
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
        }

    }

    @Override
    public void onFailure(Call<RespostaGetUbicaciones> call, Throwable t) {
        Log.d("XXX", "No he pogut obtenir una merda");



        Intent intentMove = new Intent(this.getContext(), LogInActivity.class);
        startActivity(intentMove);

    }
}