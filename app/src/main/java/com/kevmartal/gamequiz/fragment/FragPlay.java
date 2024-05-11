package com.kevmartal.gamequiz.fragment;

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

import com.kevmartal.gamequiz.R;
import com.kevmartal.gamequiz.activity.RandomImagePage;

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

        Button buttonRandom = view.findViewById(R.id.buttonRandom);
        buttonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para iniciar la nueva actividad aquí
                Intent intent = new Intent(getActivity(), RandomImagePage.class);
                startActivity(intent);
            }
        });
    }

}



