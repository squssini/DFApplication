package com.example.dfapplication.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dfapplication.Classes.CartManager;
import com.example.dfapplication.Classes.Furniture;
import com.example.dfapplication.Models.CartModel;
import com.example.dfapplication.Fragments.Checkout_Card_Details;
import com.example.dfapplication.Adapters.MyCartAdapter;
import com.example.dfapplication.R;

import java.util.ArrayList;
import java.util.List;

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

        // ✅ Get cart items from CartManager
        List<CartModel> cartItems = CartManager.getInstance().getCartItems();

        // ✅ Set up RecyclerView with correct adapter and layout
        MyCartAdapter adapter = new MyCartAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // ✅ Calculate total using Furniture model
        float total = 0;
        for (CartModel item : cartItems) {
            try {
                total += Float.parseFloat(item.getPrice()) * item.getQuantity();
            } catch (NumberFormatException e) {
                e.printStackTrace(); // Optionally log error
            }
        }
        totalPriceText.setText("Total: $" + total);


        btnCheckout.setOnClickListener(view -> {
            // Hide cart UI views
            recyclerView.setVisibility(View.GONE);
            btnCheckout.setVisibility(View.GONE);
            totalPriceText.setVisibility(View.GONE);

            // Show the fragment container
            findViewById(R.id.cart_fragment_container).setVisibility(View.VISIBLE);

            // Load the Checkout fragment
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
