package com.example.cloudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
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

public class D_Adapter extends RecyclerView.Adapter {

    private final ArrayList<D_Model> dataSet;
    private final Context mContext;
    private int total_types;

    public D_Adapter(ArrayList<D_Model>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    public class ForecastDayViewHolder extends RecyclerView.ViewHolder {

        public ForecastDayViewHolder(View itemView) {
            super(itemView);

        }
    }
    public static class DateTimeViewHolder extends RecyclerView.ViewHolder {

        public DateTimeViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_weather_container, parent, false);
                return new ForecastDayViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StorageReference StorageRefence_w_icon;
        D_Model model= dataSet.get(position);
        if(model!=null){
                    TextView date,condition,max_temp,min_temp,prec_amount,prec_hour,prec_prob,wind,wind_gst,wind_dir,uv_index,sunset,sunrise;
                    ImageView imgIcon;
                    imgIcon=holder.itemView.findViewById(R.id.day_img_condition);
                    date=holder.itemView.findViewById(R.id.d_datetime);
                    date.setText(dataSet.get(position).getDate());
                    condition=holder.itemView.findViewById(R.id.d_condition);
                    condition.setText(new Wearher_code().getWeatherDescription(dataSet.get(position).getCondition()));
                    max_temp=holder.itemView.findViewById(R.id.max_temp);
                    max_temp.setText(dataSet.get(position).getMax_temp());
                    min_temp=holder.itemView.findViewById(R.id.min_temp);
                    min_temp.setText(dataSet.get(position).getMin_tmep());
                    prec_amount=holder.itemView.findViewById(R.id.prec_amount_d);
                    prec_amount.setText(dataSet.get(position).getPrec_amount());
                    prec_hour=holder.itemView.findViewById(R.id.d_prec_hour);
                    prec_hour.setText(dataSet.get(position).getPrec_hour()+"h");
                    prec_prob=holder.itemView.findViewById(R.id.d_prec_probability);
                    prec_prob.setText(dataSet.get(position).getPrec_probability()+"%");
                    wind=holder.itemView.findViewById(R.id.d_windspeed);
                    wind.setText(dataSet.get(position).getWind());
                    wind_gst=holder.itemView.findViewById(R.id.d_windgusts);
                    wind_gst.setText(dataSet.get(position).getWind_gst());
                    wind_dir=holder.itemView.findViewById(R.id.d_wind_dir);
                    wind_dir.setText(dataSet.get(position).getWind_dir());
                    uv_index=holder.itemView.findViewById(R.id.d_uv_index);
                    uv_index.setText(dataSet.get(position).getUv_index());
                    sunset=holder.itemView.findViewById(R.id.d_sunset);
                    sunset.setText(dataSet.get(position).getSunet());
                    sunrise=holder.itemView.findViewById(R.id.d_sunrise);
                    sunrise.setText(dataSet.get(position).getSunrise());

                    try {
                        StorageRefence_w_icon= FirebaseStorage.getInstance().getReference("daily_weather_cond/"+new Wearher_code().getCurrentWIcon(dataSet.get(position).getCondition())+".png");
                        File localFile=File.createTempFile("tempfile1","png");
                        StorageRefence_w_icon.getFile(localFile).addOnSuccessListener(taskSnapshot -> {
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());

                            Drawable img=new BitmapDrawable(mContext.getResources(),bitmap);
                            imgIcon.setBackground(img);
                            // imgIcon.setImageDrawable(img);

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
