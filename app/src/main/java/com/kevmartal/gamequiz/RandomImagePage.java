package com.kevmartal.gamequiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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

public class RandomImagePage extends AppCompatActivity implements View.OnClickListener {

    private List<Game> games;
    private int puntos = 0, vidas = 3;
    private TextView puntosTxt, vidasTxt;
    private int correctAnswerIndex;
    private List<Button> optionButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_image_page);

        puntosTxt = findViewById(R.id.puntosTxt);
        vidasTxt = findViewById(R.id.vidasTxt);

        // Inicializar y configurar los botones de opción
        initializeOptionButtons();

        // Cargar los juegos desde Firestore al iniciar la actividad
        loadGamesFromFirestore();
    }

    // Método para inicializar y configurar los botones de opción
    private void initializeOptionButtons() {
        optionButtons = new ArrayList<>();
        optionButtons.add(findViewById(R.id.optionButton1));
        optionButtons.add(findViewById(R.id.optionButton2));
        optionButtons.add(findViewById(R.id.optionButton3));
        optionButtons.add(findViewById(R.id.optionButton4));

        // Asignar el mismo listener a todos los botones de opción
        for (Button button : optionButtons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        // Obtener el índice de la opción seleccionada a partir del botón
        int selectedOptionIndex = optionButtons.indexOf((Button) v);
        handleAnswerSelection(selectedOptionIndex);
    }

    private void loadGamesFromFirestore() {
        FirestoreManager firestoreManager = new FirestoreManager();
        firestoreManager.getGames(new FirestoreManager.FirestoreCallback() {
            @Override
            public void onSuccess(List<Game> gameList) {
                games = gameList;
                showNextQuestion();
            }

            @Override
            public void onFailure(Exception e) {
                showMessage("Error al obtener los juegos");
                finish();
            }
        });
    }

    private void showNextQuestion() {
        // Restaurar los colores originales de los botones y habilitarlos
        restoreButtonColors();
        enableOptionButtons();

        if (games != null && !games.isEmpty()) {
            // Seleccionar aleatoriamente 4 juegos para la pregunta actual
            List<Game> selectedGames = getRandomGames(games);
            // Seleccionar aleatoriamente el índice del juego correcto
            correctAnswerIndex = new Random().nextInt(4);

            // Obtener el juego correcto y mostrar su imagen
            Game correctGame = selectedGames.get(correctAnswerIndex);
            loadImage(correctGame.getImageUrl());

            // Asignar el nombre del juego correcto al botón correspondiente
            Button correctButton = getOptionButton(correctAnswerIndex);
            correctButton.setText(correctGame.getName());

            // Asignar los nombres de los otros juegos a los botones restantes
            for (int i = 0; i < 4; i++) {
                if (i != correctAnswerIndex) {
                    Button optionButton = getOptionButton(i);
                    optionButton.setText(selectedGames.get(i).getName());
                }
            }

            // Eliminar el juego mostrado de la lista de juegos disponibles
            games.remove(correctGame);
        } else {
            showMessage("No hay juegos disponibles");
            finish();
        }
    }

    private void handleAnswerSelection(int selectedOptionIndex) {
        Button selectedButton = getOptionButton(selectedOptionIndex);

        if (games != null && !games.isEmpty()) {
            if (selectedOptionIndex == correctAnswerIndex) {
                // Cambiar el color del botón seleccionado a verde si es la respuesta correcta
                selectedButton.setBackgroundColor(getResources().getColor(R.color.verde));
                puntos++;
                puntosTxt.setText(String.valueOf(puntos));
                // Mostrar mensaje de éxito si el jugador ha ganado una vida
                if (puntos % 5 == 0) {
                    showMessage("Has ganado una vida");
                    vidas++;
                    vidasTxt.setText(String.valueOf(vidas));
                }
            } else {
                // Cambiar el color del botón seleccionado a naranja si es una respuesta incorrecta
                selectedButton.setBackgroundColor(getResources().getColor(R.color.naranja));
                // Cambiar el color del botón correcto a verde
                Button correctButton = getOptionButton(correctAnswerIndex);
                correctButton.setBackgroundColor(getResources().getColor(R.color.verde));
                // Restar una vida al jugador
                vidas--;
                vidasTxt.setText(String.valueOf(vidas));
            }
            // Deshabilitar los botones de opción después de seleccionar uno
            disableOptionButtons();
            // Comprobar vidas después de cada respuesta
            if (vidas > 0) {
                if (games.size() >= 4) {
                    // La lista de juegos tiene 4 o más juegos para seguir generando preguntas
                    new Handler().postDelayed(this::showNextQuestion, 1000);
                } else {
                    // No quedan juegos suficientes para generar más preguntas
                    showResultMessage("ganado");
                }
            } else {
                showResultMessage("perdido");
            }
        } else {
            showMessage("NO HAY MÁS JUEGOS DISPONIBLES");
            finish();
        }
    }

    private void showResultMessage(String resultado) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Has "+resultado+"!");
        builder.setMessage("Has obtenido " + puntos + " puntos.");
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    private void disableOptionButtons() {
        findViewById(R.id.optionButton1).setEnabled(false);
        findViewById(R.id.optionButton2).setEnabled(false);
        findViewById(R.id.optionButton3).setEnabled(false);
        findViewById(R.id.optionButton4).setEnabled(false);
    }

    private void restoreButtonColors() {
        findViewById(R.id.optionButton1).setBackgroundColor(getResources().getColor(R.color.vino));
        findViewById(R.id.optionButton2).setBackgroundColor(getResources().getColor(R.color.vino));
        findViewById(R.id.optionButton3).setBackgroundColor(getResources().getColor(R.color.vino));
        findViewById(R.id.optionButton4).setBackgroundColor(getResources().getColor(R.color.vino));
    }

    private void enableOptionButtons() {
        findViewById(R.id.optionButton1).setEnabled(true);
        findViewById(R.id.optionButton2).setEnabled(true);
        findViewById(R.id.optionButton3).setEnabled(true);
        findViewById(R.id.optionButton4).setEnabled(true);
    }

    private void loadImage(String imageUrl) {
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(new ColorDrawable(getResources().getColor(R.color.morado)))
                .error(R.drawable.ic_launcher)
                .into(imageView);
    }

    private List<Game> getRandomGames(List<Game> games) {
        List<Game> randomGames = new ArrayList<>(games);
        if (randomGames.size() >= 4) {
            Collections.shuffle(randomGames);
            return randomGames.subList(0, 4);
        } else {
            // Aquí puedes manejar el caso en el que la lista tenga menos de 4 elementos
            // Por ejemplo, puedes mostrar un mensaje de error o devolver una lista vacía.
            return new ArrayList<>();
        }
    }

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