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
import com.manish.obaa.MainPlumberCat;
import com.manish.obaa.R;
import com.manish.obaa.rowitem.RowItemMainCctv;
import com.manish.obaa.rowitem.RowItemMainPlumber;

import java.util.List;

/**
 * Created by MAANSI on 6/27/2019.
 */

public class MainPlumberAdapter extends RecyclerView.Adapter<MainPlumberAdapter.MyViewHolder> {
    private Activity activity;
    private List<RowItemMainPlumber> list;
    public MainPlumberAdapter(Activity activity, List<RowItemMainPlumber> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MainPlumberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainPlumberAdapter.MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_main_plumber, viewGroup, false));
    }



    @Override
    public void onBindViewHolder(@NonNull MainPlumberAdapter.MyViewHolder myViewHolder, int i) {
        RowItemMainPlumber cat = list.get(i);
        myViewHolder.mainPlumberNameTV.setText(cat.getCatName());
        Glide.with(activity)
                .load(cat.getImg())
                .into(myViewHolder.mainPlumberIV);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainPlumberIV;
        private TextView mainPlumberNameTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mainPlumberIV = itemView.findViewById(R.id.mainPlumberIV);
            mainPlumberNameTV = itemView.findViewById(R.id.mainPlumberNameTV);
        }
    }
}
