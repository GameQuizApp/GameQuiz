package com.kevmartal.gamequiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

    ImageView action_card;
    ImageView rockstar_card;
    ImageView ubisoft_card;
    ImageView zombies_card;
    ImageView residentevil_card;
    ImageView godofwar_card;
    ImageView tekken_card;
    ImageView nba_card;
    ImageView racing_card;
    ImageView terror_card;
    ImageView zombies2_card;
    ImageView residentevil2_card;
    ImageView indies_card;
    ImageView retro_card;
    ImageView silksong_card;
    ImageView shooter_card;
    ImageView action2_card;
    ImageView epicgames_card;

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

        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Tarjeta de temática acción

        action_card = view.findViewById(R.id.Cyberpunk_img);
        action_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Acción");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género de acción");

                dialog.show();
            }
        });

        // Tarjeta de temática rockstar

        rockstar_card = view.findViewById(R.id.rockstarimg);
        rockstar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Rockstar");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la empresa Rockstar");

                dialog.show();
            }
        });

        // Tarjeta de temática ubisoft

        ubisoft_card = view.findViewById(R.id.ubisoftimg);
        ubisoft_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Ubisoft");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la empresa Ubisoft");

                dialog.show();
            }
        });

        // Tarjeta de temática zombies

        zombies_card = view.findViewById(R.id.twdimg);
        zombies_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Zombies");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género de zombies");

                dialog.show();
            }
        });

        // Tarjeta de temática resident evil

        residentevil_card = view.findViewById(R.id.RE7img);
        residentevil_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Resident Evil");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la saga Resident Evil");

                dialog.show();
            }
        });

        // Tarjeta de temática god of war

        godofwar_card = view.findViewById(R.id.GodOfWarimg);
        godofwar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("God of War");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la saga God of War");

                dialog.show();
            }
        });

        // Tarjeta de temática tekken

        tekken_card = view.findViewById(R.id.tekkenimg);
        tekken_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Tekken");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la saga Tekken");

                dialog.show();
            }
        });

        // Tarjeta de temática nba

        nba_card = view.findViewById(R.id.nbaimg);
        nba_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("NBA");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la saga NBA");

                dialog.show();
            }
        });

        // Tarjeta de temática racing

        racing_card = view.findViewById(R.id.racing_img);
        racing_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Racing");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género de carreras");

                dialog.show();
            }
        });

        // Tarjeta de temática terror

        terror_card = view.findViewById(R.id.lethealc_img);
        terror_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Terror");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género de terror");

                dialog.show();
            }
        });

        // Tarjeta de temática zombies

        zombies2_card = view.findViewById(R.id.twd2img);
        zombies2_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Zombies");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género de zombies");

                dialog.show();
            }
        });

        // Tarjeta de temática resident evil

        residentevil2_card = view.findViewById(R.id.RE7img2);
        residentevil2_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Resident Evil");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la saga Resident Evil");

                dialog.show();
            }
        });

        // Tarjeta de temática indies

        indies_card = view.findViewById(R.id.hades_img);
        indies_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Indies");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género indie");

                dialog.show();
            }
        });

        // Tarjeta de temática retro

        retro_card = view.findViewById(R.id.retro_img);
        retro_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Retro");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género retro");

                dialog.show();
            }
        });

        // Tarjeta de temática silksong

        silksong_card = view.findViewById(R.id.silksong_img);
        silksong_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Silksong");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la saga Hollow Knight");

                dialog.show();
            }
        });

        // Tarjeta de temática shooter

        shooter_card = view.findViewById(R.id.Doomimg);
        shooter_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Shooter");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género de disparos");

                dialog.show();
            }
        });

        // Tarjeta de temática acción

        action2_card = view.findViewById(R.id.Cyberpunk2_img);
        action2_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Acción");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos del género de acción");

                dialog.show();
            }
        });

        // Tarjeta de temática epic games

        epicgames_card = view.findViewById(R.id.Epic_Games_img);
        epicgames_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());

                View vista = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.card_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                TextView title = (TextView) vista.findViewById(R.id.titulo);
                title.setText("Epic Games");

                TextView description = (TextView) vista.findViewById(R.id.descripcion);
                description.setText("En esta categoría puedes encontrar diversos juegos de la empresa Epic Games");

                dialog.show();
            }
        });

        return view;
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