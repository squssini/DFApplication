package com.example.dfapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dfapplication.Activities.CartActivity;
import com.example.dfapplication.Classes.CartManager;
import com.example.dfapplication.Classes.Firebase;
import com.example.dfapplication.Classes.Furniture;
import com.example.dfapplication.Adapters.MyAdapter;
import com.example.dfapplication.R;
import com.example.dfapplication.Utilities.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFurnitureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFurnitureFragment extends Fragment {
    private Firebase fbs;
    private ArrayList<Furniture> Furs, filteredList;
    private RecyclerView rvFurs;
    private MyAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllFurnitureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllFurnitureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllFurnitureFragment newInstance(String param1, String param2) {
        AllFurnitureFragment fragment = new AllFurnitureFragment();
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
        return inflater.inflate(R.layout.fragment_all_furniture, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        fbs = Firebase.getInstance();
        Furs = new ArrayList<>();
        rvFurs = getView().findViewById(R.id.rvFurnitureFurFragment);

        adapter = new MyAdapter(getActivity(), Furs);
        rvFurs.setAdapter(adapter);
        rvFurs.setHasFixedSize(true);
        rvFurs.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set the Add to Cart click listener here
        adapter.setOnCartClickListener(furniture -> {
            CartManager.getInstance().addToCart(furniture); // ✅ correct way
            Toast.makeText(getActivity(), furniture.getName() + " added to cart", Toast.LENGTH_SHORT).show();

            // Start CartActivity to show cart
            Intent intent = new Intent(getActivity(), CartActivity.class);
            startActivity(intent);
        });

        fbs.getFire().collection("furniture").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot : queryDocumentSnapshots.getDocuments()) {
                    Furniture Fur = dataSnapshot.toObject(Furniture.class);
                    Furs.add(Fur);
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
            Log.e("AllFurnitureFragment", e.getMessage());
        });
    }






    }
