package com.example.dfapplication;
import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dfapplication.Furniture;
import com.example.wecar.R;
import com.example.wecar.D_FireBase.Car;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    ArrayList<Furniture>furnitureList;
    private OnItemClickListener itemClickListener;

    public MyAdapter(Context context, ArrayList<Furniture> furnitureList) {
        this.context = context;
        this.furnitureList = furnitureList;
    }

    @NonNull
    @Override
    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View v= LayoutInflater.from(context).inflate(R,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Furniture furniture= furnitureList.get(position);
        holder.name.setText(Furniture.getName());
        holder.Price.setText(car.getPrice()+" ₪");
        holder.Year.setText(car.getYear());
        holder.location.setText(car.getHorse_power()+"Hp");
        holder.GearShift.setText(car.getGear_shifting_model());
        holder.kilometre.setText(car.getKilometre()+"Km");
        holder.carName.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(position);
            }
        });
        if (car.getPhoto() == null || car.getPhoto().isEmpty())
        {
            Picasso.get().load(R.drawable.ic_fav).into(holder.ivCar);
        }
        else {
            Picasso.get().load(car.getPhoto()).into(holder.ivCar);
        }
        holder.ivFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFavourite(holder, car);
            }
        });
    }

    private void setFavourite(@NonNull MyViewHolder holder, Car car) {
        /*
        if (isUserFavourite(car) == true)
        {
            holder.ivFavourite.setBackgroundResource(R.drawable.ic_fav);
        }
        else
        {

        } */
    }

    @Override
    public int getItemCount(){
        return furnitureList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView  name,material,price,category;
        ImageView photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.e);
            Price=itemView.findViewById(R.id.tvPrice_carListFragment);
            Year=itemView.findViewById(R.id.tvYear_carListFragment);
            location=itemView.findViewById(R.id.tvlocation_carListFragment);
            GearShift=itemView.findViewById(R.id.tvGearShift_carListFragment);
            kilometre=itemView.findViewById(R.id.tvKelometer_carListFragment);
            ivCar = itemView.findViewById(R.id.ivCarPhotoItem);
            ivFavourite = itemView.findViewById(R.id.ivFavoriteIcon);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}
