package com.manish.obaa.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.manish.obaa.R;
import com.manish.obaa.rowitem.RowItemMainElectrician;
import com.manish.obaa.rowitem.RowItemMainPainter;

import java.util.List;

public class MainElectricianAdapter extends RecyclerView.Adapter<MainElectricianAdapter.MyViewHolder>{
    private Activity activity;
    private List<RowItemMainElectrician> list;

    public MainElectricianAdapter(Activity activity, List<RowItemMainElectrician> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_main_electrician, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        RowItemMainElectrician cat = list.get(i);
        myViewHolder.mainElectricianNameTV.setText(cat.getCatName());
        Glide.with(activity)
                .load(cat.getImg())
                .into(myViewHolder.mainElectricianIV);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mainElectricianIV;
        private TextView mainElectricianNameTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mainElectricianIV = itemView.findViewById(R.id.mainCateIV);
            mainElectricianNameTV = itemView.findViewById(R.id.mainCateNameTV);
        }
    }

}
