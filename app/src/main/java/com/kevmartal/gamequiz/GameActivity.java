package com.kevmartal.gamequiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentQuestionIndex = 0;

    // Método para cargar las preguntas desde Firestore
    private void loadQuestionsFromFirestore() {
        FirestoreManager firestoreManager = new FirestoreManager();
        firestoreManager.getQuestions(new FirestoreManager.FirestoreCallback() {
            @Override
            public void onSuccess(List<Question> questionList) {
                // Aquí puedes utilizar la lista de preguntas
                questions = questionList;

                // Elegir una pregunta aleatoria
                Random random = new Random();
                currentQuestionIndex = random.nextInt(questions.size());

                // Mostrar la pregunta aleatoria
                showQuestion(questions.get(currentQuestionIndex));
            }

            @Override
            public void onFailure(Exception e) {
                // Manejar el fallo en caso de que la obtención de datos falle
                Toast.makeText(GameActivity.this, "Error al obtener las preguntas", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Método para mostrar una pregunta en la interfaz de usuario
    private void showQuestion(Question question) {
        // Aquí puedes mostrar la imagen y las opciones de respuesta en tu interfaz de usuario
        String imageUrl = question.getImageUrl();
        List<String> options = question.getOptions();

        // Por ejemplo, puedes cargar la imagen con Glide
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load(imageUrl)
                .error(R.drawable.ic_launcher) // R.drawable.error_image es la imagen que se mostrará en caso de error
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("Glide", "Error loading image", e);
                        return false; // Return false to allow Glide to handle the error drawable
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false; // Return false to allow Glide to handle the resource drawable
                    }
                })
                .into(imageView);

        // Luego, puedes mostrar las opciones de respuesta en los botones
        Button optionButton1 = findViewById(R.id.optionButton1);
        optionButton1.setText(options.get(0));
        // Repite este proceso para cada opción de respuesta y su respectivo botón
        Button optionButton2 = findViewById(R.id.optionButton2);
        optionButton2.setText(options.get(1));
        Button optionButton3 = findViewById(R.id.optionButton3);
        optionButton3.setText(options.get(2));
        Button optionButton4 = findViewById(R.id.optionButton4);
        optionButton4.setText(options.get(3));
    }


    // Método para manejar la selección de una respuesta por parte del usuario
    private void handleAnswerSelection(int selectedOptionIndex) {
        if (questions != null && currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();
            if (selectedOptionIndex == correctAnswerIndex) {
                // Respuesta correcta: Incrementa el puntaje
                Toast.makeText(this, "Respuesta CORRECTA", Toast.LENGTH_SHORT).show();
                // Muestra la siguiente pregunta si hay más, de lo contrario, finaliza el juego
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion(questions.get(currentQuestionIndex));
                } else {
                    // El juego ha terminado
                    // Puedes mostrar un mensaje de fin de juego o realizar otras acciones
                }
            } else {
                // Respuesta incorrecta: Disminuye las vidas o realiza otras acciones
                Toast.makeText(this, "Respuesta INCORRECTA", Toast.LENGTH_SHORT).show();
            }
        } else {
            // No hay más preguntas disponibles o la lista de preguntas es nula
            // Maneja este caso según la lógica de tu juego
            Toast.makeText(this, "SE ACABARON LAS PREGUNTAS", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Cargar las preguntas desde Firestore al iniciar la actividad
        loadQuestionsFromFirestore();

        // Asignar un listener a cada botón de respuesta
        Button optionButton1 = findViewById(R.id.optionButton1);
        optionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerSelection(0); // Opción 1 seleccionada
            }
        });

        Button optionButton2 = findViewById(R.id.optionButton2);
        optionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerSelection(1); // Opción 2 seleccionada
            }
        });

        Button optionButton3 = findViewById(R.id.optionButton3);
        optionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerSelection(2); // Opción 3 seleccionada
            }
        });

        Button optionButton4 = findViewById(R.id.optionButton4);
        optionButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerSelection(3); // Opción 4 seleccionada
            }
        });
    }
}
