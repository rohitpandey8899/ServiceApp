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
import com.manish.obaa.rowitem.RowItemMainCat;

import java.util.List;

public class MainCatAdapter extends RecyclerView.Adapter<MainCatAdapter.MyViewHolder> {

    private Activity activity;
    private List<RowItemMainCat> list;

    public MainCatAdapter(Activity activity, List<RowItemMainCat> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_main_cat, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        RowItemMainCat cat = list.get(i);
        holder.mainCatNameTV.setText(cat.getCatName());
        Glide.with(activity)
                .load(cat.getImg())
                .into(holder.mainCatIV);
        System.out.println("image zoo"+cat.getImg());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainCatIV;
        private TextView mainCatNameTV;

        MyViewHolder(View view) {
            super(view);
            mainCatIV = view.findViewById(R.id.mainCatIV);
            mainCatNameTV = view.findViewById(R.id.mainCatNameTV);
        }
    }
}