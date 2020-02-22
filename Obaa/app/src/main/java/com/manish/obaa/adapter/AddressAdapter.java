package com.manish.obaa.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manish.obaa.R;
import com.manish.obaa.rowitem.RowItemAddress;
import com.manish.obaa.utils.SQLiteHelper;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyVIewHolder> {

    private Activity activity;
    private List<RowItemAddress> list;
    private SQLiteHelper helper;

    public AddressAdapter(Activity activity, List<RowItemAddress> list) {
        this.activity = activity;
        this.list = list;
        helper = new SQLiteHelper(activity);
    }

    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyVIewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_address, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyVIewHolder holder, int i) {

        final RowItemAddress address = list.get(i);
        holder.nameAddressTV.setText(address.getName());
        holder.houseNoAddressTV.setText(address.getHouseNo());
        holder.colonyAddressTV.setText(address.getColony());
        holder.cityAddressTV.setText(address.getCity());

        holder.deleteAddressBT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                        .setIcon(R.drawable.alert_small_icon)
                        .setTitle("Warning...")
                        .setMessage("You want to delete this Address?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                helper.deleteAddress(address.getId());
                                list.remove(address);
                                notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("NO",null);

                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyVIewHolder extends RecyclerView.ViewHolder {

        private TextView nameAddressTV;
        private TextView houseNoAddressTV;
        private TextView colonyAddressTV;
        private TextView cityAddressTV;
        private Button deleteAddressBT;
        private LinearLayout linearLayout;

        MyVIewHolder(@NonNull View view) {
            super(view);
            nameAddressTV = view.findViewById(R.id.nameAddressTV);
            houseNoAddressTV = view.findViewById(R.id.houseNoAddressTV);
            colonyAddressTV = view.findViewById(R.id.colonyAddressTV);
            cityAddressTV = view.findViewById(R.id.cityAddressTV);
            deleteAddressBT = view.findViewById(R.id.deleteAddressBT);
            linearLayout = view.findViewById(R.id.linearLayout);

        }
    }


}