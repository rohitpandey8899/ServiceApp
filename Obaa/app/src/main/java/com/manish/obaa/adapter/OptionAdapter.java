package com.manish.obaa.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.manish.obaa.R;
import com.manish.obaa.rowitem.Options;
import com.manish.obaa.rowitem.RowItemAddress;
import com.manish.obaa.utils.SQLiteHelper;

import java.util.HashMap;
import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.MyVIewHolder> {

    Activity activity;
    private List<Options> list;

    public OptionAdapter(Activity activity, List<Options> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyVIewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_option, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVIewHolder holder, int i) {

        Options options = list.get(i);
        holder.titleTV.setText(options.getTitle());
        holder.subtitleTV.setText(options.getSubTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyVIewHolder extends RecyclerView.ViewHolder {

        private TextView titleTV;
        private TextView subtitleTV;

        MyVIewHolder(@NonNull View view) {
            super(view);
            titleTV = view.findViewById(R.id.title2TV);
            subtitleTV = view.findViewById(R.id.subTitle2TV);

        }
    }
}