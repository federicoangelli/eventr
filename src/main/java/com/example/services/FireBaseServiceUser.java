package com.example.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.demo.FireBaseInitialize;
import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
//import sun.rmi.runtime.Log;

@Service
public class FireBaseServiceUser {

    private final FireBaseInitialize fireBaseInitialize;

    public FireBaseServiceUser(FireBaseInitialize f) {
        this.fireBaseInitialize = f;
    }

    private FirebaseDatabase dbFirebase = FirebaseDatabase.getInstance();
    private DatabaseReference dbReference = dbFirebase.getReference();

    public User saveUserDetails(User user) throws InterruptedException, ExecutionException {
        DatabaseReference usersRef = dbReference.child("users");
        usersRef.child(user.getId().toString()).setValueAsync(user);

        System.out.println("User succesfully saved!");

        return user;
    }

    public User getAllUserDetails() throws InterruptedException, ExecutionException {

        dbReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot s : dataSnapshot.getChildren())
                    {
                        User user = s.getValue(User.class);
                        System.out.println(
                                "id: "+user.getId() +
                                " || firstName: " + user.getFirstName() +
                                " || lastName: " + user.getLastName() +
                                " || email: " + user.getEmail() +
                                " || phoneNumber: " + user.getPhoneNumber() +
                                " || address: " + user.getAddress());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;
    }

    public User getOneUserDetails(Long UserId) throws InterruptedException, ExecutionException {
        dbReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot s : dataSnapshot.getChildren()) {
                        User user = s.getValue(User.class);
                        if (user.getId().equals(UserId))
                            System.out.println(
                                    "id: "+user.getId() +
                                            " || firstName: " + user.getFirstName() +
                                            " || lastName: " + user.getLastName() +
                                            " || email: " + user.getEmail() +
                                            " || phoneNumber: " + user.getPhoneNumber() +
                                            " || address: " + user.getAddress());
                    }
                }

                //GET KEY
//                        String key = dbReference.child("posts").push().getKey();
//                        System.out.println(key + "    yas");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;
    }

    public void updateUserDetails(User user, Long id) throws InterruptedException, ExecutionException {
        DatabaseReference usersRef = dbReference.child("users");
        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put(id.toString(), user);

        usersRef.updateChildrenAsync(userUpdates);

        System.out.println("User succesfully updated!");
    }

    public void deleteUser(Long id) {
        FirebaseDatabase.getInstance().getReference().child("users").child(id.toString()).removeValue(null);
        System.out.println("User with id: " + id.toString() + "was deleted!");
    }
}