package com.manish.obaa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manish.obaa.R;
import com.manish.obaa.rowitem.RowItemHome;

import java.util.List;

public class HomeCatAdapter extends RecyclerView.Adapter<HomeCatAdapter.MyViewHolder> {

    List<RowItemHome> list;

    public HomeCatAdapter(List<RowItemHome> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_home_cat, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        RowItemHome rowItemHome = list.get(i);
        myViewHolder.titleTV.setText(rowItemHome.getTitle());
        myViewHolder.suvTitleTV.setText(rowItemHome.getSubTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV,suvTitleTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            suvTitleTV = itemView.findViewById(R.id.subTitleTV);
        }
    }


}
