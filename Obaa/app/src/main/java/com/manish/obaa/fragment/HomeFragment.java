package com.manish.obaa.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.Camera;
import com.manish.obaa.Electrician;
import com.manish.obaa.EventOrg;
import com.manish.obaa.Fabrication;
import com.manish.obaa.HomeAppliance.Laptop;
import com.manish.obaa.HomeAppliances;
import com.manish.obaa.HomeCleanning;
import com.manish.obaa.HomePets;
import com.manish.obaa.MainCatActivity;
import com.manish.obaa.MainCctvActivity;
import com.manish.obaa.MainPlumberCat;
import com.manish.obaa.Makeup;
import com.manish.obaa.Mehandi;
import com.manish.obaa.PackerMover;
import com.manish.obaa.Painter;
import com.manish.obaa.PrinterArt;
import com.manish.obaa.R;
import com.manish.obaa.Saloon;
import com.manish.obaa.TattoArt;
import com.manish.obaa.adapter.EndlessPagerAdapter;
import com.manish.obaa.adapter.SliderAdapter;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.AutoScrollViewpager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {}


    private LinearLayout homeApplianceGridLL;
    private LinearLayout electricianll;
    private LinearLayout plumberll;
    private LinearLayout acRepairll;


    private RelativeLayout cctvRl;
    private RelativeLayout laptopRl;
    private RelativeLayout homeCleaningRL;
    private RelativeLayout painterRl;
    private RelativeLayout pestRl;
    private RelativeLayout packerRl;
    private RelativeLayout carpanterRl;
    private RelativeLayout fabRl;
    private RelativeLayout tattoRl;
    private RelativeLayout mehndiRl;
    private RelativeLayout photoRl;
    private RelativeLayout eventRl;


    private LinearLayout saloonLL;
    private LinearLayout makeupLL;

    private static final String[] names = new String[]
            {"Home Cleaning","Home Appliance","Ro","washing Meaching","fridge","fan","tatto","sofa","electrican","Air Conditioner","Plumber","saloon"
                    ,"Beautician","cctv","laptop"};






    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        showSlider(view);
        showPartnersSlider(view);



        homeApplianceGridLL = view.findViewById(R.id.home_applianceGridRL);
        electricianll = view.findViewById(R.id.electricianGridRL);
        plumberll = view.findViewById(R.id.plumberGridRL);
        acRepairll = view.findViewById(R.id.acRepairGridRL);


        cctvRl = view.findViewById(R.id.cctvLL);
        laptopRl = view.findViewById(R.id.laptopll);
        homeCleaningRL = view.findViewById(R.id.homeCleaningLL);
        painterRl = view.findViewById(R.id.painterGridRL);
        pestRl = view.findViewById(R.id.home_pest);
        packerRl = view.findViewById(R.id.packer);
        carpanterRl = view.findViewById(R.id.carpainter);
        fabRl = view.findViewById(R.id.fabriction);
        tattoRl = view.findViewById(R.id.TattoArtGridRL);
        mehndiRl = view.findViewById(R.id.mehandi);
        photoRl = view.findViewById(R.id.cemra);
        eventRl = view.findViewById(R.id.event);

        saloonLL = view.findViewById(R.id.saloon);
        makeupLL = view.findViewById(R.id.makeup);



        final AutoCompleteTextView area = view.findViewById(R.id.searchET);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,names);
        area.setAdapter(adapter);




        final String n = area.getText().toString();

        area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String n= adapter.getItem(position);

                 //startActivity(new Intent(getContext(),));

                Toast.makeText(getContext(),n,Toast.LENGTH_LONG).show();
            }
        });




        setListener();

        return view;
    }

    private void setListener() {
        homeApplianceGridLL.setOnClickListener(this);
        electricianll.setOnClickListener(this);
        plumberll.setOnClickListener(this);
        acRepairll.setOnClickListener(this);


        cctvRl.setOnClickListener(this);
        laptopRl.setOnClickListener(this);
        homeCleaningRL.setOnClickListener(this);
        painterRl.setOnClickListener(this);
        pestRl.setOnClickListener(this);
        packerRl.setOnClickListener(this);
        tattoRl.setOnClickListener(this);
        mehndiRl.setOnClickListener(this);
        photoRl.setOnClickListener(this);
        eventRl.setOnClickListener(this);
        fabRl.setOnClickListener(this);
        carpanterRl.setOnClickListener(this);


        saloonLL.setOnClickListener(this);
        makeupLL.setOnClickListener(this);
    }

    private void showSlider(final View view) {

        final List<String> listt = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, Api.getSlides, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              Log.e("HOME_RESPONSE", response);
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        String image = object.getString("image");

                        System.out.println("imgeeeee"+image);
                        String url = Api.BASE_URL+"img/"+image;
                  Log.e("IMAGE_URL", url);

                        listt.add(url);
                    }

                    AutoScrollViewpager viewPager;
                    viewPager = view.findViewById(R.id.viewpager);
                    viewPager.startAutoScroll();
                    viewPager.setInterval(3000);
                    viewPager.animate();
                    viewPager.setCycle(true);
                    viewPager.setStopScrollWhenTouch(true);
                    viewPager.scheduleLayoutAnimation();

                    SliderAdapter adapter = new SliderAdapter(getActivity(), listt);
                    viewPager.setAdapter(adapter);

                    EndlessPagerAdapter endlessPagerAdapter = new EndlessPagerAdapter(adapter, viewPager);
                    viewPager.setAdapter(endlessPagerAdapter);
                    viewPager.setCurrentItem(1);

                } catch (JSONException e) {
//                    Log.e("HOME_EXCEPTION", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                Log.e("HOME_ERROR", error.getMessage());
            }
        });
        Volley.newRequestQueue(Objects.requireNonNull(getContext())).add(request);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.laptopll:
                Intent intent = new Intent(getContext(), Laptop.class);
             //   intent.putExtra("key", KEYS.AC_REPAIR);
                startActivity(intent);
                break;
            case R.id.cctvLL:
                Intent i = new Intent(getContext(), MainCctvActivity.class);
                //intent.putExtra("key", KEYS.CCTV);
                startActivity(i);
                break;
          case R.id.packer:
               // intent.putExtra("key", KEYS.PLUMBER);
              Intent in = new Intent(getContext(), PackerMover.class);
              startActivity(in);
                break;
            case R.id.homeCleaningLL:
                Intent in2 = new Intent(getContext(), HomeCleanning.class);
                startActivity(in2);
                break;
            case R.id.acRepairGridRL:
                Intent inn = new Intent(getContext(), MainCatActivity.class);
                startActivity(inn);
                break;
            case R.id.cemra:
                Intent innn = new Intent(getContext(), Camera.class);
                startActivity(innn);
                break;

            case R.id.electricianGridRL:
                Intent inn1 = new Intent(getContext(), Electrician.class);
                startActivity(inn1);
                break;
            case R.id.plumberGridRL:
                Intent innnn = new Intent(getContext(), MainPlumberCat.class);
                startActivity(innnn);
                break;

            case R.id.home_applianceGridRL:
                Intent in3 = new Intent(getContext(), HomeAppliances.class);
                startActivity(in3);
                break;
            case R.id.painterGridRL:
                Intent innnnn = new Intent(getContext(), Painter.class);
                startActivity(innnnn);
                break;

            case R.id.TattoArtGridRL:
                Intent ii = new Intent(getContext(), TattoArt.class);
                startActivity(ii);
               //Toast.makeText(getContext(),"TattoArt",Toast.LENGTH_LONG).show();
               // Toast.makeText(getContext(),"TattoArt",Toast.LENGTH_LONG).show();
                break;

            case R.id.mehandi:
                startActivity(new Intent(getContext(), Mehandi.class));
                //Toast.makeText(getContext(),"Mendhi",Toast.LENGTH_LONG).show();
                break;
            case R.id.fabriction:
                startActivity(new Intent(getContext(), Fabrication.class));
               // Toast.makeText(getContext(),"Fabrication",Toast.LENGTH_LONG).show();
                break;
            case R.id.carpainter:
                startActivity(new Intent(getContext(), PrinterArt.class));
                break;
            case R.id.home_pest:
                        startActivity(new Intent(getContext(), HomePets.class));
                //Toast.makeText(getContext(),"HomePestController",Toast.LENGTH_LONG).show();
                break;
            case R.id.event:
                startActivity(new Intent(getContext(), EventOrg.class));
                //Toast.makeText(getContext(),"Home Cleaning",Toast.LENGTH_LONG).show();
                break;

            case R.id.saloon:
                startActivity(new Intent(getContext(), Saloon.class));
                //Toast.makeText(getContext(),"Home Cleaning",Toast.LENGTH_LONG).show();
                break;
            case R.id.makeup:
                startActivity(new Intent(getContext(), Makeup.class));
                //Toast.makeText(getContext(),"Home Cleaning",Toast.LENGTH_LONG).show();
                break;
        }
    }


    private void showPartnersSlider(final View view) {

        final List<String> partnerList = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, Api.getPartnersSlides, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              Log.e("HOME_RESPONSE", response);
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        String image = object.getString("image");
                        String url = Api.BASE_URL+"img/"+image;
                        partnerList.add(url);
                    }

                    AutoScrollViewpager partnersViewPager;
                    partnersViewPager = view.findViewById(R.id.partnersViewPager);
                    partnersViewPager.startAutoScroll();
                    partnersViewPager.setInterval(3000);
                    partnersViewPager.animate();
                    partnersViewPager.setCycle(true);
                    partnersViewPager.setStopScrollWhenTouch(true);
                    partnersViewPager.scheduleLayoutAnimation();

                    SliderAdapter adapter = new SliderAdapter(getActivity(), partnerList);
                    partnersViewPager.setAdapter(adapter);

                    EndlessPagerAdapter endlessPagerAdapter = new EndlessPagerAdapter(adapter, partnersViewPager);
                    partnersViewPager.setAdapter(endlessPagerAdapter);
                    partnersViewPager.setCurrentItem(1);

                } catch (JSONException e) {
                    Log.e("HOME_EXCEPTION", e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("HOME_ERROR", error.getMessage());
            }
        });
        Volley.newRequestQueue(Objects.requireNonNull(getContext())).add(request);


    }

}