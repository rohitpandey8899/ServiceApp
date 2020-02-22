package com.manish.obaa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.adapter.EndlessPagerAdapter;
import com.manish.obaa.adapter.SliderAdapter;
import com.manish.obaa.startup.Login;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.AutoScrollViewpager;
import com.manish.obaa.utils.SQLiteHelper;
import com.manish.obaa.utils.Toasty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Successfull extends AppCompatActivity{
    private Button cancel;
    private String username,usercontact;
   private TextView Booking;
   private String contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfull);
        cancel=(Button)findViewById(R.id.cancel);
        Booking=(TextView)findViewById(R.id.booking);
        Intent i=getIntent();
        username=i.getStringExtra("name");
        usercontact=i.getStringExtra("contact");
        System.out.println("username iss" +username);
        System.out.println("usercontact iss" +usercontact);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete();
            }
        });



    }

    private void delete()
    {

        StringRequest request = new StringRequest(Request.Method.POST, Api.delete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("LOGIN_RESPONSE", response);


               try {

                    /* JSONObject object = new JSONObject(response);
                    if (object.getString("success").equals("TRUE")) {

                        System.out.println("success message delete "+object.getString("message"));

                        //preference.setName(object.getString("name"));
                        //preference.setEmail(object.getString("email"));
                        //preference.setContactNo(contact);
                       // preference.setUserId(object.getString("user_id"));
                        //preference.setLogin(true);

                        //startActivity(new Intent(Login.this,Yiur MainActivity.class));
                        //finish();

                    } else {
                        System.out.println("failure message delete "+object.getString("message"));



                    }*/

                } catch (Exception e) {
                    Log.e("LOGIN_EXCEPTION", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN_ERROR", error.getMessage());
            }
        });
        Volley.newRequestQueue(Successfull.this).add(request);
        Toast.makeText(this,"Your booking is cancel",Toast.LENGTH_LONG).show();

    }

}


