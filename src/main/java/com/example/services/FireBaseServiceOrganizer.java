package com.example.services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.domain.Organizer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FireBaseServiceOrganizer {
    public String saveOrganizerDetails(Organizer organizer) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("organizers").document(organizer.getId().toString()).set(organizer);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Organizer getOrganizerDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("organizers").document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Organizer organizer = null;

        if (document.exists()) {
            organizer = document.toObject(Organizer.class);
            return organizer;
        } else {
            return null;
        }
    }

    public String updateOrganizerDetails(Organizer organizer) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("organizers").document(organizer.getId().toString()).set(organizer);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteOrganizer(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("organizers").document(name).delete();
        return "Document with ID " + name + " has been deleted";
    }
}
