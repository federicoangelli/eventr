package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class FireBaseInitialize {
    FirebaseDatabase db;

    @PostConstruct
    public void initialize()
    {
        try{
            File file = new File(
                    Objects.requireNonNull(getClass().getClassLoader().getResource("key.json")).getFile()
            );

            FileInputStream fis = new FileInputStream(file);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(fis))
                    .setDatabaseUrl("https://eventr-database.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}