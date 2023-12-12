package com.example.demo.configuration;


import com.example.demo.service.FireBaseService;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Configuration
public class FirebaseConfiguration {
    FirebaseConfiguration() {

        try{
            InputStream resourceStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("firebasekey.json");


            FirebaseOptions options = new FirebaseOptions.Builder()

                    .setCredentials(GoogleCredentials.fromStream(resourceStream))
                    .build();


            FirebaseApp.initializeApp(options);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
