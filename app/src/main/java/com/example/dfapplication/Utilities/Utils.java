package com.example.dfapplication.Utilities;


import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.dfapplication.Classes.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Utils {

    private static Utils instance;

    private Firebase fbs;
    private String imageStr;
    private Bundle seeDetailsBundle;

    public Utils()
    {
        fbs = Firebase.getInstance();
    }

    public static Utils getInstance()
    {
        if (instance == null)
            instance = new Utils();

        return instance;
    }
    public void showMessageDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(message);
        //builder.setMessage(message);

        // Add a button to dismiss the dialog box
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You can perform additional actions here if needed
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void uploadImage(Context context, Uri selectedImageUri, OnImageUploadedListener listener) {
        if (selectedImageUri != null) {
            imageStr = "images/" + UUID.randomUUID() + ".jpg";
            StorageReference imageRef = fbs.getStorage().getReference().child(imageStr);

            UploadTask uploadTask = imageRef.putFile(selectedImageUri);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    fbs.setSelectedImageURL(uri);
                    if (listener != null) listener.onImageUploaded(uri);
                }).addOnFailureListener(e -> Log.e("Utils", e.getMessage()));
                Toast.makeText(context, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(context, "Please choose an image first", Toast.LENGTH_SHORT).show();
        }
    }


    public interface OnImageUploadedListener {
        void onImageUploaded(Uri uri);
    }

    public void AddSeeDetailsBundlestring(String key, String message) {
        this.seeDetailsBundle.putString(key,message);
    }
}