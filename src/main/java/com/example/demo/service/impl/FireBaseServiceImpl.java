package com.example.demo.service.impl;

import com.example.demo.dto.DataDTO;
import com.example.demo.service.FireBaseService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FireBaseServiceImpl implements FireBaseService {
    @Override
    public Firestore getClient() {
        return FirestoreClient.getFirestore();
    }

    @Override
    public Object storeData(String group, String id, Object data) {
        DocumentReference documentRef = this.getDocument(group,id);
        if(Objects.nonNull(data)){
            DataDTO dataObject = new DataDTO(group,id,data);
            ApiFuture<WriteResult> result = documentRef.set(dataObject);
            return dataObject;
        }
        return "Fail To Push Data";
    }

    @Override
    public Object fetchData(String group, String id) {
        DocumentReference documentRef = this.getDocument(group,id);
        Object data = null;
        if(Objects.nonNull(documentRef)){

            ApiFuture<DocumentSnapshot> documentSnapShot = documentRef.get();
            while(!documentSnapShot.isDone()){

            }
            try{
                DocumentSnapshot documentData = documentSnapShot.get();
                Map<String, Object> dataMap = documentData.getData();
                if(Objects.nonNull(dataMap) && dataMap.containsKey("data")){
                    data = dataMap.get("data");
                }
            }catch (Exception e){

            }
            return data;
        }
        return "Fail To Fetch Data";
    }

    @Override
    public DocumentReference getDocument(String group, String id) {
        Firestore client = this.getClient();
        String documentKey = generateDocumentKey(group,id);
        return client.collection("fake-data").document(documentKey);
    }

    private String generateDocumentKey(String group, String id){
        String key = "undefined";
        if(Objects.nonNull(group) && Objects.nonNull(id)){
            key = group.concat("_").concat(id);
        }
        return key;
    }
}
