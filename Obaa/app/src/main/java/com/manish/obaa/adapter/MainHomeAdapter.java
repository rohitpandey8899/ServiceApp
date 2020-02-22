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
import com.manish.obaa.rowitem.RowHome;
import com.manish.obaa.rowitem.RowItemHome;

import java.util.List;

public class MainHomeAdapter extends RecyclerView.Adapter<MainHomeAdapter.MyViewHolder>{
    private Activity activity;
    private List<RowHome> list;


    public MainHomeAdapter(Activity activity, List<RowHome> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_home, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        RowHome cat = list.get(i);
        myViewHolder.mainHomeNameTV.setText(cat.getCatName());
        Glide.with(activity)
                .load(cat.getImg())
                .into(myViewHolder.mainHomeIV);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainHomeIV;
        private TextView mainHomeNameTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mainHomeIV = itemView.findViewById(R.id.mainHomeIV);
            mainHomeNameTV = itemView.findViewById(R.id.mainHomeNameTV);
        }
    }
}
