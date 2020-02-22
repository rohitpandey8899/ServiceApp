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
import com.manish.obaa.rowitem.RowItemMainPainter;

import java.util.List;

public class MainPainterAdapter extends RecyclerView.Adapter<MainPainterAdapter.MyViewHolder> {
    private Activity activity;
    private List<RowItemMainPainter> list;


    public MainPainterAdapter(Activity activity, List<RowItemMainPainter> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_main_painter, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        RowItemMainPainter cat = list.get(i);
        myViewHolder.mainPainterNameTV.setText(cat.getCatName());
        Glide.with(activity)
                .load(cat.getImg())
                .into(myViewHolder.mainPainterIV);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mainPainterIV;
        private TextView mainPainterNameTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mainPainterIV = itemView.findViewById(R.id.mainPainterIV);
            mainPainterNameTV = itemView.findViewById(R.id.mainPainterNameTV);
        }
    }
}
