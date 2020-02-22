package com.manish.obaa.ac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manish.obaa.R;
import com.manish.obaa.SummaryActivity;
import com.manish.obaa.adapter.SelectAdapter;
import com.manish.obaa.rowitem.RowItemSelectService;

import java.util.ArrayList;
import java.util.List;

public class InstallationUninstallation extends AppCompatActivity {

    List<RowItemSelectService> serviceList;
    RecyclerView recyclerView;
    private RelativeLayout subTotalRL;
    private TextView subTotalTV;
    private int subTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation_uninstallation);

        init();

        setListeners();

        initRV();


    }

    private void initRV() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RowItemSelectService service1 = new RowItemSelectService("Window Ac Installation", 799, 0);
        RowItemSelectService service2 = new RowItemSelectService("Split Ac Installation", 2000, 0);
        RowItemSelectService service3 = new RowItemSelectService("Window Ac Un-Installation", 398, 0);
        RowItemSelectService service4 = new RowItemSelectService("Split Ac Un-Installation", 599, 0);

        serviceList.add(service1);
        serviceList.add(service2);
        serviceList.add(service3);
        serviceList.add(service4);

        recyclerView.setAdapter(new SelectAdapter(this, serviceList, subTotalTV));

    }

    private void setListeners() {

        subTotalRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed();
            }
        });

    }

    private void init() {

        recyclerView = findViewById(R.id.insUnisRV);
        subTotalRL = findViewById(R.id.subTotalInsUnisRL);
        subTotalTV = findViewById(R.id.subTotalInsUninsTV);
        serviceList = new ArrayList<>();
    }

    private void proceed() {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < serviceList.size(); i++) {

            RowItemSelectService service = serviceList.get(i);

            if (service.getQuantity() != 0) {
                builder.append(service.getServiceName()).append(" × ").append(service.getQuantity()).append("\nRs. ").append(service.getPrice()).append("\n\n");
                subTotal += service.getPrice()*service.getQuantity();;
            }

        }

        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("services", builder.toString());
        intent.putExtra("subTotal", String.valueOf(subTotal));
        Log.e("SUBTOTAL", String.valueOf(subTotal));
        startActivity(intent);
    }

}