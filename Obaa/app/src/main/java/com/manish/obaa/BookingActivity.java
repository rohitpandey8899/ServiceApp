package com.manish.obaa;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.adapter.MainCatAdapter;
import com.manish.obaa.rowitem.RowItemMainCat;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.Preference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {

    private Preference preference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        init();

        StringRequest request = new StringRequest(Request.Method.POST, Api.getMainCat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Booking response"+response);
                Log.e("BOOKING_RESPONSE", response);

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("BOOKING_ERROR", error.getMessage());
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<>();
//                map.put("name", key);
//                map.put("user_id", key);
//                map.put("servisuper_idces", key);
//                map.put("", key);
//                map.put("super_id", key);
                return map;
            }
        };
        Volley.newRequestQueue(BookingActivity.this).add(request);


    }

    private void init() {

        preference = new Preference(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
    }




}