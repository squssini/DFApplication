package com.example.dfapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnCheckout;
    private TextView totalPriceText;
    private List<CartModel> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_cart);
        btnCheckout = findViewById(R.id.btn_checkout);
        totalPriceText = findViewById(R.id.total_price);

        cartList = loadFakeCartItems(); // or fetch from Firebase
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyCartAdapter(this, cartList));

        float total = 0;
        for (CartModel item : cartList) {
            total += Float.parseFloat(item.getPrice()) * item.getQuantity();
        }
        totalPriceText.setText("Total: $" + total);

        btnCheckout.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.cart_fragment_container, new Checkout_Card_Details())
                    .addToBackStack(null)
                    .commit();
        });
    }

    private List<CartModel> loadFakeCartItems() {
        List<CartModel> list = new ArrayList<>();
        list.add(new CartModel("Chair", "https://link.to/chair.jpg", "120", 1));
        list.add(new CartModel("Table", "https://link.to/table.jpg", "200", 1));
        return list;
    }
}
