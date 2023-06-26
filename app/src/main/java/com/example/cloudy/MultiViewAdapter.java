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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MultiViewAdapter extends RecyclerView.Adapter {

    private final ArrayList<Model> dataSet;
    Context mContext;
    int total_types;

    public MultiViewAdapter(ArrayList<Model>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }


    public class ForecastHourlyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public ForecastHourlyViewHolder(View itemView) {
            super(itemView);

            //this.txtType = (TextView) itemView.findViewById(R.id.type);
            //this.cardView = (CardView) itemView.findViewById(R.id.card_view);

            linearLayout=itemView.findViewById(R.id.linearLayout);
            expandableLayout=itemView.findViewById(R.id.expandable_layout);


            linearLayout.setOnClickListener(view -> {
                Model model=dataSet.get(getAdapterPosition());
                model.setExpandable(!model.isExpandable());
                notifyItemChanged(getAdapterPosition());
            });


        }
    }
    public static class DateTimeViewHolder extends RecyclerView.ViewHolder {

        TextView datetime;


        public DateTimeViewHolder(View itemView) {
            super(itemView);

            //this.txtType = (TextView) itemView.findViewById(R.id.type);
            // this.cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Model.FORECAST_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);


                return new ForecastHourlyViewHolder(view);
            case Model.DATETIME_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_row, parent, false);
                return new DateTimeViewHolder(view);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         StorageReference StorageRefence_w_icon;
        Model model= dataSet.get(position);
        if(model!=null){
            switch (model.type){
                case Model.FORECAST_TYPE:

                    TextView time,condition,temp,prec_amount,cloud_cover,visibility,pressure,aparent_temp,humidity,dew_point,wind,windm,wind_gst;
                    ImageView imgIcon;
                    imgIcon=holder.itemView.findViewById(R.id.imageW);
                    Log.d("Model",model.toString());
                    time=holder.itemView.findViewById(R.id.time_hour);
                    time.setText(dataSet.get(position).getTime());
                    condition=holder.itemView.findViewById(R.id.w_conditonH);
                    condition.setText(new Wearher_code().getWeatherDescription(dataSet.get(position).getCondition()));
                    temp=holder.itemView.findViewById(R.id.h_temp);
                    temp.setText(dataSet.get(position).getTemp());
                    prec_amount=holder.itemView.findViewById(R.id.h_rain_amount);
                    prec_amount.setText(dataSet.get(position).getPrec_amount());
                    cloud_cover=holder.itemView.findViewById(R.id.h_cloud_cover);
                    cloud_cover.setText(dataSet.get(position).getCloud_cover());
                    visibility=holder.itemView.findViewById(R.id.h_visibility);
                    visibility.setText(new DecimalFormat("##").format(Double.parseDouble(dataSet.get(position).getVisibility())*0.001)+"km");
                    pressure=holder.itemView.findViewById(R.id.h_pressure);
                    pressure.setText(dataSet.get(position).getPressure());
                    aparent_temp=holder.itemView.findViewById(R.id.h_feels_like);
                    aparent_temp.setText(dataSet.get(position).getAparent_temp());
                    humidity=holder.itemView.findViewById(R.id.h_humidity);
                    humidity.setText(dataSet.get(position).getHumidity()+"%");
                    dew_point=holder.itemView.findViewById(R.id.h_dewpoint);
                    dew_point.setText(dataSet.get(position).getDew_point());
                    wind=holder.itemView.findViewById(R.id.h_wind_speed);
                    windm=holder.itemView.findViewById(R.id.hm_wind_speed);
                    wind.setText(dataSet.get(position).getWind());
                    windm.setText(dataSet.get(position).getWind());
                    wind_gst=holder.itemView.findViewById(R.id.h_wind_gust);
                    wind_gst.setText(dataSet.get(position).getWind_gst());

                   // imgIcon=holder.itemView.findViewById(R.id.imageW);
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




                    boolean isExpandable=dataSet.get(position).isExpandable();
                    holder.itemView.findViewById(R.id.expandable_layout).setVisibility(isExpandable? View.VISIBLE:View.GONE);
                   // holder.expandableLayout.setVisibility(isExpandable? View.VISIBLE:View.GONE);

                    break;
                case Model.DATETIME_TYPE:
                    TextView datetime;
                    datetime=holder.itemView.findViewById(R.id.datetime_txt);
                    datetime.setText(dataSet.get(position).getDatetime());

                    //TO DO
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.FORECAST_TYPE;
            case 1:
                return Model.DATETIME_TYPE;
            default:
                return -1;
        }
    }




}
