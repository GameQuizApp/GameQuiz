package com.kevmartal.gamequiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hoja2.
     */
    // TODO: Rename and change types and number of parameters
    public static FragHome newInstance(String param1, String param2) {
        FragHome fragment = new FragHome();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView EnlaceAction= view.findViewById(R.id.TextviewEnlaceAction);
        TextView EnlaceRockstar= view.findViewById(R.id.TextViewEnlaceRockstar);
        TextView EnlaceZombies= view.findViewById(R.id.TextviewEnlaceZombies);
        TextView EnlaceTerror= view.findViewById(R.id.TextviewEnlaceTerror);
        EnlaceAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Crear un Intent para iniciar la nueva actividad
                Intent intent = new Intent(getContext(), GameActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });
        //Enlace activityRockstar
        EnlaceRockstar.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RockstarPage.class);
            startActivity(intent);
        });
        //Enlace activityZombie
        EnlaceZombies.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ZombiesPage.class);
            startActivity(intent);
        });
        //Enlace activityTerror
        EnlaceTerror.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TerrorPage.class);
            startActivity(intent);
        });


        //ENLACE DE LAS CATEGORÍAS
        TextView deportesTextView = view.findViewById(R.id.Deportes);
        TextView deporTextView = view.findViewById(R.id.deportes_textv);
        ScrollView scrollView = view.findViewById(R.id.scrollviewCategorias);
        //Link a deportes
        deportesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Desplazar la vista hacia el TextView "tendencia"
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0, deporTextView.getTop());
                    }
                });
            }
        });
        //Link a Acción
        TextView actionTextView = view.findViewById(R.id.Accion);
        TextView actionTxt = view.findViewById(R.id.actiontxt);

        actionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Desplazar la vista hacia el TextView "tendencia"
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0, actionTxt.getTop());
                    }
                });
            }
        });
        //Link a Terror
        TextView terrorTextView = view.findViewById(R.id.Terror);
        TextView terrorTxt = view.findViewById(R.id.Miedotxt);

        terrorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Desplazar la vista hacia el TextView "tendencia"
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0, terrorTxt.getTop());
                    }
                });
            }
        });
        //Link a Retro
        TextView retroTextView = view.findViewById(R.id.Retro);
        TextView retroTxt = view.findViewById(R.id.retrotxt);

        retroTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Desplazar la vista hacia el TextView "tendencia"
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0, retroTxt.getTop());
                    }
                });
            }
        });

    }
}