package com.kevmartal.gamequiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private Set<Integer> usedQuestionIndexes = new HashSet<>(); // Conjunto para almacenar los índices de preguntas utilizadas

    // Método para cargar las preguntas desde Firestore
    private void loadQuestionsFromFirestore() {
        FirestoreManager firestoreManager = new FirestoreManager();
        firestoreManager.getQuestions(new FirestoreManager.FirestoreCallback() {
            @Override
            public void onSuccess(List<Question> questionList) {
                // Aquí puedes utilizar la lista de preguntas
                questions = questionList;

                // Barajar aleatoriamente la lista de preguntas
                Collections.shuffle(questions);

                // Mostrar la primera pregunta
                showNextQuestion();
            }

            @Override
            public void onFailure(Exception e) {
                // Manejar el fallo en caso de que la obtención de datos falle
                Toast.makeText(GameActivity.this, "Error al obtener las preguntas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para mostrar la siguiente pregunta disponible
    private void showNextQuestion() {
        if (questions != null && !questions.isEmpty()) {
            // Buscar la siguiente pregunta no utilizada
            for (int i = 0; i < questions.size(); i++) {
                if (!usedQuestionIndexes.contains(i)) {
                    currentQuestionIndex = i;
                    showQuestion(questions.get(currentQuestionIndex));
                    usedQuestionIndexes.add(i); // Agregar el índice de la pregunta utilizada al conjunto
                    return;
                }
            }
            // Si se han mostrado todas las preguntas, finalizar el juego
            Toast.makeText(GameActivity.this, "Se han mostrado todas las preguntas", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Si no hay preguntas disponibles, finalizar el juego
            Toast.makeText(GameActivity.this, "No hay preguntas disponibles", Toast.LENGTH_SHORT).show();
            finish();
        }
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
                .centerCrop() // Centrar la imagen y recortar los bordes
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Almacenar la imagen en caché para que tarde menos en ser cargada a partir del primer intento
                .placeholder((new ColorDrawable(this.getResources().getColor(R.color.morado)))) // Añadir un color donde vaya la imagen para manejar lo que pasa en ese espacio hasta que la imagen es cargada
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
            } else {
                // Respuesta incorrecta: Disminuye las vidas o realiza otras acciones
                Toast.makeText(this, "Respuesta INCORRECTA", Toast.LENGTH_SHORT).show();
            }
            // Muestra la siguiente pregunta
            showNextQuestion();
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
