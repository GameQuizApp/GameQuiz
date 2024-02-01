package com.kevmartal.gamequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Signup extends AppCompatActivity {

    private EditText txtName, txtEmail, txtPassword;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        txtName = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        Button btnSignup = findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(v -> clickSignup());
    }

    protected void clickSignup() {
        String nameUser = txtName.getText().toString().trim();
        String emailUser = txtEmail.getText().toString().trim();
        String passwordUser = txtPassword.getText().toString().trim();

        if (nameUser.isEmpty() && emailUser.isEmpty() && passwordUser.isEmpty())
            Toast.makeText(Signup.this, "Los campos no pueden estar vacíos", Toast.LENGTH_LONG).show();
        else
            signupUser(nameUser, emailUser, passwordUser);
    }

    protected void signupUser(String nameUser, String emailUser, String passwordUser) {
        mAuth.createUserWithEmailAndPassword(emailUser, passwordUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", nameUser);
                map.put("email", emailUser);
                map.put("password", passwordUser);

                mFirestore.collection("user").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // El código que se ejecuta cuando la tarea se completa con éxito
                            finish();
                            startActivity(new Intent(Signup.this, Main.class));
                            Toast.makeText(Signup.this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, "Error al completar registro", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, "Error en los datos del registro", Toast.LENGTH_LONG).show();
            }
        });
    }

}