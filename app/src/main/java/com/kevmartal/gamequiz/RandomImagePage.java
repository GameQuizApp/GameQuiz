package com.kevmartal.gamequiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomImagePage extends AppCompatActivity {

    private List<Game> games;
    private int puntos = 0, vidas = 3;
    private TextView puntosTxt, vidasTxt;
    private int correctAnswerIndex; // Variable para almacenar el índice de la respuesta correcta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        puntosTxt = findViewById(R.id.puntosTxt);
        vidasTxt = findViewById(R.id.vidasTxt);

        // Cargar los juegos desde Firestore al iniciar la actividad
        loadGamesFromFirestore();

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

    // Método para cargar los juegos desde Firestore
    private void loadGamesFromFirestore() {
        FirestoreManager firestoreManager = new FirestoreManager();
        firestoreManager.getGames(new FirestoreManager.FirestoreCallback() {
            @Override
            public void onSuccess(List<Game> gameList) {
                // Imprimir la lista de juegos obtenida
                Log.d("FirestoreManager", "Juegos obtenidos: " + gameList.toString());

                // Aquí puedes utilizar la lista de juegos
                games = gameList;

                // Mostrar la primera pregunta
                showNextQuestion();
            }

            @Override
            public void onFailure(Exception e) {
                // Manejar el fallo en caso de que la obtención de datos falle
                showInfoMessage("Error al obtener los juegos");
                finish();
            }
        });
    }

    // Método para mostrar la siguiente pregunta disponible
    private void showNextQuestion() {
        if (games != null && !games.isEmpty()) {
            // Seleccionar aleatoriamente 4 juegos para la pregunta actual
            List<Game> selectedGames = getRandomGames(games, 4);

            // Seleccionar aleatoriamente el índice del juego correcto
            correctAnswerIndex = new Random().nextInt(4); // Actualizar el valor de correctAnswerIndex

            // Obtener el juego correcto
            Game correctGame = selectedGames.get(correctAnswerIndex);

            // Mostrar la imagen del juego correcto en el ImageView
            String imageUrl = correctGame.getImageUrl();
            ImageView imageView = findViewById(R.id.imageView);
            Glide.with(this)
                    .load(imageUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(new ColorDrawable(getResources().getColor(R.color.morado)))
                    .error(R.drawable.ic_launcher)
                    .into(imageView);

            // Asignar el nombre del juego correcto al botón con el índice correcto
            Button correctButton = getOptionButton(correctAnswerIndex);
            correctButton.setText(correctGame.getName());

            // Asignar los nombres de los otros juegos a los botones restantes
            for (int i = 0; i < 4; i++) {
                if (i != correctAnswerIndex) {
                    Button optionButton = getOptionButton(i);
                    optionButton.setText(selectedGames.get(i).getName());
                }
            }
        } else {
            // Si no hay juegos disponibles, finalizar el juego
            showInfoMessage("No hay juegos disponibles");
            finish();
        }
    }

    // Método para manejar la selección de una respuesta por parte del usuario
    private void handleAnswerSelection(int selectedOptionIndex) {
        // Obtener el botón seleccionado
        Button selectedButton = getOptionButton(selectedOptionIndex);

        if (games != null && !games.isEmpty()) {
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
            showInfoMessage("SE ACABARON LOS JUEGOS");
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

        // Hacer que el diálogo no sea cancelable
        builder.setCancelable(false);

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
        optionButton1.setBackgroundColor(getResources().getColor(R.color.vino));
        optionButton2.setBackgroundColor(getResources().getColor(R.color.vino));
        optionButton3.setBackgroundColor(getResources().getColor(R.color.vino));
        optionButton4.setBackgroundColor(getResources().getColor(R.color.vino));
    }

    // Método para obtener un conjunto aleatorio de juegos
    private List<Game> getRandomGames(List<Game> games, int count) {
        List<Game> randomGames = new ArrayList<>();
        if (games.size() <= count) {
            randomGames.addAll(games);
        } else {
            Collections.shuffle(games);
            randomGames.addAll(games.subList(0, count));
        }
        return randomGames;
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
