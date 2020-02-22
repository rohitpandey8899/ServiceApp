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
import com.manish.obaa.rowitem.RowItemMainCctv;

import java.util.List;

public class MainCctvAdapter extends RecyclerView.Adapter<MainCctvAdapter.MyViewHolder>{
    private Activity activity;
    private List<RowItemMainCctv> list;
    public MainCctvAdapter(Activity activity, List<RowItemMainCctv> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_main_cctv, viewGroup, false));
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        RowItemMainCctv cat = list.get(i);
        myViewHolder.mainCatcNameTV.setText(cat.getCatName());
        Glide.with(activity)
                .load(cat.getImg())
                .into(myViewHolder.mainCatcIV);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainCatcIV;
        private TextView mainCatcNameTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mainCatcIV = itemView.findViewById(R.id.mainCatcIV);
            mainCatcNameTV = itemView.findViewById(R.id.mainCatcNameTV);
        }
    }

}
