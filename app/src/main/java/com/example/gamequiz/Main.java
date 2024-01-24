package com.example.gamequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Main extends AppCompatActivity {

    Button loginbutton;
    Button signupbutton;
    Button resultbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbutton = findViewById(R.id.login_button);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Login.class));
            }
        });

        signupbutton = findViewById(R.id.signup_button);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Signup.class));
            }
        });

        resultbutton = findViewById(R.id.result);
        resultbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialog = new BottomSheetDialog(Main.this);

                View vista = LayoutInflater.from(getApplicationContext()).inflate(R.layout.result_bottomsheet, null);

                dialog.setCancelable(true);
                dialog.setContentView(vista);

                dialog.show();
            }
        });
    }
}