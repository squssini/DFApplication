package com.example.dfapplication;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

public class AddFurnitureActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    private EditText etmaterial;
    private EditText etName;
    private EditText etCategory;
    private EditText etPrice;
    private Button btnAdd;
    private Firebase fbs;
    private ImageView img;
    private Utils utils;
    private TextView InsertImge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_furniture); // Consider renaming the layout to activity_add_furniture.xml

        connectComponents();
    }

    private void connectComponents() {
        etmaterial = findViewById(R.id.etMaterial);
        etCategory = findViewById(R.id.etCategory);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        fbs = Firebase.getInstance();
        utils = Utils.getInstance();
        img = findViewById(R.id.ivFurnitureAddFurnitureFragment);
        InsertImge = findViewById(R.id.InsertImage);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, category, material, price;
                name = etName.getText().toString();
                category = etCategory.getText().toString();
                price = etPrice.getText().toString();
                material = etmaterial.getText().toString();

                if (name.trim().isEmpty() || category.trim().isEmpty() || price.trim().isEmpty() || material.trim().isEmpty()) {
                    Toast.makeText(AddFurnitureActivity.this, "Some fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Furniture furniture;
                if (fbs.getSelectedImageURL() == null) {
                    furniture = new Furniture(name, "", "", "", "", material, price, category, "");
                } else {
                    furniture = new Furniture(name, "", "", "", "", material, price, category, fbs.getSelectedImageURL().toString());
                }

                fbs.getFire().collection("furniture").add(furniture)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddFurnitureActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                                gotoAllFurnitureActivity();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddFurnitureActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        InsertImge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
                Uri selectedImageUri = data.getData();
                img.setImageURI(selectedImageUri);
                utils.uploadImage(selectedImageUri);
            }
        } catch (Exception ex) {
            Log.e("SHAHED1", ex.getMessage());
        }
    }

    private void gotoAllFurnitureActivity() {
        Intent intent = new Intent(this, AllFurnitureFragment.class);
        startActivity(intent);
        finish(); // Optional: Finish current activity to prevent going back
    }
}
