package com.kevmartal.gamequiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.kevmartal.gamequiz.R;

public class TerrorDescriptionPage extends AppCompatActivity {
    private boolean switchOn= false,btn_save=false;
    LottieAnimationView lottieSave;
    private int cnt=410;
    private TextView textViewCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terror_description_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un Intent para pasar al Activity GameActivity
                Intent intent = new Intent(TerrorDescriptionPage.this, GameActivity.class);
                // Agregar el parámetro String al intent
                intent.putExtra("category", "terror");
                startActivity(intent);
            }
        });

        // Inicialización del TextView del contador
        textViewCounter = findViewById(R.id.textViewCounter);
        textViewCounter.setText(String.valueOf(cnt));
        //VISTA ANIMATION VIEW CLICK
        lottieSave=findViewById(R.id.save_btn);

        lottieSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchOn){
                    lottieSave.setMinAndMaxProgress(0.0f,0.0f);
                    lottieSave.playAnimation();
                    switchOn=false;
                    cnt--;
                }else{
                    lottieSave.setMinAndMaxProgress(0.0f,1.0f);
                    lottieSave.playAnimation();
                    switchOn=true;
                    cnt++;
                }
                // Actualiza el texto del TextView con el nuevo valor del contador
                textViewCounter.setText(String.valueOf(cnt));
            }
        });



    }
}