package com.example.dfapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class Firebase {
    private static Firebase instance;
    private FirebaseAuth auth;
    private FirebaseFirestore fire;
    private FirebaseStorage storage;

    public Firebase(){
        auth= FirebaseAuth.getInstance();
        fire= FirebaseFirestore.getInstance();
        storage= FirebaseStorage.getInstance();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFire() {
        return fire;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    public static Firebase getInstance(){
        if (instance == null){
            instance= new Firebase();
        }
        return instance;
    }
}
