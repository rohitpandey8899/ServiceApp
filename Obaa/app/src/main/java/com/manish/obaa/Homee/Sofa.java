package com.manish.obaa.Homee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.manish.obaa.R;
import com.manish.obaa.RecyclerTouch;
import com.manish.obaa.adapter.OptionAdapter;
import com.manish.obaa.rowitem.Options;

import java.util.ArrayList;
import java.util.List;

public class Sofa extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofa);
        init();

        setListener();

        initRV();




    }
    private void initRV() {
        RecyclerView recyclerView = findViewById(R.id.acServiceRepairRV);

        List<Options> options = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Options option1 = new Options("Sofa Cleaning Service Include", "Clean Sofa will take 5-6 hours to dry");
        Options option2 = new Options("Carpet Cleaning", "Complete dry vaccumming of all the corner");
       // Options option3 = new Options("Car Cleaning", "For Complete interior/exterior cleaning of your car");
        Options option3 = new Options("Kitchen Deep Cleaning", "Complete cleaning and disinfection of Kitchen floor");
        Options option4 = new Options("Bathroom", "Complete cleaning and disinfection of Kitchen floor");
        Options option5 = new Options("Homedeep", "Complete cleaning and disinfection of Kitchen floor");
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        //options.add(option6);


        OptionAdapter adapter = new OptionAdapter(Sofa.this, options);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouch(this, new RecyclerTouch.ClickListener() {
            @Override
            public void onClick(View view, int position) {

              //  Toast.makeText(AcServiceAndRepairActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 0:
                        startActivity(new Intent(Sofa.this,NumberSofa.class));
                        break;

                   case 1:
                        startActivity(new Intent(Sofa.this, Carpet.class));
                        break;


                    case 2:
                        startActivity(new Intent(Sofa.this, Kitchen.class));
                        break;

                    case 3:
                        startActivity(new Intent(Sofa.this, Bathroom.class));
                        break;
                    case 4:
                        startActivity(new Intent(Sofa.this, Homedeep.class));
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
