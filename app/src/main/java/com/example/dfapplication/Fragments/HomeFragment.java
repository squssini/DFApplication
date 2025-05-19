package com.example.dfapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dfapplication.Activities.AddFurnitureActivity;
import com.example.dfapplication.R;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private MaterialButton btnAddNewProduct, btnAllProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnAddNewProduct = view.findViewById(R.id.btnAddNewProduct);
        btnAllProducts = view.findViewById(R.id.btnAllProducts);

        btnAddNewProduct.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddFurnitureActivity.class);
            startActivity(intent);
        });

        btnAllProducts.setOnClickListener(v -> {
            FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFragmentContainer, new AllFurnitureFragment());
            ft.addToBackStack(null);
            ft.commit();
        });

        return view;
    }
}
