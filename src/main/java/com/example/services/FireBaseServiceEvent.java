package com.example.services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.domain.Event;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FireBaseServiceEvent {

    public String saveEventDetails(Event event) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("events").document(event.getId().toString()).set(event);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Event getEventDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("events").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Event event = null;

        if (document.exists()) {
            event = document.toObject(Event.class);
            return event;
        } else {
            return null;
        }
    }

    public String updateEventDetails(Event event) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("events").document(event.getId().toString()).set(event);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteEvent(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("events").document(name).delete();
        return "Document with ID " + name + " has been deleted";
    }
}