package com.example.gamequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        CardView registerCardView = findViewById(R.id.registerCV);
        CardView loginCardView = findViewById(R.id.loginCV);

        registerCardView.setVisibility(View.INVISIBLE);
        loginCardView.setVisibility(View.INVISIBLE);
    }

    public void openRegister() {
        CardView registerCardView = findViewById(R.id.registerCV);

        registerCardView.setVisibility(View.VISIBLE);
    }

    public void openLogin() {
        CardView loginCardView = findViewById(R.id.loginCV);

        loginCardView.setVisibility(View.VISIBLE);
    }
}
