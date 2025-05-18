package com.example.dfapplication.Classes;
import com.example.dfapplication.Classes.Furniture;
import com.example.dfapplication.Models.CartModel;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartModel> cartItems = new ArrayList<>();

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Convert Furniture to CartModel before adding
    public void addToCart(Furniture item) {
        CartModel cartModel = new CartModel(
                item.getName(),
                item.getPhoto(),
                item.getPrice(),
                1  // default quantity is 1, or use item.getCartQty() if available
        );
        cartItems.add(cartModel);
    }

    public List<CartModel> getCartItems() {
        return cartItems;
    }
}
