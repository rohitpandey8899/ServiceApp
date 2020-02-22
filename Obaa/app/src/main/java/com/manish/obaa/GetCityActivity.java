package com.manish.obaa;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.manish.obaa.LocationUtil.LocationHelper;
import com.manish.obaa.utils.Preference;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetCityActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback {




    /*private LinearLayout pickLocationLL;
    private Preference preference;
    private SearchView searchView;
    private ListView cityLV;
    private TextView pickCityTV;
    //private TextView pickCityTV;
    private ProgressBar locationProgressBar;*/
    @BindView(R.id.pickLocationLL)LinearLayout rlPick;
    private Location mLastLocation;

    double latitude;
    double longitude;

    LocationHelper locationHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_city);
        locationHelper = new LocationHelper(this);
        locationHelper.checkpermission();


        ButterKnife.bind(this);

        rlPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLastLocation = locationHelper.getLocation();

                if (mLastLocation != null) {
                    latitude = mLastLocation.getLatitude();
                    longitude = mLastLocation.getLongitude();
                    getAddress();

                }


            }


        });
        if (locationHelper.checkPlayServices()) {

            // Building the GoogleApi client
            locationHelper.buildGoogleApiClient();
        }
    }
    public void getAddress()
    {
        Address locationAddress;

        locationAddress=locationHelper.getAddress(latitude,longitude);

        if(locationAddress!=null)
        {

            String address = locationAddress.getAddressLine(0);
            String address1 = locationAddress.getAddressLine(1);
            String city = locationAddress.getLocality();
           // String state = locationAddress.getAdminArea();
            String country = locationAddress.getCountryName();
           // String postalCode = locationAddress.getPostalCode();


            String currentLocation="";

            if(!TextUtils.isEmpty(address))
            {
                currentLocation=address;

                if (!TextUtils.isEmpty(address1))
                    currentLocation=address1;

                if (!TextUtils.isEmpty(city))
                {
                    currentLocation+=" " +city;


                }


                if (!TextUtils.isEmpty(country))
                    currentLocation+=country;


            }
            System.out.println("location is " +currentLocation);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("data", currentLocation);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();

        }


    }
      

      /*  findViewById(R.id.backBTGetCity).setOnClickListener(this);
        findViewById(R.id.searchView).setOnClickListener(this);
    //    citiesRV = findViewById(R.id.citiesRV);
        cityLV = findViewById(R.id.cityLV);
        pickCityTV = findViewById(R.id.pickCityTV);
        pickLocationLL = findViewById(R.id.pickLocationLL);
        searchView = findViewById(R.id.searchView);
        preference = new Preference(GetCityActivity.this);
        locationProgressBar = findViewById(R.id.locationProgressBar);

 // ArrayAdapter adapter = new ArrayAdapter<>(GetCityActivity.this, android.R.layout.simple_list_item_1, ContactsContract.Contacts.Data.cities);
   //     cityLV.setAdapter(adapter);

        setListeners();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

    }

    private void setListeners() {

             /*  citiesRV.addOnItemTouchListener(new RecyclerTouch(GetCityActivity.this, new RecyclerTouch.ClickListener() {
         @Override
            public void onClick(View view, int position) {

                Intent resultIntent = new Intent();
                resultIntent.putExtra("data", list.get());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        }));*/

     /*  pickLocationLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCurrentCityName();

            }
        });*/

       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Toast.makeText(GetCityActivity.this, "Coming Soon...", Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        cityLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent resultIntent = new Intent();
                resultIntent.putExtra("data", parent.getItemAtPosition(position).toString());
                setResult(Activity.RESULT_OK, resultIntent);
                preference.setCity(parent.getItemAtPosition(position).toString());
                finish();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBTGetCity) {
            finish();
        }
    }

    private void getCurrentCityName() {

        locationProgressBar.setVisibility(View.VISIBLE);
        pickCityTV.setVisibility(View.GONE);

        LocationManager lm = (LocationManager) getSystemService(Activity.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Log.e("SSSSSS", "PPERMISSION_ERROR");
            return;
        }
        double longitude;
        double latitude;

        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        try {
            longitude = location.getLongitude();
            latitude = location.getLatitude();

        } catch (Exception e) {
            return;
        }

//        Log.e("SSSSSS1", String.valueOf(longitude));
//        Log.e("SSSSSS1", String.valueOf(latitude));

        Geocoder gcd = new Geocoder(GetCityActivity.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        assert addresses != null;
        String city = addresses.get(0).getLocality();

        if (addresses.size() > 0) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("data", city);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }*/


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}