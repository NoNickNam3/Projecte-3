package org.milaifontanals.projecte3.ui.ruta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.databinding.FragmentAgendaBinding;
import org.milaifontanals.projecte3.databinding.FragmentRutaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RutaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RutaFragment extends Fragment {

    private FragmentRutaBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RutaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RutaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RutaFragment newInstance(String param1, String param2) {
        RutaFragment fragment = new RutaFragment();
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
        Log.d("XXX", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("XXX", "onCreateView");
        binding = FragmentRutaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }
}