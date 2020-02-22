package com.manish.obaa.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.manish.obaa.MainActivity;
import com.manish.obaa.R;
import com.manish.obaa.utils.Api;
import com.manish.obaa.utils.Preference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

//import javax.print.attribute.standard.Media;

import static android.support.v4.media.MediaBrowserServiceCompat.RESULT_OK;

public class Members extends Fragment implements View.OnClickListener {

    private EditText nameMemberET, addressET,contactMemberET,occupationMemberET;
    private Button sendMemberDetails;
    private ImageView image1,image2,image3;
    private Button chooseImage,choose,choose2;
    public  Bitmap bitmap,bitmap1,bitmap2;

    public Members() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_members, container, false);

        initialisation(view);

        setListener();
        return view;

    }
    private void setListener() {
        sendMemberDetails.setOnClickListener(this);
    }

    private void initialisation(View view) {

        Preference preference = new Preference(Objects.requireNonNull(getContext()));

        sendMemberDetails = (Button) view.findViewById(R.id.sendMemberDetails);
        image1=(ImageView)view.findViewById(R.id.image1);
        image2=(ImageView)view.findViewById(R.id.image2);
        image3=(ImageView)view.findViewById(R.id.image3);
        nameMemberET = view.findViewById(R.id.nameMemberET);
        addressET = view.findViewById(R.id.addressET);
        contactMemberET=(EditText) view.findViewById(R.id.contactMemberET);
        occupationMemberET=(EditText)view.findViewById(R.id.occupationMemberET);
        chooseImage=(Button)view.findViewById(R.id.chooseImage);
        choose=(Button)view.findViewById(R.id.choose);
        choose2=(Button)view.findViewById(R.id.choose2);
        chooseImage.setOnClickListener(this);
        choose.setOnClickListener(this);
        choose2.setOnClickListener(this);
        sendMemberDetails.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view==chooseImage)
        {
//
//            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
            selectImage();
        }
        else if(view==choose)
        {
            select();
        }
        else if(view==choose2)
        {
            select1();
        }
        else if(view==sendMemberDetails)
        {
            sendDetails();
        }
    }

    public void selectImage() {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 0);//zero can be replaced with any action code

//        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        // ******** code for crop image
        i.putExtra("crop", "true");
        i.putExtra("aspectX", 100);
        i.putExtra("aspectY", 100);
        i.putExtra("outputX", 300);
        i.putExtra("outputY", 300);
        try {
            i.putExtra("return-data", true);
            startActivityForResult(
                    Intent.createChooser(i, "Select Picture"), 0);
        }catch (ActivityNotFoundException ex){
            ex.printStackTrace();
        }

    }
    public void select() {
//        Intent i1 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent i1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i1, 0);//zero can be replaced with any action code

        // ******** code for crop image
        i1.putExtra("crop", "true");
        i1.putExtra("aspectX", 100);
        i1.putExtra("aspectY", 100);
        i1.putExtra("outputX", 300);
        i1.putExtra("outputY", 300);
        try {
            i1.putExtra("return-data", true);
            startActivityForResult(
                    Intent.createChooser(i1, "Select Picture"), 1);
        }catch (ActivityNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public void select1() {
       //Intent i2 = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent i2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i2, 0);//zero can be replaced with any action code

        // ******** code for crop image
        i2.putExtra("crop", "true");
        i2.putExtra("aspectX", 100);
        i2.putExtra("aspectY", 100);
        i2.putExtra("outputX", 300);
        i2.putExtra("outputY", 300);
        try {
            i2.putExtra("return-data", true);
            startActivityForResult(
                    Intent.createChooser(i2, "Select Picture"), 3);
        }catch (ActivityNotFoundException ex){
            ex.printStackTrace();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            try {
//                Bundle bundle = data.getExtras();
//                bitmap = bundle.getParcelable("data"); // use this bitmap
//                image1.setImageBitmap(bitmap);//set image to imageView

                Bitmap pic = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                image1.setImageBitmap(pic);

            } catch (Exception e) {
                e.printStackTrace();
            }}
        else if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            try {
                Bundle bundle1 = data.getExtras();
                bitmap1 = bundle1.getParcelable("data"); // use this bitmap
                image2.setImageBitmap(bitmap1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
            try {
                Bundle bundle2 = data.getExtras();
                bitmap2 = bundle2.getParcelable("data"); // use this bitmap
                image3.setImageBitmap(bitmap2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
    private String imageToString1(Bitmap bitmap1)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }
    private String imageToString2(Bitmap bitmap2)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

    private void sendDetails() {
        Random rd=new Random();
        final String id=String.valueOf(Math.floor(Math.random()*999));
        final String name = nameMemberET.getText().toString();
        final String address = addressET.getText().toString();
        final String contact = contactMemberET.getText().toString();
        final String occupation = occupationMemberET.getText().toString();


        if (name.length() == 0) {
            showAlert("Please enter name");
        }
        else if (address.length() == 0) {
            showAlert("Please enter address");
        }
        else if (contact.length() < 10) {
            showAlert("Please enter contact");
        }
        else if (occupation.length() == 0) {
            showAlert("Please enter occupation");
        } else {

            StringRequest sendDetails = new StringRequest(Request.Method.POST, Api.member, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("HELP_RESPONSE", response);
                    if (response.equals("TRUE")) {
                        Toast.makeText(getActivity(), "Data Submitted", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getActivity(), "Data Submitted.", Toast.LENGTH_SHORT).show();
                        nameMemberET.setText(" ");
                        contactMemberET.setText(" ");
                        occupationMemberET.setText(" ");
                        addressET.setText(" ");
                        image1.setImageBitmap(null);
                        image2.setImageBitmap(null);
                        image3.setImageBitmap(null);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("EXEPTION_HELP", error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("id", id);
                    System.out.println("Random number"+id);
                    map.put("name", name);
                    map.put("contact", contact);
                    map.put("occupation", occupation);
                    map.put("address", address);
                    map.put("image1", imageToString(bitmap));
                    map.put("image2", imageToString1(bitmap1));
                    map.put("image3", imageToString2(bitmap2));

                    return map;
                }
            };
            Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(sendDetails);

        }
    }


    private void showAlert(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }


}
