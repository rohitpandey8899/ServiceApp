package com.manish.obaa;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.SweetAlert.SweetAlertDialog;
import com.manish.obaa.adapter.MainElectricianAdapter;
import com.manish.obaa.adapter.MainPainterAdapter;
import com.manish.obaa.electrician.Fan;
import com.manish.obaa.electrician.Geyser;
import com.manish.obaa.electrician.Lights;
import com.manish.obaa.electrician.Mcb;
import com.manish.obaa.electrician.Switches;
import com.manish.obaa.rowitem.RowItemMainElectrician;
import com.manish.obaa.rowitem.RowItemMainPainter;
import com.manish.obaa.utils.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Electrician extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RowItemMainElectrician> mainElectricianList;
    private String key;

    {
        key = "5";
    }

    private String keys="5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician);
        init();
        System.out.println("111");
        key = getIntent().getStringExtra("key");
        System.out.println("key is " +key);
        //System.out.println("222");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println("333");
        initRV();
        //System.out.println("444");
        setListener();
    }
    private void setListener() {

        recyclerView.addOnItemTouchListener(new RecyclerTouch(this, new RecyclerTouch.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (position == 0)
                {
                    startActivity(new Intent(Electrician.this, Fan.class));
                }
                if (position == 1)
                {
                    startActivity(new Intent(Electrician.this, Switches.class));
                }
                if (position == 2)
                {
                    startActivity(new Intent(Electrician.this, Lights.class));
                }
                if (position == 3)
                {
                    startActivity(new Intent(Electrician.this, Mcb.class));
                }
                if (position == 4)
                {
                    startActivity(new Intent(Electrician.this, Geyser.class));
                }

            }
        }));

    }

    private void initRV() {


        final SweetAlertDialog progressDialog = new SweetAlertDialog(Electrician.this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Api.getMainElectrician, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("apiiiiii" + Api.getMainCctv);
                Log.e("MAIN_CAT_RESPONSE", response);
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        RowItemMainElectrician cat = new RowItemMainElectrician(object.getString("id"), object.getString("cat"), object.getString("img"), object.getString("super_id"));
                        mainElectricianList.add(cat);
                    }

                    System.out.println("main cat list" +mainElectricianList);
                    MainElectricianAdapter adapter = new MainElectricianAdapter(Electrician.this, mainElectricianList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.e("MAIN_CAT_EXCEPTION", e.getMessage());
                }

                progressDialog.dismissWithAnimation();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e("MAIN_CAT_ERROR", error.getMessage());
                progressDialog.dismissWithAnimation();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<>();
                map.put("super_id", keys);
                return map;
            }
        };
        Volley.newRequestQueue(Electrician.this).add(request);
    }

    private void init() {

        recyclerView = findViewById(R.id.mainCateRV);
        mainElectricianList = new ArrayList<RowItemMainElectrician>();
    }

}
