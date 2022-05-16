package com.example.tara.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tara.Main.RecyclerViewInterface;
import com.example.tara.Models.Car;
import com.example.tara.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder>{
    private final RecyclerViewInterface recyclerViewInterface;
    private final int lastPosition = -1;
    Context context;

    ArrayList<Car> list;

    public CarAdapter(Context context, ArrayList<Car> list, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.car_item,parent,false);
        return new MyViewHolder(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car car = list.get(position);
        holder.bmy.setText(car.getBmy());
        holder.city.setText(car.getLocation());
        holder.price.setText(car.getPriceRate());
        Glide.with(holder.img.getContext()).load(car.getCarUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView img;
        TextView bmy, city , price;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.ivCarDisplay);
            bmy = (TextView)itemView.findViewById(R.id.tvBMY);
            city = (TextView)itemView.findViewById(R.id.tvLocation);
            price = (TextView)itemView.findViewById(R.id.tvPricing);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}


