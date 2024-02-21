package com.kevmartal.gamequiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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

        // Obtén una referencia al LinearLayout en tu layout
        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);

        // Crea botones y añádelos al LinearLayout
        addButtonToLinearLayout(linearLayout, "Modo Random", Color.parseColor("#FFC107"));
        addButtonToLinearLayout(linearLayout, "1vs1", Color.parseColor("#4CAF50"));
        addButtonToLinearLayout(linearLayout, "Si", Color.parseColor("#2196F3"));
        // Agrega más botones según sea necesario

        // Configura el fondo del LinearLayout con bordes redondos
        linearLayout.setBackgroundResource(R.drawable.rounded_border);

        // Agrega un OnClickListener al primer botón
        Button primerBoton = (Button) linearLayout.getChildAt(0);
        if (primerBoton != null) {
            primerBoton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción cuando se hace clic en el primer botón ("Modo Random")
                    // Por ejemplo, inicia una nueva actividad
                    Intent intent = new Intent(getActivity(), RandomImagePage.class);
                    startActivity(intent);
                }
            });
        }

        // Agrega un OnClickListener a los otros botones y muestra Toasts
        Button segundoBoton = (Button) linearLayout.getChildAt(1);
        Button tercerBoton = (Button) linearLayout.getChildAt(2);

        if (segundoBoton != null) {
            segundoBoton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción cuando se hace clic en el segundo botón ("1vs1")
                    // Muestra un Toast
                    Toast.makeText(requireContext(), "1vs1", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (tercerBoton != null) {
            tercerBoton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Acción cuando se hace clic en el tercer botón ("Si")
                    // Muestra un Toast
                    Toast.makeText(requireContext(), "Si", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void addButtonToLinearLayout(LinearLayout linearLayout, String buttonText, int buttonColor) {
        // Crea un nuevo botón
        Button button = new Button(requireContext());
        button.setText(buttonText);
        button.setBackgroundColor(buttonColor);
        button.setTextColor(Color.WHITE);
        button.setTextSize(18);

        // Crea parámetros para el botón
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 10, 10, 10);

        // Agrega el botón al LinearLayout con los parámetros proporcionados
        linearLayout.addView(button, params);
    }
    }



