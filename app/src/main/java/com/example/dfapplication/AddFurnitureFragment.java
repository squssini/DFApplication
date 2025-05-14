package com.example.dfapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFurnitureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFurnitureFragment extends Fragment {

    private static final int GALLERY_REQUEST_CODE = 123;
    private EditText etmaterial;
    private EditText etName;
    private EditText etCategory;
    private EditText etPrice;
    private Button btnAdd;
    private Firebase fbs;
    ImageView img;
    private Utils utils;
    private TextView InsertImge;

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {
        etmaterial=getActivity().findViewById(R.id.etMaterial);
        etCategory=getActivity().findViewById(R.id.etCategory);
        etName=getActivity().findViewById(R.id.etName);
        etPrice=getActivity().findViewById(R.id.etPrice);
        fbs= Firebase.getInstance();
        utils = Utils.getInstance();
        img = getView().findViewById(R.id.ivFurnitureAddFurnitureFragment);
        InsertImge=getView().findViewById(R.id.InsertImage);
        btnAdd=getActivity().findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name , category , material, phoneNum,color;
                String price;
                name = etName.getText().toString();
                category= etCategory.getText().toString();
                price=etPrice.getText().toString();
                material = etmaterial.getText().toString();


                if (name.trim().isEmpty() || category.trim().isEmpty() || price.trim().isEmpty() ||material.trim().isEmpty() )
                {
                    Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                //    public Furniture(String name, String phoneNum, String color, String numOfFur, String owner, String material, String price, String category, String photo) {
                Furniture furniture;
                if (fbs.getSelectedImageURL() == null)
                    furniture = new Furniture( name,  "",  "",  "", "", material, price,  category, "" );
                else
                    furniture = new Furniture( name,  "",  "",  "", "", material, price,  category,
                fbs.getSelectedImageURL().toString() );

                 fbs.getFire().collection("furniture").add(furniture).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {
                         Toast.makeText(getActivity(), "Successfully Added ", Toast.LENGTH_SHORT).show();
                         gotoAllFurnitureFragment();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
                Uri selectedImageUri = data.getData();
                img.setImageURI(selectedImageUri);
                utils.uploadImage(selectedImageUri);
            }
        }
        catch(Exception ex) {
            Log.e("SHAHED1", ex.getMessage());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_furniture, container, false);
    }
    private void gotoAllFurnitureFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main, new AllFurnitureFragment());
        ft.commit();
    }
}