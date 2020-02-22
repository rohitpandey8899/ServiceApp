package com.manish.obaa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.manish.obaa.adapter.MainCatAdapter;
import com.manish.obaa.cooler.CooletRepairActivity;
import com.manish.obaa.rowitem.RowItemMainCat;
import com.manish.obaa.utils.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RowItemMainCat> mainCatList;
    private String key;
    private String keys="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cat);


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
        //System.out.println("555");

    }

    private void setListener() {

        recyclerView.addOnItemTouchListener(new RecyclerTouch(this, new RecyclerTouch.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (position == 0)
                {
                    startActivity(new Intent(MainCatActivity.this, AcServiceAndRepairActivity.class));
                }else if (position == 1)
                {
                    startActivity(new Intent(MainCatActivity.this, CooletRepairActivity.class));
                }

            }
        }));

    }

    private void initRV() {


        final SweetAlertDialog progressDialog = new SweetAlertDialog(MainCatActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        progressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        progressDialog.setTitleText("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Api.getMainCat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("apiiiiii" + Api.getMainCctv);
                Log.e("MAIN_CAT_RESPONSE", response);
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String imge=object.getString("img");
                        System.out.println("image gone" +imge);

                        RowItemMainCat cat = new RowItemMainCat(object.getString("id"), object.getString("cat"), object.getString("img"), object.getString("super_id"));
                        mainCatList.add(cat);
                    }

                    System.out.println("main cat list" +mainCatList);
                    MainCatAdapter adapter = new MainCatAdapter(MainCatActivity.this, mainCatList);
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
        Volley.newRequestQueue(MainCatActivity.this).add(request);
    }

    private void init() {

        recyclerView = findViewById(R.id.mainCatRV);
        mainCatList = new ArrayList<>();
    }
}
