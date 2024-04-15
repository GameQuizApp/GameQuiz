package com.kevmartal.gamequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActionDescriptionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_action_description_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ActionDescriptionPage.this, GameActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageButton = (ImageView) findViewById(R.id.guardar_juego);
        imageButton.setOnClickListener(new View.OnClickListener() {
            boolean isFavorito = false;

            @Override
            public void onClick(View v) {
                if (isFavorito) {
                    // Si ya es favorito, cambia a la imagen no favorita
                    imageButton.setImageResource(R.drawable.guardar);
                    isFavorito = false;
                } else {
                    // Si no es favorito, cambia a la imagen favorita
                    imageButton.setImageResource(R.drawable.guardado_black);
                    isFavorito = true;
                }
            }
        });


    }
}