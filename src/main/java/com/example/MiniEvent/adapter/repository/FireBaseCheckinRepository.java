package com.example.MiniEvent.adapter.repository;

import com.example.MiniEvent.adapter.web.exception.DataNotFoundException;
import com.example.MiniEvent.model.entity.Checkin;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Repository
@RequiredArgsConstructor
public class FireBaseCheckinRepository implements CheckinRepository{

    private final Firestore firestore;
    private final FirebaseAuth firebaseAuth;

    @Override
    public Checkin save(Checkin checkin) {
        try {
            ApiFuture<WriteResult> query = firestore.collection("checkins")
                    .document(checkin.getId())
                    .set(checkin);
            query.get();
            return checkin;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save checkin: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existsByEventIdAndUserId(String eventId, String userId){
        try {
            ApiFuture<QuerySnapshot> query = firestore.collection("checkins")
                    .whereEqualTo("eventId", eventId)
                    .whereEqualTo("userId", userId)
                    .limit(1)
                    .get();

            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            return !documents.isEmpty();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to query checkins collection", e);
        }
    }

    @Override
    public void deleteByUserId(String userId) {
        try {
            ApiFuture<QuerySnapshot> query = firestore.collection("checkins")
                    .whereEqualTo("userId", userId)
                    .get();

            WriteBatch batch = firestore.batch();
            List<QueryDocumentSnapshot> documents = query.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                batch.delete(document.getReference());
            }

            batch.commit().get();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to delete events create by user with userId: %s", userId), e);
        }
    }

    @Override
    public Optional<Checkin> findByEventIdAndUserId(String eventId, String userId) {
        try {
            Query query = firestore.collection("checkins")
                    .whereEqualTo("userId", userId)
                    .whereEqualTo("eventId", eventId);

            ApiFuture<QuerySnapshot> future = query.get();
            List<Checkin> checkins = future.get().toObjects(Checkin.class);
            if (checkins.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(checkins.getFirst());

        } catch (Exception e) {
            throw new DataNotFoundException("Failed to find checkin with userId and eventId: " + e.getMessage(), HttpStatus.NOT_FOUND, e);
        }
    }
}
