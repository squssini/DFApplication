package com.example.dfapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dfapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Checkout_Card_Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Checkout_Card_Details extends Fragment {
    private EditText cardNumber, expiry, cvv;
    private Button btnConfirm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout__card__details, container, false);

        cardNumber = view.findViewById(R.id.etCardNumber);
        expiry = view.findViewById(R.id.etExpiry);
        cvv = view.findViewById(R.id.etCVV);
        btnConfirm = view.findViewById(R.id.btnConfirmCheckout);

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Checkout Complete!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
