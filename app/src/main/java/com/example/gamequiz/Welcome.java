package com.example.gamequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void openLogin(View v) {
        Intent intent = new Intent(Welcome.this, Login.class);
        startActivity(intent);
    }

    public void openRegister(View v) {
        Intent intent = new Intent(Welcome.this, Register.class);
        startActivity(intent);
    }

}
