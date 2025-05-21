package com.example.dfapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dfapplication.Activities.MainActivity;
import com.example.dfapplication.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Checkout_Card_Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Checkout_Card_Details extends Fragment {
    private EditText cardNumber, expiry, cvv;
    private Button btnConfirm;
    private TextView tvCardType;
    private ImageView ivCardLogo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout__card__details, container, false);

        cardNumber = view.findViewById(R.id.etCardNumber);
        expiry = view.findViewById(R.id.etExpiry);
        cvv = view.findViewById(R.id.etCVV);
        btnConfirm = view.findViewById(R.id.btnConfirmCheckout);
        tvCardType = view.findViewById(R.id.tvCardType);
        ivCardLogo = view.findViewById(R.id.ivCardLogo);

        cardNumber.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;

                String str = s.toString().replaceAll("\\s", ""); // remove spaces

                if (str.length() > 19) {
                    str = str.substring(0, 19);
                }

                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < str.length(); i++) {
                    if (i > 0 && i % 4 == 0) formatted.append(" ");
                    formatted.append(str.charAt(i));
                }

                isUpdating = true;
                cardNumber.setText(formatted.toString());
                cardNumber.setSelection(formatted.length());

                String type = getCardType(str);
                tvCardType.setText("Card Type: " + type);
                updateCardIcon(type);

                isUpdating = false;
            }
        });

        expiry.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) return;

                String str = s.toString().replaceAll("[^\\d]", ""); // digits only

                if (str.length() > 4) {
                    str = str.substring(0, 4);
                }

                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < str.length(); i++) {
                    if (i == 2) formatted.append("/");
                    formatted.append(str.charAt(i));
                }

                isUpdating = true;
                expiry.setText(formatted.toString());
                expiry.setSelection(formatted.length());
                isUpdating = false;
            }
        });

        btnConfirm.setOnClickListener(v -> {
            String cardNum = cardNumber.getText().toString().replaceAll("\\s", "").trim();
            String exp = expiry.getText().toString().trim();
            String cvvText = cvv.getText().toString().trim();

            String cardType = getCardType(cardNum);

            if (!cardNum.matches("\\d+")) {
                Toast.makeText(getContext(), "Card number must contain only digits", Toast.LENGTH_SHORT).show();
                return;
            }

            if (cardType.equals("American Express")) {
                if (cardNum.length() != 15) {
                    Toast.makeText(getContext(), "American Express card must be 15 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cvvText.length() != 4 || !cvvText.matches("\\d+")) {
                    Toast.makeText(getContext(), "CVV must be 4 digits for American Express", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                if (cardNum.length() != 16) {
                    Toast.makeText(getContext(), "Card number must be 16 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cvvText.length() != 3 || !cvvText.matches("\\d+")) {
                    Toast.makeText(getContext(), "CVV must be 3 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (!exp.matches("\\d{2}/\\d{2}")) {
                Toast.makeText(getContext(), "Invalid expiry format. Use MM/YY", Toast.LENGTH_SHORT).show();
                return;
            }

            int month = Integer.parseInt(exp.substring(0, 2));
            int year = Integer.parseInt(exp.substring(3, 5)) + 2000;

            if (month < 1 || month > 12) {
                Toast.makeText(getContext(), "Invalid expiry month", Toast.LENGTH_SHORT).show();
                return;
            }

            Calendar now = Calendar.getInstance();
            int currYear = now.get(Calendar.YEAR);
            int currMonth = now.get(Calendar.MONTH) + 1;

            if (year < currYear || (year == currYear && month < currMonth)) {
                Toast.makeText(getContext(), "Card is expired", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "Order placed", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("loadFragment", "home");
            intent.putExtra("message", "Order placed");
            startActivity(intent);
            requireActivity().finish();
        });

        return view;
    }

    private String getCardType(String cardNumber) {
        if (cardNumber.length() < 4) return "Unknown";

        if (cardNumber.startsWith("4026") || cardNumber.startsWith("417500") ||
                cardNumber.startsWith("4508") || cardNumber.startsWith("4844") ||
                cardNumber.startsWith("4913") || cardNumber.startsWith("4917")) {
            return "Visa Electron";
        }
        if (cardNumber.startsWith("4")) return "Visa";
        if (cardNumber.startsWith("5")) return "MasterCard";
        if (cardNumber.startsWith("34") || cardNumber.startsWith("37")) return "American Express";
        if (cardNumber.startsWith("6")) return "Discover";

        return "Unknown";
    }

    private void updateCardIcon(String type) {
        switch (type) {
            case "Visa":
                ivCardLogo.setImageResource(R.drawable.visa);
                break;
            case "Visa Electron":
                ivCardLogo.setImageResource(R.drawable.visa_electron);
                break;
            case "MasterCard":
                ivCardLogo.setImageResource(R.drawable.mastercard);
                break;
            case "American Express":
                ivCardLogo.setImageResource(R.drawable.american);
                break;
            case "Discover":
                ivCardLogo.setImageResource(R.drawable.discover);
                break;
            default:
                ivCardLogo.setImageResource(R.drawable.unknown);
                break;
        }
    }
}
