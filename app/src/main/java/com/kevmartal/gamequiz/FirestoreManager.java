package com.kevmartal.gamequiz;

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
    private CollectionReference questionsRef;

    public FirestoreManager() {
        db = FirebaseFirestore.getInstance();
        questionsRef = db.collection("questions");
    }

    public interface FirestoreCallback {
        void onSuccess(List<Question> questions);
        void onFailure(Exception e);
    }

    public void getQuestions(final FirestoreCallback callback) {
        questionsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Question> questions = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    String imageUrl = document.getString("imageURL");
                    List<String> options = (List<String>) document.get("options");
                    long correctAnswerIndex = (long) document.get("correctAnswerIndex");
                    Question question = new Question(imageUrl, options, (int) correctAnswerIndex);
                    questions.add(question);
                }
                callback.onSuccess(questions);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }
}
