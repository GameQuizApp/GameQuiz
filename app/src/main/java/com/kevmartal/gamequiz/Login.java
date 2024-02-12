package com.kevmartal.gamequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email, passwd;
    Button btnLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.txt_email);
        passwd = findViewById(R.id.txt_passwd);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> clickLogin());
    }

    public void clickLogin() {
        String emailUser = email.getText().toString().trim();
        String passwdUser = passwd.getText().toString().trim();

        if (emailUser.isEmpty() && passwdUser.isEmpty()) {
            String toastTxt = getString(R.string.complete_fields);
            Toast.makeText(this, toastTxt, Toast.LENGTH_SHORT).show();
        } else {
            loginUser(emailUser, passwdUser);
        }
    }

    public void loginUser(String emailUser, String passwdUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passwdUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), Main.class));
                    String toastTxt = getString(R.string.welcome);
                    Toast.makeText(getApplicationContext(), toastTxt, Toast.LENGTH_SHORT).show();
                } else {
                    String toastTxt = getString(R.string.error_login);
                    Toast.makeText(getApplicationContext(), toastTxt, Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String toastTxt = getString(R.string.error_login);
                Toast.makeText(getApplicationContext(), toastTxt, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openRegister(View v) {
        Intent intent= new Intent(Login.this, Signup.class);
        startActivity(intent);
    }

}
