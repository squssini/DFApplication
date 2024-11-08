package com.example.dfapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    private EditText etmaterial;
    private EditText etName;
    private EditText etCategory;
    private EditText etPrice;
    private Button btnAdd;
    private Firebase fbs;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFurnitureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();
    }

    private void connectComponents() {
        etmaterial=getActivity().findViewById(R.id.etmaterial);
        etCategory=getActivity().findViewById(R.id.etCategory);
        etName=getActivity().findViewById(R.id.etName);
        etPrice=getActivity().findViewById(R.id.etPrice);
        fbs= Firebase.getInstance();
        btnAdd=getActivity().findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name , category , material;
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

                // public Furniture(String category, double price, String material, String name) {
                Furniture rest = new Furniture(category, price,material ,name );

                 fbs.getFire().collection("furniture").add(rest).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {
                         Toast.makeText(getActivity(), "Successfully signed up ", Toast.LENGTH_SHORT).show();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                     }
                 });

            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFurnitureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFurnitureFragment newInstance(String param1, String param2) {
        AddFurnitureFragment fragment = new AddFurnitureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_furniture, container, false);
    }
}