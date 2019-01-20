package com.myweather.ir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myweather.ir.model.Forecast;


import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.MyViewHolder> {

    private Context context;
    private List<Forecast> list;// ساخت یک لیست از مقادیر آب و هوای آینده

    NameAdapter(Context context , List<Forecast> theList){
        this.context = context;
        list=theList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // تنظیم لایه ای که باید برای هر سطر نمایش دهد

        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.day_list_items,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // // گرفتن اطلاعات از لیست و نمایش آن روی ویو ها

        Forecast currentForCast = list.get(i);
        myViewHolder.day.setText(currentForCast.getDay());
        myViewHolder.tempLow.setText(currentForCast.getLow().toString()+"  ∨");
        myViewHolder.tempHigh.setText(currentForCast.getHigh().toString()+"  ∧");
        myViewHolder.imgWeatherState.setImageDrawable(context.getResources()
                                         .getDrawable(WeatherPhoto
                                         .getPhotoWeather(currentForCast.getText()
                                 )
                        )
                );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView day;
        public ImageView imgWeatherState;
        public TextView tempLow;
        public TextView tempHigh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.tvDayId);
            imgWeatherState=itemView.findViewById(R.id.imgWeatherStateId);
            tempLow=itemView.findViewById(R.id.tvLowTempId);
            tempHigh=itemView.findViewById(R.id.tvHighTempId);
        }
    }
}
