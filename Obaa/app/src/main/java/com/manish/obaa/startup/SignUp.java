package com.manish.obaa.startup;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText nameRegET;
    private EditText emailRegET;
    private EditText contactRegET;
    private EditText passwordRegET;
    private EditText confirmPasswordRegET;
    private TextView loginTV;
    private Button signUpBT;
    private Preference preference;

    private String GENDER = "";
    private RelativeLayout maleRL;
    private RelativeLayout femaleRL;
    private TextView maleTV;
    private TextView femaleTV;
    private ImageView maleIV, femaleIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        setListener();

    }

    private void setListener() {

        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

    }

    private void init() {

        nameRegET = findViewById(R.id.nameRegET);
        emailRegET = findViewById(R.id.emailRegET);
        contactRegET = findViewById(R.id.contactRegET);
        passwordRegET = findViewById(R.id.passwordRegET);
        confirmPasswordRegET = findViewById(R.id.confirmPasswordRegET);
        loginTV = findViewById(R.id.loginTV);
        signUpBT = findViewById(R.id.signUpBT);
        preference = new Preference(this);

        maleRL = findViewById(R.id.maleRL);
        femaleRL = findViewById(R.id.femaleRL);
        maleIV = findViewById(R.id.maleIV);
        femaleIV = findViewById(R.id.femaleIV);
        maleRL.setOnClickListener(this);
        femaleRL.setOnClickListener(this);
        maleTV = findViewById(R.id.maleTV);
        femaleTV = findViewById(R.id.femaleTV);

        maleIV.setBackgroundResource(R.drawable.male_user_black);
        femaleIV.setBackgroundResource(R.drawable.female_user_black);

    }


    private void validate() {
        String name = nameRegET.getText().toString();
        String email = emailRegET.getText().toString();
        String contact = contactRegET.getText().toString();
        String password = passwordRegET.getText().toString();
        String confirmPassword = confirmPasswordRegET.getText().toString();

        if (name.isEmpty()) {

            showAlert("Enter Your Name.");

        } else if (email.isEmpty()) {

            showAlert("Enter Your Email.");

        } else if (contact.isEmpty()) {

            showAlert("Enter Your Contact No.");

        } else if (contact.length() < 10) {

            showAlert("Contact No Must be 10 digit.");

        } else if (password.isEmpty()) {

            showAlert("Enter Your Password.");

        } else if (confirmPassword.isEmpty()) {

            showAlert("Enter Your Confirm Password.");

        } else if (!password.equals(confirmPassword)) {

            showAlert("Password and confirm password not match.");

        } else if (GENDER.length() == 0) {

            showAlert("Select Gender.");

        } else {

            signUp(name, email, contact, password);
        }

    }

    private void signUp(final String name, final String email, final String contact, final String password) {

        StringRequest request = new StringRequest(Request.Method.POST, Api.register, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(String response) {

                Log.e("REGISTER_RESPONSE", response);

                try {

                    JSONObject object = new JSONObject(response);
                    if (object.getString("success").equals("TRUE")) {
                        Toast.makeText(SignUp.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        //System.out.println("nameeee  is " +name);
                        preference.setName(name);
                        System.out.println("nameeee  is " +name);
                        preference.setEmail(email);
                        preference.setContactNo(contact);
                        preference.setUserId(object.getString("user_id"));
                        preference.setLogin(true);

                        startActivity(new Intent(SignUp.this, MainActivity.class));
                        finish();

                    } else {

//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            Toasty.error(SignUp.this, object.getString("message"), Toast.LENGTH_SHORT);
//                        } else {
                            Toast.makeText(SignUp.this, object.getString("message"), Toast.LENGTH_SHORT).show();
//                        }

                    }

                } catch (Exception e) {
                    Log.e("REGISTER_EXCEPTION", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("REGISTER_ERROR", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("email", email);
                map.put("contact", contact);
                map.put("password", password);
                map.put("gender", GENDER);
                return map;
            }
        };
        Volley.newRequestQueue(SignUp.this).add(request);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.maleRL:
                male();
                break;
            case R.id.femaleRL:
                female();
                break;
        }

    }

    private void female() {

        GENDER = "Female";
        femaleRL.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        femaleTV.setTextColor(getResources().getColor(R.color.white));
        femaleIV.setBackgroundResource(R.drawable.female_user_white);

        maleRL.setBackgroundColor(getResources().getColor(R.color.white));
        maleTV.setTextColor(getResources().getColor(R.color.black));
        maleIV.setBackgroundResource(R.drawable.male_user_black);

    }

    private void male() {

        GENDER = "male";
        maleRL.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        maleTV.setTextColor(getResources().getColor(R.color.white));
        maleIV.setBackgroundResource(R.drawable.male_user_white);

        femaleRL.setBackgroundColor(getResources().getColor(R.color.white));
        femaleTV.setTextColor(getResources().getColor(R.color.black));
        femaleIV.setBackgroundResource(R.drawable.female_user_black);
    }

    private void showAlert(String message) {

        new SweetAlertDialog(SignUp.this, SweetAlertDialog.ERROR_TYPE)
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
}