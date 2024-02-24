package com.kevmartal.gamequiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int puntos = 0, vidas = 3;
    private TextView puntosTxt, vidasTxt;
    private Set<Integer> usedQuestionIndexes = new HashSet<>(); // Conjunto para almacenar los índices de preguntas utilizadas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        puntosTxt = findViewById(R.id.puntosTxt);
        vidasTxt = findViewById(R.id.vidasTxt);

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
                showInfoMessage("Error al obtener las preguntas");
                finish();
            }
        });
    }

    // Método para mostrar la siguiente pregunta disponible
    private void showNextQuestion() {
        // Reactivar los botones y restaurar su color original
        enableOptionButtons();
        restoreButtonColors();

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
            showSuccessMessage("Enhorabuena, te has pasado el juego");
            finish();
        } else {
            // Si no hay preguntas disponibles, finalizar el juego
            showInfoMessage("No hay preguntas disponibles");
            finish();
        }
    }

    // Método para restaurar el color original de los botones de opción
    private void restoreButtonColors() {
        Button optionButton1 = findViewById(R.id.optionButton1);
        Button optionButton2 = findViewById(R.id.optionButton2);
        Button optionButton3 = findViewById(R.id.optionButton3);
        Button optionButton4 = findViewById(R.id.optionButton4);
        optionButton1.setBackgroundColor(getResources().getColor(R.color.vino)); // Restaurar el color
        optionButton2.setBackgroundColor(getResources().getColor(R.color.vino));
        optionButton3.setBackgroundColor(getResources().getColor(R.color.vino));
        optionButton4.setBackgroundColor(getResources().getColor(R.color.vino));
    }

    // Método para activar todos los botones de opción
    private void enableOptionButtons() {
        Button optionButton1 = findViewById(R.id.optionButton1);
        Button optionButton2 = findViewById(R.id.optionButton2);
        Button optionButton3 = findViewById(R.id.optionButton3);
        Button optionButton4 = findViewById(R.id.optionButton4);
        optionButton1.setEnabled(true);
        optionButton2.setEnabled(true);
        optionButton3.setEnabled(true);
        optionButton4.setEnabled(true);
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
        // Obtener el botón seleccionado
        Button selectedButton = getOptionButton(selectedOptionIndex);

        if (questions != null && currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();

            // Cambiar el color del botón seleccionado dependiendo de si es correcto o no
            if (selectedOptionIndex == correctAnswerIndex) {
                selectedButton.setBackgroundColor(getResources().getColor(R.color.verde)); // Cambiar a verde si es correcta
                puntos++;
                puntosTxt.setText(String.valueOf(puntos));
                if (puntos % 5 == 0) {
                    showSuccessMessage("Has ganado una vida");
                    vidas++;
                    vidasTxt.setText(String.valueOf(vidas));
                }
            } else {
                selectedButton.setBackgroundColor(getResources().getColor(R.color.naranja)); // Cambiar a naranja si es incorrecta

                // Encontrar el botón con la respuesta correcta y cambiarle el color
                Button correctButton = getOptionButton(correctAnswerIndex);
                correctButton.setBackgroundColor(getResources().getColor(R.color.verde)); // Cambiar a verde

                // Restar una vida al fallar una pregunta
                vidas--;
                vidasTxt.setText(String.valueOf(vidas));
                if (vidas == 0) {
                    // Si las vidas llegan a cero, mostrar mensaje de pérdida y finalizar el juego
                    showLossMessage();
                }
            }
            // Desactivar los botones después de seleccionar uno
            disableOptionButtons();

            // Mostrar la siguiente pregunta después de un breve retraso
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNextQuestion();
                }
            }, 1000); // Retraso de 1 segundo (1000 milisegundos)
        } else {
            showInfoMessage("SE ACABARON LAS PREGUNTAS");
            finish();
        }
    }

    // Método para mostrar un mensaje de pérdida y finalizar el juego
    private void showLossMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Has perdido!");
        builder.setMessage("Has obtenido " + puntos + " puntos.");

        // Agregar el botón de "Aceptar" para cerrar la actividad
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Cerrar el diálogo
                finish(); // Cerrar la actividad
            }
        });

        // Crear y mostrar el diálogo de alerta
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    // Método para mostrar un mensaje de éxito
    private void showSuccessMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    // Método para mostrar un mensaje de información
    private void showInfoMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    // Método para desactivar todos los botones de opción después de que el usuario haya seleccionado una respuesta
    private void disableOptionButtons() {
        Button optionButton1 = findViewById(R.id.optionButton1);
        Button optionButton2 = findViewById(R.id.optionButton2);
        Button optionButton3 = findViewById(R.id.optionButton3);
        Button optionButton4 = findViewById(R.id.optionButton4);
        optionButton1.setEnabled(false);
        optionButton2.setEnabled(false);
        optionButton3.setEnabled(false);
        optionButton4.setEnabled(false);
    }

    // Método para obtener el botón de opción según el índice proporcionado
    private Button getOptionButton(int optionIndex) {
        switch (optionIndex) {
            case 0:
                return findViewById(R.id.optionButton1);
            case 1:
                return findViewById(R.id.optionButton2);
            case 2:
                return findViewById(R.id.optionButton3);
            case 3:
                return findViewById(R.id.optionButton4);
            default:
                return null;
        }
    }
}
