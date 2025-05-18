package com.example.dfapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dfapplication.Classes.Furniture;
import com.example.dfapplication.Models.CartModel;
import com.example.dfapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {
    private Context context;
    private List<CartModel> cartList;

    public MyCartAdapter(Context context, List<CartModel> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cart_item, parent, false);
        return new MyViewHolder(view); // ✅ Match class name
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartModel cartItem = cartList.get(position);

        holder.cartItemName.setText("Name: " + cartItem.getName());
        holder.cartItemPrice.setText("Price: " + cartItem.getPrice() + " ₪");
        holder.cartItemQty.setText("Qty: " + cartItem.getQuantity());

        if (cartItem.getImage() == null || cartItem.getImage().isEmpty()) {
            Picasso.get().load(R.drawable.ic_launcher_background).into(holder.cartItemImage);
        } else {
            Picasso.get().load(cartItem.getImage()).into(holder.cartItemImage);
        }
    }



    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cartItemImage;
        TextView cartItemName, cartItemPrice, cartItemQty;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemImage = itemView.findViewById(R.id.cartItemImage);
            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemQty = itemView.findViewById(R.id.cartItemQty);
        }
    }

}
