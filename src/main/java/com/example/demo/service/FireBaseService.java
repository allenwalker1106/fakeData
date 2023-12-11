package com.example.demo.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;

public interface FireBaseService {
    Firestore getClient();

    Object storeData(String group, String id, Object data);

    Object fetchData(String group, String id);

    DocumentReference getDocument(String group, String id);
}
