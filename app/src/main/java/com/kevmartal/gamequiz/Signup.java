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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Signup extends AppCompatActivity {

    private EditText name, email, passwd, passwdRepeat;
    private Button btnSignup;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.txt_name);
        email = findViewById(R.id.txt_email);
        passwd = findViewById(R.id.txt_passwd);
        passwdRepeat = findViewById(R.id.txt_passwd_repeat);
        btnSignup = findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(v -> clickSignup());
    }

    public void clickSignup() {
        String nameUser = name.getText().toString().trim();
        String emailUser = email.getText().toString().trim();
        String passwdUser = passwd.getText().toString().trim();

        if (nameUser.isEmpty() && emailUser.isEmpty() && passwdUser.isEmpty()) {
            String toastTxt = getString(R.string.complete_signup);
            Toast.makeText(this, toastTxt, Toast.LENGTH_SHORT).show();
        } else {
            if (passwdUser.equals(passwdRepeat.getText().toString().trim()))
                registerUser(nameUser, emailUser, passwdUser);
            else {
                String toastTxt = getString(R.string.error_passwd);
                Toast.makeText(this, toastTxt, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void registerUser(String nameUser, String emailUser, String passwdUser) {
        mAuth.createUserWithEmailAndPassword(emailUser, passwdUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("name", nameUser);
                map.put("email", emailUser);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), Main.class));
                        String toastTxt = getString(R.string.welcome);
                        Toast.makeText(getApplicationContext(), toastTxt, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String toastTxt = getString(R.string.error_signup);
                        Toast.makeText(getApplicationContext(), toastTxt, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String toastTxt = getString(R.string.error_signup);
                Toast.makeText(getApplicationContext(), toastTxt, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openLogin(View v) {
        Intent intent= new Intent(Signup.this, Login.class);
        startActivity(intent);
    }

}
