package com.example.dfapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dfapplication.Adapters.MyCartAdapter;
import com.example.dfapplication.Classes.CartManager;
import com.example.dfapplication.Fragments.Checkout_Card_Details;
import com.example.dfapplication.Models.CartModel;
import com.example.dfapplication.R;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnCheckout;
    private TextView totalPriceText;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_cart);
        btnCheckout = findViewById(R.id.btn_checkout);
        totalPriceText = findViewById(R.id.total_price);
        btnBack = findViewById(R.id.btnBack); // Assuming you have a back button in your layout

        // Get cart items
        List<CartModel> cartItems = CartManager.getInstance().getCartItems();

        // Set up RecyclerView
        MyCartAdapter adapter = new MyCartAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Calculate total
        float total = 0;
        for (CartModel item : cartItems) {
            try {
                total += Float.parseFloat(item.getPrice()) * item.getQuantity();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        totalPriceText.setText("Total: $" + total);

        // Back button: go back to MainActivity with AllFurnitureFragment
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            intent.putExtra("loadFragment", "allFurniture");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // Checkout button shows fragment
        btnCheckout.setOnClickListener(view -> {
            // Hide views
            recyclerView.setVisibility(View.GONE);
            btnCheckout.setVisibility(View.GONE);
            totalPriceText.setVisibility(View.GONE);
            btnBack.setVisibility(View.GONE);

            // Show checkout fragment
            findViewById(R.id.cart_fragment_container).setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.cart_fragment_container, new Checkout_Card_Details())
                    .addToBackStack(null)
                    .commit();
        });
    }
}
