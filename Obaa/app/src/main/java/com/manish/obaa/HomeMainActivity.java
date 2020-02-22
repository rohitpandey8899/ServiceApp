package com.manish.obaa;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.Homee.Carpet;
import com.manish.obaa.Homee.Sofa;
import com.manish.obaa.SweetAlert.SweetAlertDialog;
import com.manish.obaa.adapter.MainCctvAdapter;
import com.manish.obaa.adapter.MainHomeAdapter;
import com.manish.obaa.cctv.Uninstall;
import com.manish.obaa.cctv.install;
import com.manish.obaa.cctv.installtion;
import com.manish.obaa.rowitem.RowHome;
import com.manish.obaa.rowitem.RowItemHome;
import com.manish.obaa.rowitem.RowItemMainCctv;
import com.manish.obaa.utils.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RowHome> mainHomeList;
    private String key;
    private String keys="4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
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
                    startActivity(new Intent(HomeMainActivity.this, Sofa.class));
                }else if (position == 1)
                {
                    startActivity(new Intent(HomeMainActivity.this, Carpet.class));
                }
                /*else if(position==2)
                {
                    startActivity(new Intent(MainCctvActivity.this, Uninstall.class));
                }*/

            }
        }));

    }
    private void initRV() {

        final SweetAlertDialog progressDialog = new SweetAlertDialog(HomeMainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Api.getMainHome, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {
                System.out.println("apiiiiii" + Api.getMainHome);
                Log.e("MAIN_CCTVRESPONSE", response);
                try {
                    System.out.println("inside try");
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        RowHome cat = new RowHome(object.getString("id"), object.getString("cat"), object.getString("img"), object.getString("super_id"));

                        mainHomeList.add(cat);
                    }
                    System.out.println("main cat list" +mainHomeList);
                    MainHomeAdapter adapter = new MainHomeAdapter(HomeMainActivity.this, mainHomeList);
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
        Volley.newRequestQueue(HomeMainActivity.this).add(request);
    }
    private void init() {

        recyclerView = findViewById(R.id.mainCathRV);
        mainHomeList = new ArrayList<>();
    }
}
