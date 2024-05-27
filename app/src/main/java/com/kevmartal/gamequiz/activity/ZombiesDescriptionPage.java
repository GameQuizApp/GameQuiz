package com.kevmartal.gamequiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.kevmartal.gamequiz.R;

public class ZombiesDescriptionPage extends AppCompatActivity {
    private boolean switchOn= false,btn_save=false;
    LottieAnimationView lottieSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zombies_description_page);
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
                Intent intent = new Intent(ZombiesDescriptionPage.this, GameActivity.class);
                // Agregar el parámetro String al intent
                intent.putExtra("category", "zombies");
                startActivity(intent);
            }
        });
        //VISTA ANIMATION VIEW CLICK
        lottieSave=findViewById(R.id.save_btn);

        lottieSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchOn){
                    lottieSave.setMinAndMaxProgress(0.0f,0.0f);
                    lottieSave.playAnimation();
                    switchOn=false;
                }else{
                    lottieSave.setMinAndMaxProgress(0.0f,1.0f);
                    lottieSave.playAnimation();
                    switchOn=true;
                }
            }
        });



    }
}