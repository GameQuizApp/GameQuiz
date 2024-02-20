package com.kevmartal.gamequiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragPlay extends Fragment {

    public FragPlay() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtén una referencia al GridLayout en tu layout
        GridLayout gridLayout = view.findViewById(R.id.gridLayout);

        // Define los parámetros del GridLayout
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = GridLayout.LayoutParams.MATCH_PARENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;

        // Crea botones y añádelos al GridLayout
        addButtonToGridLayout(gridLayout, "Modo 1", Color.parseColor("#FFC107"), params);
        addButtonToGridLayout(gridLayout, "Modo 2", Color.parseColor("#4CAF50"), params);
        addButtonToGridLayout(gridLayout, "Modo 3", Color.parseColor("#2196F3"), params);
        // Agrega más botones según sea necesario

        // Configura el marco para el GridLayout (puedes ajustar los valores según tu preferencia)
        gridLayout.setBackgroundResource(R.drawable.rounded_border);
    }

    private void addButtonToGridLayout(GridLayout gridLayout, String buttonText, int buttonColor, GridLayout.LayoutParams params) {
        // Crea un nuevo botón
        Button button = new Button(requireContext());
        button.setText(buttonText);
        button.setBackgroundColor(buttonColor);
        button.setTextColor(Color.WHITE);
        button.setTextSize(18);

        // Establece el oyente de clic para cada botón si es necesario
        // button.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         // Acciones cuando se hace clic en el botón
        //     }
        // });

        // Agrega el botón al GridLayout con los parámetros proporcionados
        gridLayout.addView(button, params);
    }
}
