package com.example.dfapplication.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dfapplication.Activities.CartActivity;
import com.example.dfapplication.Classes.CartManager;
import com.example.dfapplication.Classes.Firebase;
import com.example.dfapplication.Classes.Furniture;
import com.example.dfapplication.Fragments.FurnitureDetailsFragment;
import com.example.dfapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Furniture> FurList;
    private Firebase fbs;
    private OnItemClickListener itemClickListener;
    private OnDetailsClickListener detailsClickListener;

    public void setOnDetailsClickListener(OnDetailsClickListener listener) {
        this.detailsClickListener = listener;
    }


    public MyAdapter(Context context, ArrayList<Furniture> restList) {
        this.context = context;
        this.FurList = restList;
        this.fbs = Firebase.getInstance();
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.fur_item,parent,false);
        return  new MyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Furniture Fur = FurList.get(position);
        holder.tvName.setText(Fur.getCategory());
        holder.tvPrice.setText(Fur.getPrice());


        if (Fur.getPhoto() == null || Fur.getPhoto().isEmpty())
        {
            Picasso.get().load(R.drawable.ic_launcher_background).into(holder.imageView);
        }
        else {
            Picasso.get().load(Fur.getPhoto()).into(holder.imageView);
        }
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(Fur);  // pass clicked Furniture item
            }
        });

        holder.btnAddToCart.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(Fur); // Use CartManager instead of Utils
            Toast.makeText(context, Fur.getColor() + " added to cart", Toast.LENGTH_SHORT).show();

            // Navigate to CartActivity
            Intent intent = new Intent(context, CartActivity.class);
            context.startActivity(intent);
        });
        holder.btnDetails.setOnClickListener(v -> {
            if (detailsClickListener != null) {
                detailsClickListener.onDetailsClick(Fur);
            }
        });



    }
    public interface OnCartClickListener {
        void onAddToCartClick(Furniture furniture);
    }

    private OnCartClickListener cartClickListener;

    public void setOnCartClickListener(OnCartClickListener listener) {
        this.cartClickListener = listener;
    }
    public interface OnDetailsClickListener {
        void onDetailsClick(Furniture furniture);
    }

    @Override
    public int getItemCount(){
        return FurList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice ;
        ImageView imageView;
        Button btnDetails, btnAddToCart;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvNameFurItem);
            tvPrice=itemView.findViewById(R.id.tvPriceFurItem);
            imageView=itemView.findViewById(R.id.imageView);
            btnDetails = itemView.findViewById(R.id.btnDetails);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Furniture furniture);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

}
