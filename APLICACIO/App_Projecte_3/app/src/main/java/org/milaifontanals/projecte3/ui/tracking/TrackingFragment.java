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
import org.milaifontanals.projecte3.ui.adapters.RutaAdapter;
import org.milaifontanals.projecte3.utils.direccions.DireccionsUtil;

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

        //---------------------------------
        // Configuració del RecyclerView
        //---------------------------------
        binding.rcvRutes.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.rcvRutes.setHasFixedSize(true);

        adapter = new RutaAdapter(Ruta.getRutes(), this.getContext());
        binding.rcvRutes.setAdapter(adapter);
        adapter.setOnRutaClickedListener(new RutaAdapter.OnRutaClickedListener() {
            @Override
            public void onRutaClicked(Ruta r) {

            }
        });

    }
}