package com.kevmartal.gamequiz.clase;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirestoreManager {

    private FirebaseFirestore db;
    private CollectionReference gamesRef, charactersRef;

    public FirestoreManager() {
        db = FirebaseFirestore.getInstance();
        gamesRef = db.collection("games");
        charactersRef = db.collection("characters");
    }

    public interface FirestoreCallbackGames {
        void onSuccess(List<Game> games);
        void onFailure(Exception e);
    }

    public interface FirestoreCallbackCharacters {
        void onSuccess(List<Character> characters);
        void onFailure(Exception e);
    }

    public void getGames(final FirestoreCallbackGames callback) {
        gamesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Game> games = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    String name = document.getString("name");
                    String imageUrl = document.getString("imageUrl");
                    List<String> categories = (List<String>) document.get("categories");
                    Game game = new Game(name, imageUrl, categories);
                    games.add(game);
                }
                callback.onSuccess(games);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void getCharacters(final FirestoreCallbackCharacters callback) {
        charactersRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Character> characters = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    String name = document.getString("name");
                    String imageUrl = document.getString("imageUrl");
                    Character character = new Character(name, imageUrl);
                    characters.add(character);
                }
                callback.onSuccess(characters);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }
}
