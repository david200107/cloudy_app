package com.example.cloudy;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class L_Adapter extends RecyclerView.Adapter {

    private final ArrayList<L_Model> dataSet;
    private Context mContext;
    public int total_types;

    public L_Adapter(ArrayList<L_Model>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }
    public class LocViewHolder extends RecyclerView.ViewHolder {

        public LocViewHolder(View itemView) {
            super(itemView);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_row, parent, false);
        return new LocViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        L_Model model= dataSet.get(position);

        StorageReference storageReference;
        if(model!=null){
            TextView temperature,location;
            ImageView imgIcon;
            imgIcon=holder.itemView.findViewById(R.id.image_nav);
            temperature=holder.itemView.findViewById(R.id.temp_nav);
            temperature.setText(dataSet.get(position).getTemperature());
            location=holder.itemView.findViewById(R.id.location_txt);
            location.setText(dataSet.get(position).getLocation());

            try {
                storageReference= FirebaseStorage.getInstance().getReference("daily_weather_cond/"+new Wearher_code().getCurrentWIcon(dataSet.get(position).getWeatherIcon())+".png");
                File localFile=File.createTempFile("tempfile1","png");
                storageReference.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                    Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());

                    Drawable img=new BitmapDrawable(mContext.getResources(),bitmap);
                    imgIcon.setBackground(img);

                });
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    @Override
    public int getItemViewType(int position) {

        return -1;
    }




}

