package com.manish.obaa.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.manish.obaa.R;
import com.manish.obaa.rowitem.RowItemSelectService;

import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.MyVIewHolder> {

    Activity activity;
    private List<RowItemSelectService> list;
    private TextView subTotalTV;
    private int subTotal = 0;

    public SelectAdapter(Activity activity, List<RowItemSelectService> list, TextView subTotalTV) {
        this.activity = activity;
        this.list = list;
        this.subTotalTV = subTotalTV;
    }

    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyVIewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_select_service, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVIewHolder holder, int i) {

        final RowItemSelectService service = list.get(i);
        holder.serviceNameTV.setText(service.getServiceName());
        holder.servicePriceTV.setText(String.valueOf(service.getPrice()));

        subTotalTV.setText(String.valueOf(subTotal));

        holder.removeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, "" + service.getQuantity(), Toast.LENGTH_SHORT).show();

                if (service.getQuantity() > 0) {
                    service.setQuantity(service.getQuantity() - 1);
                    holder.quantityTV.setText(String.valueOf(service.getQuantity()));
                    subTotal -= service.getPrice();
                    subTotalTV.setText(String.valueOf(subTotal));

                } else {
                    Toast.makeText(activity, "No Service Selected.", Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
            }
        });

        holder.addBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                service.setQuantity(service.getQuantity() + 1);
                holder.quantityTV.setText(String.valueOf(service.getQuantity()));

                subTotal += service.getPrice();
                subTotalTV.setText(String.valueOf(subTotal));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyVIewHolder extends RecyclerView.ViewHolder {

        private TextView serviceNameTV;
        private TextView servicePriceTV;
        private Button removeBT;
        private TextView quantityTV;
        private Button addBT;

        MyVIewHolder(@NonNull View view) {
            super(view);
            serviceNameTV = view.findViewById(R.id.serviceNameTV);
            servicePriceTV = view.findViewById(R.id.servicePriceTV);
            removeBT = view.findViewById(R.id.removeBT);
            quantityTV = view.findViewById(R.id.quantityTV);
            addBT = view.findViewById(R.id.addBT);

        }
    }
}