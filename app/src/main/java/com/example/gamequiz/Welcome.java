package com.example.gamequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Welcome extends AppCompatActivity {

    Button loginbutton;
    Button registerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        loginbutton = findViewById(R.id.loginbutton);
        registerbutton = findViewById(R.id.signupbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog logindialog = new BottomSheetDialog(Welcome.this);

                View vista = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_login, null);

                logindialog.setCancelable(true);
                logindialog.setContentView(vista);

                logindialog.show();
            }
        });

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog registerdialog = new BottomSheetDialog(Welcome.this);

                View vista = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet_signup, null);

                registerdialog.setCancelable(true);
                registerdialog.setContentView(vista);

                registerdialog.show();
            }
        });
    }
}
