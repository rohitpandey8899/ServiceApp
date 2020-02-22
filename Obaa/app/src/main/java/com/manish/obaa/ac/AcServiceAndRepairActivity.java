package com.manish.obaa.ac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manish.obaa.R;
import com.manish.obaa.RecyclerTouch;
import com.manish.obaa.adapter.OptionAdapter;
import com.manish.obaa.rowitem.Options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AcServiceAndRepairActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_service_and_repair);

        init();

        setListener();

        initRV();

    }

    private void initRV() {
        RecyclerView recyclerView = findViewById(R.id.acServiceRepairRV);

        List<Options> options = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Options option1 = new Options("AC Service", "");
        Options option2 = new Options("AC Repair", "(Less Cooling, no cooling, Water Leakage etc.)");
        Options option3 = new Options("Installation / Un-Installation", "");

        options.add(option1);
        options.add(option2);
        options.add(option3);

        OptionAdapter adapter = new OptionAdapter(AcServiceAndRepairActivity.this, options);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouch(this, new RecyclerTouch.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(AcServiceAndRepairActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 0:
                        startActivity(new Intent(AcServiceAndRepairActivity.this, AcServiceActivity.class));
                        break;

                    case 1:
                        startActivity(new Intent(AcServiceAndRepairActivity.this, AcRepairActivity.class));
                        break;

                    case 2:
                        startActivity(new Intent(AcServiceAndRepairActivity.this, InstallationUninstallation.class));
                        break;

                }

            }
        }));

    }

    private void setListener() {
//        acServiceRL.setOnClickListener(this);
//        acRepairRL.setOnClickListener(this);
//        insUninsRL.setOnClickListener(this);
    }

    private void init() {

//        acServiceRL = (RelativeLayout) findViewById(R.id.acServiceRL);
//        acServiceTV = (TextView) findViewById(R.id.acServiceTV);
//        acServiceSmallTV = (TextView) findViewById(R.id.acServiceSmallTV);
//        acRepairRL = (RelativeLayout) findViewById(R.id.acRepairRL);
//        acRepairTV = (TextView) findViewById(R.id.acRepairTV);
//        acRepairSmallTV = (TextView) findViewById(R.id.acRepairSmallTV);
//        insUninsRL = (RelativeLayout) findViewById(R.id.insUninsRL);
//        acInstallUninstallTV = (TextView) findViewById(R.id.acInstallUninstallTV);
//        acInstallUninstallSmallTV = (TextView) findViewById(R.id.acInstallUninstallSmallTV);

    }

}
