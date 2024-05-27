package com.kevmartal.gamequiz.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

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
import com.kevmartal.gamequiz.R;
import com.kevmartal.gamequiz.clase.Character;
import com.kevmartal.gamequiz.clase.FirestoreManager;
import com.kevmartal.gamequiz.clase.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Game> games;
    private List<Character> characters;
    private int puntos = 0, vidas = 3;
    private TextView puntosTxt, vidasTxt;
    private int correctAnswerIndex;
    private List<Button> optionButtons;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        puntosTxt = findViewById(R.id.puntosTxt);
        vidasTxt = findViewById(R.id.vidasTxt);

        // Establecer los valores iniciales de puntos y vidas
        puntosTxt.setText("0");
        category = getIntent().getStringExtra("category");
        if (category == null || !category.equals("inf")) {
            vidasTxt.setText("3");
        } else {
            vidasTxt.setText("∞");
        }

        // Inicializar y configurar los botones de opción
        initializeOptionButtons();

        // Cargar los contenidos desde Firestore al iniciar la actividad
        loadContentFromFirestore();
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
        handleContentSelection(selectedOptionIndex);
    }

    private void loadContentFromFirestore() {
        FirestoreManager firestoreManager = new FirestoreManager();
        if (category == null || !category.equals("characters")) {
            // Cargar juegos
            firestoreManager.getGames(new FirestoreManager.FirestoreCallbackGames() {
                @Override
                public void onSuccess(List<Game> gameList) {
                    if (category != null && category.equals("action")) {
                        // Filtrar los juegos por la categoría "action"
                        games = filterGamesByCategory(gameList, "action");
                    } else if (category != null && category.equals("rockstar_games")) {
                        // Filtrar los juegos por la categoría "rockstar_games"
                        games = filterGamesByCategory(gameList, "rockstar_games");
                    } else if (category != null && category.equals("terror")) {
                        // Filtrar los juegos por la categoría "terror"
                        games = filterGamesByCategory(gameList, "terror");
                    } else if (category != null && category.equals("zombies")) {
                        // Filtrar los juegos por la categoría "zombies"
                        games = filterGamesByCategory(gameList, "zombies");
                    } else {
                        // Usar todos los juegos si no se especifica una categoría existente
                        games = gameList;
                    }

                    if (!games.isEmpty()) {
                        showNextContent();
                    } else {
                        showMessage("No hay juegos disponibles");
                        finish();
                    }
                }
                @Override
                public void onFailure(Exception e) {
                    showMessage("Error al obtener los juegos");
                    finish();
                }
            });
        } else {
            // Cargar personajes
            firestoreManager.getCharacters(new FirestoreManager.FirestoreCallbackCharacters() {
                @Override
                public void onSuccess(List<Character> characterList) {
                    characters = characterList;

                    if (!characters.isEmpty()) {
                        showNextContent();
                    } else {
                        showMessage("No hay personajes disponibles");
                        finish();
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    showMessage("Error al obtener los personajes");
                    finish();
                }
            });
        }
    }

    private List<Game> filterGamesByCategory(List<Game> games, String category) {
        List<Game> filteredGames = new ArrayList<>();
        for (Game game : games) {
            if (game.getCategories() != null && game.getCategories().contains(category)) {
                filteredGames.add(game);
            }
        }
        return filteredGames;
    }

    private void showNextContent() {
        // Restaurar los colores originales de los botones y habilitarlos
        restoreButtonColors();
        enableOptionButtons();

        if (category != null && category.equals("characters")) {
            if (characters != null && !characters.isEmpty()) {
                // Seleccionar aleatoriamente 4 personajes para la pregunta actual
                List<Character> selectedCharacters = getRandomCharacters(characters);
                // Seleccionar aleatoriamente el índice del personaje correcto
                correctAnswerIndex = new Random().nextInt(4);

                // Obtener el personaje correcto y mostrar su imagen
                Character correctCharacter = selectedCharacters.get(correctAnswerIndex);
                loadImage(correctCharacter.getImageUrl());

                // Asignar el nombre del personaje correcto al botón correspondiente
                Button correctButton = getOptionButton(correctAnswerIndex);
                correctButton.setText(correctCharacter.getName());

                // Asignar los nombres de los otros personajes a los botones restantes
                for (int i = 0; i < 4; i++) {
                    if (i != correctAnswerIndex) {
                        Button optionButton = getOptionButton(i);
                        optionButton.setText(selectedCharacters.get(i).getName());
                    }
                }

                // Eliminar el personaje mostrado de la lista de personajes disponibles
                characters.remove(correctCharacter);
            } else {
                showMessage("No hay personajes disponibles");
                finish();
            }
        } else {
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
    }

    private void handleContentSelection(int selectedOptionIndex) {
        Button selectedButton = getOptionButton(selectedOptionIndex);

        if ((games != null && !games.isEmpty()) || (characters != null && !characters.isEmpty())) {
            if (selectedOptionIndex == correctAnswerIndex) {
                // Cambiar el fondo del botón seleccionado a verde si es la respuesta correcta
                selectedButton.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.verde));
                puntos++;
                puntosTxt.setText(String.valueOf(puntos));
                // Mostrar mensaje de éxito si el jugador ha ganado una vida, solo si la categoría no es "inf" o es nula
                if (category == null || !category.equals("inf")) {
                if (puntos % 5 == 0) {
                    showMessage("Has ganado una vida");
                    vidas++;
                    vidasTxt.setText(String.valueOf(vidas));
                }
                }
            } else {
                // Cambiar el fondo del botón seleccionado a naranja si es una respuesta incorrecta
                selectedButton.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.naranja_fuerte));
                // Cambiar el fondo del botón correcto a verde
                Button correctButton = getOptionButton(correctAnswerIndex);
                correctButton.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.verde_amarillo));
                // Manejo de vidas
                if (category == null || !category.equals("inf")) {
                    vidas--;
                    vidasTxt.setText(String.valueOf(vidas));
                }
            }
            // Deshabilitar los botones de opción después de seleccionar uno
            disableOptionButtons();
            // Comprobar vidas después de cada respuesta
            if (vidas > 0) {
                if (category != null && category.equals("characters")) {
                    // Verificar si hay suficientes personajes para generar más preguntas
                    if (characters != null && characters.size() >= 4) {
                        // La lista de personajes tiene 4 o más personajes para seguir generando preguntas
                        new Handler().postDelayed(this::showNextContent, 1300);
                    } else {
                        // No hay suficientes personajes, mostrar mensaje y finalizar actividad
                        showResultMessage("ganado");
                    }
                } else {
                    // Verificar si hay suficientes juegos para generar más preguntas
                    if (games != null && games.size() >= 4) {
                        // La lista de juegos tiene 4 o más juegos para seguir generando preguntas
                        new Handler().postDelayed(this::showNextContent, 1300);
                    } else {
                        // No hay suficientes juegos, mostrar mensaje y finalizar actividad
                        showResultMessage("ganado");
                    }
                }
            } else {
                showResultMessage("perdido");
            }
        } else {
            showMessage("ERROR EN LA CARGA DE DATOS");
            finish();
        }
    }

    private void showResultMessage(String resultado) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Has " + resultado + "!");
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
        for (Button button : optionButtons) {
            button.setEnabled(false);
        }
    }

    private void restoreButtonColors() {
        for (Button button : optionButtons) {
            button.setBackgroundTintList(AppCompatResources.getColorStateList(this, R.color.vino));
        }
    }

    private void enableOptionButtons() {
        for (Button button : optionButtons) {
            button.setEnabled(true);
        }
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
        Collections.shuffle(randomGames);
        return randomGames.subList(0, Math.min(4, randomGames.size()));
    }

    private List<Character> getRandomCharacters(List<Character> characters) {
        List<Character> randomCharacters = new ArrayList<>(characters);
        Collections.shuffle(randomCharacters);
        return randomCharacters.subList(0, Math.min(4, randomCharacters.size()));
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
