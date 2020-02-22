package com.manish.obaa.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.MainActivity;
import com.manish.obaa.R;
import com.manish.obaa.SweetAlert.SweetAlertDialog;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.Preference;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText contactET;
    private EditText passwordET;
    private Button loginBT;
    private TextView signUpTV;
    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        setListener();

    }

    private void setListener() {

        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void init() {
        contactET = findViewById(R.id.contactET);
        passwordET = findViewById(R.id.passwordET);
        loginBT = findViewById(R.id.loginBT);
        signUpTV = findViewById(R.id.signUpTV);
        preference = new Preference(this);
    }

    private void validate() {
        String contact = contactET.getText().toString();
        String password = passwordET.getText().toString();

        if (contact.isEmpty()) {

            errorToast("Please Enter Your Contact No.");

        } else if (contact.length() < 10) {

            errorToast("Contact must be 10 digit.");

        } else if (password.isEmpty()) {

            errorToast("Please Enter Your Password.");

        } else {

            login(contact, password);
        }

    }

    private void login(final String contact, final String password) {

        StringRequest request = new StringRequest(Request.Method.POST, Api.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("LOGIN_RESPONSE", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("success").equals("TRUE")) {

                        successToast(object.getString("message"));

                        preference.setName(object.getString("name"));
                        preference.setEmail(object.getString("email"));
                        preference.setContactNo(contact);
                        preference.setUserId(object.getString("user_id"));
                        preference.setLogin(true);

                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();

                    } else {

                        showAlert(object.getString("message"));
                    }

                } catch (Exception e) {
                    Log.e("LOGIN_EXCEPTION", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN_ERROR", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<>();
                map.put("contact", contact);
                map.put("password", password);
                return map;
            }
        };
        Volley.newRequestQueue(Login.this).add(request);

    }

    private void showAlert(String message) {

        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(message)
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        //finish();
                    }
                })
                .show();

    }

    private void errorToast(String msg) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Toasty.error(Login.this, msg, Toasty.LENGTH_SHORT);
//        } else {
        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
//        }
    }

    private void successToast(String msg) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Toasty.success(Login.this, msg, Toasty.LENGTH_SHORT);
//        } else {
        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
//        }
    }
}