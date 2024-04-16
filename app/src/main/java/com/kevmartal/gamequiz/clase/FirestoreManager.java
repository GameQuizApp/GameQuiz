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
    private CollectionReference gamesRef;

    public FirestoreManager() {
        db = FirebaseFirestore.getInstance();
        gamesRef = db.collection("games");
    }

    public interface FirestoreCallback {
        void onSuccess(List<Game> games);
        void onFailure(Exception e);
    }

    public void getGames(final FirestoreCallback callback) {
        gamesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Game> games = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    String name = document.getString("name");
                    String imageUrl = document.getString("imageUrl");
                    Game game = new Game(name, imageUrl);
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
}
