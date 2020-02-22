package com.manish.obaa.ac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class AcServiceActivity extends AppCompatActivity{

    List<RowItemSelectService> serviceList = new ArrayList<>();
    RecyclerView recyclerView;
    private RelativeLayout subTotalRL;
    private TextView subTotalTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_service);

        init();

        setListeners();

        initRV();

    }

    private void initRV() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RowItemSelectService service1 = new RowItemSelectService("Window Ac Service", 399, 0);
        RowItemSelectService service2 = new RowItemSelectService("Split Ac Service", 499, 0);

        serviceList.add(service1);
        serviceList.add(service2);

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

        recyclerView = findViewById(R.id.acServiceRV);
        subTotalRL = findViewById(R.id.subTotalAcServiceRL);
        subTotalTV = findViewById(R.id.subTotalAcServiceTV);

    }


    private void proceed() {

        int subTotal = 0;

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < serviceList.size(); i++) {

            RowItemSelectService service = serviceList.get(i);

            if (service.getQuantity() != 0) {
                builder.append(service.getServiceName()).append(" × ").append(service.getQuantity()).append("\nRs. ").append(service.getPrice()).append("\n\n");
                subTotal += service.getPrice()*service.getQuantity();
            }

        }

        Log.e("SUBTOTAL", String.valueOf(subTotal));
System.out.println("subtotal is " +subTotal);

        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("services", builder.toString());
        intent.putExtra("subTotal", String.valueOf(subTotal));
        startActivity(intent);

    }

}