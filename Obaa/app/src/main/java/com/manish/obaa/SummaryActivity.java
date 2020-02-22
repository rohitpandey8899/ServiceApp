package com.manish.obaa;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.startup.Login;
import com.manish.obaa.startup.SignUp;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.Preference;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.manish.obaa.utils.Api.login;

public class SummaryActivity extends AppCompatActivity {

    private TextView showSelectedServiceTV;
    private TextView subTotalTV2;
    private Button nextBT;

    private TextView dateTV,timeTV;
    private String DATE = "",TIME = "";
    private int myear, mmonth, mday, hh, min;
    private static final int DATE_DIALOG_ID = 999;
    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        showSelectedServiceTV = findViewById(R.id.showSelectedServiceTV);
        subTotalTV2 = findViewById(R.id.subTotalTV2);
        nextBT = findViewById(R.id.nextBT);
        dateTV = findViewById(R.id.dateTV);
        timeTV = findViewById(R.id.timeTV);
        preference=new Preference(this);
        setCurrentDateOnView();

        final String selectedServices = getIntent().getStringExtra("services");
        System.out.println("selected service is "+selectedServices);
        final String subTotal = getIntent().getStringExtra("subTotal");
        System.out.println("zozoo next bday "+subTotal);

        showSelectedServiceTV.setText(selectedServices);
        subTotalTV2.setText(subTotal);

        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (DATE.isEmpty())
                {
                    Toast.makeText(SummaryActivity.this, "Please Select Date.", Toast.LENGTH_SHORT).show();
                }else
                if (TIME.isEmpty())
                {
                    Toast.makeText(SummaryActivity.this, "Please Select Time.", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println("suubka total is " +subTotal);

                    Intent intent = new Intent(SummaryActivity.this, AddressActivity.class);
                    intent.putExtra("service", selectedServices);
                    intent.putExtra("subtotal", subTotal);
                    intent.putExtra("date", DATE);
                    intent.putExtra("time", TIME);
                    startActivity(intent);
                }

            }
        });

        dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(DATE_DIALOG_ID);

            }
        });
        timeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePickerDialog();

            }
        });
       /* nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog myDialog=new Dialog(SummaryActivity.this);
                myDialog.setContentView(R.layout.popupwindow);
                final EditText nameET=(EditText)myDialog.findViewById(R.id.nameET);
                final EditText contactET=(EditText)myDialog.findViewById(R.id.contactET);
                Button loginBT=(Button)myDialog.findViewById(R.id.loginBT);
                TextView txtClose=(TextView)myDialog.findViewById(R.id.txtClose);
                txtClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
                loginBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name=String.valueOf(nameET.getText());
                        String contact=String.valueOf(contactET.getText());
                        validate(name,contact);
                        /*if(!nameET.getText().toString().isEmpty() && !contactET.getText().toString().isEmpty())
                        {
                            SQLiteDatabase sqdb;
                            sqdb=openOrCreateDatabase("loginuser.db",MODE_PRIVATE,null);
                            String query="Create table if not exists LoginUser(name varchar not null,contact varchar primary key)";
                            try
                            {

                               // String q1="insert into LoginUser values('"+name+"','"+contact+"')";
                                logiin(name,contact);
                                Toast.makeText(SummaryActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                                nameET.setText("");
                                contactET.setText("");
                                Intent intent=new Intent(SummaryActivity.this,AddressActivity.class);
                                startActivity(intent);
                            }
                            catch(Exception e)
                            {
                                System.out.println(e.getMessage());
                            }
                        }
                      //  else
                        //{
                          //  Toast.makeText(SummaryActivity.this,"Error occured",Toast.LENGTH_LONG).show();
                        //}
                    }
                });
                myDialog.show();

            }
        });
    }*/



    //  else
    //{
    //  Toast.makeText(SummaryActivity.this,"Error occured",Toast.LENGTH_LONG).show();
    //}

      /*  StringRequest request = new StringRequest(Request.Method.POST, Api.register, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(String response) {

                Log.e("REGISTER_RESPONSE", response);

                try {

                    JSONObject object = new JSONObject(response);
                    if (object.getString("success").equals("TRUE")) {
                        Toast.makeText(SummaryActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        //System.out.println("nameeee  is " +name);
                        preference.setName(name);
                        //System.out.println("nameeee  is " +name);
                        ///preference.setEmail(email);
                        preference.setContactNo(contact);
                        preference.setUserId(object.getString("user_id"));
                        preference.setLogin(true);

                        startActivity(new Intent(SummaryActivity.this,AddressActivity.class));
                        finish();

                    } else {

//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            Toasty.error(SignUp.this, object.getString("message"), Toast.LENGTH_SHORT);
//                        } else {
                        Toast.makeText(SummaryActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
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
                //map.put("email", email);
                map.put("contact", contact);
                //map.put("password", password);
                //map.put("gender", GENDER);
                return map;
            }
        };
        Volley.newRequestQueue(SummaryActivity.this).add(request);*/

    }

    private void successToast(String message) {
        Toast.makeText(SummaryActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        if (id == DATE_DIALOG_ID) {// set date picker as current date
            return new DatePickerDialog(this, datePickerListener, myear, mmonth, mday) {
                @Override
                public void onDateChanged(@NonNull DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (year < myear)
                        view.updateDate(myear, mmonth, mday);
                    if (monthOfYear < mmonth && year == myear)
                        view.updateDate(myear, mmonth, mday);
                    if (dayOfMonth < mday && year == myear && monthOfYear == mmonth)
                        view.updateDate(myear, mmonth, mday);

                }
            };
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            myear = selectedYear;
            mmonth = selectedMonth;
            mday = selectedDay;
            DATE = mday + "-" + (mmonth + 1) + "-" + myear + " ";
            dateTV.setText(DATE);

        }
    };

    // display current date
    public void setCurrentDateOnView() {

        ///tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        final Calendar c = Calendar.getInstance();
        myear = c.get(Calendar.YEAR);
        mmonth = c.get(Calendar.MONTH);
        mday = c.get(Calendar.DAY_OF_MONTH);

    }

    private void showTimePickerDialog() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        hh = c.get(Calendar.HOUR_OF_DAY);
        hh = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String am_pm = null;
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);

                        if (c.get(Calendar.AM_PM) == Calendar.AM)
                            am_pm = "AM";
                        else if (c.get(Calendar.AM_PM) == Calendar.PM)
                            am_pm = "PM";

                        TIME = hourOfDay + ":" + minute +" "+am_pm;
                        timeTV.setText(TIME);

                    }
                }, hh, min, false);

        timePickerDialog.show();
    }


}