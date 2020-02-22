package com.manish.obaa;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.adapter.AddressAdapter;
import com.manish.obaa.rowitem.RowItemAddress;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.Preference;
import com.manish.obaa.utils.SQLiteHelper;
import com.manish.obaa.utils.Toasty;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddressActivity extends AppCompatActivity {


    LocationManager locationManager;
    LocationListener locationListener;

    private SQLiteHelper helper;
    private RecyclerView addressRV;
    private List<RowItemAddress> addressList;
    private LinearLayout emptyAddressLL;
    private TextView addNewAddressTV;
    private Preference preference;
    private Button addAddressBT;
    private Button book;
    private Button bookcancel;
    SQLiteDatabase sqdb;
    private String service, subTotal, date, time,username,usercontact;
    private String name ,contact;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }

            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        init();

        Intent intent = getIntent();
        service = intent.getStringExtra("service");
        System.out.println("subtotal isss" +service );
        subTotal = intent.getStringExtra("subtotal");
        System.out.println("subtotal isss" +subTotal );
        date = intent.getStringExtra("date");
        System.out.println("date isss" +date);
        time = intent.getStringExtra("time");
        System.out.println("time isss" +time );
        name=intent.getStringExtra("key");

        contact=intent.getStringExtra("keey");
        System.out.println("name is " +name);
        System.out.println("contact is "+contact);
        addressRV.setLayoutManager(new LinearLayoutManager(this));

        getAddress();

//        getAddressDialog();

        addAddressBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddressDialog();
            }
        });
            bookcancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AddressActivity.this,"clicked",Toast.LENGTH_SHORT).show();
                    Toast.makeText(AddressActivity.this,"Delted",Toast.LENGTH_SHORT).show();

                }
            });
        addNewAddressTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAddressDialog();
            }
        });


        addressRV.addOnItemTouchListener(new RecyclerTouch(this, new RecyclerTouch.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                String address = addressList.get(position).getHouseNo()+","+addressList.get(position).getColony()+","+addressList.get(position).getCity();
                book(address);

            }
        }));

    }


    private void getAddress() {

      /*  if (addressList.size() > 0)
        {
            addressList.removeAll(addressList);
            System.out.println("address list count " +addressList.size());

        }*/

        SQLiteHelper helper = new SQLiteHelper(this);

        Cursor cursor = helper.getAddressData();
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                username=cursor.getString(1);
                usercontact=cursor.getString(2);
                System.out.println("nameeeeeee is " +username +"contacttttt is"+usercontact);
                String houseNo = cursor.getString(3);
                String colony = cursor.getString(4);
                String city = cursor.getString(5);
//                String pinCode = cursor.getString(4);
                System.out.println("id is " +id + "houseNo is " + houseNo + "colony is " + colony + "city is " +city);
                 //System.out.println("nameeeeeeeeeeeeeee is " +preference.getName());
                //System.out.println("ling is " +preference.getGender());
                //System.out.println("contact number is " +preference.getContactNo());
                //System.out.println("email is " +preference.getEmail());

                //TO CHANGE
                RowItemAddress item = new RowItemAddress(id, username, houseNo, colony, city);
                System.out.println("name is " +username);
                if (addressList.size() > 0)
                {
                    addressList.removeAll(addressList);
                    System.out.println("address list count " +addressList.size());

                }
                addressList.add(item);

            } while (cursor.moveToNext());

            if (addressList.size() == 0) {
                addressRV.setVisibility(View.GONE);
                addNewAddressTV.setVisibility(View.GONE);
                emptyAddressLL.setVisibility(View.VISIBLE);

            } else {

                AddressAdapter adapter = new AddressAdapter(this, addressList);
                addressRV.setAdapter(adapter);
                addressRV.setVisibility(View.VISIBLE);
                addNewAddressTV.setVisibility(View.VISIBLE);
                emptyAddressLL.setVisibility(View.GONE);
            }

        }
    }

    private void init() {
        //book=findViewById(R.id.book);
        bookcancel=findViewById(R.id.bookcancel);
        addressRV = findViewById(R.id.addressRV);
        emptyAddressLL = findViewById(R.id.emptyAddressLL);
        addAddressBT = findViewById(R.id.addAddressBT);
        addNewAddressTV = findViewById(R.id.addNewAddressTV);
        addressList = new ArrayList<>();
        preference = new Preference(this);
        helper = new SQLiteHelper(this);

      /*  sqdb=openOrCreateDatabase("loginuser.db",MODE_PRIVATE,null);
        String query="Create table if not exists LoginUser(name varchar not null,contact varchar primary key)";
        sqdb.execSQL(query);
        String q1="select * from LoginUser";
        Cursor c=sqdb.rawQuery(q1,null);
        c.moveToLast();
        String contact=c.getString(0);
        String name=c.getString(1);
        System.out.println("name is " +name);
        System.out.println("contact is " +contact);
          */

    }


    private void getAddressDialog() {

        final Dialog view = new Dialog(this);
        view.setContentView(R.layout.get_address_dialog);
//        LinearLayout getAddressLL;
        final EditText nameET;
        final EditText contactET;
        final EditText houseNoAddressET;
        final EditText colonyAddressET;
        final EditText stateAddressET;
        final EditText pincodeET;
        Button saveAddressBT;
//        getAddressLL = (LinearLayout) view.findViewById(R.id.getAddressLL);
        houseNoAddressET = view.findViewById(R.id.houseNoAddressET);
        nameET=view.findViewById(R.id.name);
        contactET=view.findViewById(R.id.contact);
        colonyAddressET = view.findViewById(R.id.colonyAddressET);
        stateAddressET = view.findViewById(R.id.stateAddressET);
        saveAddressBT =  view.findViewById(R.id.saveAddressBT);
        pincodeET = view.findViewById(R.id.pincodeET);



        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try{

                    List <Address>add = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);

                    if(add !=null && add.size() >= 0){


                        if(add.get(0).getFeatureName() != null) {

                            houseNoAddressET.setText(add.get(0).getAddressLine(0));

                            if (add.get(0).getAdminArea() != null) {
                                stateAddressET.setText(add.get(0).getAdminArea() + "/" + add.get(0).getCountryName());
                                if (add.get(0).getLocality() != null) {
                                    colonyAddressET.setText(add.get(0).getLocality());
                                    if (add.get(0).getPostalCode() != null) {
                                        pincodeET.setText(add.get(0).getPostalCode());
                                    }

                                }

                            }
                        }

                    }



                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }



        view.show();

       saveAddressBT.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name=nameET.getText().toString();
               String contact=contactET.getText().toString();
               System.out.println("name is " +name +"contact is "+contact);
               String houseNo = houseNoAddressET.getText().toString();
               String colony = colonyAddressET.getText().toString();
               String city = stateAddressET.getText().toString();

               if (houseNo.isEmpty())
               {
                   Toast.makeText(AddressActivity.this, "Please Enter House No.", Toast.LENGTH_SHORT).show();
               }else if (colony.isEmpty())
               {
                   Toast.makeText(AddressActivity.this, "Please Enter Colony.", Toast.LENGTH_SHORT).show();
               }
              // else if (name.isEmpty())
               //{
                 //  Toast.makeText(AddressActivity.this, "Please Enter Your Name.", Toast.LENGTH_SHORT).show();
               //}
//               else if (contact.isEmpty())
  //             {
    //               Toast.makeText(AddressActivity.this, "Please Enter Your Contact.", Toast.LENGTH_SHORT).show();
      //         }
               else if (city.isEmpty())
               {
                   Toast.makeText(AddressActivity.this, "Please Enter  City.", Toast.LENGTH_SHORT).show();
               }else {

                   boolean b = helper.addAddress(name,contact,houseNo, colony, city);
                   if (b) {
                       Toast.makeText(AddressActivity.this, "Saved.", Toast.LENGTH_SHORT).show();
                       view.dismiss();
                       getAddress();
                   } else {

                       Toast.makeText(AddressActivity.this, "Some Error Occurred.", Toast.LENGTH_SHORT).show();
                   }
               }

           }
       });

    }

    private void book(final String address)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        preference = new Preference(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Api.book, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response is " +response);
                Log.e("BOOKING_RESPONSE", response);

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("success").equals("TRUE"))
                    {
                        progressDialog.dismiss();
                        finish();
                        Toasty.success(AddressActivity.this, "Booking SuccessfulLLLL.", Toasty.LENGTH_SHORT);
                        Intent i=new Intent(AddressActivity.this,Successfull.class);
                        i.putExtra("name",username);
                        i.putExtra("contact",usercontact);
                        startActivity(i);

                    }

                } catch (JSONException e) {

                    Toasty.error(AddressActivity.this, "Something Went Wrong.", Toasty.LENGTH_SHORT);
                }

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
                map.put("name", preference.getName());
               // System.out.println("name isssss " +preference.getName());
                map.put("user_id", preference.getUserId());
              System.out.println("userrrrrrrrrrrr id " +preference.getUserId());
              map.put("name",username);
              map.put("contact",usercontact);
                map.put("services", service);
                //System.out.println("service is " +service);
                map.put("total", subTotal);
                //System.out.println("total"+subTotal);
                map.put("date_time", date+"  "+time);
                System.out.println("dateeee is " + date + "timeee is " +time);
                map.put("address", address);
               // map.put("contact",contactee);
               // map.put("name",nameee);
                return map;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }


}
