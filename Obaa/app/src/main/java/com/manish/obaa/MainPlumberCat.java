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
import com.manish.obaa.ac.AcServiceAndRepairActivity;
import com.manish.obaa.adapter.MainCctvAdapter;
import com.manish.obaa.adapter.MainPlumberAdapter;
import com.manish.obaa.cooler.CooletRepairActivity;
import com.manish.obaa.plumber.Bathing;
import com.manish.obaa.plumber.Blockage;
import com.manish.obaa.plumber.Other;
import com.manish.obaa.plumber.Shower;
import com.manish.obaa.plumber.Sink;
import com.manish.obaa.plumber.Tapmixer;
import com.manish.obaa.plumber.Toilet;
import com.manish.obaa.plumber.Watertank;
import com.manish.obaa.plumber.Watertank1;
import com.manish.obaa.rowitem.RowItemMainCctv;
import com.manish.obaa.rowitem.RowItemMainPlumber;
import com.manish.obaa.utils.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPlumberCat extends AppCompatActivity {
    private RecyclerView recyclerView;
    //private List<RowItemMainCat> mainCatList;
    private List<RowItemMainPlumber> mainPlumberList;
    private String key;
    private String keys="3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plumber_cat);

        init();

        key = getIntent().getStringExtra("key");
        System.out.println("key is " +key);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initRV();

        setListener();
    }
    private void setListener() {

        recyclerView.addOnItemTouchListener(new RecyclerTouch(this, new RecyclerTouch.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (position == 0)
                {
                    startActivity(new Intent(MainPlumberCat.this, Toilet.class));
                }else if (position == 1)
                {
                    startActivity(new Intent(MainPlumberCat.this, Tapmixer.class));
                }
                else if (position == 2)
                {
                    startActivity(new Intent(MainPlumberCat.this, Blockage.class));
                }
                else if(position==3)
                {
                    startActivity(new Intent(MainPlumberCat.this, Bathing.class));
                }
                else if(position==4)
                {
                    startActivity(new Intent(MainPlumberCat.this, Watertank.class));
                }
                else if(position==5)
                {
                    startActivity(new Intent(MainPlumberCat.this, Shower.class));
                }
                else if(position==6)
                {
                    startActivity(new Intent(MainPlumberCat.this, Sink.class));
                }
                else if(position==7)
                {
                    startActivity(new Intent(MainPlumberCat.this, Other.class));
                }
                else if(position==8)
                {
                    startActivity(new Intent(MainPlumberCat.this, Watertank1.class));
                }

            }
        }));

    }
    private void initRV() {

        final SweetAlertDialog progressDialog = new SweetAlertDialog(MainPlumberCat.this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Api.getMainPlumber, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {
                System.out.println("apiiiiii" + Api.getMainCat);
                Log.e("MAIN_CCTV_RESPONSE", response);
                try {
                    System.out.println("inside try");
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        RowItemMainPlumber cat = new RowItemMainPlumber(object.getString("id"), object.getString("cat"), object.getString("img"), object.getString("super_id"));

                        mainPlumberList.add(cat);
                    }
                    System.out.println("main cat list" +mainPlumberList);
                    MainPlumberAdapter adapter = new MainPlumberAdapter(MainPlumberCat.this, mainPlumberList);
                    recyclerView.setAdapter(adapter);
                    System.out.println("zoozoo birthday");

                } catch (JSONException e) {
                    System.out.println("Inside catch");
                    Log.e("MAIN_CCTV_EXCEPTION", e.getMessage());
                }

                progressDialog.dismissWithAnimation();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MAIN_CAT_ERROR", error.getMessage());
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
        Volley.newRequestQueue(MainPlumberCat.this).add(request);
    }
    private void init() {

        recyclerView = findViewById(R.id.mainCatcRV);
        mainPlumberList = new ArrayList<>();
    }
}
