package com.example.dfapplication;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Furniture> FurList;
    private Firebase fbs;
    private OnItemClickListener itemClickListener;

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
        holder.tvName.setText(Fur.getName());
        holder.tvPrice.setText(Fur.getPrice());


        if (Fur.getPhoto() == null || Fur.getPhoto().isEmpty())
        {
            Picasso.get().load(R.drawable.ic_launcher_background).into(holder.imageView);
        }
        else {
            Picasso.get().load(Fur.getPhoto()).into(holder.imageView);
        }
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getActivity(), "Clicked: " + selectedItem, Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putParcelable("fur", (Parcelable) Fur); // or use Parcelable for better performance
                FurnitureDetailsFragment cd = new FurnitureDetailsFragment();
                cd.setArguments(args);
                FragmentTransaction ft=((MainActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.main,cd);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount(){
        return FurList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice ;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvNameFurItem);
            tvPrice=itemView.findViewById(R.id.tvPriceFurItem);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
